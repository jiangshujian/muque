package ${packageName}.common.dao;

import java.util.HashMap;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * 多数据源表Dao工厂类
 * （增加了schema）
 * @author jiangshujian
 * @version 1.1,2013.6.24
 * 
 */
public class MultiDbDaoFactory implements InitializingBean {
	private HashMap<String, DataSource> dataSourceMap;

	private HashMap<String, BaseDao> daoMap;

	private SqlMapClient sqlMapClient;

	private String daoClassName;

	/**
	 * 数据库schema，默认为空字符串
	 */
	protected String schema = "";

	private HashMap<String, String> schemaMap;

	/**
	 * 根据Key获取实例化好的Dao
	 * 
	 * @param key
	 * @return
	 */
	public BaseDao getDao(String key) {
		if (null != daoMap && daoMap.containsKey(key)) {
			return daoMap.get(key);
		} else {
			return null;
		}
	}

	public HashMap<String, DataSource> getDataSourceMap() {
		return dataSourceMap;
	}

	public void setDataSourceMap(HashMap<String, DataSource> dataSourceMap) {
		this.dataSourceMap = dataSourceMap;
	}

	public HashMap<String, BaseDao> getDaoMap() {
		return daoMap;
	}

	public void setDaoMap(HashMap<String, BaseDao> daoMap) {
		this.daoMap = daoMap;
	}

	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public String getDaoClassName() {
		return daoClassName;
	}

	public void setDaoClassName(String daoClassName) {
		this.daoClassName = daoClassName;
	}

	public HashMap<String, String> getSchemaMap() {
		return schemaMap;
	}

	public void setSchemaMap(HashMap<String, String> schemaMap) {
		this.schemaMap = schemaMap;
	}

	/**
	 * 设置数据库schema
	 * 
	 * @param schema
	 */
	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getSchema() {
		return schema;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (null != dataSourceMap && null != daoClassName) {
			daoMap = new HashMap<String, BaseDao>(dataSourceMap.size());
			Set<String> keys = dataSourceMap.keySet();
			for (String key : keys) {
				BaseDao dao = (BaseDao) Class.forName(this.daoClassName)
						.newInstance();
				dao.setSchema(this.getSchema(key));
				dao.setDataSource(dataSourceMap.get(key));
				dao.setSqlMapClient(this.sqlMapClient);
				dao.afterPropertiesSet();// 模拟Spring注入
				daoMap.put(key, dao);
			}
		}
	}

	/**
	 * 获取schema
	 * @param key
	 * @return
	 */
	private String getSchema(String key) {
		if (null != this.schemaMap && this.schemaMap.containsKey(key)) {
			return schemaMap.get(key);
		} else {
			return this.schema;
		}
	}
}
