package com.lease.web.app.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lease.common.login.LoginUserHolder;
import com.lease.model.entity.*;
import com.lease.model.enums.ItemType;
import com.lease.web.app.mapper.RoomInfoMapper;
import com.lease.web.app.service.*;
import com.lease.web.app.vo.apartment.ApartmentItemVo;
import com.lease.web.app.vo.attr.AttrValueVo;
import com.lease.web.app.vo.fee.FeeValueVo;
import com.lease.web.app.vo.graph.GraphVo;
import com.lease.web.app.vo.room.RoomDetailVo;
import com.lease.web.app.vo.room.RoomItemVo;
import com.lease.web.app.vo.room.RoomQueryVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

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
	private final ApartmentInfoService apartmentInfoService;
	private final GraphInfoService graphInfoService;
	private final AttrValueService attrValueService;
	private final FacilityInfoService facilityInfoService;
	private final LabelInfoService labelInfoService;
	private final PaymentTypeService paymentTypeService;
	private final FeeValueService feeValueService;
	private final LeaseTermService leaseTermService;
	private final BrowsingHistoryService browsingHistoryService;

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

	@Override
	public RoomDetailVo findDetailById(Long id) {
		//1 房间信息
		RoomInfo roomInfo = super.getById(id);
		//2 公寓信息
		ApartmentItemVo apartmentItemVo = apartmentInfoService.findApartmentItemById(roomInfo.getApartmentId());
		//3 图片列表
		List<GraphVo> graphVoList = graphInfoService.findByItemIdAndItemType(id, ItemType.ROOM);
		//4 属性信息列表
		List<AttrValueVo> attrValueVoList = attrValueService.getAttrValueVoByRoomId(roomInfo.getId());
		//5 配套信息列表
		List<FacilityInfo> facilityInfoList = facilityInfoService.getByRoomId(roomInfo.getId());
		//6 标签信息列表
		List<LabelInfo> labelInfoList = labelInfoService.getByRoomId(roomInfo.getId());
		//7 支付方式列表
		List<PaymentType> paymentTypeList = paymentTypeService.getByRoomId(roomInfo.getId());
		//8 杂费列表
		List<FeeValueVo> feeValueVoList = feeValueService.getByApartmentId(roomInfo.getApartmentId());
		//9 租期列表
		List<LeaseTerm> leaseTermList = leaseTermService.getByRoomId(roomInfo.getId());

		RoomDetailVo roomDetailVo = new RoomDetailVo();
		BeanUtils.copyProperties(roomInfo, roomDetailVo);
		roomDetailVo.setGraphVoList(graphVoList);
		roomDetailVo.setAttrValueVoList(attrValueVoList);
		roomDetailVo.setApartmentItemVo(apartmentItemVo);
		roomDetailVo.setFacilityInfoList(facilityInfoList);
		roomDetailVo.setLabelInfoList(labelInfoList);
		roomDetailVo.setPaymentTypeList(paymentTypeList);
		roomDetailVo.setFeeValueVoList(feeValueVoList);
		roomDetailVo.setLeaseTermList(leaseTermList);

		// 保存浏览历史
		browsingHistoryService.saveHistoryByRoomId(id, LoginUserHolder.getLoginUser().getUserId());
		return roomDetailVo;
	}

	@Override
	public IPage<RoomItemVo> findRoomItemPageByApartmentId(IPage<RoomItemVo> roomItemVoPage, Long id) {
		return roomInfoMapper.selectRoomItemPageByApartmentId(roomItemVoPage, id);
	}
}




