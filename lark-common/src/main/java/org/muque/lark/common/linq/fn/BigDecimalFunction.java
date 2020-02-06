package org.muque.lark.common.linq.fn;

import java.math.BigDecimal;

import com.google.common.base.Function;

public interface BigDecimalFunction<T> extends Function<T, BigDecimal> {
	BigDecimal apply(T input);
}
