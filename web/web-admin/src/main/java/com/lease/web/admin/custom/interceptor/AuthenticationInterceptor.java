package com.lease.web.admin.custom.interceptor;

import com.lease.common.util.JwtUtil;
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
		JwtUtil.parseToken(token);
		return true;
	}
}
