package org.muque.mold.codes.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.muque.mold.codes.util.ConvertUtil;
import org.muque.mold.codes.util.PathUtil;

/**
 * 应用属性文件工具。<br>
 * 你的配置文件路径必须是：“your app\conf\configs\app.properties”<br>
 * your config must locate in:"your app\conf\configs\app.properties"
 * 
 * @author jiangshujian
 * @version 1.0,2013.06.05
 */
public class FragProperties {

	/**
	 * the path of config files
	 */
	public static final String CONFIGS_PATH = PathUtil.getAppConfPath()
			.concat(PathUtil.getSeparator()).concat("templets")
			.concat(PathUtil.getSeparator());
	/**
	 * the path of config files
	 */
	public static final String DEFAULT_PROPERTIES = "frag.properties";

	private static final Log log = LogFactory.getLog(FragProperties.class);

	private static HashMap<String, String> hashMap;
	private static FragProperties configsUtil = new FragProperties();

	static {
		hashMap = new HashMap<String, String>();
		hashMap.put(DEFAULT_PROPERTIES, DEFAULT_PROPERTIES);
	}

	private FragProperties() {

	}

	private Properties getProperties(String fileAlias) {
		Properties prp = new Properties();
		try {
			prp.load(new FileInputStream(CONFIGS_PATH.concat((String) hashMap
					.get(fileAlias))));
		} catch (IOException e) {
			log.error(e);//
		} catch (Exception ex) {
			log.error(ex);//
		}
		return prp;
	}

	/**
	 * get the ConfigsUtil instance
	 * 
	 * @return
	 */
	public static FragProperties newInstance() {
		return configsUtil;
	}

	/**
	 * @param key
	 *            the key in properties file
	 * @return String
	 */
	public String getStringValue(String key) {
		Properties prp = getProperties(DEFAULT_PROPERTIES);
		return prp.getProperty(key);
	}

	/**
	 * @param key
	 *            the key in properties file
	 * @return int
	 */
	public int getIntValue(String key) {
		Properties prp = getProperties(DEFAULT_PROPERTIES);
		return ConvertUtil.toInt(prp.getProperty(key));
	}

	/**
	 * @param key
	 *            the key in properties file
	 * @return long
	 */
	public long getLongValue(String key) {
		Properties prp = getProperties(DEFAULT_PROPERTIES);
		return ConvertUtil.toLong(prp.getProperty(key));
	}

	/**
	 * @param key
	 *            the key in properties file
	 * @return boolean
	 */
	public boolean getBooleanValue(String key) {
		Properties prp = getProperties(DEFAULT_PROPERTIES);
		return ConvertUtil.toBoolean(prp.getProperty(key));
	}

	/**
	 * Convert to Date,only support the format "yyyy-MM-dd HH:mm:ss"
	 * 
	 * @param key
	 *            the key in properties file
	 * @return Date
	 */
	public Date getDateValue(String key) {
		Properties prp = getProperties(DEFAULT_PROPERTIES);
		return ConvertUtil.toDate(prp.getProperty(key));
	}

	/**
	 * 
	 * 
	 * @param key
	 *            the key in properties file
	 * @return Date
	 */
	/**
	 * 
	 * Convert to Date by your support date format string, such as
	 * "yyyy-MM-dd HH:mm:ss" "yyyyMMddHHmmss" "yyyy/MM/dd HH:mm:ss" etc.
	 * 
	 * @param key
	 *            the key in properties file
	 * @param dateFormatStr
	 *            "yyyy-MM-dd HH:mm:ss" "yyyyMMddHHmmss" "yyyy/MM/dd HH:mm:ss"
	 *            etc
	 * @return Date
	 */
	public Date getDateValue(String key, String dateFormatStr) {
		Properties prp = getProperties(DEFAULT_PROPERTIES);
		return ConvertUtil.toDate(prp.getProperty(key), dateFormatStr);
	}

}