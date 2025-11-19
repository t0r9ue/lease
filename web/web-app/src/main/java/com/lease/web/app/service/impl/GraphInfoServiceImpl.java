package com.lease.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lease.model.entity.GraphInfo;
import com.lease.model.enums.ItemType;
import com.lease.web.app.mapper.GraphInfoMapper;
import com.lease.web.app.service.GraphInfoService;
import com.lease.web.app.vo.graph.GraphVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author liubo
* @description 针对表【graph_info(图片信息表)】的数据库操作Service实现
* @createDate 2023-07-26 11:12:39
*/
@Service
@RequiredArgsConstructor
public class GraphInfoServiceImpl extends ServiceImpl<GraphInfoMapper, GraphInfo>
    implements GraphInfoService {

	private final GraphInfoMapper graphInfoMapper;

	@Override
	public List<GraphVo> findByItemIdAndItemType(Long apartmentId, ItemType itemType) {
		return graphInfoMapper.selectNameAndUrlByItemIdAndItemType(apartmentId, itemType);
	}
}




