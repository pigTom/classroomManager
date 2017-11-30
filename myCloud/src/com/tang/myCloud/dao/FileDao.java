package com.tang.myCloud.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tang.myCloud.bean.CloudFile;
import com.tang.myCloud.bean.PageInfo;
import com.tang.myCloud.utils.DBUtils;

@Component("fileDao")
public class FileDao {
	@Resource(name = "dbUtils")
	private DBUtils db;

	public void addFile(CloudFile file) {
/*		Transaction tr = db.getTransaction();
		String sql = " select cloudfile_seq.nextVal from dual";
		tr.start();// start transaction
		long id = db.queryLong(sql);
		file.setFileId(id);
		tr.close(); // end transaction
*/		
		db.add(file);
//		return id;
	}
	/**
	 * 从数据库得到该用户的文件信息,并用<code>PageInfo</code>包装起来
	 * @param id
	 * @return
	 */
	public PageInfo getFileInfo(long id){ // 一次性全部从数据中，取出影响性能
		String sql = " select * from cloudfile where userid = ? ";
		List<CloudFile> list = db.listQuery(CloudFile.class, sql, id);
		
		return new PageInfo(list);
	}
	public boolean deleteFile(long fileId){
		String sql = " delete from cloudfile where fileid = ? ";
		if( db.update(sql, fileId) > 0 )
			return true;
		return false;
	}
}
