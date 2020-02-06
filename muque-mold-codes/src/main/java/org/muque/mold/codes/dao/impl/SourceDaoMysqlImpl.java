package org.muque.mold.codes.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.muque.mold.codes.dao.SourceDao;
import org.muque.mold.codes.dto.SourceColumn;
import org.muque.mold.codes.dto.SourceTable;
import org.muque.mold.codes.util.ConvertUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class SourceDaoMysqlImpl implements InitializingBean, SourceDao {

	private SimpleJdbcTemplate simpleJdbcTemplate;

	@Resource
	private DataSource dataSource;

	/* (non-Javadoc)
	 * @see com.mytools.aoto_codes.dao.SourceDao#getTableList(java.lang.String)
	 */
	@Override
	public List<SourceTable> getTableList(String databaseName) {
		String sql = "select  table_name as name,table_comment as description FROM `information_schema`.`TABLES` where TABLE_SCHEMA=:databaseName";
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("databaseName", databaseName);
		MapSqlParameterSource para=new MapSqlParameterSource(map);
		List<Map<String, Object>> list = this.simpleJdbcTemplate.queryForList(
				sql, para);
		List<SourceTable> results = new ArrayList<SourceTable>();
		for (Map<String, Object> rMap : list) {
			String name = ConvertUtil.toString(rMap.get("name"));
			String description = ConvertUtil.toString(rMap.get("description"));
			SourceTable target = new SourceTable();
			target.setName(name);
			target.setDescription(description);
			results.add(target);
		}
		return results;
	}

	/* (non-Javadoc)
	 * @see com.mytools.aoto_codes.dao.SourceDao#getColumnList(java.lang.String, java.lang.String)
	 */
	@Override
	public List<SourceColumn> getColumnList(String databaseName,
			String tableName) {
		String sql = "SELECT COLUMN_NAME as name,IS_NULLABLE as isNullable,DATA_TYPE as dataType,CHARACTER_MAXIMUM_LENGTH as length,COLUMN_COMMENT as description,COLUMN_KEY as isPrimaryKey FROM `information_schema`.`COLUMNS` where TABLE_SCHEMA=:databaseName and TABLE_NAME=:tableName";
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("databaseName", databaseName);
		map.put("tableName", tableName);
		MapSqlParameterSource para=new MapSqlParameterSource(map);
		List<Map<String, Object>> list = this.simpleJdbcTemplate.queryForList(
				sql, para);
		List<SourceColumn> results = new ArrayList<SourceColumn>();
		for (Map<String, Object> rMap : list) {
			String name = ConvertUtil.toString(rMap.get("name"));
			boolean isNullable = ConvertUtil.toBoolean(rMap.get("isNullable"));
			String dataType = ConvertUtil.toString(rMap.get("dataType"));
			String description = ConvertUtil.toString(rMap.get("description"));
			boolean isPrimaryKey = ConvertUtil.toBoolean(rMap
					.get("isPrimaryKey"));

			SourceColumn target = new SourceColumn();
			target.setName(name);
			target.setNullable(isNullable);
			target.setDataType(dataType);
			target.setDescription(description);
			target.setPrimaryKey(isPrimaryKey);
			results.add(target);
		}
		return results;
	}

	public void afterPropertiesSet() throws Exception {
		this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}

}
