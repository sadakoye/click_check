package com.check.system.pojo.dto;


import com.check.common.pojo.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zzc
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class DictDto extends BaseDto {

    private Long id;
    private String dictKey;
    private String dictGroup;
}
