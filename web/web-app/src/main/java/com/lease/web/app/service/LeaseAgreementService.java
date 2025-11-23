package com.lease.web.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lease.model.entity.LeaseAgreement;
import com.lease.web.app.vo.agreement.AgreementDetailVo;
import com.lease.web.app.vo.agreement.AgreementItemVo;

import java.util.List;

/**
* @author liubo
* @description 针对表【lease_agreement(租约信息表)】的数据库操作Service
* @createDate 2023-07-26 11:12:39
*/
public interface LeaseAgreementService extends IService<LeaseAgreement> {
	List<AgreementItemVo> listItem();

	AgreementDetailVo getDetailById(Long id);
}
