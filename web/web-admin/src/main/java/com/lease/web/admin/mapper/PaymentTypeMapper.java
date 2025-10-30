package com.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lease.model.entity.PaymentType;

import java.util.List;

/**
 * @author 孙依鹏
 * @version 1.0
 * @create 2025/10/10
 */

public interface PaymentTypeMapper extends BaseMapper<PaymentType> {
	List<PaymentType> selectListByRoomId(Long id);
}