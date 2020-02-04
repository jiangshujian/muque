package org.muque.common.exception;

import org.muque.common.ErrorCode;

import lombok.Getter;
import lombok.Setter;

/**
 * 统一异常
 * <p>
 * 具有错误码的非受检异常
 */
@Getter
@Setter
public class UniException extends RuntimeException {
	private static final long serialVersionUID = 3376536004397382434L;
	private final ErrorCode errorCode;

	public UniException(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public UniException(ErrorCode errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public UniException(ErrorCode errorCode, Throwable cause) {
		super(errorCode.getMessage(), cause);
		this.errorCode = errorCode;
	}

	public UniException(ErrorCode errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

}
