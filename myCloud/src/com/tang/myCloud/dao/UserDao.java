package com.tang.myCloud.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tang.myCloud.bean.CloudUser;
import com.tang.myCloud.utils.DBUtils;

@Component("userDao")
public class UserDao {

	private DBUtils db;

	@Resource(name = "dbUtils")
	public void setDb(DBUtils db) {
		this.db = db;
	}

	/**
	 * get id of the user in database
	 * 
	 * @param user
	 * @return
	 */
	public long login(CloudUser user) {
		String sql = " select userid from clouduser where username = ? and userpassword = ? ";
		return db.queryLong(sql, user.getUserName(), user.getUserPassword());

	}

	public void addUser(CloudUser user) {
		db.add(user);
	}

	public void delete(CloudUser user) {
		String sql = " delete from clouduser where userid = ? ";
		db.update(sql, user.getUserId());
	}
}
