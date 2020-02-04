package org.muque.common.linq.inner;

import java.util.ArrayList;
import java.util.List;

import org.muque.common.linq.Linq;
import org.muque.common.linq.Query;

import com.google.common.collect.Lists;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GroupQuery<T> implements Query<List<List<T>>> {
	private List<List<T>> groupList;

	public GroupQuery<T> clone() {
		return new GroupQuery<T>(new ArrayList<>(groupList));
	}

	public GroupQuery<T> havingCount(int count) {
		List<List<T>> target = Lists.newArrayList();
		for (List<T> item : this.groupList) {
			if (item.size() >= count)
				target.add(item);
		}
		this.groupList = target;
		return this;
	}
	
	public ListQuery<T> asList(){
		List<T> target = Lists.newArrayList();
		for (List<T> item : this.groupList) {
			target.addAll(item);
		}
		return Linq.forList(target);
	}

	@Override
	public List<List<T>> done() {
		return groupList;
	}

}
