package com.tang.myCloud.utils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class DBUtils implements AbstractDBUtils {
	public DBUtils() {
	}

	// private static DBUtils instance = new DBUtils();
	// Singleton
	private static class Factory {
		static DBUtils instance = new DBUtils();
	}

	public static DBUtils newInstance() {
		return Factory.instance;
	}

	private DataSource dataSource;

	/**
	 * set DateSource to be used
	 * 
	 * @param source
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<?> listQuery(String sql, Object... args) {
		Connection con = this.getConection();
		return MyDB.query(con, sql, args);
	}

	private Connection getConection() {
		Connection con = null;
		try {
			con = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * return a list of Object that specified by Class<?>
	 * 
	 * @param <T>
	 * @param clazz
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException
	 */
	public <T> List<T> listQuery(Class<T> clazz, String sql, Object... args) {
		Connection con = this.getConection();
		return MyDB.query(con, clazz, sql, args);
	}

	public int queryInt(String sql, Object... args) {
		Connection con = this.getConection();
		return MyDB.queryInt(con, sql, args);
	}

	public long queryLong(String sql, Object... args) {
		Connection con = this.getConection();
		return MyDB.queryLong(con, sql, args);
	}

	/**
	 * 根据给定的类型，将数据库查询的结果包装成该类型，然后返回， 该类型应该与数据库字段一一对应(该类应该为一个bean)
	 * 
	 * @param clazz
	 * @param sql
	 * @param args
	 * @return
	 * @return
	 * @throws SQLException
	 */
	public <T> T query(Class<T> clazz, String sql, Object... args) {
		Connection con = this.getConection();
		return MyDB.queryBean(con, clazz, sql, args);
	}

	/**
	 * the result will be the type transform from database such as String ,
	 * BigDecimal, integer, double and so on
	 * 
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException
	 */
	public Object query(String sql, Object... args) {
		Connection con = this.getConection();
		return MyDB.queryObject(con, sql, args);
	}

	public int update(String sql, Object... args) {
		Connection con = this.getConection();
		return MyDB.update(con, sql, args);
	}

	/**
	 * get SimpleTransactin
	 * @return
	 */
	public Transaction getTransaction() {
		Connection con = this.getConection();
		Transaction transaction = null;
		transaction = new SimpleTransaction(con);
		return transaction;
	}


	@Override
	public <T> void add(T bean) {
		// TODO Auto-generated method stub
		Connection con = this.getConection();
		String tableName = bean.getClass().getSimpleName().toUpperCase();
		Field[] fields = bean.getClass().getDeclaredFields();

		StringBuilder sbFieldNames = new StringBuilder("insert into "
				+ tableName + "(");
		StringBuilder sbValues = new StringBuilder("values( ");
		List<Object> listFieldValues = new ArrayList<>();
		for (Field field : fields) {
			field.setAccessible(true);
			Object o = null;
			try {
				o = field.get(bean);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (o != null && !o.toString().trim().isEmpty()) {
				System.out.println(o.toString());
				listFieldValues.add(o);
				sbValues.append("?,");
				sbFieldNames.append(field.getName() + ",");
			}

		}
		sbFieldNames.deleteCharAt(sbFieldNames.length() - 1);
		sbValues.deleteCharAt(sbValues.length() - 1);
		sbFieldNames.append(")");
		sbValues.append(")");
		// a real sql statement
		sbFieldNames.append(sbValues);
		String sql = sbFieldNames.toString();
		System.out.println("sql:" + sql);
		MyDB.update(con, sql, listFieldValues.toArray());
	}
	/*
	 * private String getFieldNames(Field[] fields){
	 * 
	 * }
	 */
	// private String getFieldValues(Field)
}
