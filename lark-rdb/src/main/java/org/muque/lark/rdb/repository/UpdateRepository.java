package org.muque.lark.rdb.repository;

/**
 * 数据访问层修改操作接口.
 * 
 * @param <T> 实体类
 * 
 * @author sj.jiang
 */
public interface UpdateRepository<T> extends BaseRepository {

	/**
	 * 修改一个实体对象（UPDATE一条记录）.
	 * 
	 * @param entity 实体对象
	 * @return 返回修改的对象个数，正常情况=1
	 */
	int update(T entity);

	/**
	 * 修改一个实体对象（UPDATE一条记录,如果参数为空则不会更新）.
	 * 
	 * @param entity 实体对象
	 * @return 返回修改的对象个数，正常情况=1
	 */
	int updateNotNull(T entity);
}
