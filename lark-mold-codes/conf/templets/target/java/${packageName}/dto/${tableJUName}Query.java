package ${packageName}.dto;

import java.io.Serializable;
import java.util.Date;
import org.muque.lark.rdb.ext.Pageable;
import org.muque.lark.rdb.ext.Pager;
import ${packageName}.model.${tableJUName};
import lombok.Getter;
import lombok.Setter;

/**
 * 实体查询扩展类:${tableDes}
 * @author sj.jiang
 *
 */
@Getter
@Setter
public class ${tableJUName}Query extends ${tableJUName} implements Pageable{
	/**
	 * 查询参数
	 */
	private Pager pager;
}
