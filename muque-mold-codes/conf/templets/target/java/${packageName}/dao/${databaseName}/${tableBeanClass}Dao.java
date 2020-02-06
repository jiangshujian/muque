package ${packageName}.dao.${databaseName};

import java.util.List;

import ${packageName}.dto.${databaseName}.${tableBeanClass};

public interface ${tableBeanClass}Dao {

	/**
	 * 保存 ${tableDescription}
	 * 
	 * @param obj
	 */
	public void insertObject(${tableBeanClass} obj);

	/**
	 * 根据主键删除${tableDescription}
	 * 
	 * @param pk
	 * @return
	 */
	public int deleteByPK(Object pk);

	/**
	 * 根据主键删除${tableDescription}
	 * 
	 * @param pk
	 * @return
	 */
	public int updateObject(${tableBeanClass} obj);

	/**
	 * 根据主键获取实体对象
	 * 
	 * @param PK
	 * @return
	 */
	public ${tableBeanClass} selectObjectByPK(Object PK);

	/**
	 * 分页查询实体
	 * 
	 * @param obj
	 * @return
	 */
	public List<${tableBeanClass}> selectListByConditionWithPage(${tableBeanClass} obj);

}