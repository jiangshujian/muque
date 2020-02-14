/**   
 * @Title: PathUtil.java 
 * @Package com.dang.rocket.core.util 
 * @Description: 路径工具，提供App项目、Web项目获取一些路径方法。
 * @author wmjs  
 * @date 2014-8-1 上午11:27:01 
 * @version V1.0   
 */
package org.muque.lark.common.mix;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 路径工具，提供java项目一些获取的路径方法。
 * 
 * @author sj.jiang
 * @date 2018年2月28日 下午3:15:40
 * @version V1.0
 */
public class PathUtil {

	private static final Logger log = LoggerFactory.getLogger(PathUtil.class);

	/**
	 * 获取App运行所在路径。 相对于getAppWorkPath()，该方法获得是原生的路径，没有任何后期处理。
	 * 
	 * @return
	 */
	public static String getAppPath() {
		String path = "";
		try {
			File directory = new File("");
			path = directory.getCanonicalPath();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return path;
	}

	/**
	 * 获取classpath物理路径<br>
	 * Thread.currentThread() .getContextClassLoader().getResource("").getPath()
	 */
	public static String getClassPath() {
		return Thread.currentThread().getContextClassLoader().getResource("").getPath();
	}

	/**
	 * linux path 处理。<br>
	 * 将路径中的"\"统一换成"/"。
	 * 
	 * @param path
	 * @return
	 */
	public static String formatPath(String path) {
		String target = path;
		target = target.replaceAll("\\\\", "/");
		target = target.replaceAll("\\/", "//");
		target = target.replaceAll("(\\/){4,}", "//");
		return target;
	}

	/**
	 * 
	 * 获取路径分隔符syetem property:file.separator（兼容windows和linux）。
	 * 
	 * @return
	 */
	public static String separator() {
		String se = System.getProperty("file.separator");
		return StringUtils.isEmpty(se) ? "\\" : se;
	}

	public static PathUtilBuilder builder() {
		return new PathUtilBuilder();
	}

	/**
	 * 路径工具
	 * 
	 * @author sj.jiang
	 * @date 2018年2月28日 下午3:19:02
	 * @version V1.0
	 */
	public static class PathUtilBuilder {
		private StringBuilder sb = new StringBuilder();

		public PathUtilBuilder append(String source) {
			if (null != source)
				sb.append(source);
			return this;
		}

		public PathUtilBuilder separator() {
			sb.append(PathUtil.separator());
			return this;
		}

		public String build() {
			return PathUtil.formatPath(sb.toString());
		}
	}
}
