package org.muque.lark.common.data;

import java.io.Serializable;

/**
 * just for mysql
 * 
 * @author: sj.jiang
 * @date: 2019年2月22日 下午6:41:40
 */
public class Pager implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int pageIndex;

	// private int pageCount;

	private int pageSize = 100;

	private int totalCount;

	// private int startRownum;

	private String sortSql;

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageCount() {
		int mod = this.totalCount % this.pageSize;
		int divide = this.totalCount / this.pageSize;
		return (this.totalCount == 0) ? 0 : (mod == 0 ? divide : (divide + 1));
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getStartRownum() {
		// just for mysql
		return this.pageIndex == 0 ? 0 : (this.pageSize * this.pageIndex);
	}

	public String getSortSql() {
		return sortSql;
	}

	public void setSortSql(String sortSql) {
		this.sortSql = sortSql;
	}

}
