package com.tang.myCloud.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyDB {

	/**
	 * get preparedStatement by connection set SQL to preparedStatement and set
	 * element to preparedSatement to fill the SQL
	 * 
	 * @param sql
	 * @param list
	 */
	private static void setSql(final PreparedStatement ps, Object... args) {
		try {
			for (int i = 0; i < args.length; ++i) {
				if (args[i] instanceof java.util.Date)
					ps.setObject(i + 1, new java.sql.Date(
							((java.util.Date) args[i]).getTime()));
				else {
					ps.setObject(i + 1, args[i]);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static public Object queryObject(Connection con, String sql, Object... args) {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			setSql(ps, args);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getObject(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Retrieves the value of the designated column in the current row of this
	 * ResultSet object as a long in the Java programming language.
	 * 
	 * @param con
	 * @param sql
	 * @param args
	 * @return the column value; if the value is SQL NULL, the value returned is
	 *         0
	 */
	static public long queryLong(Connection con, String sql, Object... args) {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			setSql(ps, args);
			ResultSet rs = ps.executeQuery();

			if (rs.next())
				return rs.getLong(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * Retrieves the value of the designated column in the current row of this
	 * ResultSet object as an int in the Java programming language
	 * 
	 * @param con
	 * @param sql
	 * @param args
	 * @return the column value; if the value is SQL NULL, the value returned is
	 *         0
	 */
	static public int queryInt(Connection con, String sql, Object... args) {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			setSql(ps, args);
			ResultSet rs = ps.executeQuery();

			if (rs.next())
				return rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static List<Object> query(Connection con, String sql, Object... args) {
		PreparedStatement ps = null;
		List<Object> list = new ArrayList<Object>();
		try {
			ps = con.prepareStatement(sql);
			setSql(ps, args);
			ResultSet rs = ps.executeQuery();
			int count = 0;
			while (rs.next()) {
				list.add(rs.getObject(++count));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public static <T> T queryBean(Connection con, Class<T> clazz, String sql,
			Object... args) {
		if (con == null) {
			return null;
		}
		PreparedStatement ps = null;
		T entity = null;
		try {
			ps = con.prepareStatement(sql);
			setSql(ps, args);
			ResultSet rs = null;
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();

			if (rs.next()) {
				entity = clazz.newInstance();
				for (int i = 0; i < rsmd.getColumnCount(); ++i) {
					Object value = rs.getObject(i + 1);
					String name = rsmd.getColumnLabel(i + 1);
					name = name.toLowerCase();
					BeanUtils.setProperty(entity, name, value);
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return entity;
	}

	/**
	 * get Object Type by Class clazz
	 * 
	 * @param clazz
	 * @param sql
	 * @param args
	 * @return List<T> that specified by SQL, maybe null if there is no date in
	 *         ResultSet
	 */
	static public <T> List<T> query(Connection con, Class<T> clazz, String sql,
			Object... args) {
		if (con == null) {
			return null;
		}
		PreparedStatement ps = null;
		List<T> list = new ArrayList<T>();
		try {
			ps = con.prepareStatement(sql);
			T entity = null;
			setSql(ps, args);
			ResultSet rs = null;
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();

			while (rs.next()) {
				entity = clazz.newInstance();
				for (int i = 0; i < rsmd.getColumnCount(); ++i) {
					Object value = rs.getObject(i + 1);
					String name = rsmd.getColumnLabel(i + 1);
					name = name.toLowerCase();
					BeanUtils.setProperty(entity, name, value);
				}
				list.add(entity);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * Executes the SQL statement in this PreparedStatement object created by
	 * this Connection, the Connection is committed depends on the parameters by
	 * autoCommit The SQL must be an SQL Data Manipulation Language (DML) SQL,
	 * such as INSERT, UPDATE or DELETE; or an SQL statement that returns
	 * nothing, such as a DDL statement.
	 * 
	 * @param con
	 * @param autoCommit
	 * @param sql
	 * @param args
	 * @return
	 */
	static int update(Connection con, boolean autoCommit, String sql,
			Object... args) {
		try {
			con.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return update(sql, con, args);
	}

	/**
	 * Executes the SQL statement in this PreparedStatement object created by
	 * this Connection, the Connection is automatically committed The SQL must
	 * be an SQL Data Manipulation Language (DML) SQL, such as INSERT, UPDATE or
	 * DELETE; or an SQL statement that returns nothing, such as a DDL
	 * statement.
	 * 
	 * @param con
	 * @param sql
	 * @param args
	 * @return
	 */
	static int update(Connection con, String sql, Object... args) {
		return update(sql, con, args);
	}

	private static int update(String sql, Connection con, Object... args) {
		int rs = 0;
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			setSql(ps, args);
			// double i = 10/Math.random();
			rs = ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println(e.getMessage());
			}
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}

}
