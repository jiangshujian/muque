package ${packageName}.service.common;

import java.util.List;

import org.muque.lark.common.data.PageTableDto;
import org.muque.lark.common.data.ResponseData;
import org.muque.lark.rdb.ext.Pageable;

public interface GeneralService<T,P extends Pageable> {
	/**
	 * 保存实体
	 * 
	 * @param dto
	 * @return
	 */
	ResponseData<String> save(T dto);

	/**
	 * 更新实体
	 * 
	 * @param dto
	 */
	ResponseData<String> update(T dto);

	/**
	 * 获取实体
	 * 
	 * @param id
	 * @return
	 */
	ResponseData<T> queryById(Long id);
	
	/**
	 * 条件查询列表
	 * 
	 * @param param
	 * @return
	 */
	ResponseData<List<T>> queryList(P param);

	/**
	 * 分页查询实体列表
	 * 
	 * @param param
	 * @return
	 */
	PageTableDto<T> queryPageTable(P param);
}
