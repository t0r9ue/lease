package com.lease.web.app.service;

import com.lease.web.app.vo.user.LoginVo;
import com.lease.web.app.vo.user.UserInfoVo;

public interface LoginService {
	void getSMSCode(String phone);

	String login(LoginVo loginVo);

	UserInfoVo getUserInfoByUserId(Long userId);
}
