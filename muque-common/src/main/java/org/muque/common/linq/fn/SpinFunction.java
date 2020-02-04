package org.muque.common.linq.fn;

import com.google.common.base.Function;

/**
 * 转个身Function
 * 
 * @author: sj.jiang
 * @date: 2019年2月22日 下午7:24:31
 */
public interface SpinFunction<T> extends Function<T, T> {
	T apply(T input);
}
