package ${packageName}.common.transaction;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;

/**
 * 事物管理对象。
 * 
 * @author jiangshujian
 * @version 2013-03-14
 */
public class DTransactionBean {

	private DataSourceTransactionManager manager;
	private TransactionStatus status;
		
	public DTransactionBean(DataSourceTransactionManager manager,
			TransactionStatus status) {
		super();
		this.manager = manager;
		this.status = status;
	}

	public DataSourceTransactionManager getManager() {
		return manager;
	}

	public void setManager(DataSourceTransactionManager manager) {
		this.manager = manager;
	}

	public TransactionStatus getStatus() {
		return status;
	}

	public void setStatus(TransactionStatus status) {
		this.status = status;
	}

}
