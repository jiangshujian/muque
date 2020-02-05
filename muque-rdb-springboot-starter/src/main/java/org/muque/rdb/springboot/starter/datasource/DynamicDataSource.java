package org.muque.rdb.springboot.starter.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.dangdang.ddframe.rdb.datasource.common.DataSourceContextHolder;

/**
 * 通过集成AbstractRoutingDataSource转换数据源
 * 
 * @Title: DynamicDataSource.java
 * @Package com.dang.rocket.dao.datasource
 * @Description: 通过集成AbstractRoutingDataSource转换数据源
 * @author qijun.wang
 * @date 2014年6月23日 下午7:28:48
 * @version V1.0
 */

public class DynamicDataSource extends AbstractRoutingDataSource {


	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceContextHolder.getDataSourceName();
	}
}
