package com.check.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.check.common.pojo.bean.Result;
import com.check.system.pojo.SysUser;
import com.check.system.pojo.dto.UserAddDto;
import com.check.system.pojo.dto.UserDto;
import com.check.system.pojo.dto.UserUpdateDto;
import com.check.system.pojo.vo.UserVo;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author zzc
 */
public interface UserService extends IService<SysUser> {

    /**
     * 列表查询
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    Result<PageInfo<UserVo>> list(UserDto dto);

    /**
     * 新增
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    Result<Object> add(UserAddDto dto);

    /**
     * 修改
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    Result<Object> update(UserUpdateDto dto);

    /**
     * 删除
     *
     * @param ids ids
     * @return Result
     * @author zzc
     */
    Result<Object> delete(List<Long> ids);

    /**
     * 获取单点用户信息
     *
     * @param response    HttpServletResponse
     * @param hidName     用户名
     * @param hidUserId   用户id
     * @param hidUserType 用户类型（1-企业；2-个人）
     * @param cardId      身份证号码
     * @param hidInfo     用户信息（base64 加密）
     * @return String
     * @author zzc
     */
    Result<Object> info(HttpServletResponse response, String hidName, String hidUserId, String hidUserType, String cardId, String hidInfo);
}
