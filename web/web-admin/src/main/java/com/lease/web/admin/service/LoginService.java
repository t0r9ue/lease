package com.lease.web.admin.service;

import com.lease.web.admin.vo.login.CaptchaVo;
import com.lease.web.admin.vo.login.LoginVo;
import com.lease.web.admin.vo.system.user.SystemUserInfoVo;

/**
 * @author 孙依鹏
 * @version 1.0
 * @create 2025/11/8
 */
public interface LoginService {
	CaptchaVo getCaptcha();

	String login(LoginVo loginVo);

	SystemUserInfoVo getUserInfo(String username);
}
