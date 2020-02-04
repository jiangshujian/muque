package org.muque.common.collection;

import java.util.Collection;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

/**
 * 集合字符串互相转换
 * 
 * @author sj.jiang
 *
 */
public class Collections2StringUtil {

	private static final String DEFAULT_SPLIT = ",";

	/**
	 * 拼接节后元素字符串
	 * @param <T>
	 * @param src
	 * @param split
	 * @return
	 */
	public static <T> String concatItemToString(Collection<T> src) {
		return concatItemToString(src, DEFAULT_SPLIT, null);
	}

	/**
	 * 拼接节后元素字符串
	 * @param <T>
	 * @param src
	 * @param split
	 * @param wrap
	 * @return
	 */
	public static <T> String concatItemToString(Collection<T> src, String split, String wrap) {
		StringBuilder temp = new StringBuilder();
		for (T item : src) {

			temp.append((null != wrap) ? (wrap + item + wrap) : item).append(split);
		}
		String target = temp.toString();
		target = target.substring(0, target.length() - 1);
		return target;
	}

	/**
	 * 拼接节后元素【属性】字符串
	 * @param <T>
	 * @param src
	 * @param split
	 * @return
	 */
	public static <T> String concatItemPropertiesToString(Collection<T> src, Function<T, String> fun) {
		return concatItemPropertiesToString(src, DEFAULT_SPLIT, null, fun);
	}

	/**
	 * 拼接节后元素【属性】字符串
	 * @param <T>
	 * @param src
	 * @param split
	 * @param wrap
	 * @param fun
	 * @return
	 */
	public static <T> String concatItemPropertiesToString(Collection<T> src, String split, String wrap,
			Function<T, String> fun) {
		StringBuilder temp = new StringBuilder();
		for (T item : src) {
			temp.append((null != wrap) ? (wrap + fun.apply(item) + wrap) : fun.apply(item)).append(split);
		}
		String target = temp.toString();
		target = target.substring(0, target.length() - 1);
		return target;
	}

	/**
	 * 对list集合分区拼接
	 * 
	 * @param <T>
	 * @param src
	 * @param size
	 * @return
	 */
	public static <T> List<String> concatItemToStringByPartition(List<T> src, int size) {
		return concatItemToStringByPartition(src, DEFAULT_SPLIT, null, size);
	}

	/**
	 * 对list集合分区拼接
	 * 
	 * @param <T>
	 * @param src
	 * @param split
	 * @param wrap
	 * @param size
	 * @param callback
	 */
	public static <T> List<String> concatItemToStringByPartition(List<T> src, String split, String wrap, int size) {
		List<String> targetList = Lists.newArrayList();
		List<List<T>> results = Lists.partition(src, size);
		for (List<T> itemList : results) {
			targetList.add(concatItemToString(itemList, split, wrap));
		}
		return targetList;
	}

	public static void main(String[] args) {
		try {
			//测试代码
			System.out.println(concatItemToString(Lists.newArrayList(1, 2, 3, 4, 5, 6)));
			System.out.println(concatItemToString(Lists.newArrayList(1, 2, 3, 4, 5, 6), "|", "'"));
			System.out.println(
					concatItemPropertiesToString(Lists.newArrayList(1, 2, 3, 4, 5, 6), new Function<Integer, String>() {

						@Override
						public String apply(Integer input) {
							return String.valueOf(input.floatValue());
						}

					}));
			System.out.println(concatItemToStringByPartition(Lists.newArrayList(1, 2, 3, 4, 5, 6),10).size());
			System.out.println(concatItemToStringByPartition(Lists.newArrayList(1, 2, 3, 4, 5, 6),3).size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
