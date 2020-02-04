package org.muque.common.files;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author: sj.jiang
 * @date: 2019年2月22日 下午6:43:47
 */
@Slf4j
public class ReadTxtUtil {

	/**
	 * 读取文本中第一行,异常返回null
	 * 
	 * @param filePath
	 * @return
	 */
	public static String readFirstLine(String filePath) {
		try (BufferedReader buffReader = new BufferedReader(new FileReader(filePath))) {
			return buffReader.readLine();
		} catch (IOException e) {
			log.error("read file:[" + filePath + "]exception", e);
			return null;
		}
	}

	/**
	 * 读取文本到字符串集合,异常返回空集合
	 * 
	 * @param filePath
	 * @return
	 */
	public static List<String> readToStringList(String filePath) {
		List<String> list = Lists.newArrayList();
		try (BufferedReader buffReader = new BufferedReader(new FileReader(filePath))) {
			String sLine = "";
			while (null != sLine) {
				sLine = buffReader.readLine();
				if (StringUtils.isNotBlank(sLine)) {
					list.add(sLine);
				}
			}
		} catch (IOException e) {
			log.error("read file:[" + filePath + "]exception", e);
		}
		return list;
	}

	/**
	 * 读取文本到指定集合,异常返回空集合
	 * 
	 * @param filePath
	 * @param func
	 * @return
	 */
	public static <T> List<T> readToList(String filePath, ReadFunction<T> func) {
		List<T> list = Lists.newArrayList();
		try (BufferedReader buffReader = new BufferedReader(new FileReader(filePath))) {
			String sLine = "";
			while (null != sLine) {
				sLine = buffReader.readLine();
				if (StringUtils.isNotBlank(sLine)) {
					list.add(func.apply(sLine));
				}
			}
		} catch (IOException e) {
			log.error("read file:[" + filePath + "]exception", e);
		}
		return list;
	}

	/**
	 * 读取文本中第一行,异常返回null
	 * 
	 * @param filePath
	 * @return
	 */
	public static String readFirstLine(InputStream inStream) {
		try (BufferedReader buffReader = new BufferedReader(new InputStreamReader(inStream))) {
			return buffReader.readLine();
		} catch (IOException e) {
			log.error("read inStream exception", e);
			return null;
		}
	}

	/**
	 * 读取流文件到指定集合,异常返回空集合
	 * 
	 * @param inStream
	 * @param func
	 * @return
	 */
	public static <T> List<T> readToList(InputStream inStream, ReadFunction<T> func) {
		List<T> list = Lists.newArrayList();
		try (BufferedReader buffReader = new BufferedReader(new InputStreamReader(inStream))) {
			String sLine = "";
			while (null != sLine) {
				sLine = buffReader.readLine();
				if (StringUtils.isNotBlank(sLine)) {
					list.add(func.apply(sLine));
				}
			}
		} catch (IOException e) {
			log.error("read inStream exception", e);
		}
		return list;
	}
}
