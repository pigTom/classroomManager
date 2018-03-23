package com.tang.myCloud.utils;

import java.util.List;

public interface AbstractDBUtils {
	List<?> listQuery(String sql, Object... args);

	<T> List<T> listQuery(Class<T> clazz, String sql, Object... args);

	<T> T query(Class<T> clazz, String sql, Object... args);

	Object query(String sql, Object... args);

	int update(String sql, Object... args);

	/**
	 * the class name of the bean specified table( with the same name) the
	 * fields of bean reflect the fields of the table insert the value of the
	 * fields to specified tabled <code>eg:</code> sql: insert into
	 * <code>bean.getClass().getSimpleName()</code> ( bean.field1,
	 * bean.field2,...) values ( bean.getField1(), bean.getField2(),...)
	 * 
	 * @param bean
	 */
	<T> void add(T bean);
}
