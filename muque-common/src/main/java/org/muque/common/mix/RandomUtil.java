package org.muque.common.mix;

import java.util.Random;

public class RandomUtil {
	/**
	 * 生成指定长度的数字序列
	 * 
	 * @param length
	 * @return
	 */
	public static String getNumbers(int length) {
		String[] str = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		StringBuffer buffer = new StringBuffer("");
		Random r = new Random();
		for (int i = 0; i < length; i++) {
			buffer.append(str[r.nextInt(str.length)]);
		}
		return buffer.toString();
	}

	/**
	 * 生成指定长度的字符序列
	 * 
	 * @param length
	 * @return
	 */
	public static String getChars(int length) {
		String[] str = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
				"l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
				"x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I",
				"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
				"V", "W", "X", "Y", "Z" };
		StringBuffer buffer = new StringBuffer("");
		Random r = new Random();
		for (int i = 0; i < length; i++) {
			buffer.append(str[r.nextInt(str.length)]);
		}
		return buffer.toString();
	}

	/**
	 * 生成指定长度的数字与字符序列
	 * 
	 * @param length
	 * @return
	 */
	public static String getNumbersAndChars(int length) {
		String[] strNumbers = { "0", "1", "2", "3", "4", "5", "6", "7", "8",
				"9" };
		String[] strChars = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
				"k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
				"w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H",
				"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
				"U", "V", "W", "X", "Y", "Z" };
		StringBuffer buffer = new StringBuffer("");
		Random r = new Random();
		int numberLength = r.nextInt(length - 2) + 1;
		for (int i = 0; i < length; i++) {
			if (i < numberLength) {
				buffer.append(strNumbers[r.nextInt(strNumbers.length)]);
			} else {
				buffer.append(strChars[r.nextInt(strChars.length)]);
			}
		}
		return buffer.toString();
	}

	/**
	 * 生成指定长度，不连号长度的数字序列
	 * 
	 * @param length
	 *            长度
	 * @param startNumber
	 *            开始数字序列
	 * @param nonSequentialLength
	 *            不连号长度
	 * @return
	 */
	public static Integer getNumberByRules(int length, int startNumber,
			int nonSequentialLength) {

		String[] except = { "0000", "1111", "2222", "3333", "4444", "5555",
				"6666", "7777", "8888", "9999" };
		Integer n = startNumber;
		boolean b = true;
		while (b) {
			boolean isContains = true;
			for (String s : except) {
				if (n.toString().contains(s)) {
					isContains = false;
					break;
				}
			}
			n = n + 1;
			if (isContains)
				b = false;
		}
		return n;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(getNumbers(6));
		System.out.println(getChars(6));
		System.out.println(getNumbersAndChars(6));

		System.out.println(getNumberByRules(7, 1000101, 4));
	}
}
