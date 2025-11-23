package com.lease.web.admin.schedule;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.lease.model.entity.LeaseAgreement;
import com.lease.model.enums.LeaseStatus;
import com.lease.web.admin.service.LeaseAgreementService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * @author 孙依鹏
 * @version 1.0
 * @create 2025/11/2
 */
@RequiredArgsConstructor
public class ScheduleTasks {

	private final LeaseAgreementService leaseAgreementService;

	@Scheduled(cron = "0 0 0 * * *")
	public void checkAgreementStatus() {
		LambdaUpdateWrapper<LeaseAgreement> updateWrapper = new LambdaUpdateWrapper<LeaseAgreement>()
				.le(LeaseAgreement::getLeaseEndDate, new Date())
				.in(LeaseAgreement::getStatus, LeaseStatus.SIGNED, LeaseStatus.WITHDRAWING)
				.set(LeaseAgreement::getStatus, LeaseStatus.EXPIRED);
		leaseAgreementService.update(updateWrapper);
	}

}
