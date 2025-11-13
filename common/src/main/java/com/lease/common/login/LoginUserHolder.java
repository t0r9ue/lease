package com.lease.common.login;

/**
 * @author 孙依鹏
 * @version 1.0
 * @create 2025/11/12
 */
public class LoginUserHolder {

	public static ThreadLocal<LoginUser> threadLocal = new ThreadLocal<>();

	public static void setLoginUser(LoginUser user) {
		threadLocal.set(user);
	}

	public static LoginUser getLoginUser() {
		return threadLocal.get();
	}

	public static void clear() {
		threadLocal.remove();
	}

}
