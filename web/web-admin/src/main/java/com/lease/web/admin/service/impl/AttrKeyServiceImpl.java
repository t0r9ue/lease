package com.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lease.model.entity.AttrKey;
import com.lease.web.admin.mapper.AttrKeyMapper;
import com.lease.web.admin.service.AttrKeyService;
import com.lease.web.admin.vo.attr.AttrKeyVo;
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
public class AttrKeyServiceImpl extends ServiceImpl<AttrKeyMapper, AttrKey> implements AttrKeyService{

	private final AttrKeyMapper attrKeyMapper;

	@Override
	public List<AttrKeyVo> listAttrInfo() {
		return attrKeyMapper.selectAttrInfo();
	}
}
