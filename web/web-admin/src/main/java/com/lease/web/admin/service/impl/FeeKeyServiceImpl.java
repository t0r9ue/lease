package com.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lease.model.entity.FeeKey;
import com.lease.web.admin.mapper.FeeKeyMapper;
import com.lease.web.admin.service.FeeKeyService;
import com.lease.web.admin.vo.fee.FeeKeyVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author  孙依鹏
 * @version 1.0
 * @create 2025/10/10
 */
    
@Service
@RequiredArgsConstructor
public class FeeKeyServiceImpl extends ServiceImpl<FeeKeyMapper, FeeKey> implements FeeKeyService{

	private final FeeKeyMapper feeKeyMapper;

	@Override
	public List<FeeKeyVo> feeInfoList() {
		List<FeeKeyVo> feeKeyVos = feeKeyMapper.selectFeeKeyInfo();
		return feeKeyVos;
	}
}
