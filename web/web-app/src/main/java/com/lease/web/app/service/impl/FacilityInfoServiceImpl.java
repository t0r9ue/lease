package com.lease.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lease.model.entity.FacilityInfo;
import com.lease.web.app.mapper.FacilityInfoMapper;
import com.lease.web.app.service.FacilityInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author liubo
* @description 针对表【facility_info(配套信息表)】的数据库操作Service实现
* @createDate 2023-07-26 11:12:39
*/
@Service
@RequiredArgsConstructor
public class FacilityInfoServiceImpl extends ServiceImpl<FacilityInfoMapper, FacilityInfo>
    implements FacilityInfoService {

	private final FacilityInfoMapper facilityInfoMapper;

	@Override
	public List<FacilityInfo> getByRoomId(Long id) {
		return facilityInfoMapper.selectByRoomId(id);
	}

	@Override
	public List<FacilityInfo> getByApartmentId(Long id) {
		return facilityInfoMapper.selectByApartmentId(id);
	}
}




