package com.check.common.controller;

import com.check.common.pojo.bean.Result;
import com.check.common.pojo.dto.DictAddDto;
import com.check.common.pojo.dto.DictDto;
import com.check.common.pojo.dto.DictUpdateDto;
import com.check.common.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author zzc
 */
@CrossOrigin
@RequestMapping("/dict")
@RestController
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

    /**
     * 新增
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    @RequestMapping("/add")
    public Result add(@RequestBody @Valid DictAddDto dto) {
        return service.add(dto);
    }

    /**
     * 修改
     *
     * @param dto dto
     * @return Result
     * @author zzc
     */
    @RequestMapping("/update")
    public Result update(@RequestBody @Valid DictUpdateDto dto) {
        return service.update(dto);
    }

    /**
     * 删除
     *
     * @param ids ids
     * @return Result
     * @author zzc
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody @NotNull List<Long> ids) {
        return service.delete(ids);
    }
}
