package com.check.common.pojo.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author zzc
 */
@Data
public class DictAddDto {

    @NotBlank
    private String dictKey;
    @NotBlank
    private String dictValue;
    @NotBlank
    private String dictGroup;
}
