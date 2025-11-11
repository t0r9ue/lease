package com.lease.web.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lease.model.entity.SystemUser;
import com.lease.web.admin.vo.system.user.SystemUserItemVo;
import com.lease.web.admin.vo.system.user.SystemUserQueryVo;

/**
 * @author  孙依鹏
 * @version 1.0
 * @create 2025/10/10
 */
    
public interface SystemUserService extends IService<SystemUser>{

	IPage<SystemUserItemVo> findUserItemVoByPage(Page<SystemUserItemVo> objectPage, SystemUserQueryVo queryVo);

	SystemUserItemVo findUserDetailById(Long id);

	SystemUser findUserByUsername(String username);
}
