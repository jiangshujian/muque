package ${packageName}.service.impl;


import javax.annotation.Resource;
import org.springframework.stereotype.Service;


import ${packageName}.service.common.GeneralService;
import ${packageName}.service.common.GeneralServiceAbstract;
import ${packageName}.dao.${tableJUName}Dao;
import ${packageName}.model.${tableJUName};
import ${packageName}.dto.${tableJUName}Query;
import ${packageName}.service.${tableJUName}Service;

import org.muque.lark.rdb.repository.CRUDRepository;

import org.slf4j.Logger;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class ${tableJUName}ServiceImpl extends GeneralServiceAbstract<${tableJUName},${tableJUName}Query> implements ${tableJUName}Service {

	@Resource
	private ${tableJUName}Dao ${tableJName}Dao;
	
	protected Logger getLog() {
		return log;
	}

	@Override
	protected CRUDRepository<${tableJUName}, Long> getDao() {
		return this.${tableJName}Dao;
	}
}

