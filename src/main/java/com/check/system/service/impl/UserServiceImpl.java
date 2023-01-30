package com.check.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.check.common.constant.ConstantException;
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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;

/**
 * @author zzc
 */
@Service
public class UserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements UserService {

    @Resource
    SysUserMapper userMapper;

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

    /**
     * 获取单点用户信息
     *
     * @param hidName     用户名
     * @param hidUserId   用户id
     * @param hidUserType 用户类型（1-企业；2-个人）
     * @param cardId      身份证号码
     * @param hidInfo     用户信息（base64 加密）
     * @return Result
     * @author zzc
     */
    @Override
    public Result<Object> info(String hidName, String hidUserId, String hidUserType, String cardId, String hidInfo) {
        if (StringUtils.isBlank(hidName)) {
            throw ConstantException.SYSTEM_USER_HID_NAME_NULL;
        }
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUsername, cardId);
        List<SysUser> list = list(queryWrapper);
        if (list.size() > 0) {
            throw ConstantException.SYSTEM_USER_RE;
        }
        String maxCode = userMapper.getMaxCode();
        int i = Integer.parseInt(maxCode);
        i = i + 1;

        SysUser user = new SysUser();
        user.setUsername(cardId);
        user.setCasId(hidUserId);
        user.setIdCard(cardId);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode("123456"));
        user.setNickName(hidName);
        user.setGender("1");
        user.setEnabled(1L);
        user.setCreateBy("SYSTEM");
        user.setUpdateBy("SYSTEM");
        user.setCode(Integer.toString(i));

        if (StringUtils.isNotBlank(hidInfo)) {
            String jsonStr = "";
            try {
                jsonStr = new String(Base64.getDecoder().decode(hidInfo), "GBK");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if (StringUtils.isNotBlank(jsonStr)) {
                JSONObject object = JSONObject.parseObject(jsonStr);
                if (object != null) {
                    String name = object.getString(ConstantString.CAPS_S_NAME);
                    String mobile = object.getString(ConstantString.CAPS_S_MOBILE);
                    String phone = object.getString(ConstantString.CAPS_S_PHONE);
                    user.setNickName(name);
                    if (StringUtils.isNotBlank(phone)) {
                        user.setPhone(phone);
                    }
                    if (StringUtils.isNotBlank(mobile)) {
                        user.setPhone(mobile);
                    }
                }
            }
        }

        save(user);

        return Result.success();
    }
}
