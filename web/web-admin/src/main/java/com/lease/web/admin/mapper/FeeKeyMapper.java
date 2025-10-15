package com.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lease.model.entity.FeeKey;
import com.lease.web.admin.vo.fee.FeeKeyVo;

import java.util.List;

/**
 * @author 孙依鹏
 * @version 1.0
 * @create 2025/10/10
 */

public interface FeeKeyMapper extends BaseMapper<FeeKey> {
	List<FeeKeyVo> selectFeeKeyInfo();
}