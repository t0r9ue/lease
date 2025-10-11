package com.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lease.model.entity.SystemUser;
import com.lease.web.admin.mapper.SystemUserMapper;
import com.lease.web.admin.service.SystemUserService;
import org.springframework.stereotype.Service;
/**
 * @author  孙依鹏
 * @version 1.0
 * @create 2025/10/10
 */
    
@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements SystemUserService{

}
