package com.tang.myCloud.utils;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateConverter implements Converter {

	@Override
	public Object convert(Class<?> clazz, Object value) {
		if (value == null)
			return null;
		// if type of value equals to excepted class type
		if( clazz == value.getClass() )
			return value;
		// if type of value if java.util.Date
		// converter to string or to java.sql.Date
		if (clazz == java.lang.String.class)
			return	value.toString();
			
		
		if (clazz == java.util.Date.class){
			
			if( value instanceof java.util.Date)
				return new java.util.Date (((java.util.Date)value).getTime());
			
			if( value instanceof java.lang.String){
				String s = (String)value;
				return getDate(s);
			}
			
			if( value instanceof java.sql.Date)
				return new java.util.Date(((java.sql.Date)value).getTime());
			
			if( value instanceof java.lang.Long)
				return new java.util.Date((Long)value);
		}
			
		if (clazz == java.sql.Date.class) {

			if (value instanceof java.lang.String) {

				String s = (String) value;
				return new java.sql.Date(getDate(s).getTime());
			}
			
			if( value instanceof java.util.Date){
				java.util.Date date = (java.util.Date) value;
				return new java.sql.Date(date.getTime());
			}
			
			if( value instanceof java.lang.Long)
				return new java.sql.Date((Long)value);

		}

		return null;
	}

	private java.util.Date getDate(String s) {
		SimpleDateFormat format = new SimpleDateFormat();
		Date date = null;
		// 2014-07-02 10:20:57
		if (s.contains("-")) {
			if (s.contains(":"))
				format.applyPattern("yyyy-MM-dd HH:mm:ss");
			else
				format.applyPattern("yyyy-MM-dd");
		} else if (s.contains("/")) {
			if (s.contains(":"))
				format.applyPattern("yyyy/MM/dd HH:mm:ss");
			else
				format.applyPattern("yyyy/MM/dd");
		} else
			throw new RuntimeException("can not parse the string to date");
		try {
			date = format.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
}
