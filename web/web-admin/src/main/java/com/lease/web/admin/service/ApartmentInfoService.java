package com.lease.web.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lease.model.entity.ApartmentInfo;
import com.lease.web.admin.vo.apartment.ApartmentSubmitVo;

/**
 * @author  孙依鹏
 * @version 1.0
 * @create 2025/10/10
 */
    
public interface ApartmentInfoService extends IService<ApartmentInfo>{

	void saveOrUpdateApartmentInfo(ApartmentSubmitVo apartmentSubmitVo);
}
