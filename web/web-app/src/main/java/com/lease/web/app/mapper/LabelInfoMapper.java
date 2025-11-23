package com.lease.web.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lease.model.entity.LabelInfo;

import java.util.List;

/**
 * @author  孙依鹏
 * @version 1.0
 * @create 2025/11/13
 */
    
public interface LabelInfoMapper extends BaseMapper<LabelInfo> {
	List<LabelInfo> selectByApartmentId(Long apartmentId);

	List<LabelInfo> selectByRoomId(Long roomId);
}