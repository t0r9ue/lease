package com.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lease.model.entity.LabelInfo;

import java.util.List;

/**
 * @author 孙依鹏
 * @version 1.0
 * @create 2025/10/10
 */

public interface LabelInfoMapper extends BaseMapper<LabelInfo> {
	List<LabelInfo> selectListByApartmentId(Long id);

	List<LabelInfo> selectListByRoomId(Long id);
}