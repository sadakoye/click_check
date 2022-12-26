package com.check.bus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.check.bus.pojo.GasStationVote;
import com.check.bus.pojo.vo.GasStationVoteCountVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * @author zzc
 */
@Mapper
public interface GasStationVoteMapper extends BaseMapper<GasStationVote> {

    /**
     * 投票统计
     *
     * @param districtCode 区code
     * @param name         加油站名称
     * @param address      加油站地址
     * @param startTime    开始时间
     * @param endTime      结束时间
     * @return List<GasStationVoteCountVo>
     * @author zzc
     */
    @Select("SELECT COUNT(*) count, v.GAS_STATION_CODE, g.* FROM t_gas_station_vote v LEFT JOIN t_gas_station g ON v.GAS_STATION_CODE = g.CODE \n" +
            "WHERE g.DISTRICT_CODE LIKE #{districtCode} AND g.NAME LIKE #{name} AND g.ADDRESS LIKE #{address} \n" +
            "AND v.VOTE_TIME BETWEEN #{startTime} AND #{endTime} AND g.IS_DELETE = 0\n" +
            "GROUP BY v.GAS_STATION_CODE, g.ID, g.CODE, g.DISTRICT_CODE, g.DISTRICT_NAME, g.MANAGEMENT_NATURE, g.MANAGEMENT_STATE, \n" +
            "g.BRAND, g.NAME, g.ADDRESS, g.CONTACTS_NAME, g.CONTACTS_PHONE, g.CONTACTS_LANDLINE_TELEPHONE, g.REMARK, g.CREATE_TIME, \n" +
            "g.UPDATE_TIME, g.IS_DELETE\n" +
            "ORDER BY count DESC")
    List<GasStationVoteCountVo> voteCount(String districtCode, String name, String address, Date startTime, Date endTime);
}
