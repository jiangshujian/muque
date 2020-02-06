package ${packageName}.dto.${databaseName};

import java.io.Serializable;
import java.util.Date;
import ${packageName}.common.util.Pager;

/**
 * 实体类:${tableDescription}
 * @author sj.jiang
 *
 */
public class ${tableBeanClass} extends Pager implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
${javaDtoProperties}
	
${javaDtoPropertiesGetterSetter}

}
