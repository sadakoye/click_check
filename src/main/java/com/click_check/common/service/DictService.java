package com.click_check.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.click_check.common.pojo.SysDict;
import com.click_check.common.pojo.bean.Result;
import com.click_check.common.pojo.dto.DictDto;

/**
 * @author zzc
 */
public interface DictService extends IService<SysDict> {

    /**
     * 列表查询
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    Result list(DictDto dto);
}
