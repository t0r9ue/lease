package com.lease.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lease.model.entity.PaymentType;
import com.lease.web.app.mapper.PaymentTypeMapper;
import com.lease.web.app.service.PaymentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author liubo
* @description 针对表【payment_type(支付方式表)】的数据库操作Service实现
* @createDate 2023-07-26 11:12:39
*/
@Service
@RequiredArgsConstructor
public class PaymentTypeServiceImpl extends ServiceImpl<PaymentTypeMapper, PaymentType>
    implements PaymentTypeService {

	private final PaymentTypeMapper paymentTypeMapper;

	@Override
	public List<PaymentType> getByRoomId(Long id) {
		return paymentTypeMapper.selectByRoomId(id);
	}
}




