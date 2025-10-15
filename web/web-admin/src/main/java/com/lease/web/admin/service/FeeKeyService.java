package com.lease.web.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lease.model.entity.FeeKey;
import com.lease.web.admin.vo.fee.FeeKeyVo;

import java.util.List;

/**
 * @author  孙依鹏
 * @version 1.0
 * @create 2025/10/10
 */
    
public interface FeeKeyService extends IService<FeeKey>{

	List<FeeKeyVo> feeInfoList();
}
