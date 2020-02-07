package org.muque.lark.rdb.security;

/**
 * 字符串SQL防止注入攻击
 * 
 * @author sj.jiang
 * @date 2020年2月7日 上午10:47:43
 * @version V1.0
 */
public class SQLInjectionHandler {
	/**
	 * 处理
	 * @param str
	 * @return
	 */
	public static String handle(String str) {
		if (null == str) {
			return "";
		} else {
			return str.replaceAll(".*([';]+|(--)+).*", "");
		}
	}
}
