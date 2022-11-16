package com.check.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.check.common.pojo.bean.Result;
import com.check.common.util.DataUtils;
import com.check.system.mapper.DictMapper;
import com.check.system.pojo.SysDict;
import com.check.system.pojo.dto.DictAddDto;
import com.check.system.pojo.dto.DictDto;
import com.check.system.pojo.dto.DictUpdateDto;
import com.check.system.pojo.vo.DictVo;
import com.check.system.service.DictService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zzc
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, SysDict> implements DictService {

    /**
     * 列表查询
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    @Override
    public Result<PageInfo<DictVo>> list(DictDto dto) {
        SysDict bean = new SysDict();
        DictVo vo = new DictVo();
        BeanUtils.copyProperties(dto, bean);
        QueryWrapper<SysDict> queryWrapper = DataUtils.query(bean, dto, vo);
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());

        List<SysDict> list = list(queryWrapper);

        PageInfo<DictVo> page = DataUtils.getPageInfo(list, vo.getClass());

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
    public Result<Object> add(DictAddDto dto) {
        SysDict bean = new SysDict();
        BeanUtils.copyProperties(dto, bean);
        save(bean);
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
    public Result<Object> update(DictUpdateDto dto) {
        SysDict bean = new SysDict();
        BeanUtils.copyProperties(dto, bean);
        updateById(bean);
        return Result.success();
    }

    /**
     * 删除
     *
     * @param ids ids
     * @return Result
     * @author zzc
     */
    @Override
    public Result<Object> delete(List<Long> ids) {
        UpdateWrapper<SysDict> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("ID", ids);
        SysDict bean = new SysDict();
        bean.setIsDelete("1");
        update(bean, updateWrapper);
        return Result.success();
    }
}
