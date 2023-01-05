package com.check.bus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.check.bus.pojo.GasStation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

/**
 * @author zzc
 */
@Mapper
public interface GasStationMapper extends BaseMapper<GasStation> {


    /**
     * 获取现有加油站所有code
     *
     * @return Set<String>
     * @author zzc
     */
    @Select("SELECT CODE FROM t_gas_station WHERE IS_DELETE = 0")
    Set<String> getCodeSet();
}
