package org.muque.lark.rdb.ext;

import org.muque.lark.rdb.security.SQLInjectionHandler;

import lombok.Setter;

/**
 * 
 * @author sj.jiang
 * @date 2020年2月7日 上午10:53:47
 * @version V1.0
 */
@Setter
public class SumSingleColumn {
	/**
	 * 汇总的列名
	 */
	String name;

	public String getName() {
		// 防SQL注入处理
		return SQLInjectionHandler.handle(name);
	}

}
