package com.tang.myCloud.utils;

import java.math.BigDecimal;

import oracle.sql.CHAR;

public class SimpleConverter extends DateConverter {
	@Override
	public Object convert(Class<?> clazz, Object value) {
		if( value == null)
			return null;
		
		Object result = super.convert(clazz, value);
		
		if (result != null)
			return result;
		
		Class<?> valueType = value.getClass();

		// type is int
		if (clazz ==  int.class||clazz==Integer.class) {
			Integer in = null;
			// type of value is BigDecimal
			if (valueType == BigDecimal.class) {
				BigDecimal gd = (BigDecimal) value;
				in = gd.intValue();
			}

			// type of value is Double,long,float,short
			else if (valueType == Double.class || valueType == Long.class
					|| valueType == Float.class || valueType == Short.class
					|| valueType == CHAR.class)
				in = (Integer) value;

			return in;
		}
		// clazz is long
		if (clazz == long.class  || clazz == Long.class) {
			Long in = null;
			// type of value is BigDecimal
			if (valueType == BigDecimal.class) {
				BigDecimal gd = (BigDecimal) value;
				in = gd.longValue();
				return in;
			}

			// type of value is Double,long,float,short
			if (valueType == Double.class || valueType == Long.class
					|| valueType == Float.class || valueType == Short.class
					|| valueType == Character.class){
				in = (long) value;
				return in;
			}
		}
		
		// clazz is short
		if (clazz == short.class || clazz==Short.class) {
			Short in = null;
			// type of value is BigDecimal
			if (valueType == BigDecimal.class) {
				BigDecimal gd = (BigDecimal) value;
				in = gd.shortValue();
				return in;
			}

			// type of value is Double,long,float,short
			else if (valueType == Double.class || valueType == Long.class
					|| valueType == Float.class || valueType == Short.class
					|| valueType == CHAR.class){
				in = (short) value;
				return in;
			}
		}
		
		// clazz is float
		if (clazz == Float.class || clazz == float.class) {
			Float in = null;
			// type of value is BigDecimal
			if (valueType == BigDecimal.class) {
				BigDecimal gd = (BigDecimal) value;
				in = gd.floatValue();
				return in;
			}

			// type of value is Double,long,float,short
			else if (valueType == Double.class || valueType == Long.class
					|| valueType == Float.class || valueType == Short.class
					|| valueType == CHAR.class){
				in = (float) value;
				return in;
			}
		}
		
		
		// clazz is double
		if (clazz == Double.class || clazz == double.class) {
			Double in = null;
			// type of value is BigDecimal
			if (valueType == BigDecimal.class) {
				BigDecimal gd = (BigDecimal) value;
				in = gd.doubleValue();
				return in;
			}

			// type of value is Double,long,float,short
			else if (valueType == Double.class || valueType == Long.class
					|| valueType == Float.class || valueType == Short.class
					|| valueType == Character.class){
				in = (Double) value;
				return in;
			}
		}
		

		// clazz is char
		if (clazz == Character.class || clazz == char.class) {
			Character in = null;
			// type of value is BigDecimal
			if (valueType == BigDecimal.class) {
				BigDecimal gd = (BigDecimal) value;
				in = (char) gd.intValue();
				return in;
			}

			// type of value is Double,long,float,short
			else if (valueType == Double.class || valueType == Long.class
					|| valueType == Float.class || valueType == Short.class
					|| valueType == Character.class){
				in = (char)((int)value);
				return in;
			}
		}
		return null;
	}
}
