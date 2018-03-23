package com.tang.myCloud.bean;

import java.util.List;

public class PageInfo extends AbstractPageInfo<CloudFile>{
	public PageInfo() {
		// TODO Auto-generated constructor stub
	}
	public PageInfo(List<CloudFile> file) {
		super(file);
	}
	public void deleteById(long id){
		System.out.println("pageinfo :"+id);
		List<CloudFile> data = this.getData();
		if( data==null || data.size()==0)
			return;
		for( CloudFile file : data){
			if( file.getFileId() == id)
				data.remove(file);
		}
	}
}
