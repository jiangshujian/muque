package ${packageName}.common.dao;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

/**
 * Dao基类
 * 
 * @author jiangshujian
 * @version 1.1,2013-06-24
 */
public abstract class BaseDao extends SqlMapClientTemplate {

	/**
	 * 数据库schema，默认为空字符串
	 */
	protected String schema = "";

	/**
	 * 设置数据库schema
	 * 
	 * @param schema
	 */
	public void setSchema(String schema) {
		this.schema = schema;
	}

	/**
	 * ibatis sqlmap 命名空间<br>
	 * sqlmap namespace.
	 */
	protected abstract String getSqlMapNamespace();

	/**
	 * 获取sqlmap文件中完整的语句id
	 * 
	 * @param statementId
	 * @return
	 */
	protected String getStatementName(String statementId) {
		return this.getSqlMapNamespace() + "." + statementId;
	}
}
