package com.lease.web.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lease.model.entity.FeeValue;
import com.lease.web.app.vo.fee.FeeValueVo;

import java.util.List;

/**
 * @author  孙依鹏
 * @version 1.0
 * @create 2025/11/13
 */
    
public interface FeeValueMapper extends BaseMapper<FeeValue> {
	List<FeeValueVo> selectByApartmentId(Long apartmentId);
}