package com.check.system.pojo.dto;


import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author zzc
 */
@Data
public class DictUpdateDto {

  @NotNull
  private Long id;
  private String dictKey;
  private String dictValue;
  private String dictGroup;
}
