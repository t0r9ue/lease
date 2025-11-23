package com.lease.web.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lease.model.entity.PaymentType;

import java.util.List;

/**
 * @author  孙依鹏
 * @version 1.0
 * @create 2025/11/13
 */
    
public interface PaymentTypeMapper extends BaseMapper<PaymentType> {
	List<PaymentType> selectByRoomId(Long roomId);
}