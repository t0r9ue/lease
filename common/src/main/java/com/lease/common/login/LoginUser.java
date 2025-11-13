package com.lease.common.login;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 孙依鹏
 * @version 1.0
 * @create 2025/11/12
 */
@Data
@AllArgsConstructor
public class LoginUser {

	private Long userId;
	private String username;

}
