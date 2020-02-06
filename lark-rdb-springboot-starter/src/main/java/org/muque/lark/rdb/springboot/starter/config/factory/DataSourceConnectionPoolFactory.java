package org.muque.lark.rdb.springboot.starter.config.factory;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.muque.lark.rdb.springboot.starter.Constant;
import org.muque.lark.rdb.springboot.starter.config.exception.DataSourceConfigException;
import org.springframework.core.env.Environment;

/**
 * @author sj.jiang
 * @date 2020年2月5日 下午6:37:41
 * @version V1.0
 */
public class DataSourceConnectionPoolFactory {
	/**
	 * 构建数据源(连接池)
	 * 
	 * @param dsName
	 * @param env
	 * @return
	 */
	public static DataSource newDataSourceWithConnectionPool(final String dsName, final Environment env) {
		Class<?> clazz;
		Object obj;
		try {
			clazz = DataSourceConnectionPoolFactory.class.getClassLoader()
					.loadClass(String.format(Constant.DS_CONNECTION_POOL_CLASS_FRT, dsName));
			obj = clazz.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			throw new DataSourceConfigException(
					String.format("datasource name=%s,fail load connection pool,error=%s", dsName, e));
		}
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			String value = env.getProperty(String.format(Constant.DS_PROP_FRT, dsName, field.getName()));
			if (!StringUtils.isBlank(value)) {
				try {
					field.setAccessible(true);
					field.set(obj, getTypedValue(field.getType(), value));
				} catch (IllegalArgumentException e) {
					throw new DataSourceConfigException(
							String.format("datasource name=%s,field=%s,error=%s", dsName, field.getName(), e));
				} catch (IllegalAccessException e) {
					throw new DataSourceConfigException(
							String.format("datasource name=%s,field=%s,error=%s", dsName, field.getName(), e));
				}
			}
		}
		return (DataSource) obj;
	}

	/**
	 * 
	 * @param type
	 * @param value
	 * @return
	 */
	private static Object getTypedValue(Type type, String value) {
		if (type == char.class) {
			return value.charAt(0);
		} else if (type == Boolean.class) {
			return Boolean.valueOf(value);
		} else if (type == Byte.class) {
			return Byte.valueOf(value);
		} else if (type == Short.class) {
			return Short.valueOf(value);
		} else if (type == Integer.class) {
			return Integer.valueOf(value);
		} else if (type == Long.class) {
			return Long.valueOf(value);
		} else if (type == Float.class) {
			return Float.valueOf(value);
		} else if (type == Double.class) {
			return Double.valueOf(value);
		} else {
			return value;
		}

	}
}
