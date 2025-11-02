package com.lease.web.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lease.model.entity.LeaseAgreement;
import com.lease.web.admin.vo.agreement.AgreementQueryVo;
import com.lease.web.admin.vo.agreement.AgreementVo;

/**
 * @author  孙依鹏
 * @version 1.0
 * @create 2025/10/10
 */
    
public interface LeaseAgreementService extends IService<LeaseAgreement>{


	IPage<AgreementVo> pageItem(IPage<AgreementVo> agreementVoPage, AgreementQueryVo queryVo);

	AgreementVo getAgreementVoById(Long id);
}
