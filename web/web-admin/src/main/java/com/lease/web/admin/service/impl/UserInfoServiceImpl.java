package com.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lease.model.entity.UserInfo;
import com.lease.web.admin.mapper.UserInfoMapper;
import com.lease.web.admin.service.UserInfoService;
import org.springframework.stereotype.Service;
/**
 * @author  孙依鹏
 * @version 1.0
 * @create 2025/10/10
 */
    
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService{

}
