package org.muque.rdb.springboot.starter;

/**
 * 常量
 * 
 * @author sj.jiang
 * @date 2017年7月14日 下午7:58:46
 * @version V1.0
 */
public interface Constant {
	/**
	 * 精确匹配切面
	 */
	public static final String DATASOURCE_POINTCUT_PATTERN = "target(org.muque.rdb.repository.BaseRepository)";
	/**
	 * 数据源配置KEY
	 */
	public static final String PROP_KEY_DATASOURCES = "rdb.datasources";
	/**
	 * 数据源配置缺省值
	 */
	public static final String DS_PREFIX = "rdb.datasource.";
	public static final String DS_PROP_FRT = "rdb.datasource.%s.%s";
	// eg:org.apache.commons.dbcp.BasicDataSource
	public static final String DS_CONNECTION_POOL_CLASS_FRT = "rdb.datasource.%s.connectionPoolClass";
	/**
	 * mybatis
	 */
	public static final String MYBATIS_MAPPER_LOCATIONS = "mybatis.mapperLocations";
	public static final String MYBATIS_TYPE_ALIASES_PACKAGE = "mybatis.typeAliasesPackage";

}
