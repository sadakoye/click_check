package com.check.common.constant;

import com.check.common.exception.CommonException;

/**
 * Exception常量
 *
 * @author zzc
 */
public interface ConstantException {

    /**
     * System 系统模块 预设错误
     */
    CommonException SYSTEM_TOKEN_INVALID = new CommonException(401, "token无效或过期");

    /**
     * System 系统模块
     */
    CommonException SYSTEM_LOGIN_OUT_ERROR = new CommonException(10000, "未登录或已登出");
    CommonException SYSTEM_USER_HID_NAME_NULL = new CommonException(10001, "hidName不能为空");
    CommonException SYSTEM_USER_RE = new CommonException(10002, "已有此用户");

    /**
     * GasStation 加油站模块
     */
    CommonException GAS_STATION_RE_CODE = new CommonException(10101, "code重复");
    CommonException GAS_STATION_ID_NULL = new CommonException(10102, "传入的id为空");

    /**
     * GasStation 加油站投票模块
     */
    CommonException GAS_STATION_VOTE_COUNT_GT_THREE = new CommonException(10201, "七天内投票数不能超过3");

}
