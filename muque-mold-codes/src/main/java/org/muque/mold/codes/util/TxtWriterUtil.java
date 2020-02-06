package org.muque.mold.codes.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * 文本写工具类,default for app(1.* for web app,2.* for app)<br/>
 * 默认路径:"app\conf\configs\"
 * 
 * @author jiangshujian
 * @version 2.0,2013.06.09
 */
public class TxtWriterUtil {

	public static final String DEFAULT_ENCODE = "UTF-16";

	/**
	 * 文件写内容（完全覆盖），指定路径
	 * 
	 * @param fileDirectory
	 * @param fileName
	 * @param content
	 * @throws IOException
	 */
	public static void write(String filePath, String content)
			throws IOException {
		unExistDoCreate(filePath);// path is't exist create one
		FileOutputStream ostream = new FileOutputStream(filePath, false);
		Writer fw = new OutputStreamWriter(ostream);//, DEFAULT_ENCODE);
		// write content
		fw.write(content);
		fw.flush();
		fw.close();
	}

	/**
	 * 文件追加内容（指定路径）
	 * 
	 * @param fileDirectory
	 * @param fileName
	 * @param content
	 * @throws IOException
	 */
	public static void append(String filePath, String content)
			throws IOException {
		unExistDoCreate(filePath);// path is't exist create one
		FileOutputStream ostream = new FileOutputStream(filePath, true);
		Writer fw = new OutputStreamWriter(ostream);//, DEFAULT_ENCODE);
		// write content
		fw.write(content);

		fw.flush();
		fw.close();
	}

	/**
	 * 判断文件路径（目录或文件）是否存在，不存在则创建
	 * 
	 * @param path
	 * @throws IOException
	 */
	public static void unExistDoCreate(String path) throws IOException {
		File file = new File(path);
		if (!file.getParentFile().exists()) {
			mkdirs(file.getParentFile().getPath());
		}
	}

	/**
	 * 
	 * @param path
	 * @throws IOException
	 */
	public static void mkdirs(String path) throws IOException {
		File file = new File(path);
		if (!file.exists()) {
			if (!file.mkdirs()) {
				throw new IOException("failured make dir:" + path);
			}
		}
	}

	public static void main(String[] args) {
	}

}
