package com.lease.common.exception;

import com.lease.common.result.ResultCodeEnum;
import lombok.Data;

/**
 * @author 孙依鹏
 * @version 1.0
 * @create 2025/10/27
 */
@Data
public class LeaseException extends RuntimeException {

	private Integer code;

	public LeaseException(String message, Integer code) {
		super(message);
		this.code = code;
	}

	public LeaseException(ResultCodeEnum resultCodeEnum) {
		super(resultCodeEnum.getMessage());
		this.code = resultCodeEnum.getCode();
	}
}
