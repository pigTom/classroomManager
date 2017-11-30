package com.tang.myCloud.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
/**
 * SimpleTransaction is a transaction that implements Transaction
 * use method start to start transaction and close to end transaction,
 * these two operations( call the two method ) are essential is you want
 * to do transaction.
 * @author Administrator
 *
 */
public class SimpleTransaction implements Transaction {
	private Connection con;

	public SimpleTransaction(Connection con) {
		this.con = con;
	}

	@Override
	public List<?> listQuery(String sql, Object... args) {
		return MyDB.query(this.con, sql, args);
	}

	@Override
	public <T> List<T> listQuery(Class<T> clazz, String sql, Object... args) {
		return MyDB.query(con, clazz, sql, args);
	}

	@Override
	public <T> T query(Class<T> clazz, String sql, Object... args) {
		List<T> list = MyDB.query(con, clazz, sql, args);
		if (list.size() == 0)
			return null;
		else
			return list.get(0);
	}

	@Override
	public Object query(String sql, Object... args) {
		return MyDB.query(con, sql, args);
	}

	@Override
	public int update(String sql, Object... args) {
		return MyDB.update(con, false, sql, args);
	}
	/**
	 * to start a transaction
	 */
	@Override
	public void start() {
		try {
			con.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * to end a transaction
	 */
	@Override
	public void close() {
		try {
			con.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public <T> void add(T bean) {

	}

}
