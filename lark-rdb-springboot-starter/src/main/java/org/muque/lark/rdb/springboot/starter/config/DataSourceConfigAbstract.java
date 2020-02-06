package org.muque.lark.rdb.springboot.starter.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.muque.lark.common.collection.ArrayConcatUtil;
import org.muque.lark.rdb.springboot.starter.Constant;
import org.muque.lark.rdb.springboot.starter.datasource.DynamicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * 配置数据源
 * 
 * @author sj.jiang
 * @date 2017年7月14日 上午10:19:56
 * @version V1.0
 */
public abstract class DataSourceConfigAbstract {

	private static final String STRING_SPLIT = ",";

	/**
	 * 根据数据源创建SqlSessionFactory
	 */
	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(DynamicDataSource ds, @Autowired Environment env) throws Exception {
		SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
		fb.setDataSource(ds);// 指定数据源(这个必须有，否则报错)
		// 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
		// fb.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));//
		// 指定基包
		// Resource resources[]={new
		// ClassPathResource(env.getProperty("mybatis.mapperLocations"))};
		String mapperLocations = env.getProperty(Constant.MYBATIS_MAPPER_LOCATIONS);
		// 支持多个
		String[] mapperLocationsAry = mapperLocations.split(STRING_SPLIT);
		Resource[] mapperLocationsResourceAry = new Resource[0];
		for (String item : mapperLocationsAry) {
			Resource[] ary = new PathMatchingResourcePatternResolver().getResources(item);
			mapperLocationsResourceAry = ArrayConcatUtil.concatAll(mapperLocationsResourceAry, ary);
		}
		fb.setMapperLocations(mapperLocationsResourceAry);//
		return fb.getObject();

	}

	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer(@Autowired Environment env) {
		MapperScannerConfigurer target = new MapperScannerConfigurer();
		target.setBasePackage(env.getProperty(Constant.MYBATIS_TYPE_ALIASES_PACKAGE));
		target.setSqlSessionFactoryBeanName("sqlSessionFactory");
		return target;
	}

	/**
	 * 配置事务管理器
	 */
	@Bean
	public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) throws Exception {
		return new DataSourceTransactionManager(dataSource);
	}

}
