package org.muque.lark.common.collection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;

/**
 * 
 * @author sj.jiang
 * @date 2018年3月9日 下午6:51:58
 * @version V1.0
 */
public class CollectionCastUtil {

	/**
	 * 将String集合转换为Long集合
	 * 
	 * @param userDatas
	 * @return Set<Long>
	 */
	public static <T> Set<Long> getLongSet(Iterable<T> data) {
		Set<Long> longSet = new HashSet<Long>();
		for (T value : data) {
			longSet.add(Long.parseLong(String.valueOf(value)));
		}
		return longSet;
	}

	/**
	 * 将String集合转换为Integer集合
	 * 
	 * @param userDatas
	 * @return List<String>
	 */
	public static <T> Set<Integer> getIntegerSet(Iterable<T> data) {
		Set<Integer> integerSet = new HashSet<Integer>();
		for (T value : data) {
			integerSet.add(Integer.parseInt(String.valueOf(value)));
		}
		return integerSet;
	}

	/**
	 * 将String集合转换为Long集合
	 * 
	 * @param userDatas
	 * @return Set<Long>
	 */
	public static <T> List<Integer> castToIntegerList(Iterable<T> formList) {
		List<Integer> targetList = Lists.newArrayList();
		for (T value : formList) {
			targetList.add(Integer.parseInt(String.valueOf(value)));
		}
		return targetList;
	}

	/**
	 * 将String集合转换为Long集合
	 * 
	 * @param userDatas
	 * @return Set<Long>
	 */
	public static <T> List<Integer> castToIntegerList(T[] formList) {
		List<Integer> targetList = Lists.newArrayList();
		for (T value : formList) {
			targetList.add(Integer.parseInt(String.valueOf(value)));
		}
		return targetList;
	}

	/**
	 * 将String集合转换为Long集合
	 * 
	 * @param userDatas
	 * @return Set<Long>
	 */
	public static <T> List<Long> castToLongList(Iterable<T> formList) {
		List<Long> targetList = Lists.newArrayList();
		for (T value : formList) {
			targetList.add(Long.parseLong(String.valueOf(value)));
		}
		return targetList;
	}

	/**
	 * 将String集合转换为Long集合
	 * 
	 * @param userDatas
	 * @return Set<Long>
	 */
	public static <T> List<Long> castToLongList(T[] formList) {
		List<Long> targetList = Lists.newArrayList();
		for (T value : formList) {
			targetList.add(Long.parseLong(String.valueOf(value)));
		}
		return targetList;
	}
}
