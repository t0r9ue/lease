package com.lease.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lease.model.entity.ApartmentInfo;
import com.lease.model.entity.FacilityInfo;
import com.lease.model.entity.LabelInfo;
import com.lease.model.enums.ItemType;
import com.lease.web.app.mapper.ApartmentInfoMapper;
import com.lease.web.app.mapper.RoomInfoMapper;
import com.lease.web.app.service.ApartmentInfoService;
import com.lease.web.app.service.FacilityInfoService;
import com.lease.web.app.service.GraphInfoService;
import com.lease.web.app.service.LabelInfoService;
import com.lease.web.app.vo.apartment.ApartmentDetailVo;
import com.lease.web.app.vo.apartment.ApartmentItemVo;
import com.lease.web.app.vo.graph.GraphVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author liubo
 * @description 针对表【apartment_info(公寓信息表)】的数据库操作Service实现
 * @createDate 2023-07-26 11:12:39
 */
@Service
@RequiredArgsConstructor
public class ApartmentInfoServiceImpl extends ServiceImpl<ApartmentInfoMapper, ApartmentInfo>
        implements ApartmentInfoService {

	private final ApartmentInfoMapper apartmentInfoMapper;
	private final GraphInfoService graphInfoService;
	private final LabelInfoService labelInfoService;
	private final RoomInfoMapper roomInfoMapper;
	private final FacilityInfoService facilityInfoService;

	@Override
	public ApartmentItemVo findApartmentItemById(Long apartmentId) {
		ApartmentInfo apartmentInfo = apartmentInfoMapper.selectById(apartmentId);
		List<GraphVo> graphVoList = graphInfoService.findByItemIdAndItemType(apartmentId, ItemType.APARTMENT);
		List<LabelInfo> labelInfoList = labelInfoService.findByApartmentId(apartmentId);
		BigDecimal minRent = roomInfoMapper.selectMinRentByApartmentId(apartmentId);
		ApartmentItemVo apartmentItemVo = new ApartmentItemVo();
		BeanUtils.copyProperties(apartmentInfo, apartmentItemVo);
		apartmentItemVo.setGraphVoList(graphVoList);
		apartmentItemVo.setLabelInfoList(labelInfoList);
		apartmentItemVo.setMinRent(minRent);
		return apartmentItemVo;
	}

	@Override
	public ApartmentDetailVo getDetailById(Long id) {
		ApartmentItemVo apartmentItemVo = this.findApartmentItemById(id);
		List<FacilityInfo> facilityInfoList = facilityInfoService.getByApartmentId(id);

		ApartmentDetailVo apartmentDetailVo = new ApartmentDetailVo();
		BeanUtils.copyProperties(apartmentItemVo, apartmentDetailVo);
		apartmentDetailVo.setFacilityInfoList(facilityInfoList);
		return apartmentDetailVo;
	}
}




