package com.check.common.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.check.common.pojo.dto.BaseDto;
import com.check.common.pojo.vo.BaseVo;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author zzc
 */
public class DataUtils {

    static final Pattern COMPILE = Pattern.compile("[A-Z]");

    @SuppressWarnings("unchecked")
    public static <T> PageInfo<T> getPageInfo(List<?> list, Class<? extends BaseVo> voClass) {
        List<T> voList = list.stream().map(item -> {
            T vo = null;
            try {
                vo = (T) voClass.newInstance();
                BeanUtils.copyProperties(item, vo);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return vo;
        }).collect(Collectors.toList());
        PageInfo<T> tPageInfo = new PageInfo<>();
        BeanUtils.copyProperties(new PageInfo<>(list), tPageInfo);
        tPageInfo.setList(voList);
        return tPageInfo;
    }

    public static <T> QueryWrapper<T> query(T bean, BaseDto dto) {
        String[] fieldName = getFieldName(bean);
        Map<String, Object> eqMap = new HashMap<>(fieldName.length);
        Map<String, String> likeMap = new HashMap<>(fieldName.length);
        Map<String, String> keywordMap = new HashMap<>(fieldName.length);
        String keyword = dto.getKeyword();
        int isTime = 0;
        int isOrder = 0;
        for (String s : fieldName) {
            if (s.equals(dto.getBaseTime())) {
                isTime = 1;
            }
            if (s.equals(dto.getDesc())){
                isOrder = 1;
            }
            if (s.equals(dto.getAsc())){
                isOrder = 1;
            }
            Object o = getFieldValueByName(s, bean);
            if (o != null) {
                String underline = toUnderline(s);
                if (o instanceof Date){
                    continue;
                }
                if (keyword == null) {
                    if (o instanceof String) {
                        likeMap.put(underline, String.valueOf(o));
                    } else {
                        eqMap.put(underline, o);
                    }
                } else {
                    keywordMap.put(underline, keyword);
                }
            }
        }
        QueryWrapper<T> queryWrapper;

        if (keyword == null) {
            queryWrapper = baseQuery(eqMap, likeMap);
        } else {
            queryWrapper = keywordQuery(keywordMap);
        }

        if (isTime == 1 && StringUtils.isNotBlank(dto.getBaseTime())) {
            String baseTime = toUnderline(dto.getBaseTime());
            if (dto.getBaseStartTime() != null && dto.getBaseEndTime() != null) {
                queryWrapper.between(baseTime, dto.getBaseStartTime(), dto.getBaseEndTime());
            } else if (dto.getBaseStartTime() != null) {
                queryWrapper.ge(baseTime, dto.getBaseStartTime());
            } else if (dto.getBaseEndTime() != null) {
                queryWrapper.le(baseTime, dto.getBaseEndTime());
            }
        }

        if (isOrder == 1 && StringUtils.isNotBlank(dto.getDesc())) {
            queryWrapper.orderByDesc(toUnderline(dto.getDesc()));
        } else if (StringUtils.isNotBlank(dto.getAsc())) {
            queryWrapper.orderByAsc(toUnderline(dto.getAsc()));
        }
        return queryWrapper;
    }


    /**
     * 获取属性名数组
     */
    private static String[] getFieldName(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fieldNames[i] = fields[i].getName();
        }
        return fieldNames;
    }


    /**
     * 根据属性名获取属性值
     *
     * @param fieldName 属性名称
     * @param o         对象
     * @return Object
     */
    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter);
            return method.invoke(o);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将驼峰转为下划线
     *
     * @param str 要转化的字符串
     * @return java.lang.String
     */
    public static String toUnderline(String str) {
        Matcher matcher = COMPILE.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static <T> QueryWrapper<T> baseQuery(Map<String, Object> eqMap, Map<String, String> likeMap) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        //精确匹配
        if (eqMap != null && eqMap.size() > 0) {
            for (String s : eqMap.keySet()) {
                Object object = eqMap.get(s);
                if (object instanceof String) {
                    if (StringUtils.isBlank(String.valueOf(object))) {
                        continue;
                    }
                } else if (object == null) {
                    continue;
                }
                queryWrapper.eq(s, object);
            }
        }
        //模糊匹配
        if (likeMap != null && likeMap.size() > 0) {
            for (String s : likeMap.keySet()) {
                Object object = likeMap.get(s);
                queryWrapper.like(s, "%" + object + "%");
            }
        }

        return queryWrapper;
    }

    public static <T> QueryWrapper<T> keywordQuery(Map<String, String> keywordMap) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        //关键字匹配
        Set<String> set = keywordMap.keySet();
        for (int i = 0; i < keywordMap.keySet().size(); i++) {
            String key = set.iterator().next();
            String value = keywordMap.get(key);
            if (StringUtils.isNotBlank(value)) {
                queryWrapper.like(key, "%" + value + "%");
            }
            if (i < keywordMap.keySet().size()) {
                queryWrapper.or();
            }
        }
        return queryWrapper;
    }

}
