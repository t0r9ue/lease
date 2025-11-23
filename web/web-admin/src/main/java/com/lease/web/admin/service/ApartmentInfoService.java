package com.lease.web.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lease.model.entity.ApartmentInfo;
import com.lease.web.admin.vo.apartment.ApartmentDetailVo;
import com.lease.web.admin.vo.apartment.ApartmentItemVo;
import com.lease.web.admin.vo.apartment.ApartmentQueryVo;
import com.lease.web.admin.vo.apartment.ApartmentSubmitVo;

/**
 * @author  孙依鹏
 * @version 1.0
 * @create 2025/10/10
 */
    
public interface ApartmentInfoService extends IService<ApartmentInfo>{

	void saveOrUpdateApartmentInfo(ApartmentSubmitVo apartmentSubmitVo);

	IPage<ApartmentItemVo> pageItem(IPage<ApartmentItemVo> apartmentItemVoPage, ApartmentQueryVo queryVo);

	ApartmentDetailVo getDetailById(Long id);

	void removeByApartmentId(Long id);
}
