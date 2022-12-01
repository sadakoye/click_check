package com.check.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.check.common.constant.ConstantString;
import com.check.common.pojo.bean.Result;
import com.check.common.util.DataUtils;
import com.check.system.mapper.SysRoleMapper;
import com.check.system.mapper.SysUserRolesMapper;
import com.check.system.pojo.SysRole;
import com.check.system.pojo.SysUsersRoles;
import com.check.system.pojo.dto.RoleAddDto;
import com.check.system.pojo.dto.RoleDto;
import com.check.system.pojo.dto.RoleUpdateDto;
import com.check.system.pojo.dto.UsersRolesDto;
import com.check.system.pojo.vo.RoleVo;
import com.check.system.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zzc
 */
@Slf4j
@Service
public class RoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements RoleService {

    @Resource
    SysUserRolesMapper userRolesMapper;

    /**
     * 列表查询
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    @Override
    public Result<PageInfo<RoleVo>> list(RoleDto dto) {
        SysRole bean = new SysRole();
        RoleVo vo = new RoleVo();
        BeanUtils.copyProperties(dto, bean);
        QueryWrapper<SysRole> queryWrapper = DataUtils.query(bean, dto, vo);
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());

        List<SysRole> list = list(queryWrapper);

        PageInfo<RoleVo> page = DataUtils.getPageInfo(list, vo.getClass());

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
    public Result<Object> add(RoleAddDto dto) {
        SysRole bean = new SysRole();
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
    public Result<Object> update(RoleUpdateDto dto) {
        SysRole bean = new SysRole();
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
        UpdateWrapper<SysRole> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("ID", ids);
        SysRole bean = new SysRole();
        bean.setIsDelete(ConstantString.ONE);
        update(bean, updateWrapper);
        return Result.success();
    }

    /**
     * 根据用户查询角色
     *
     * @param userCode 用户code
     * @return Result
     * @author zzc
     */
    @Override
    public Result<List<RoleVo>> listByUserCode(String userCode) {
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
        String sql = "SELECT ROLE_CODE FROM sys_users_roles WHERE user_code = " + userCode;
        queryWrapper.select(SysRole::getCode, SysRole::getId, SysRole::getLevel,
                SysRole::getName);
        queryWrapper.eq(SysRole::getIsDelete, "0");
        queryWrapper.inSql(SysRole::getCode, sql);
        List<SysRole> list = list(queryWrapper);
        List<RoleVo> resultList = list.stream().map(item -> {
            RoleVo roleVo = new RoleVo();
            BeanUtils.copyProperties(item, roleVo);
            return roleVo;
        }).collect(Collectors.toList());
        return Result.success(resultList);
    }

    /**
     * 用户新增角色
     *
     * @param dtoList UsersRolesDto
     * @return Result
     * @author zzc
     */
    @Override
    public Result<Object> addUserRole(List<UsersRolesDto> dtoList) {
        if (dtoList != null && dtoList.size() > 0) {
            dtoList.forEach(usersRolesDto -> {
                try {
                    SysUsersRoles usersRoles = new SysUsersRoles();
                    BeanUtils.copyProperties(usersRolesDto, usersRoles);
                    userRolesMapper.insert(usersRoles);
                } catch (BeansException e) {
                    log.error("用户角色添加失败" + usersRolesDto);
                    e.printStackTrace();
                }
            });
        }
        return Result.success();
    }

    /**
     * 用户删除角色
     *
     * @param dtoList UsersRolesDto
     * @return Result
     * @author zzc
     */
    @Override
    public Result<Object> deleteUserRole(List<UsersRolesDto> dtoList) {
        if (dtoList != null && dtoList.size() > 0){
            dtoList.forEach(usersRolesDto -> {
                try {
                    LambdaQueryWrapper<SysUsersRoles> queryWrapper = new LambdaQueryWrapper<>();
                    queryWrapper.eq(SysUsersRoles::getRoleCode, usersRolesDto.getRoleCode())
                            .eq(SysUsersRoles::getUserCode, usersRolesDto.getUserCode());
                    userRolesMapper.delete(queryWrapper);
                } catch (Exception e) {
                    log.error("用户角色删除失败" + usersRolesDto);
                    e.printStackTrace();
                }
            });
        }
        return Result.success();
    }
}
