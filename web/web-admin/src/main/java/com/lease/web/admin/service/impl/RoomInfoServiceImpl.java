package com.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lease.common.constant.RedisConstant;
import com.lease.common.util.RedisUtil;
import com.lease.model.entity.*;
import com.lease.model.enums.ItemType;
import com.lease.web.admin.mapper.*;
import com.lease.web.admin.service.*;
import com.lease.web.admin.vo.attr.AttrValueVo;
import com.lease.web.admin.vo.graph.GraphVo;
import com.lease.web.admin.vo.room.RoomDetailVo;
import com.lease.web.admin.vo.room.RoomItemVo;
import com.lease.web.admin.vo.room.RoomQueryVo;
import com.lease.web.admin.vo.room.RoomSubmitVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author  孙依鹏
 * @version 1.0
 * @create 2025/10/10
 */
    
@Service
@RequiredArgsConstructor
public class RoomInfoServiceImpl extends ServiceImpl<RoomInfoMapper, RoomInfo> implements RoomInfoService{

	private final GraphInfoService graphInfoService;
	private final RoomFacilityService roomFacilityService;
	private final RoomLabelService roomLabelService;
	private final RoomAttrValueService roomAttrValueService;
	private final RoomPaymentTypeService roomPaymentTypeService;
	private final RoomLeaseTermService  roomLeaseTermService;

	private final RoomInfoMapper roomInfoMapper;
	private final GraphInfoMapper graphInfoMapper;
	private final AttrValueMapper attrValueMapper;
	private final FacilityInfoMapper facilityInfoMapper;
	private final LabelInfoMapper labelInfoMapper;
	private final PaymentTypeMapper paymentTypeMapper;
	private final LeaseTermMapper leaseTermMapper;
	private final ApartmentInfoMapper apartmentInfoMapper;

	private final RedisUtil redisUtil;

	@Override
	public void saveOrUpdateRoomInfo(RoomSubmitVo roomSubmitVo) {
		Long roomId = roomSubmitVo.getId();
		//1 修改和更新RoomInfo
		super.saveOrUpdate(roomSubmitVo);
		//2 判断是更新还是修改
		if(roomId != null) {
			this.removeRoomRelevant(roomId);
		}
		//3 新增操作
		//3.1 新增图片列表
		List<GraphVo> graphVoList = roomSubmitVo.getGraphVoList();
		if(!CollectionUtils.isEmpty(graphVoList)) {
			List<GraphInfo> graphInfoList = graphVoList.stream()
					.map(graphVo -> GraphInfo.builder()
							.url(graphVo.getUrl())
							.name(graphVo.getName())
							.itemId(roomId)
							.itemType(ItemType.ROOM)
							.build()).toList();
			graphInfoService.saveBatch(graphInfoList);
		}
		//3.2 新增属性列表
		List<Long> attrValueIds = roomSubmitVo.getAttrValueIds();
		if(!CollectionUtils.isEmpty(attrValueIds)) {
			List<RoomAttrValue> roomAttrValues = attrValueIds.stream()
					.map(id -> RoomAttrValue.builder()
							.roomId(roomId)
							.attrValueId(id)
							.build()).toList();
			roomAttrValueService.saveBatch(roomAttrValues);
		}
		//3.3 新增配套列表
		List<Long> facilityInfoIds = roomSubmitVo.getFacilityInfoIds();
		if(!CollectionUtils.isEmpty(facilityInfoIds)) {
			List<RoomFacility> roomFacilitieList = facilityInfoIds.stream()
					.map(id -> RoomFacility.builder()
							.roomId(roomId)
							.facilityId(id)
							.build()).toList();
			roomFacilityService.saveBatch(roomFacilitieList);
		}
		//3.4 新增标签列表
		List<Long> labelInfoIds = roomSubmitVo.getLabelInfoIds();
		if(!CollectionUtils.isEmpty(labelInfoIds)) {
			List<RoomLabel> roomLabelList = labelInfoIds.stream()
					.map(id -> RoomLabel.builder()
							.roomId(roomId)
							.labelId(id)
							.build()).toList();
			roomLabelService.saveBatch(roomLabelList);
		}
		//3.5 新增支付方式列表
		List<Long> paymentTypeIds = roomSubmitVo.getPaymentTypeIds();
		if(!CollectionUtils.isEmpty(paymentTypeIds)) {
			List<RoomPaymentType> roomPaymentTypeList = paymentTypeIds.stream()
					.map(id -> RoomPaymentType.builder()
							.roomId(roomId)
							.paymentTypeId(id)
							.build()).toList();
			roomPaymentTypeService.saveBatch(roomPaymentTypeList);
		}
		//3.6 新增可选租期列表
		List<Long> leaseTermIds = roomSubmitVo.getLeaseTermIds();
		if(!CollectionUtils.isEmpty(leaseTermIds)) {
			List<RoomLeaseTerm> roomLeaseTermList = leaseTermIds.stream()
					.map(id -> RoomLeaseTerm.builder()
							.roomId(roomId)
							.leaseTermId(id)
							.build()).toList();
			roomLeaseTermService.saveBatch(roomLeaseTermList);
		}

		//4 删除redis中的roomDetail
		redisUtil.del(RedisConstant.APP_ROOM_PREFIX + roomId);
	}

