package org.muque.mold.codes.ctx;

import org.muque.mold.codes.util.PathUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.util.Log4jConfigurer;

/**
 * 应用会话
 * 
 * @author jiangshujian
 * @version 1.0,2013.06.08
 */
public class AppContext {

	private static final String SPRING_FILENAME = "spring.xml";
	private static ApplicationContext springContext;

	/**
	 * 获取spring会话
	 * 
	 * @return
	 */
	public static synchronized ApplicationContext getSpringContext() {
		if (null == springContext) {
			Log4jConfigurer.setWorkingDirSystemProperty("log4j.WebApp.root");
			String path = PathUtil.getAppConfPath()
					.concat(PathUtil.getSeparator()).concat(SPRING_FILENAME);
			path = PathUtil.getPath(path);
			springContext = new FileSystemXmlApplicationContext(path);
		}
		return springContext;
	}

	/**
	 * 初始化应用
	 * 
	 */
	public static void init() {
		getSpringContext();
	}

	public static final int STATUS_RUNNING = 0;

	public static final int STATUS_EXIT = 1;

	/**
	 * 当前 应用状态 0:AppStatus.RUN:run 1:AppStatus.EXIT:exit
	 */
	public static int CurrentAppStatus = AppContext.STATUS_RUNNING;

}
