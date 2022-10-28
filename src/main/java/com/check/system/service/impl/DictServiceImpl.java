package com.check.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.check.system.mapper.DictMapper;
import com.check.system.pojo.SysDict;
import com.check.common.pojo.bean.Result;
import com.check.system.pojo.dto.DictAddDto;
import com.check.system.pojo.dto.DictDto;
import com.check.system.pojo.dto.DictUpdateDto;
import com.check.system.pojo.vo.DictVo;
import com.check.system.service.DictService;
import com.check.common.util.DataUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        QueryWrapper<SysDict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("IS_DELETE", 0);
        if (dto.getId() != null) {
            queryWrapper.eq("ID", dto.getId());
        }
        if (StringUtils.isNotBlank(dto.getDictKey())) {
            queryWrapper.like("DICT_KEY", "%" + dto.getDictKey() + "%");
        }
        if (StringUtils.isNotBlank(dto.getDictGroup())) {
            queryWrapper.like("DICT_GROUP", "%" + dto.getDictGroup() + "%");
        }
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());

        List<SysDict> list = list(queryWrapper);

        DictVo dictVo = new DictVo();

        PageInfo<DictVo> page = DataUtils.getPageInfo(list, dictVo.getClass());

        return Result.success(page);
    }

    /**
     * 新增
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    @Override
    public Result add(DictAddDto dto) {
        SysDict dict = new SysDict();
        BeanUtils.copyProperties(dto, dict);
        save(dict);
        return Result.success();
    }

    /**
     * 修改
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    @Override
    public Result update(DictUpdateDto dto) {
        SysDict dict = new SysDict();
        BeanUtils.copyProperties(dto, dict);
        updateById(dict);
        return Result.success();
    }

    @Override
    public Result delete(List<Long> ids) {
        UpdateWrapper<SysDict> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("ID", ids);
        SysDict dict = new SysDict();
        dict.setIsDelete("1");
        update(dict, updateWrapper);
        return Result.success();
    }
}
