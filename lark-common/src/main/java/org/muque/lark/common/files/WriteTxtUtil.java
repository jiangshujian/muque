package org.muque.lark.common.files;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.Iterator;

import lombok.extern.slf4j.Slf4j;

/**
 * 文本写工具
 * 
 * @author: sj.jiang
 * @date: 2019年2月22日 下午7:10:16
 */
@Slf4j
public class WriteTxtUtil {

	private static final int DEFAULT_FLUSH_LIMIT = 100;
	public static final String DEFAULT_ENCODE = "utf-8";
	public static final String NEW_LINE = "\r\n";
	private static final String FORMAT_ERROR = "write file[%s] error.";

	/**
	 * 文件写内容（完全覆盖）
	 * 
	 * @param filePath
	 * @param content
	 * @throws IOException
	 */
	public static void writeAsNew(String filePath, String content) throws IOException {
		writeAsNew(filePath, content, DEFAULT_ENCODE);
	}

	/**
	 * 文件写内容（完全覆盖）
	 * 
	 * @param filePath
	 * @param content
	 * @throws IOException
	 */
	public static void writeAsNew(String filePath, String content, String charset) throws IOException {
		unExistDoCreate(filePath);// path is't exist create one
		try (Writer fw = new OutputStreamWriter(//
				new FileOutputStream(filePath, false), charset)) {
			fw.write(content);
			fw.flush();
		} catch (IOException e) {
			log.error(String.format(FORMAT_ERROR, filePath), e);
		}
	}

	/**
	 * 文件追加内容（指定路径）
	 * 
	 * @param filePath
	 * @param content
	 * @throws IOException
	 */
	public static void append(String filePath, String content) throws IOException {
		append(filePath, content, DEFAULT_ENCODE);
	}

	/**
	 * 
	 * @param filePath
	 * @param content
	 * @param charset
	 * @throws IOException
	 */
	public static void append(String filePath, String content, String charset) throws IOException {
		unExistDoCreate(filePath);// path is't exist create one
		try (Writer fw = new OutputStreamWriter(//
				new FileOutputStream(filePath, true), charset)) {
			fw.write(content);
			fw.flush();
		} catch (IOException e) {
			log.error(String.format(FORMAT_ERROR, filePath), e);
		}
	}

	/**
	 * 高性能写入文本
	 * 
	 * @param filePath
	 * @param collection
	 * @param func
	 * @throws IOException
	 */
	public static <T> void append(String filePath, Collection<T> collection, WriteFunction<T> func) throws IOException {
		append(filePath, collection, func, DEFAULT_FLUSH_LIMIT, DEFAULT_ENCODE);
	}

	/**
	 * 高性能写入文本
	 * 
	 * @param filePath
	 * @param collection
	 * @param func
	 * @param charset
	 * @throws IOException
	 */
	public static <T> void append(String filePath, Collection<T> collection, WriteFunction<T> func, int flushLimit,
			String charset) throws IOException {
		unExistDoCreate(filePath);// path is't exist create one
		int step = 0;
		try (Writer fw = new OutputStreamWriter(//
				new FileOutputStream(filePath, true), charset)) {
			// write content
			Iterator<T> iterator = collection.iterator();
			while (iterator.hasNext()) {
				fw.write(func.apply(iterator.next()) + NEW_LINE);
				step++;
				if (step % flushLimit == 0) {// flushLimit行刷新一次文件，提高性能
					fw.flush();
				}
			}
			fw.flush();
		} catch (IOException e) {

		}
	}

	/**
	 * 文件存在删除
	 * 
	 * @param filePath
	 * @return
	 */
	public static void existDoDelete(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {
			boolean result = file.delete();
			log.warn("existing file:[{}],delete[{}]!", filePath, (result ? "success" : "failure"));
		}
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
			if (!file.getParentFile().mkdir()) {
				throw new IOException("failured make dir:" + path);
			}
		}
	}

}
