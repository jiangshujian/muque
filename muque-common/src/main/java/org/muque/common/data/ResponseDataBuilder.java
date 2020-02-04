package org.muque.common.data;

import org.apache.commons.lang3.StringUtils;
import org.muque.common.ErrorCode;
import org.muque.common.enums.StatusEnum;

/**
 * 响应数据构建
 * 
 * @author sj.jiang
 */
public class ResponseDataBuilder {
	/**
	 * 创建成功响应数据
	 * 
	 * @return {@code ReturnData<T>}
	 */
	public static <T> ResponseData<T> newSuccess() {
		return new ResponseData<T>(StatusEnum.SUCCESS.getValue());
	}

	/**
	 * 创建成功响应数据
	 * 
	 * @param data 实体数据
	 * @return {@code ReturnData<T>}
	 */
	public static <T> ResponseData<T> newSuccess(T data) {
		return new ResponseData<T>(StatusEnum.SUCCESS.getValue(), data);
	}

	/**
	 * 创建失败响应数据
	 * 
	 * @param errorCode 错误码
	 * @return {@code ReturnData<T>}
	 */
	public static <T> ResponseData<T> newFailure(ErrorCode errorCode) {
		return new ResponseData<>(StatusEnum.FAILURE.getValue(), errorCode.getCode(), errorCode.getMessage());
	}

	/**
	 * 创建失败响应数据
	 * 
	 * @param <T>
	 * @param data
	 * @param errorCode
	 * @return
	 */
	public static <T> ResponseData<T> newFailure(T data, ErrorCode errorCode) {
		return new ResponseData<>(StatusEnum.FAILURE.getValue(), data, errorCode.getCode(), errorCode.getMessage());
	}

	/**
	 * 创建失败响应数据
	 * 
	 * @param errorCode    错误码
	 * @param errorMessage 错误信息
	 * @return
	 */
	public static <T> ResponseData<T> newFailure(ErrorCode errorCode, String errorMessage) {
		return new ResponseData<>(StatusEnum.FAILURE.getValue(), errorCode.getCode(),
				StringUtils.isEmpty(errorMessage) ? errorCode.getMessage()
						: errorCode.getMessage().concat(":").concat(errorMessage));
	}
}
