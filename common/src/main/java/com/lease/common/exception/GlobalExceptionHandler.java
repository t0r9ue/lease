package com.lease.common.exception;

import com.lease.common.result.Result;
import com.lease.common.result.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 孙依鹏
 * @version 1.0
 * @create 2025/10/18
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	private static final String SYSTEM_ERROR_MSG = "系统繁忙，请稍后再试";

	/**
	 * 处理所有不可知异常
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Result<Void> handleException(Exception e) {
		log.error("系统异常: ", e);
		return Result.build(null, ResultCodeEnum.FAIL);
	}

	/**
	 * 处理业务异常
	 */
	@ExceptionHandler(LeaseException.class)
	@ResponseBody
	public Result<Void> handleBusinessException(LeaseException e) {
		log.warn("业务异常: {}", e.getMessage());
		return Result.fail(e.getCode(), e.getMessage());
	}
//
//	/**
//	 * 处理参数校验异常
//	 */
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	@ResponseBody
//	public Result<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
//		String message = e.getBindingResult().getFieldErrors().stream()
//				.map(FieldError::getDefaultMessage)
//				.collect(Collectors.joining(", "));
//		log.warn("参数校验异常: {}", message);
//		return Result.fail(ResultCode.PARAM_VALID_ERROR, message);
//	}
//
//	/**
//	 * 处理空指针异常
//	 */
//	@ExceptionHandler(NullPointerException.class)
//	@ResponseBody
//	public Result<Void> handleNullPointerException(NullPointerException e) {
//		log.error("空指针异常: ", e);
//		return Result.fail(ResultCode.SYSTEM_ERROR, SYSTEM_ERROR_MSG);
//	}

}
