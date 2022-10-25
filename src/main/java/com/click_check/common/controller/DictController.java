package com.click_check.common.controller;

import com.click_check.common.pojo.bean.Result;
import com.click_check.common.pojo.dto.DictDto;
import com.click_check.common.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zzc
 */
@Controller
@RequestMapping("/dict")
public class DictController {

    @Autowired
    DictService service;

    /**
     * 列表查询
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    @RequestMapping("/list")
    public Result list(@RequestBody DictDto dto) {
        return service.list(dto);
    }
}
