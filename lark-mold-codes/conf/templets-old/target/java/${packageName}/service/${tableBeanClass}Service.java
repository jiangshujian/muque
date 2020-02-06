package ${packageName}.service;

import java.util.List;

import ${packageName}.dto.${databaseName}.${tableBeanClass};

/**
 * 服务接口 <br>
 * <${tableBeanClass}><${tableDescription}>
 * 
 * @author author name
 * 
 */
public interface ${tableBeanClass}Service {

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
	 * @param obj ${tableBean}
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
	 * @param obj ${tableBeanClass}
	 * @return
	 */
	public List<${tableBeanClass}> selectListByConditionWithPage(${tableBeanClass} obj);

}