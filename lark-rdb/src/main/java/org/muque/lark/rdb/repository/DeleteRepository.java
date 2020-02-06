package org.muque.lark.rdb.repository;

import java.io.Serializable;
import java.util.List;

/**
 * 数据访问层删除接口.
 * 
 * @param <P> 主键类，必须实现Serializable接口
 * 
 * @author caohao
 */
public interface DeleteRepository<P extends Serializable> extends BaseRepository {
    
    /**
     * 按主键删除记录.
     * 
     * @param primaryKey 主键
     * @return 返回删除的对象个数，正常情况=1
     */
    int delete(P primaryKey);
    
    /**
     * 按主键列表删除多条记录.
     * 
     * @param primaryKeys 主键列表
     * @return 返回删除的对象个数
     */
    int deleteBatch(List<P> primaryKeys);
    
}
