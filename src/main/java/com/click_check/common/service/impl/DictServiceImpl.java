package com.click_check.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.click_check.common.mapper.DictMapper;
import com.click_check.common.pojo.SysDict;
import com.click_check.common.pojo.bean.Result;
import com.click_check.common.pojo.dto.DictDto;
import com.click_check.common.pojo.vo.DictVo;
import com.click_check.common.service.DictService;
import com.click_check.common.util.DataUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zzc
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, SysDict> implements DictService {

    @Autowired
    DictMapper mapper;

    /**
     * 列表查询
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    @Override
    public Result list(DictDto dto) {
        System.out.println(dto.getPageSize() + "" + dto.getPageNum());
        QueryWrapper<SysDict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("IS_DELETE", 0);
        if (dto.getId() != null){
            queryWrapper.eq("ID", dto.getId());
        }
        if (StringUtils.isNotBlank(dto.getDictKey())){
            queryWrapper.like("DICT_KEY", "%" + dto.getDictKey() + "%");
        }
        if (StringUtils.isNotBlank(dto.getDictGroup())){
            queryWrapper.like("DICT_GROUP", "%" + dto.getDictGroup() + "%");
        }
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());

        List<SysDict> list = list(queryWrapper);

//        PageInfo<DictVo> page = DataUtil.getPageInfo(list);

        List<DictVo> voList = list.stream().map(sysDict -> {
            DictVo dictVo = new DictVo();
            BeanUtils.copyProperties(sysDict, dictVo);
            return dictVo;
        }).collect(Collectors.toList());
        PageInfo<DictVo> page = new PageInfo<>(voList);

        return Result.success(page);
    }
}
