package org.muque.common.enums;

import lombok.Getter;

/**
 * ResponseData状态枚举
 * 
 * @author sj.jiang
 *
 */
@Getter
public enum StatusEnum {
	SUCCESS(1), FAILURE(0);

	private int value;

	private StatusEnum(int value) {
		this.value = value;
	}
}
