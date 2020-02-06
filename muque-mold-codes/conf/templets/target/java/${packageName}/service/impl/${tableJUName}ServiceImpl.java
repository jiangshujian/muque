package ${packageName}.service.impl;


import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.saber.easy.orm.BaseDao;
import ${packageName}.common.BaseServiceImpl;

import ${packageName}.dao.${tableJUName}Dao;
import ${packageName}.dto.${tableJUName};
import ${packageName}.service.${tableJUName}Service;

@Service
public class ${tableJUName}ServiceImpl extends BaseServiceImpl<${tableJUName}> implements ${tableJUName}Service {

	private static final Log logger = LogFactory
			.getLog(${tableJUName}ServiceImpl.class);
	
	@Resource
	private ${tableJUName}Dao ${tableJName}Dao;

	@Override
	protected BaseDao<${tableJUName}, Long> getDao() {
		return this.${tableJName}Dao;
	}
}

