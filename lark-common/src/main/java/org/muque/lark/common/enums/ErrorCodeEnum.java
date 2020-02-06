package org.muque.lark.common.enums;

import org.muque.lark.common.ErrorCode;

import lombok.Getter;

/**
 * 公共错误码
 * 
 * @author sj.jiang
 * @date 2020年2月4日 下午8:32:44
 * @version V1.0
 */
@Getter
public enum ErrorCodeEnum implements ErrorCode {
	OK("0", "处理成功"),

	INVALID_CHARSET("1", "charset缺失或无效"),

	INVALID_PARTNER("2", "PARTNER缺失或无效"),

	INVALID_SIGN("3", "SIGN缺失或无效"),

	PERMISSION_DENY("4", "无权限访问"),

	INVALID_PARAM("5", "系统接收数据异常~"),

	NOT_LOGIN("6", "未登录"),

	DB_ERROR("7", "获取数据失败~"),

	HTTP_ERROR("8", "内部通信错误"),
	
	DATA_ERROR("9", "系统数据异常~"),

	SYSTEM_ERROR("10", "系统出现小异常~");

	private String code;

	private String message;

	private ErrorCodeEnum(String code, String message) {
		this.message = message;
	}

}
