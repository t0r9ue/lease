package com.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lease.model.entity.RoomInfo;
import com.lease.web.admin.vo.room.RoomItemVo;
import com.lease.web.admin.vo.room.RoomQueryVo;

/**
 * @author 孙依鹏
 * @version 1.0
 * @create 2025/10/10
 */

public interface RoomInfoMapper extends BaseMapper<RoomInfo> {
	IPage<RoomItemVo> selectRoomItemByPage(IPage<RoomItemVo> page, RoomQueryVo queryVo);
}