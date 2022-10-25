package com.click_check.common.util;

import com.click_check.common.pojo.dto.BaseDto;
import com.click_check.common.pojo.vo.BaseVo;
import com.click_check.common.pojo.vo.DictVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zzc
 */
public class DataUtil {

//    public static PageInfo<BaseVo> getPageInfo(List<?> list){
//        List<BaseVo> voList = list.stream().map(item -> {
//            BaseVo vo = new BaseVo();
//            try {
//                BeanUtils.copyProperties(item, vo);
//            } catch (BeansException e) {
//                e.printStackTrace();
//            }
//            return vo;
//        }).collect(Collectors.toList());
//        return new PageInfo<>(voList);
//    }

}
