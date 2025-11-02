package com.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lease.model.entity.LeaseAgreement;
import com.lease.web.admin.vo.agreement.AgreementQueryVo;
import com.lease.web.admin.vo.agreement.AgreementVo;

/**
 * @author 孙依鹏
 * @version 1.0
 * @create 2025/10/10
 */

public interface LeaseAgreementMapper extends BaseMapper<LeaseAgreement> {
	IPage<AgreementVo> selectAgreementVoByPage(IPage<AgreementVo> agreementVoPage, AgreementQueryVo queryVo);
}