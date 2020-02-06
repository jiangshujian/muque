package ${packageName}.dao.${databaseName}.impl;

import java.util.List;

import ${packageName}.common.dao.BaseDao;
import ${packageName}.dto.${databaseName}.${tableBeanClass};
import ${packageName}.dao.${databaseName}.${tableBeanClass}Dao;

public class ${tableBeanClass}DaoImpl extends BaseDao implements ${tableBeanClass}Dao {

	/* (non-Javadoc)
	 */
	@Override
	public void insertObject(${tableBeanClass} obj) {
		this.insert(this.getStatementName("insertObject"), obj);
	}

	/* (non-Javadoc)
	 */
	@Override
	public int deleteByPK(Object pk) {
		return this.delete(this.getStatementName("deleteByPK"), pk);
	}

	/* (non-Javadoc)
	 */
	@Override
	public int updateObject(${tableBeanClass} obj) {
		return this.update(this.getStatementName("updateObject"), obj);
	}

	/* (non-Javadoc)
	 */
	@Override
	public ${tableBeanClass} selectObjectByPK(Object PK) {
		return (${tableBeanClass})this.queryForObject(this.getStatementName("selectObjectByPK"),
				PK);
	}

	/* (non-Javadoc)
	 */
	@Override
	public List<${tableBeanClass}> selectListByConditionWithPage(${tableBeanClass} obj) {
		Integer totalCount = (Integer) this.queryForObject(
				this.getStatementName("selectCountByConditionWithPage"), obj);
		List<${tableBeanClass}> list = (List<${tableBeanClass}>) this.queryForObject(
				this.getStatementName("selectListByConditionWithPage"), obj);
		if (list.size() > 0) {
			list.get(0).setTotalCount(totalCount);
		}
		return list;
	}

	@Override
	protected String getSqlMapNamespace() {
		return "${databaseName}.${tableBeanClass}";
	}

}
