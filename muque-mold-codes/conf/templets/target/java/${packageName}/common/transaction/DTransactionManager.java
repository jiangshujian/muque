package ${packageName}.common.transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import ${packageName}.common.dao.BaseDao;


import javax.sql.DataSource;

/**
 * 事物管理 transaction manager. only support spring+ibatis
 * <p>
 * 注：多数据源，不能保证所有事务都能提交或回滚.
 * </p>
 * <p>
 * note:for multiple data sources,can't ensure all the transactions be committed
 * or rollbacked.
 * </p>
 * 示例：<br>
 * <div style="background-color:#DDDDDD;"> try {<br>
 * // 将需要开启事务的若干个Dao放入构造函数构造<br>
 * DTransactionManager transaction = new DTransactionManager(<br>
 * platformOrdersDao, platformJobLogDao);<br>
 * transaction.startTransaction();// 开启事务<br>
 * try {<br>
 * // platformOrdersDao….<br>
 * // platformJobLogDao….<br>
 * transaction.commit();// 提交事务<br>
 * } catch (Exception e) {<br>
 * getLog().error(e);<br>
 * transaction.rollback();// 回滚事务<br>
 * }<br>
 * } catch (Exception transExp) {<br>
 * getLog().error(transExp);<br>
 * }<br>
 * </div>
 * 
 * @author jiangshujian
 * @version 2013-03-14
 */
public class DTransactionManager {

	private Map<DataSource, DataSource> dataSourceMap = null;
	private List<DTransactionBean> transList = null;

	/**
	 * 构造函数
	 * 
	 * @param daos
	 *            extends from BaseDao
	 * @throws Exception
	 */
	public DTransactionManager(Object... daos) throws Exception {
		if (null != daos && daos.length > 0) {

			this.dataSourceMap = new HashMap<DataSource, DataSource>(
					daos.length);

			for (int i = 0; i < daos.length; i++) {

				Object dao = daos[i];
				if (dao instanceof BaseDao) {
					DataSource dataSource = ((BaseDao) dao).getDataSource();
					if (!dataSourceMap.containsKey(dataSource)) {
						dataSourceMap.put(dataSource, dataSource);
					}
				} else {
					throw new Exception(dao.getClass().getName()
							+ "is not instance of BaseDao");
				}
			}// end for
			this.transList = new ArrayList<DTransactionBean>(
					dataSourceMap.size());

		}// end if
		else {
			throw new Exception("please construct with dao");
		}
	}

	/**
	 * 开启事物start transaction
	 * 
	 * @throws Exception
	 * 
	 * @throws DTransactionException
	 */
	public void startTransaction() throws Exception {
		if (this.transList.size() > 0) {
			this.transList.clear();
		}
		try {
			Iterator<DataSource> it = dataSourceMap.keySet().iterator();
			while (it.hasNext()) {
				DataSource dateSource = it.next();
				DataSourceTransactionManager manager = new DataSourceTransactionManager(
						dateSource);
				DefaultTransactionDefinition td = new DefaultTransactionDefinition();
				// 开启一个嵌套事务start a nested transaction
				// (if current transaction exsit't,
				// execute like PROPAGATION_REQUIRED)
				td.setPropagationBehavior(TransactionDefinition.PROPAGATION_NESTED);
				// build transaction
				TransactionStatus status = manager.getTransaction(td);
				// add to list
				this.transList.add(new DTransactionBean(manager, status));
			}// end while
		} catch (Exception e) {
			rollback();// exception to rollback started transaction
			throw e;
		}
	}

	/**
	 * 提交事物commit transaction
	 * 
	 */
	public void commit() {
		for (int i = this.transList.size() - 1; i >= 0; i--) {
			DTransactionBean trans = this.transList.get(i);
			trans.getManager().commit(trans.getStatus());
		}
	}

	/**
	 * 回滚事物rollback transaction
	 */
	public void rollback() {
		if (null != this.transList) {
			for (int i = this.transList.size() - 1; i >= 0; i--) {
				DTransactionBean trans = this.transList.get(i);
				trans.getManager().rollback(trans.getStatus());
			}
		}
	}

}
