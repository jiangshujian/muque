package org.muque.rdb.datasource.common;

/**
 * 当前线程数据源存放类.
 * 
 * @author caohao
 */
public final class DataSourceContextHolder {
    
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<String>();
    
    private DataSourceContextHolder() {
    }
    
    public static void setDataSourceName(final String dataSourceName) {
        CONTEXT_HOLDER.set(dataSourceName);
    }
    
    public static String getDataSourceName() {
        return CONTEXT_HOLDER.get();
    }
    
    public static void clearDataSourceName() {
        CONTEXT_HOLDER.remove();
    }
}
