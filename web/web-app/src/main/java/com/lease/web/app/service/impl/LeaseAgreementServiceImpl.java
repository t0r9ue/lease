package com.lease.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lease.common.login.LoginUserHolder;
import com.lease.model.entity.LeaseAgreement;
import com.lease.web.app.mapper.LeaseAgreementMapper;
import com.lease.web.app.service.LeaseAgreementService;
import com.lease.web.app.vo.agreement.AgreementDetailVo;
import com.lease.web.app.vo.agreement.AgreementItemVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liubo
 * @description 针对表【lease_agreement(租约信息表)】的数据库操作Service实现
 * @createDate 2023-07-26 11:12:39
 */
@Service
@RequiredArgsConstructor
public class LeaseAgreementServiceImpl extends ServiceImpl<LeaseAgreementMapper, LeaseAgreement>
        implements LeaseAgreementService {

	private final LeaseAgreementMapper leaseAgreementMapper;

	@Override
	public List<AgreementItemVo> listItem() {
		String phone = LoginUserHolder.getLoginUser().getUsername();
		return leaseAgreementMapper.selectListItemByPhoneNumber(phone);
	}

	@Override
	public AgreementDetailVo getDetailById(Long id) {
		return leaseAgreementMapper.selectDetailById(id);
	}
}




