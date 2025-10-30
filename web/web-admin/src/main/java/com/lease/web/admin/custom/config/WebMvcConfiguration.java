package com.lease.web.admin.custom.config;

import com.lease.web.admin.custom.converter.StringToBaseEnumConverterFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 孙依鹏
 * @version 1.0
 * @create 2025/10/12
 */
@Configuration
@RequiredArgsConstructor
public class WebMvcConfiguration implements WebMvcConfigurer {

//	private final StringToItemTypeConverter stringToItemTypeConverter;
	private final StringToBaseEnumConverterFactory stringToBaseEnumConverterFactory;

	@Override
	public void addFormatters(FormatterRegistry registry) {
//		registry.addConverter(stringToItemTypeConverter);
		registry.addConverterFactory(stringToBaseEnumConverterFactory);
	}
}
