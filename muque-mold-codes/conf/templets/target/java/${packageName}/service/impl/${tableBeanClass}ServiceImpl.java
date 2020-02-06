package ${packageName}.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ${packageName}.dto.${databaseName}.${tableBeanClass};
import ${packageName}.dao.${databaseName}.${tableBeanClass}Dao;
import ${packageName}.service.${tableBeanClass}Service;

/**
 * 服务实现类 <br>
 * <${tableBeanClass}><${tableDescription}>
 * 
 * @author author name
 * 
 */
public class ${tableBeanClass}ServiceImpl implements ${tableBeanClass}Service {

	private static final Log logger = LogFactory.getLog(${tableBeanClass}ServiceImpl.class);

	@Resource
	private ${tableBeanClass}Dao ${tableBean}Dao;

	/* (non-Javadoc)
	 */
	@Override
	public void insertObject(${tableBeanClass} obj) {
		this.${tableBean}Dao.insertObject(obj);
	}

	/* (non-Javadoc)
	 */
	@Override
	public int deleteByPK(Object pk) {
		return this.${tableBean}Dao.deleteByPK(pk);
	}

	/* (non-Javadoc)
	 */
	@Override
	public int updateObject(${tableBeanClass} obj) {
		return this.${tableBean}Dao.updateObject(obj);
	}

	/* (non-Javadoc)
	 */
	@Override
	public ${tableBeanClass} selectObjectByPK(Object PK) {
		return this.${tableBean}Dao.selectObjectByPK(PK);
	}

	/* (non-Javadoc)
	 */
	@Override
	public List<${tableBeanClass}> selectListByConditionWithPage(${tableBeanClass} obj) {
		return this.${tableBean}Dao.selectListByConditionWithPage(obj);
	}

}
