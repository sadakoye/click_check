package com.check.common.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.check.system.pojo.vo.DictVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zzc
 */
public class DataUtils {

    @SuppressWarnings("unchecked")
    public static <T> PageInfo<T> getPageInfo(List<?> list, Class<? extends DictVo> voClass) {
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

    public static <T> QueryWrapper<T> baseQuery(Map<String, List<Object>> eqMap, Map<String, List<Object>> likeMap) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        //精确匹配
        if (eqMap != null && eqMap.size() > 0) {
            for (String s : eqMap.keySet()) {
                List<Object> objectList = eqMap.get(s);
                if (objectList != null && objectList.size() > 0) {
                    if (objectList.size() == 1) {
                        queryWrapper.eq(s, objectList.get(0));
                    } else {
                        queryWrapper.and(tQueryWrapper -> {
                            QueryWrapper<T> newQueryWrapper = new QueryWrapper<>();
                            for (int i = 0; i < objectList.size(); i++) {
                                Object o = objectList.get(i);
                                newQueryWrapper.eq(s, o);
                                if (i < objectList.size()) {
                                    newQueryWrapper.or();
                                }
                            }
                            return newQueryWrapper;
                        });
                    }
                }
            }
        }
        //模糊匹配
        if (likeMap != null && likeMap.size() > 0) {
            for (String s : likeMap.keySet()) {
                List<Object> objectList = likeMap.get(s);
                if (objectList != null && objectList.size() > 0) {
                    if (objectList.size() == 1) {
                        queryWrapper.like(s, "%" + objectList.get(0) + "%");
                    } else {
                        queryWrapper.and(tQueryWrapper -> {
                            QueryWrapper<T> newQueryWrapper = new QueryWrapper<>();
                            for (int i = 0; i < objectList.size(); i++) {
                                Object o = objectList.get(i);
                                newQueryWrapper.like(s, "%" + o + "%");
                                if (i < objectList.size()) {
                                    newQueryWrapper.or();
                                }
                            }
                            return newQueryWrapper;
                        });
                    }
                }
            }
        }

        return queryWrapper;
    }

}
