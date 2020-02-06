package ${packageName}.dao.impl;

import com.saber.easy.orm.MyBatisDao;
import com.saber.easy.orm.annotation.Dao;
import ${packageName}.dao.${tableJUName}Dao;
import ${packageName}.dto.${tableJUName};

@Dao(dsKey = "${databaseName}")
public class ${tableJUName}DaoImpl extends MyBatisDao<${tableJUName}, ${PKJType}>
implements ${tableJUName}Dao {

	@Override
	protected String getNamespace() {
		return "${tableJUName}";
	}

}