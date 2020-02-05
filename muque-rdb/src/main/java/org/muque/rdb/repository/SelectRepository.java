package org.muque.rdb.repository;

import java.io.Serializable;

/**
 * 数据访问层查询操作接口.
 * 
 * @param <T> 实体类.
 * @param <P> 主键类，必须实现Serializable接口
 * 
 * @author caohao
 */
public interface SelectRepository<T, P extends Serializable> extends BaseRepository {
    
    /**
     * 根据主键查询一个实体对象.
     * 
     * @param primaryKey 数据库主键
     * @return 查询到的实体对象
     */
    T selectById(P primaryKey);
}
