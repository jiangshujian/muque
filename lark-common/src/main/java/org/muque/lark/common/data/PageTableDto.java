package org.muque.lark.common.data;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 目前基于bootstrap dataTable格式
 * 
 * @author sj.jiang
 * @date 2020年2月7日 下午5:30:00
 * @version V1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageTableDto<T> {
	private int total;
	private List<T> rows;
	/**
	 * 汇总金额（可选）
	 */
	private BigDecimal sumMoney;
	/**
	 * 预留扩展信息（可选）
	 */
	private String extStr;
}