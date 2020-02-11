package ${packageName}.dao;

import org.muque.lark.rdb.datasource.annotation.DataSourceName;
import org.muque.lark.rdb.repository.CRUDRepository;

import ${packageName}.ds.DataSourceConstant;
import ${packageName}.model.${tableJUName};

@DataSourceName(DataSourceConstant.DS_NAME)
public interface ${tableJUName}Dao extends CRUDRepository<${tableJUName},${PKJType}>{

} 