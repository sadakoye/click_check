package com.check.bus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.check.bus.pojo.GasStation;
import com.check.bus.pojo.vo.GasStationStatisticsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
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

    /**
     * 加油站统计
     *
     * @param code 区code
     * @return List<GasStationStatisticsVo>
     * @author zzc
     */
    @Select("<script> " +
            "select BRAND, COUNT(ID) count FROM t_gas_station " +
            "<if test='code != null'> WHERE DISTRICT_CODE = #{code} </if>" +
            "GROUP BY BRAND " +
            "</script> ")
    List<GasStationStatisticsVo> statistics(@Param("code") String code);
}
