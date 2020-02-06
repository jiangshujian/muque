package org.muque.mold.codes.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author jiangshujian
 * @version 2.0,2013.06.09
 */
public class TxtReaderUtil {

	/**
	 * 读取文本中第一行（指定路径）
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static String readFirstLine(String filePath) throws IOException {
		FileReader reader = new FileReader(filePath);
		BufferedReader buffReader = new BufferedReader(reader);
		String sLine = "";
		sLine = buffReader.readLine();
		buffReader.close();
		reader.close();
		return sLine;
	}

	/**
	 * 读取文本中第一行（指定路径）
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static String readToEnd(String filePath) throws IOException {
		FileReader reader = new FileReader(filePath);
		BufferedReader buffReader = new BufferedReader(reader);
		int readC = buffReader.read();
		StringBuilder sb = new StringBuilder();
		while (-1 != readC) {
			char s = (char) readC;
			sb.append(s);
			readC = buffReader.read();
		}
		buffReader.close();
		reader.close();
		return sb.toString();
	}

}
