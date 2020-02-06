package org.muque.lark.rdb.repository;

import java.io.Serializable;

/**
 * 数据访问层增删改查接口.
 * 
 * @param <T> 实体类.
 * @param <P> 主键类, 必须实现Serializable接口.
 * 
 * @author sj.jiang
 */
public interface CRUDRepository<T, P extends Serializable> extends
        InsertRepository<T>, UpdateRepository<T>, DeleteRepository<P>, SelectRepository<T, P> {
}
