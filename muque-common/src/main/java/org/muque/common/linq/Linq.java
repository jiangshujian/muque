package org.muque.common.linq;

import java.util.List;

import org.muque.common.linq.inner.ListQuery;
import org.muque.common.linq.inner.ObjectQuery;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

/**
 * 集合流式处理
 * 
 * @author sj.jiang
 * @date 2016年12月30日 下午2:14:50
 * @version V1.0
 */
public class Linq {

	public static <T> ListQuery<T> forList(List<T> list) {
		return new ListQuery<>(list);
	}

	public static <T> ObjectQuery<T> forObject(T object) {
		return new ObjectQuery<>(object);
	}

	public static void main(String[] args) {
		List<Integer> list1 = Lists.newArrayList(1, 2, 3, 4, 5);
		List<Integer> list2 = Linq.forList(list1).clone().done();
		List<Integer> list3 = Linq.forList(list1).remove(new Predicate<Integer>() {
			@Override
			public boolean apply(Integer input) {
				return input.equals(2);
			}
		}).limit(3).done();
		List<Integer> list4 = Linq.forList(list2).orderByDesc().done();
		List<Integer> list5 = Linq.forList(list1).limitAt(3).done();
		System.out.println(list1);
		System.out.println(list2);
		System.out.println(list3);
		System.out.println(list4);
		System.out.println(list5);
	}

}
