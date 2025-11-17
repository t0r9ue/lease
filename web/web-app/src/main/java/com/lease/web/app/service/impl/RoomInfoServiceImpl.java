package com.lease.web.app.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lease.model.entity.RoomInfo;
import com.lease.web.app.mapper.RoomInfoMapper;
import com.lease.web.app.service.RoomInfoService;
import com.lease.web.app.vo.room.RoomItemVo;
import com.lease.web.app.vo.room.RoomQueryVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.stereotype.Service;

/**
 * @author liubo
 * @description 针对表【room_info(房间信息表)】的数据库操作Service实现
 * @createDate 2023-07-26 11:12:39
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RoomInfoServiceImpl extends ServiceImpl<RoomInfoMapper, RoomInfo>
        implements RoomInfoService {

	private final RoomInfoMapper roomInfoMapper;

	@Override
	public IPage<RoomItemVo> findRoomItemByPage(IPage<RoomItemVo> roomItemVoPage, RoomQueryVo queryVo) {
		IPage<RoomItemVo> roomItemVoIPage = null;
		try {
			roomItemVoIPage = roomInfoMapper.selectRoomItemByPage(roomItemVoPage, queryVo);
		} catch (MyBatisSystemException e) {
			log.error("完整异常信息:", e);
			log.error("根本原因:", e.getCause());
		}
		return roomItemVoIPage;
	}
}




