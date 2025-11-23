package com.lease.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lease.model.entity.AttrValue;
import com.lease.web.app.mapper.AttrValueMapper;
import com.lease.web.app.service.AttrValueService;
import com.lease.web.app.vo.attr.AttrValueVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author liubo
* @description 针对表【attr_value(房间基本属性值表)】的数据库操作Service实现
* @createDate 2023-07-26 11:12:39
*/
@Service
@RequiredArgsConstructor
public class AttrValueServiceImpl extends ServiceImpl<AttrValueMapper, AttrValue>
    implements AttrValueService {

	private final AttrValueMapper attrValueMapper;

	@Override
	public List<AttrValueVo> getAttrValueVoByRoomId(Long id) {
		return attrValueMapper.selectAttrValueVoByRoomId(id);
	}
}




