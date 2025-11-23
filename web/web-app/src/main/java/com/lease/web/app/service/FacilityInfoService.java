package com.lease.web.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lease.model.entity.FacilityInfo;

import java.util.List;

/**
* @author liubo
* @description 针对表【facility_info(配套信息表)】的数据库操作Service
* @createDate 2023-07-26 11:12:39
*/
public interface FacilityInfoService extends IService<FacilityInfo> {

	List<FacilityInfo> getByRoomId(Long id);

	List<FacilityInfo> getByApartmentId(Long id);
}
