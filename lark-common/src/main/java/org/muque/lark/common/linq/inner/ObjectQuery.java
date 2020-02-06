package org.muque.lark.common.linq.inner;

import java.util.ArrayList;

import org.muque.lark.common.linq.Linq;
import org.muque.lark.common.linq.Query;

import com.google.common.collect.Lists;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ObjectQuery<T> implements Query<T> {
	private T data;

	@SuppressWarnings("unchecked")
	public ListQuery<T> asList() {
		return Linq.forList(null == data ? new ArrayList<T>() : Lists.newArrayList(data));
	}

	@Override
	public T done() {
		return this.data;
	}

}
