package com.check.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.check.common.constant.ConstantString;
import com.check.common.pojo.bean.Result;
import com.check.common.util.DataUtils;

import com.check.common.util.RedisUtils;
import com.check.system.mapper.SysUserMapper;
import com.check.system.pojo.SysUser;
import com.check.system.pojo.dto.UserAddDto;
import com.check.system.pojo.dto.UserDto;
import com.check.system.pojo.dto.UserUpdateDto;
import com.check.system.pojo.vo.UserVo;
import com.check.system.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zzc
 */
@Service
public class UserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements UserService {

    /**
     * 列表查询
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    @Override
    public Result<PageInfo<UserVo>> list(UserDto dto) {
        SysUser bean = new SysUser();
        UserVo vo = new UserVo();
        BeanUtils.copyProperties(dto, bean);
        QueryWrapper<SysUser> queryWrapper = DataUtils.query(bean, dto, vo);
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());

        List<SysUser> list = list(queryWrapper);

        PageInfo<UserVo> page = DataUtils.getPageInfo(list, vo.getClass());

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
    public Result<Object> add(UserAddDto dto) {
        SysUser bean = new SysUser();
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
    public Result<Object> update(UserUpdateDto dto) {
        SysUser bean = new SysUser();
        BeanUtils.copyProperties(dto, bean);
        updateById(bean);
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getId, dto.getId());
        SysUser one = getOne(queryWrapper);
        RedisUtils.deleteValue(ConstantString.REDIS_USER + one.getUsername());
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
        UpdateWrapper<SysUser> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("ID", ids);
        SysUser bean = new SysUser();
        bean.setEnabled(0L);
        update(bean, updateWrapper);
        return Result.success();
    }
}
