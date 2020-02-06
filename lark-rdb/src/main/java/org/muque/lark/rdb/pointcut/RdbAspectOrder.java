package org.muque.lark.rdb.pointcut;

/**
 * RDB切面执行顺序.
 * 
 * @author sj.jiang
 * @date 2020年2月4日 下午9:03:50
 * @version V1.0
 */
public final class RdbAspectOrder {

	/**
	 * RDB模块的切面执行顺序的偏移量.
	 */
	public static final int RDB_ASPECT_OFFSET = -1000;

	public static final int SPRING_FRAMEWORK_TRANSACTIONAL_ASPECT = RDB_ASPECT_OFFSET;

	public static final int TRANSACTIONAL_DYNAMIC_DATA_SOURCE_ASPECT = RDB_ASPECT_OFFSET - 1;

	public static final int DYNAMIC_DATA_SOURCE_ASPECT = RDB_ASPECT_OFFSET + 1;

	public static final int DATABASE_SHARDING_DATA_SOURCE_ASPECT = RDB_ASPECT_OFFSET + 2;

	private RdbAspectOrder() {
	}

}
