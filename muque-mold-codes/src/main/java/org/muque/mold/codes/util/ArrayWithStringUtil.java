package org.muque.mold.codes.util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 数组工具，主要和字符串之间的转换
 * 
 * @author sj,jiang
 * 
 */
public class ArrayWithStringUtil {
	/**
	 * 
	 * split the string to integer array with delimit ",".
	 * 
	 * @param ints
	 * @return
	 */
	public static Integer[] splitInt(String str) {
		return splitInt(str, ",");
	}

	/**
	 * 
	 * split the string to integer array with the delimit you support.
	 * 
	 * @param ints
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Integer[] splitInt(String str, String delim) {
		if ((str != null) && (str.length() > 0)) {
			List integers = new ArrayList();
			for (StringTokenizer tokenizer = new StringTokenizer(str.trim(),
					delim); tokenizer.hasMoreTokens();) {
				String s = tokenizer.nextToken();
				try {
					integers.add(Integer.valueOf(Integer.parseInt(s.trim())));
				} catch (Exception e) {
				}
			}
			Integer[] rc = new Integer[integers.size()];
			integers.toArray(rc);
			return rc;
		}
		return null;
	}

	/**
	 * 
	 * join with the delimit ","
	 * 
	 * @param ints
	 * @return
	 */
	public static String join(Integer[] ints) {
		return join(ints, ",");
	}

	/**
	 * 
	 * join with the delimit ","
	 * 
	 * @param ints
	 * @return
	 */
	public static String join(Object[] objs) {
		return join(objs, ",");
	}

	/**
	 * 
	 * join the integer array to string with the delimit you support.
	 * 
	 * @param ints
	 * @return
	 */
	public static String join(Integer[] ints, String delim) {
		if (ints != null) {
			StringBuilder builder = new StringBuilder();
			for (Integer anInt : ints) {
				builder.append(',');
				builder.append(anInt);
			}
			if (builder.length() > 0) {
				return builder.substring(1);
			}
			return builder.toString();
		}
		return null;
	}

	/**
	 * 
	 * join the object array to string with the delimit you support.
	 * 
	 * @param ints
	 * @return
	 */
	public static String join(Object[] objs, String delim) {
		if (objs != null) {
			StringBuilder builder = new StringBuilder();
			for (Object anObj : objs) {
				builder.append(',');
				builder.append(anObj);
			}
			if (builder.length() > 0) {
				return builder.substring(1);
			}
			return builder.toString();
		}
		return null;
	}
}
