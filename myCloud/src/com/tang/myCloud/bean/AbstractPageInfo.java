package com.tang.myCloud.bean;

import java.util.List;

public class AbstractPageInfo <T>{
	private int currPage; // 当前页数
	private int totalPage; // 总页数
	// private int totalNum; // 数据总行数
	private int evePageNum = 6; // 每页显示行数
	private int realPageNum; // 每页真正能显示的行数
	private List<T> data; // data 中将存放所有从数据库查询到的数据
	public AbstractPageInfo() {
		// TODO Auto-generated constructor stub
	}
	public AbstractPageInfo(List<T> data) {
		this.setData(data);
	}

	public void setRealPageNum() {
		// 假设数据为空
		if( totalPage == 0){
			realPageNum = 0;
			return;
		}
		
		if( currPage > totalPage)
			currPage = 1;
		
		// 假设当前页为最后一页
		 if( currPage == totalPage){
			realPageNum = (data.size() - (currPage-1)*evePageNum) % (evePageNum+1);
		}else
			realPageNum = evePageNum;
		
	}

	private void setTotalPage() {
		if( data == null)
			totalPage = 0;
		else
			totalPage = (int) Math.ceil((double) data.size() / evePageNum);
	}

	/**
	 * 当前显示页面一旦改变，真正能显示的行数也可能会改变
	 * 
	 * @param currPage
	 */
	public void setCurrPage(int currPage) {
		// 假设数据为空
		if( totalPage == 0){
			this.currPage = 1;
			return;
		}
		// 假设当前页为最后一页
		if (currPage == totalPage) {
			this.currPage = currPage;
			setRealPageNum();
			return;
		}
		
		if (currPage < totalPage && currPage > 0){
			this.currPage = currPage;
			this.setRealPageNum();
		}
		
		
		// if other situation do nothing
	}

	public void setEvePageNum(int evePageNum) {
		if( evePageNum > 0){
			
			this.evePageNum = evePageNum;
			
			// if evePageNum changes that totalPage will be changed
			setTotalPage();
			
			// reset current page to 1
			currPage = 1;
			
			// reset real pagenum
			setRealPageNum();
		}
	}

	public void setData(List<T> data) {
		this.data = data;
		setTotalPage(); // 得到总页数
		currPage = 1; // 当前页数默认为1
		setRealPageNum();//
	}

	/**
	 * 得到当前页面的全部数据 数据的由<code>evePageNum</code>指定 实际的显示行数应该小于或等
	 * <code>evePageNum</code>
	 * 
	 * @return
	 */
	public List<T> getData() {
		if( data == null)
			return null;
		// 当前页面开始的下标 ，startIndex+realPageNum 为当前页面结束时的下标
		this.setTotalPage();
		this.setRealPageNum();
		int startIndex = (currPage - 1) * evePageNum;
		return data.subList(startIndex, startIndex + realPageNum);
	}

	public int getTotalPage() {
		return this.totalPage;
	}

	public int getCurrPage() {
		return this.currPage;
	}

	@Override
	public String toString() {
		return "PageInfo [currPage=" + currPage + ", totalPage=" + totalPage
				+ ", evePageNum=" + evePageNum + ", realPageNum=" + realPageNum
				+ ", data=" + data + "]";
	}
}
