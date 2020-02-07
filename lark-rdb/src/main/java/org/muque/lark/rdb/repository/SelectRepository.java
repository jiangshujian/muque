package org.muque.lark.rdb.repository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.muque.lark.rdb.ext.Pageable;
import org.muque.lark.rdb.ext.SumMoneyable;

/**
 * 数据访问层查询操作接口.
 * 
 * @param <T> 实体类.
 * @param <P> 主键类，必须实现Serializable接口
 * 
 * @author sj.jiang
 */
public interface SelectRepository<T, P extends Serializable> extends BaseRepository {

	/**
	 * 根据主键查询一个实体对象.
	 * 
	 * @param primaryKey 数据库主键
	 * @return 查询到的实体对象
	 */
	T selectById(P primaryKey);

	/**
	 * 
	 * @param entity
	 * @return
	 */
	List<T> selectByCondition(T entity);

	/**
	 * 根据统计
	 * 
	 * @param entity
	 * @return
	 */
	int countByCondition(T entity);

	/**
	 * 根据某列汇总
	 * 
	 * @param <Q>
	 * @param <SumMoneyable>
	 * @param entity
	 * @return
	 */
	<Q extends SumMoneyable> BigDecimal sumByCondition(Q entity);

	/**
	 * 分页查询（包含排序功能）
	 * 
	 * @param <Q>
	 * @param <Pageable>
	 * @param entity
	 * @return
	 */
	<Q extends Pageable> List<T> selectByConditionPage(Q entity);
}
