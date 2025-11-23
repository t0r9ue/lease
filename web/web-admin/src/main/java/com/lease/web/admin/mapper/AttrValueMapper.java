package com.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lease.model.entity.AttrValue;
import com.lease.web.admin.vo.attr.AttrValueVo;

import java.util.List;

/**
 * @author 孙依鹏
 * @version 1.0
 * @create 2025/10/10
 */

public interface AttrValueMapper extends BaseMapper<AttrValue> {
	List<AttrValueVo> selectAttrValueVoByRoomId(Long id);
}