package org.muque.lark.common.exception;

import org.muque.lark.common.ErrorCode;

/**
 * 异常构建
 * <p>
 * <li>UniException</li>
 * </p>
 * 
 * @author sj.jiang
 *
 */
public class UniExceptionBuilder {

	/**
	 * 构建异常
	 * 
	 * @param errorCode
	 * @return
	 */
	public static UniException build(ErrorCode errorCode) {
		return new UniException(errorCode);
	}

	/**
	 * 构建异常
	 * 
	 * @param errorCode
	 * @param message
	 * @return
	 */
	public static UniException build(ErrorCode errorCode, String message) {
		return new UniException(errorCode, message);
	}

	/**
	 * 构建ApiException异常
	 * 
	 * @param errorCode
	 * @param cause
	 * @return
	 */
	public static UniException build(ErrorCode errorCode, Throwable cause) {
		return new UniException(errorCode, cause);
	}

	/**
	 * 构建异常
	 * 
	 * @param errorCode
	 * @param message
	 * @param cause
	 * @return
	 */
	public static UniException build(ErrorCode errorCode, String message, Throwable cause) {
		return new UniException(errorCode, message, cause);
	}

}
