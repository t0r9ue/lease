package com.lease.web.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lease.model.entity.RoomInfo;
import com.lease.web.app.vo.room.RoomItemVo;
import com.lease.web.app.vo.room.RoomQueryVo;

import java.math.BigDecimal;

/**
 * @author  孙依鹏
 * @version 1.0
 * @create 2025/11/13
 */
    
public interface RoomInfoMapper extends BaseMapper<RoomInfo> {
	IPage<RoomItemVo> selectRoomItemByPage(IPage<RoomItemVo> roomItemVoPage, RoomQueryVo queryVo);

	BigDecimal selectMinRentByApartmentId(Long apartmentId);

	IPage<RoomItemVo> selectRoomItemPageByApartmentId(IPage<RoomItemVo> roomItemVoPage, Long apartmentId);
}