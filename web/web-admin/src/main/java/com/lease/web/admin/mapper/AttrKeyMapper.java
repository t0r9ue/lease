package com.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lease.model.entity.AttrKey;
import com.lease.web.admin.vo.attr.AttrKeyVo;

import java.util.List;

/**
 * @author 孙依鹏
 * @version 1.0
 * @create 2025/10/10
 */

public interface AttrKeyMapper extends BaseMapper<AttrKey> {
	List<AttrKeyVo> selectAttrInfo();
}