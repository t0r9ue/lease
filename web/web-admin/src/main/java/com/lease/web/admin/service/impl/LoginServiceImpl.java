package com.lease.web.admin.service.impl;

import com.lease.common.constant.RedisConstant;
import com.lease.common.exception.LeaseException;
import com.lease.common.result.ResultCodeEnum;
import com.lease.common.util.JwtUtil;
import com.lease.common.util.RedisUtil;
import com.lease.model.entity.SystemUser;
import com.lease.model.enums.BaseStatus;
import com.lease.web.admin.service.LoginService;
import com.lease.web.admin.service.SystemUserService;
import com.lease.web.admin.vo.login.CaptchaVo;
import com.lease.web.admin.vo.login.LoginVo;
import com.lease.web.admin.vo.system.user.SystemUserInfoVo;
import com.wf.captcha.SpecCaptcha;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * @author 孙依鹏
 * @version 1.0
 * @create 2025/11/8
 */
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

	private final RedisUtil redisUtil;
	private final SystemUserService systemUserService;

	@Override
	public CaptchaVo getCaptcha() {
		SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 4);
		String code = specCaptcha.text().toLowerCase();
		String key = RedisConstant.ADMIN_LOGIN_PREFIX + UUID.randomUUID();
		redisUtil.set(key, code, RedisConstant.ADMIN_LOGIN_CAPTCHA_TTL_SEC);
		return new CaptchaVo(specCaptcha.toBase64(), key);
	}

	@Override
	public String login(LoginVo loginVo) {
		//1 检查校验码
		//1.1 是否为空
		if(!StringUtils.hasText(loginVo.getCaptchaCode())) {
			throw new LeaseException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_NOT_FOUND);
		}
		//1.2 验证码是否过期
		String captcha = (String) redisUtil.get(loginVo.getCaptchaKey());
		if(!StringUtils.hasText(captcha)) {
			throw new LeaseException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_EXPIRED);

		}

		//1.3 是否正确
		if(!loginVo.getCaptchaCode().toLowerCase().equals(captcha)) {
			throw new LeaseException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_ERROR);
		}

		//2 验证用户
		SystemUser systemUser = systemUserService.findUserByUsername(loginVo.getUsername());
		//2.1 用户是否存在
		if(systemUser == null) {
			throw new LeaseException(ResultCodeEnum.ADMIN_ACCOUNT_NOT_EXIST_ERROR);
		}
		//2.2 验证用户密码
		if(!systemUser.getPassword().equals(DigestUtils.md5Hex(loginVo.getPassword()))) {
			throw new LeaseException(ResultCodeEnum.ADMIN_ACCOUNT_ERROR);
		}
		//2.3 验证用户状态
		if(systemUser.getStatus() == BaseStatus.DISABLE) {
			throw new LeaseException(ResultCodeEnum.ADMIN_ACCOUNT_DISABLED_ERROR);
		}
		//3 返回jwt
		return JwtUtil.createToken(systemUser.getId(), systemUser.getUsername());
	}

	@Override
	public SystemUserInfoVo getUserInfo(String username) {
		SystemUser systemUser = systemUserService.findUserByUsername(username);
		SystemUserInfoVo userInfoVo = new SystemUserInfoVo();
		userInfoVo.setName(systemUser.getName());
		userInfoVo.setAvatarUrl(systemUser.getAvatarUrl());
		return userInfoVo;
	}
}
