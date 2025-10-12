package com.lease.web.admin.custom.config;

import com.lease.model.enums.BaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author 孙依鹏
 * @version 1.0
 * @create 2025/10/12
 */
@Component
public class StringToBaseEnumConverterFactory implements ConverterFactory<String, BaseEnum> {

	@Override
	public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {

		return new Converter<String, T>() {
			@Override
			public T convert(String source) {
				T[] enumConstants = targetType.getEnumConstants();
				for (T enumConstant : enumConstants) {
					if (Objects.equals(enumConstant.getCode(), Integer.valueOf(source))) {
						return enumConstant;
					}
				}
				throw new IllegalArgumentException("code:" + source + "参数非法");
			}
		};
	}
}
