package com.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lease.model.entity.SystemUser;
import com.lease.web.admin.vo.system.user.SystemUserItemVo;
import com.lease.web.admin.vo.system.user.SystemUserQueryVo;

/**
 * @author 孙依鹏
 * @version 1.0
 * @create 2025/10/10
 */

public interface SystemUserMapper extends BaseMapper<SystemUser> {
	IPage<SystemUserItemVo> selectUserItemByPage(Page<SystemUserItemVo> page, SystemUserQueryVo queryVo);
}