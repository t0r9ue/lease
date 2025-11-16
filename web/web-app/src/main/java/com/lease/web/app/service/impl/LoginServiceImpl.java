package com.lease.web.app.service.impl;

import com.lease.common.constant.RedisConstant;
import com.lease.common.exception.LeaseException;
import com.lease.common.result.ResultCodeEnum;
import com.lease.common.util.JwtUtil;
import com.lease.common.util.RedisUtil;
import com.lease.common.util.VerifyCodeUtil;
import com.lease.model.entity.UserInfo;
import com.lease.model.enums.BaseStatus;
import com.lease.web.app.service.LoginService;
import com.lease.web.app.service.UserInfoService;
import com.lease.web.app.vo.user.LoginVo;
import com.lease.web.app.vo.user.UserInfoVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

	private final RedisUtil redisUtil;

	private final UserInfoService userInfoService;

	@Override
	public void getSMSCode(String phone) {
		// 判断上次发送的验证码时间
		String key = RedisConstant.APP_LOGIN_PREFIX + phone;
		boolean hasKey = redisUtil.hasKey(key);
		// 不足60s返回异常
		if(hasKey) {
			long ttl = RedisConstant.APP_LOGIN_CODE_TTL_SEC - redisUtil.getExpire(key);
			if(ttl < 60) {
				throw new LeaseException(ResultCodeEnum.APP_SEND_SMS_TOO_OFTEN);
			}
		}
		String verifyCode = VerifyCodeUtil.getVerifyCode(4);
		redisUtil.set(key, verifyCode, RedisConstant.APP_LOGIN_CODE_TTL_SEC);
	}

	@Override
	public String login(LoginVo loginVo) {
		//1、验证验证码
		String code = (String) redisUtil.get(RedisConstant.APP_LOGIN_PREFIX + loginVo.getPhone());
		if(code == null) {
			throw new LeaseException(ResultCodeEnum.APP_LOGIN_CODE_EXPIRED);
		}
		if(!code.equals(loginVo.getCode())) {
			throw new LeaseException(ResultCodeEnum.APP_LOGIN_CODE_ERROR);
		}
		//2、验证用户信息，如果不存在就创建用户
		UserInfo userInfo = userInfoService.getByPhone(loginVo.getPhone());
		if(userInfo == null) {
			userInfo = new UserInfo();
			userInfo.setPhone(loginVo.getPhone());
			userInfo.setStatus(BaseStatus.ENABLE);
			userInfo.setNickname("用户-" + loginVo.getPhone().substring(7));
			userInfoService.save(userInfo);
		} else {
			if(userInfo.getStatus() == BaseStatus.DISABLE) {
				throw new LeaseException(ResultCodeEnum.APP_ACCOUNT_DISABLED_ERROR);
			}
		}

		return JwtUtil.createToken(userInfo.getId(), userInfo.getPhone());
	}

	@Override
	public UserInfoVo getUserInfoByUserId(Long userId) {
		UserInfo userInfo = userInfoService.getById(userId);
		return new UserInfoVo(userInfo.getNickname(), userInfo.getAvatarUrl());
	}
}
