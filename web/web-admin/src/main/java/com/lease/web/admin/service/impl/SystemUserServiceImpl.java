package com.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lease.model.entity.SystemPost;
import com.lease.model.entity.SystemUser;
import com.lease.web.admin.mapper.SystemPostMapper;
import com.lease.web.admin.mapper.SystemUserMapper;
import com.lease.web.admin.service.SystemUserService;
import com.lease.web.admin.vo.system.user.SystemUserItemVo;
import com.lease.web.admin.vo.system.user.SystemUserQueryVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
/**
 * @author  孙依鹏
 * @version 1.0
 * @create 2025/10/10
 */
    
@Service
@RequiredArgsConstructor
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements SystemUserService{

	private final SystemUserMapper systemUserMapper;

	private final SystemPostMapper systemPostMapper;

	@Override
	public IPage<SystemUserItemVo> findUserItemVoByPage(Page<SystemUserItemVo> page, SystemUserQueryVo queryVo) {
		return systemUserMapper.selectUserItemByPage(page, queryVo);
	}

	@Override
	public SystemUserItemVo findUserDetailById(Long id) {
		SystemUser systemUser = systemUserMapper.selectById(id);
		SystemPost systemPost = systemPostMapper.selectById(systemUser.getPostId());
		SystemUserItemVo systemUserItemVo = new SystemUserItemVo();
		BeanUtils.copyProperties(systemUser, systemUserItemVo);
		systemUserItemVo.setPostName(systemPost.getName());
		return systemUserItemVo;
	}

	@Override
	public SystemUser findUserByUsername(String username) {
		return systemUserMapper.selectByUsername(username);
	}

}
