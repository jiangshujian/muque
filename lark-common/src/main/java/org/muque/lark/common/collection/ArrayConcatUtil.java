package org.muque.lark.common.collection;

import java.util.Arrays;

/**
 * 
 * @author sj.jiang
 * @date 2018年1月16日 下午3:21:13
 * @version V1.0
 */
public class ArrayConcatUtil {
	/**
	 * 合并多个数组
	 * 
	 * @param first
	 * @param appended
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] concatAll(T[] first, T[]... appended) {
		int totalLength = first.length;
		for (T[] array : appended) {
			totalLength += array.length;
		}
		T[] result = Arrays.copyOf(first, totalLength);
		int offset = first.length;
		for (T[] array : appended) {
			System.arraycopy(array, 0, result, offset, array.length);
			offset += array.length;
		}
		return result;
	}
}
