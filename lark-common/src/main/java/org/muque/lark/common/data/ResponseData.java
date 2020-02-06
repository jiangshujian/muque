package org.muque.lark.common.data;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 响应数据
 * 
 * @author sj.jiang
 *
 * @param <T> 业务数据
 */
@Getter
@Setter
@ToString
@JsonSerialize(include = Inclusion.NON_NULL)
public class ResponseData<T> implements Serializable {
	private static final long serialVersionUID = -262292897193109350L;

	private int status;
	private String errorCode;
	private String errorMessage;
	private long elasped;
	private T data;

	public ResponseData() {
	}

	public ResponseData(int status) {
		this.status = status;
	}

	public ResponseData(int status, T data) {
		this.status = status;
		this.data = data;
	}

	public ResponseData(int status, String errorCode, String errorMessage) {
		this.status = status;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public ResponseData(int status, T data, String errorCode, String errorMessage) {
		this.status = status;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.data = data;
	}
}
