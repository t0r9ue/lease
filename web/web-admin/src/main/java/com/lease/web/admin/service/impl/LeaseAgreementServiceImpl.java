package com.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lease.model.entity.*;
import com.lease.web.admin.mapper.*;
import com.lease.web.admin.service.LeaseAgreementService;
import com.lease.web.admin.vo.agreement.AgreementQueryVo;
import com.lease.web.admin.vo.agreement.AgreementVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
/**
 * @author  孙依鹏
 * @version 1.0
 * @create 2025/10/10
 */
    
@Service
@RequiredArgsConstructor
public class LeaseAgreementServiceImpl extends ServiceImpl<LeaseAgreementMapper, LeaseAgreement> implements LeaseAgreementService{

	private final LeaseAgreementMapper leaseAgreementMapper;
	private final ApartmentInfoMapper apartmentInfoMapper;
	private final PaymentTypeMapper paymentTypeMapper;
	private final RoomInfoMapper roomInfoMapper;
	private final LeaseTermMapper leaseTermMapper;

	@Override
	public IPage<AgreementVo> pageItem(IPage<AgreementVo> agreementVoPage, AgreementQueryVo queryVo) {
		return leaseAgreementMapper.selectAgreementVoByPage(agreementVoPage, queryVo);
	}

	@Override
	public AgreementVo getAgreementVoById(Long id) {
		LeaseAgreement leaseAgreement = leaseAgreementMapper.selectById(id);
		ApartmentInfo apartmentInfo = apartmentInfoMapper.selectById(leaseAgreement.getApartmentId());
		PaymentType paymentType = paymentTypeMapper.selectById(leaseAgreement.getPaymentTypeId());
		RoomInfo roomInfo = roomInfoMapper.selectById(leaseAgreement.getRoomId());
		LeaseTerm leaseTerm = leaseTermMapper.selectById(leaseAgreement.getLeaseTermId());

		AgreementVo agreementVo = new AgreementVo();
		BeanUtils.copyProperties(leaseAgreement, agreementVo);
		agreementVo.setLeaseTerm(leaseTerm);
		agreementVo.setApartmentInfo(apartmentInfo);
		agreementVo.setPaymentType(paymentType);
		agreementVo.setRoomInfo(roomInfo);
		return agreementVo;
	}
}
