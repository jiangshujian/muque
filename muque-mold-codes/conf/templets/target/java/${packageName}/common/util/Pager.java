package ${packageName}.common.util;

import java.io.Serializable;

public class Pager implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int pageIndex;

	private int pageCount;

	private int pageSize = 100;

	private int totalCount;

	private int startRownum;

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
		this.pageCount = (this.totalCount == 0) ? 0 : (mod == 0 ? divide
				: (divide + 1));
		return pageCount;
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
		this.startRownum = this.pageIndex == 0 ? 0
				: (this.pageSize * this.pageIndex);
		return startRownum;
	}

	public String getSortSql() {
		return sortSql;
	}

	public void setSortSql(String sortSql) {
		this.sortSql = sortSql;
	}

}
