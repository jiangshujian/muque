package org.muque.lark.common.mix;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * 格式化工具
 * 
 * @author sj.jiang
 * @date 2016年12月8日 下午5:41:56
 * @version V1.0
 */
public class BigDecimalUtil {

	private static final String DECIMAL_FORMAT_PATTERN = "###,###0.00";
	private static final String DECIMAL_FORMAT_PATTERN_INTEGER = "######0";
	private static final String DECIMAL_FORMAT_PATTERN_2_POINT = "######0.00";

	/**
	 * 格式化金额
	 * 
	 * @param source
	 * @return
	 */
	public static String format(BigDecimal source) {
		DecimalFormat df = new DecimalFormat(DECIMAL_FORMAT_PATTERN);
		df.setRoundingMode(RoundingMode.DOWN);
		return df.format(source);
	}

	/**
	 * 格式化金额(只保留整数部分)
	 * 
	 * @param source
	 * @return
	 */
	public static String formatInteger(BigDecimal source) {
		DecimalFormat df = new DecimalFormat(DECIMAL_FORMAT_PATTERN_INTEGER);
		df.setRoundingMode(RoundingMode.DOWN);
		return df.format(source);
	}
	
	/**
	 * 格式化金额(只保留2位小数)
	 * 
	 * @param source
	 * @return
	 */
	public static String format2Point(BigDecimal source) {
		DecimalFormat df = new DecimalFormat(DECIMAL_FORMAT_PATTERN_2_POINT);
		df.setRoundingMode(RoundingMode.DOWN);
		return df.format(source);
	}
}
