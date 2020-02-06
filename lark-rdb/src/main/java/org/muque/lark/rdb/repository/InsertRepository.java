package org.muque.lark.rdb.repository;

import java.util.List;

/**
 * 数据访问层插入操作接口.
 * @param <T> 实体类
 * 
 * @author sj.jiang
 */
public interface InsertRepository<T> extends BaseRepository {
    
    /**
     * 新增一个实体（INSERT一条记录）.
     * 
     * @param entity 实体对象
     */
    void insert(T entity);
    
    /**
     * 新增多个实体（INSERT多条记录）.
     * 
     * @param entities 实体对象列表
     * @return 返回插入的对象个数 
     */
    int insertBatch(List<T> entities);
    
}
