package org.muque.common.collection;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

/**
 * @author sj.jiang
 * @date 2020年2月4日 下午6:19:04
 * @version V1.0
 */
public class StringSplit2ListUtil {
	private static final String DEFAULT_SPLIT = ",";

	/**
	 * @param str
	 * @return
	 */
	public static List<String> toStringList(String str) {
		List<String> targetList = Lists.newArrayList();
		if (!StringUtils.isEmpty(str)) {
			String[] ary = str.split(DEFAULT_SPLIT);
			for (String item : ary) {
				targetList.add(item);
			}
		}
		return targetList;
	}

	/**
	 * @param str
	 * @return
	 */
	public static List<Integer> toIntegerList(String str) {
		List<Integer> targetList = Lists.newArrayList();
		if (!StringUtils.isEmpty(str)) {
			String[] ary = str.split(DEFAULT_SPLIT);
			for (String item : ary) {
				targetList.add(Integer.valueOf(item));
			}
		}
		return targetList;
	}

	/**
	 * @param str
	 * @return
	 */
	public static List<Long> toLongList(String str) {
		List<Long> targetList = Lists.newArrayList();
		if (!StringUtils.isEmpty(str)) {
			String[] ary = str.split(DEFAULT_SPLIT);
			for (String item : ary) {
				targetList.add(Long.valueOf(item));
			}
		}
		return targetList;
	}

	/**
	 * @param str
	 * @return
	 */
	public static <T> List<T> toTargetList(String str, Function<String, T> fun) {
		List<T> targetList = Lists.newArrayList();
		if (!StringUtils.isEmpty(str)) {
			String[] ary = str.split(DEFAULT_SPLIT);
			for (String item : ary) {
				targetList.add(fun.apply(item));
			}
		}
		return targetList;
	}
}
