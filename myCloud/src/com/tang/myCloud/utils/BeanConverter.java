package com.tang.myCloud.utils;

public class BeanConverter {
	static Converter converter = new SimpleConverter();

	public static void register(Converter converter, Class<?> clazz) {
		BeanConverter.converter = converter;
	}
}
