package com.lease.web.admin.custom.converter;

import com.lease.model.enums.ItemType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author 孙依鹏
 * @version 1.0
 * @create 2025/10/12
 */
@Component
public class StringToItemTypeConverter implements Converter<String, ItemType> {
	@Override
	public ItemType convert(String source) {
		ItemType[] values = ItemType.values();
		for (ItemType value : values) {
			if (Objects.equals(value.getCode(), Integer.valueOf(source))) {
				return value;
			}
		}
		throw new IllegalArgumentException("code:" + source + ",非法参数");
	}
}
