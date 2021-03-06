package org.muque.lark.common.linq.fn;

import org.muque.lark.common.linq.inner.GroupKey;

import com.google.common.base.Function;

public interface GroupKeyFunction<T> extends Function<T, GroupKey> {
	/**
	 * 生成分组的键
	 * 
	 * @param input
	 * @return
	 */
	GroupKey apply(T input);
}
