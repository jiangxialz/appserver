package com.maker.app.common;

public class Page {
	/*当前页数 默认为1*/
	private int currentPage  = 1;
	/*页面记录数 默认为10*/
	private int pageSize  = 10;
	/*总页面数*/
	private int totalPage = 0;
	/*总记录数*/
	private int totalCount = 0;
	/*开始检索的下标*/
	private int indexNo;
	
	public Page(){
	}
	/**
	 * @param currentPage
	 * @param pageSize
	 */
	public Page(int currentPage, int pageSize ){
		if(currentPage<1){
			this.currentPage = 1;
		}else{
			this.currentPage = currentPage;
		}
		
		this.pageSize = pageSize;
	}

	
	/**
	 * 总页面数计算.
	 * @return int.
	 */
	public int getTotalPage() {
		if(totalCount%pageSize==0){
			totalPage=totalCount/pageSize;
		}else{
			totalPage=totalCount/pageSize+1;
		}
		if(totalPage==0)
			totalPage = 1;
		return totalPage;
	}
	
	
	
	/**数据库的开始查询数据*/
	public int getIndexNo() {
		this.indexNo = (this.currentPage - 1) * this.pageSize;
		return indexNo;
	}
	
	
	/** 根据数据下标找到在第几页 */
	public  int getCurrentPageByIndex(int index){
		
		if(index==0)
			return 1;
		
		int findPage = 0;
		if(index%pageSize==0){
			findPage=index/pageSize;
		}else{
			findPage=index/pageSize+1;
		}
		return findPage;
		
	}
	
	public int getPageSize() {
		return pageSize;
	}
	/**设置每次大小*/
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**总记录数*/
	public int getTotalCount() {
		return totalCount;
	}
	/**设置总记录数*/
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	
/*	public static void main(String[] args) {
		Page page = new Page(1,10);
		page.setTotalCount(100);
		
		System.out.println(page.getCurrentPageByIndex(51));
	}*/
	
}