	@Override
	public IPage<RoomItemVo> pageItem(IPage<RoomItemVo> page, RoomQueryVo queryVo) {
		return roomInfoMapper.selectRoomItemByPage(page, queryVo);
	}

	@Override
	public RoomDetailVo getRoomDetailById(Long id) {
		//1 查询房间信息
		RoomInfo roomInfo = super.getById(id);
		//2 查询对应公寓信息
		ApartmentInfo apartmentInfo = apartmentInfoMapper.selectById(roomInfo.getApartmentId());
		//3 查询图片列表
		List<GraphVo> graphVoList = graphInfoMapper.selectNameAndUrlByItemIdAndItemType(ItemType.ROOM, id);
		//4 查询属性列表
		List<AttrValueVo> attrValueVoList = attrValueMapper.selectAttrValueVoByRoomId(id);
		//5 查询配套列表
		List<FacilityInfo> facilityInfoList = facilityInfoMapper.selectListByRoomId(id);
		//6 查询标签列表
		List<LabelInfo> labelInfoList = labelInfoMapper.selectListByRoomId(id);
		//7 查询支付方式列表
		List<PaymentType> paymentTypes = paymentTypeMapper.selectListByRoomId(id);
		//8 查询租期列表
		List<LeaseTerm> leaseTermList = leaseTermMapper.selectListByRoomId(id);
		//9 构造Vo
		RoomDetailVo roomDetailVo = new RoomDetailVo();
		BeanUtils.copyProperties(roomInfo, roomDetailVo);
		roomDetailVo.setApartmentInfo(apartmentInfo);
		roomDetailVo.setGraphVoList(graphVoList);
		roomDetailVo.setAttrValueVoList(attrValueVoList);
		roomDetailVo.setFacilityInfoList(facilityInfoList);
		roomDetailVo.setLabelInfoList(labelInfoList);
		roomDetailVo.setPaymentTypeList(paymentTypes);
		roomDetailVo.setLeaseTermList(leaseTermList);
		return roomDetailVo;
	}

	@Override
	public void removeByRoomId(Long id) {
		super.removeById(id);
		this.removeRoomRelevant(id);
		//删除redis中的roomDetail
		redisUtil.del(RedisConstant.APP_ROOM_PREFIX + id);
	}

	private void removeRoomRelevant(Long roomId) {
		//1 删除图片
		LambdaQueryWrapper<GraphInfo> graphQueryWrapper = new LambdaQueryWrapper<>();
		graphQueryWrapper.eq(GraphInfo::getItemId, roomId);
		graphQueryWrapper.eq(GraphInfo::getItemType, ItemType.ROOM);
		graphInfoService.remove(graphQueryWrapper);
		//2 删除配套信息
		LambdaQueryWrapper<RoomFacility> roomFacilityWrapper = new LambdaQueryWrapper<RoomFacility>()
				.eq(RoomFacility::getRoomId, roomId);
		roomFacilityService.remove(roomFacilityWrapper);
		//3 删除属性信息
		LambdaQueryWrapper<RoomLabel> roomLabelWrapper = new LambdaQueryWrapper<RoomLabel>()
				.eq(RoomLabel::getRoomId, roomId);
		roomLabelService.remove(roomLabelWrapper);
		//4 删除属性信息
		LambdaQueryWrapper<RoomAttrValue> roomAttrValueWrapper = new LambdaQueryWrapper<RoomAttrValue>()
				.eq(RoomAttrValue::getRoomId, roomId);
		roomAttrValueService.remove(roomAttrValueWrapper);
		//5 删除支付方式
		LambdaQueryWrapper<RoomPaymentType> roomPaymentTypeWrapper = new LambdaQueryWrapper<RoomPaymentType>()
				.eq(RoomPaymentType::getRoomId, roomId);
		roomPaymentTypeService.remove(roomPaymentTypeWrapper);
		//6 删除租期列表
		LambdaQueryWrapper<RoomLeaseTerm> roomLeaseTermWrapper = new LambdaQueryWrapper<RoomLeaseTerm>()
				.eq(RoomLeaseTerm::getRoomId, roomId);
		roomLeaseTermService.remove(roomLeaseTermWrapper);
	}
}
