package org.muque.lark.rdb.springboot.starter.datasource;

import java.lang.reflect.Type;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.muque.lark.rdb.datasource.annotation.DataSourceName;
import org.muque.lark.rdb.datasource.common.DataSourceContextHolder;
import org.muque.lark.rdb.pointcut.RdbAspectOrder;
import org.muque.lark.rdb.springboot.starter.Constant;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * DAO拦截器,扫描拦截设置数据源
 * 
 * @Title: AbstractDaoInterceptor
 * @Package com.dang.order.orm.core.interceptor
 * @Description: TODO
 * @author sj.jiang
 * @date 2015年12月10日 下午7:54:03
 * @version V1.1
 */
@Component
@Order(RdbAspectOrder.RDB_ASPECT_OFFSET)
@Aspect
@Slf4j
public class DynamicDataSourceAspect implements InitializingBean {
	
    @Pointcut(Constant.DATASOURCE_POINTCUT_PATTERN)
    public void repositoryPointcut() {
        throw new UnsupportedOperationException();
    }

	@Before("repositoryPointcut()")
	public void changeDataSource(JoinPoint point) throws Throwable {
		setExactlyDataSourceNameByInterface(point.getTarget().getClass().getGenericInterfaces());
	}

	@After("repositoryPointcut()")
	public void restoreDataSource(JoinPoint point) {
		DataSourceContextHolder.clearDataSourceName();
	}

	/**
	 * 根据接口的标注设置数据源名称
	 * 
	 * @param targetProxyInterfaces
	 * @return
	 */
	private boolean setExactlyDataSourceNameByInterface(final Type... targetProxyInterfaces) {
		for (Type each : targetProxyInterfaces) {
			DataSourceName annotation = ((Class<?>) each).getAnnotation(DataSourceName.class);
			if (null != annotation) {
				DataSourceContextHolder.setDataSourceName(annotation.value());
				log.debug("@@@change DataSource={}", annotation.value());
				return true;
			}
		}
		return false;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("Aspect:{} startup ok!", this.getClass().getCanonicalName());
	}
}
