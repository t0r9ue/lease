package com.lease.web.admin.custom.interceptor;

import com.lease.common.login.LoginUser;
import com.lease.common.login.LoginUserHolder;
import com.lease.common.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author 孙依鹏
 * @version 1.0
 * @create 2025/11/11
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//1 从请求头中取出access-token
		String token = request.getHeader("access-token");
		//2 验证token
		Claims claims = JwtUtil.parseToken(token);
		//3 将数据存入ThreadLocal
		Long userId = claims.get("userId", Long.class);
		String username = claims.get("username", String.class);
		LoginUserHolder.setLoginUser(new LoginUser(userId, username));
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		//清理ThreadLocal
		LoginUserHolder.clear();
	}
}
