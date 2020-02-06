package org.muque.lark.rdb.springboot.starter.config.exception;

/**
 * 
 * @author sj.jiang
 * @date 2020年2月5日 上午10:06:54
 * @version V1.0
 */
public class DataSourceConfigException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataSourceConfigException(String msg) {
		super(msg);
	}
}
