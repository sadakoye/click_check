package com.check.common.util;

import com.check.system.pojo.vo.DictVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;

import java.util.List;
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
        PageInfo<T> tPageInfo = new PageInfo<>(voList);
        BeanUtils.copyProperties(new PageInfo<>(list), tPageInfo);
        return tPageInfo;
    }

}
