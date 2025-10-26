package com.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lease.model.entity.GraphInfo;
import com.lease.model.enums.ItemType;
import com.lease.web.admin.vo.graph.GraphVo;

import java.util.List;

/**
 * @author 孙依鹏
 * @version 1.0
 * @create 2025/10/10
 */

public interface GraphInfoMapper extends BaseMapper<GraphInfo> {
	List<GraphVo> selectNameAndUrlByApartmentIdAndItemType(ItemType itemType, Long id);
}