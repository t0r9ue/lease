package com.lease.common.util;

import java.util.Random;

/**
 * @author 孙依鹏
 * @version 1.0
 * @create 2025/11/15
 */
public class VerifyCodeUtil {

	public static String getVerifyCode(int length) {
		Random random = new Random();
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < length; i++) {
			builder.append(random.nextInt(10));
		}

		return builder.toString();
	}

}
