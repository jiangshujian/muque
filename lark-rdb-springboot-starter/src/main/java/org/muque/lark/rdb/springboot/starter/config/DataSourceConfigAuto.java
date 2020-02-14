package org.muque.lark.rdb.springboot.starter.config;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.muque.lark.common.collection.StringSplit2ListUtil;
import org.muque.lark.rdb.springboot.starter.Constant;
import org.muque.lark.rdb.springboot.starter.config.factory.DataSourceConnectionPoolFactory;
import org.muque.lark.rdb.springboot.starter.datasource.DynamicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import lombok.Getter;
import lombok.Setter;

/**
 * 自动配置所有数据源
 * 
 * @author sj.jiang
 * @date 2017年7月14日 上午10:19:56
 * @version V1.0
 */
@Configuration
@EnableAutoConfiguration
public class DataSourceConfigAuto extends DataSourceConfigAbstract {

	@Bean
	@Primary
	public DynamicDataSource dataSource(@Autowired Environment env) {
		DynamicDataSource target = new DynamicDataSource();
		List<DataSourceConfiged> dscList = this.getDataSourceConfigedList(env);
		if (!dscList.isEmpty()) {
			// 配置多数据源
			Map<Object, Object> dsMap = Maps.newHashMap();
			for (DataSourceConfiged item : dscList) {
				if (item.isDefaultDs()) {
					// 默认数据源
					target.setDefaultTargetDataSource(item.getDs());
				}
				dsMap.put(item.getName(), item.getDs());
			}
			target.setTargetDataSources(dsMap);
		}
		return target;
	}

	/**
	 * 从配置获取数据源(反射)
	 * 
	 * @param env
	 * @return
	 */
	private List<DataSourceConfiged> getDataSourceConfigedList(final Environment env) {
		List<DataSourceConfiged> targetList = Lists.newArrayList();
		String dsNameStrs = env.getProperty(Constant.PROP_KEY_DATASOURCES);
		if (null == dsNameStrs) {
			return targetList;
		}
		List<String> dsNameList = StringSplit2ListUtil.toStringList(dsNameStrs);
		for (String dsName : dsNameList) {
			DataSource ds = DataSourceConnectionPoolFactory.newDataSourceWithConnectionPool(dsName, env);
			DataSourceConfiged target = new DataSourceConfiged();
			target.setName(dsName);
			target.setDs(ds);
			target.setDefaultDs(false);
			targetList.add(target);
		}
		// 第一个数据源为默认数据源
		if ((targetList.size() > 0)) {
			targetList.get(0).setDefaultDs(true);
		}
		return targetList;
	}

	@Getter
	@Setter
	private static class DataSourceConfiged {
		private String name;
		private boolean defaultDs;
		private DataSource ds;
	}
}
