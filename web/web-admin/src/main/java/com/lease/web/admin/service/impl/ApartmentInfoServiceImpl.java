package com.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lease.model.entity.*;
import com.lease.model.enums.ItemType;
import com.lease.web.admin.mapper.ApartmentInfoMapper;
import com.lease.web.admin.service.*;
import com.lease.web.admin.vo.apartment.ApartmentSubmitVo;
import com.lease.web.admin.vo.graph.GraphVo;
import lombok.RequiredArgsConstructor;
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
public class ApartmentInfoServiceImpl extends ServiceImpl<ApartmentInfoMapper, ApartmentInfo> implements ApartmentInfoService{

	private final GraphInfoService graphInfoService;

	private final ApartmentFacilityService facilityService;

	private final ApartmentFeeValueService feeValueService;

	private final ApartmentLabelService labelService;

	@Override
	public void saveOrUpdateApartmentInfo(ApartmentSubmitVo apartmentSubmitVo) {
		//1、判断是新增操作还是修改操作
		Long apartmentId = apartmentSubmitVo.getId();
		boolean isUpdate = apartmentId != null;
		//2、保存公寓的信息
		boolean b = super.saveOrUpdate(apartmentSubmitVo);
		//3、修改操作是先删除再执行新增操作
		if(isUpdate) {
			//删除操作
			//删除对应图片
			LambdaQueryWrapper<GraphInfo> graphWrapper = new LambdaQueryWrapper<GraphInfo>()
					.eq(GraphInfo::getItemType, ItemType.APARTMENT)
					.eq(GraphInfo::getItemId, apartmentId);
			graphInfoService.remove(graphWrapper);
			//删除配套
			LambdaQueryWrapper<ApartmentFacility> facilityWrapper = new LambdaQueryWrapper<ApartmentFacility>()
					.eq(ApartmentFacility::getApartmentId, apartmentId);
			facilityService.remove(facilityWrapper);
			//删除杂费
			LambdaQueryWrapper<ApartmentFeeValue> feeValueWrapper = new LambdaQueryWrapper<ApartmentFeeValue>()
					.eq(ApartmentFeeValue::getApartmentId, apartmentId);
			feeValueService.remove(feeValueWrapper);
			//删除标签
			LambdaQueryWrapper<ApartmentLabel> labelWrapper = new LambdaQueryWrapper<ApartmentLabel>()
					.eq(ApartmentLabel::getApartmentId, apartmentId);
			labelService.remove(labelWrapper);
		}
		//新增操作
		//新增图片
		List<GraphVo> graphVoList = apartmentSubmitVo.getGraphVoList();
		if(!CollectionUtils.isEmpty(graphVoList)) {
			List<GraphInfo> graphInfos = graphVoList.stream()
					.map(graphVo -> {
						GraphInfo graphInfo = new GraphInfo();
						graphInfo.setName(graphVo.getName());
						graphInfo.setUrl(graphInfo.getUrl());
						graphInfo.setItemId(apartmentId);
						graphInfo.setItemType(ItemType.APARTMENT);
						return graphInfo;
					}).toList();
			graphInfoService.saveBatch(graphInfos);
		}

		//新增配套关系
		List<Long> facilityInfoIds = apartmentSubmitVo.getFacilityInfoIds();
		if(!CollectionUtils.isEmpty(facilityInfoIds)) {
			List<ApartmentFacility> facilityList = facilityInfoIds.stream()
					.map(facilityId -> ApartmentFacility.builder()
							.apartmentId(apartmentId)
							.facilityId(facilityId)
							.build()).toList();
			facilityService.saveBatch(facilityList);
		}

		//新增杂费关系
		List<Long> feeValueIds = apartmentSubmitVo.getFeeValueIds();
		if(!CollectionUtils.isEmpty(feeValueIds)) {
			List<ApartmentFeeValue> feeValueList = feeValueIds.stream()
					.map(feeValueId -> ApartmentFeeValue.builder()
							.apartmentId(apartmentId)
							.feeValueId(feeValueId)
							.build()).toList();
			feeValueService.saveBatch(feeValueList);
		}

		//新增标签关系
		List<Long> labelIds = apartmentSubmitVo.getLabelIds();
		if(!CollectionUtils.isEmpty(labelIds)) {
			List<ApartmentLabel> labelList = labelIds.stream()
					.map(labelId -> ApartmentLabel.builder()
							.apartmentId(apartmentId)
							.labelId(labelId)
							.build()).toList();
			labelService.saveBatch(labelList);
		}
	}
}
