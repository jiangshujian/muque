package ${packageName}.service.common;

import java.math.BigDecimal;
import java.util.List;
import org.slf4j.Logger;
import lombok.extern.slf4j.Slf4j;

import org.muque.lark.common.data.PageTableDto;
import org.muque.lark.common.data.ResponseData;
import org.muque.lark.common.data.ResponseDataBuilder;
import org.muque.lark.common.enums.ErrorCodeEnum;
import org.muque.lark.common.exception.UniException;
import org.muque.lark.rdb.ext.Pageable;
import org.muque.lark.rdb.ext.Pager;
import org.muque.lark.rdb.ext.SumMoneyable;
import org.muque.lark.rdb.repository.CRUDRepository;

import com.google.common.collect.Lists;

public abstract class GeneralServiceAbstract<T, P extends Pageable> implements GeneralService<T, P> {

	protected abstract CRUDRepository<T, Long> getDao();

	protected abstract Logger getLog();

	@Override
	public ResponseData<String> save(T obj) {
		try {
			this.getDao().insert(obj);
			return ResponseDataBuilder.newSuccess("保存成功~");
		} catch (Exception e) {
			getLog().error(e.getMessage(), e);
			throw new UniException(ErrorCodeEnum.DB_ERROR, e.getMessage());
		}
	}

	@Override
	public ResponseData<String> update(T obj) {
		try {
			boolean r = this.getDao().update(obj) > 0;
			if (r) {
				return ResponseDataBuilder.newSuccess("编辑成功~");
			} else {
				return ResponseDataBuilder.newFailure(ErrorCodeEnum.DATA_ERROR, "编辑失败~");
			}
		} catch (Exception e) {
			getLog().error(e.getMessage(), e);
			throw new UniException(ErrorCodeEnum.DB_ERROR, e.getMessage());
		}
	}

	@Override
	public ResponseData<T> queryById(Long id) {
		try {
			T data = this.getDao().selectById(id);
			return ResponseDataBuilder.newSuccess(data);
		} catch (Exception e) {
			getLog().error(e.getMessage(), e);
			throw new UniException(ErrorCodeEnum.DB_ERROR, e.getMessage());
		}
	}

	protected int getQueryListLimit() {
		return 100;
	}

	@Override
	public ResponseData<List<T>> queryList(P param) {
		try {
			Pager pager = new Pager();
			pager.setOffset(0);
			pager.setLimit(getQueryListLimit());
			param.setPager(pager);
			List<T> data = this.getDao().selectByConditionPage(param);
			return ResponseDataBuilder.newSuccess(data);
		} catch (Exception e) {
			getLog().error(e.getMessage(), e);
			throw new UniException(ErrorCodeEnum.DB_ERROR, e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageTableDto<T> queryPageTable(Pageable param) {
		int total= 0;
		List<T> rows=Lists.newArrayList();
		BigDecimal sumMoney=BigDecimal.ZERO.setScale(2);
		try {
			total = this.getDao().countByCondition((T)param);
			if(total>0) {
				rows = this.getDao().selectByConditionPage(param);
				if(param instanceof SumMoneyable) {//汇总列
					sumMoney=this.getDao().sumByCondition((SumMoneyable)param);
				}
			}
		} catch (Exception e) {
			getLog().error(e.getMessage(), e);
		}
		PageTableDto<T> result=new PageTableDto<>();
		result.setTotal(total);
		result.setRows(rows);
		result.setSumMoney(sumMoney);
		return result;
	}
}
