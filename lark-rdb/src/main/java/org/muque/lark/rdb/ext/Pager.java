package org.muque.lark.rdb.ext;

import org.muque.lark.rdb.security.SQLInjectionHandler;

import lombok.Getter;
import lombok.Setter;

/**
 * 分页排序
 * 
 * @author sj.jiang
 * @date 2020年2月7日 上午10:31:13
 * @version V1.0
 */
@Getter
@Setter
public class Pager {
	/**
	 * 每页数据起始偏移量
	 */
	int offset;
	/**
	 * 一页条目
	 */
	int limit;
	/**
	 * 是否支持排序：default false
	 */
	boolean sortable = false;
	/**
	 * 排序顺序：ASC / DESC
	 */
	String sortOrder = "ASC";
	/**
	 * 排序列名
	 */
	String sortColumn;

	public String getSortOrder() {
		return SQLInjectionHandler.handle(sortOrder);
	}

	public String getSortColumn() {
		return SQLInjectionHandler.handle(sortColumn);
	}

}
