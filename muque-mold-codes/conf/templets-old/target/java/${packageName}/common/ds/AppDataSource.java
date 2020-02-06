package ${packageName}.common.ds;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;

/**
 * 应用程序数据源，派生于dbcp BasicDataSource<br>
 * mysql 自动检查
 * 
 * @author jiangshujian
 * @version 1.0,2013.06.15
 */
public class AppDataSource extends BasicDataSource implements InitializingBean {

	private static final int DEFAULT_MAXACTIVE = 10;
	private static final int DEFAULT_INITIALSIZE = 1;
	private static final int DEFAULT_MAXWAIT = 60000;// ms
	private static final int DEFAULT_MAXIDLE = 10;
	private static final int DEFAULT_MINIDLE = 1;
	private static final int DEFAULT_TIMEBETWEENEVICTIONRUNSMILLIS = 60000;// ms
	private static final int DEFAULT_MINEVICTABLEIDLETIMEMILLIS = 60000;// ms
	private static final int DEFAULT_NUMTESTSPEREVICTIONRUN = 10;// eq MAXACTIVE
	private static final int DEFAULT_REMOVEABANDONEDTIMEOUT = 60;// s

	private static final int DB_TYPE_UNKONW = -1;
	private static final int DB_TYPE_MYSQL = 0;
	private static final int DB_TYPE_SQLSERVER = 1;
	private static final int DB_TYPE_ORACLE = 2;

	private static final String VALIDATION_QUERY_MYSQL = "select 1 from dual";
	private static final String VALIDATION_QUERY_SQLSERVER = "select 1";
	private static final String VALIDATION_QUERY_ORACLE = "select 1 from dual";

	public AppDataSource() {
		// default
		this.setMaxActive(DEFAULT_MAXACTIVE);
		this.setInitialSize(DEFAULT_INITIALSIZE);
		this.setMaxWait(DEFAULT_MAXWAIT);
		this.setMaxIdle(DEFAULT_MAXIDLE);
		this.setMinIdle(DEFAULT_MINIDLE);

		this.setTimeBetweenEvictionRunsMillis(DEFAULT_TIMEBETWEENEVICTIONRUNSMILLIS);
		this.setMinEvictableIdleTimeMillis(DEFAULT_MINEVICTABLEIDLETIMEMILLIS);
		this.setNumTestsPerEvictionRun(DEFAULT_NUMTESTSPEREVICTIONRUN);

		this.setRemoveAbandoned(true);
		this.setRemoveAbandonedTimeout(DEFAULT_REMOVEABANDONEDTIMEOUT);
	}

	@Override
	public synchronized void setDriverClassName(String driverClassName) {
		int dbType = this.getDbType(this.driverClassName);
		if (dbType == DB_TYPE_MYSQL) {
			this.setTestWhileIdle(true);// mysql default true
		}
		super.setDriverClassName(driverClassName);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (this.testWhileIdle) {
			if (StringUtils.isBlank(this.validationQuery)) {
				int dbType = this.getDbType(this.driverClassName);
				switch (dbType) {
				case DB_TYPE_MYSQL:
					this.setValidationQuery(VALIDATION_QUERY_MYSQL);
					break;
				case DB_TYPE_SQLSERVER:
					this.setValidationQuery(VALIDATION_QUERY_SQLSERVER);
					break;
				case DB_TYPE_ORACLE:
					this.setValidationQuery(VALIDATION_QUERY_ORACLE);
					break;
				default:
					break;
				}
			}
		}
		// default
		this.createDataSource();
	}

	/**
	 * get database type
	 * 
	 * @param driverClassName
	 * @return
	 */
	private int getDbType(String driverClassName) {
		if (StringUtils.isBlank(driverClassName)) {
			return DB_TYPE_UNKONW;
		}
		String temp = driverClassName.toUpperCase();

		if (temp.contains("MYSQL")) {
			return DB_TYPE_MYSQL;
		} else if (temp.contains("SQLSERVER")) {
			return DB_TYPE_SQLSERVER;
		} else if (temp.contains("ORACLE")) {
			return DB_TYPE_ORACLE;
		} else {
			return DB_TYPE_UNKONW;
		}
	}
}
