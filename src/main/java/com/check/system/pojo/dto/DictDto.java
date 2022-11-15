package com.check.system.pojo.dto;


import com.check.common.pojo.dto.BaseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author zzc
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Api("字典查询类")
public class DictDto extends BaseDto {

    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("字典键")
    private String dictKey;
    @ApiModelProperty("字典所属组")
    private String dictGroup;
    @ApiModelProperty(hidden = true)
    private Date createTime;
}
