package org.muque.common.linq.inner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.muque.common.linq.Query;
import org.muque.common.linq.fn.BigDecimalFunction;
import org.muque.common.linq.fn.GroupKeyFunction;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import lombok.AllArgsConstructor;

/**
 * 
 * @author sj.jiang
 * @date 2016年12月30日 下午7:37:41
 * @version V1.0
 */
@AllArgsConstructor
public class ListQuery<T> implements Query<List<T>> {
	private List<T> list;

	/**
	 * 第一个元素
	 * 
	 * @return
	 */
	public ObjectQuery<T> firstOne() {
		return new ObjectQuery<T>(CollectionUtils.isEmpty(list) ? null : list.get(0));
	}

	/**
	 * 根据条件查询
	 * 
	 * @param predicate
	 * @return
	 */
	public ListQuery<T> select(Predicate<T> predicate) {
		if (null == predicate)
			return this;
		list = Lists.newArrayList(Collections2.filter(list, predicate));
		return this;
	}

	/**
	 * 根据条件查询一个
	 * 
	 * @param predicate
	 * @return
	 */
	public ObjectQuery<T> selectOne(Predicate<T> predicate) {
		if (predicate == null)
			return new ObjectQuery<T>(null);
		for (T item : this.list) {
			if (predicate.apply(item))
				return new ObjectQuery<T>(item);
		}
		return new ObjectQuery<T>(null);
	}

	/**
	 * 金額項求和
	 * 
	 * @param predicate
	 * @return
	 */
	public ObjectQuery<BigDecimal> sum(BigDecimalFunction<T> function) {
		BigDecimal target = BigDecimal.ZERO;
		for (T item : this.list) {
			BigDecimal temp = function.apply(item);
			if (temp != null)
				target = target.add(temp);
		}
		return new ObjectQuery<BigDecimal>(target);
	}

	/**
	 * 根据条件移除
	 * 
	 * @param predicate
	 * @return
	 */
	public ListQuery<T> remove(Predicate<T> predicate) {
		if (null == predicate)
			return this;
		List<T> temp = Lists.newArrayList();
		for (T item : this.list) {
			if (!predicate.apply(item)) {
				temp.add(item);
			}
		}
		this.list = temp;
		return this;
	}

	/**
	 * 合并另外一个集合
	 * 
	 * @param predicate
	 * @return
	 */
	public ListQuery<T> union(List<T> src) {
		this.list.addAll(src);
		return this;
	}

	/**
	 * 合并另外一个元素
	 * 
	 * @param predicate
	 * @return
	 */
	public ListQuery<T> union(T element) {
		this.list.add(element);
		return this;
	}

	/**
	 * 按条件分组
	 * 
	 * @param function
	 * @return
	 */
	public GroupQuery<T> groupBy(GroupKeyFunction<T> function) {
		List<List<T>> target = Lists.newArrayList();
		Map<String, List<T>> map = Maps.newHashMap();
		for (T item : this.list) {
			GroupKey gk = function.apply(item);
			if (map.containsKey(gk.getKey())) {
				map.get(gk.getKey()).add(item);
			} else {
				@SuppressWarnings("unchecked")
				List<T> list = Lists.newArrayList(item);
				map.put(gk.getKey(), list);
			}
		}
		Iterator<String> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			target.add(map.get(iterator.next()));
		}
		return new GroupQuery<T>(target);
	}

	/**
	 * 按照大小顺序分组
	 * 
	 * @param size
	 * @return
	 */
	public GroupQuery<T> groupBy(int size) {
		List<List<T>> target = Lists.partition(this.list, size);
		return new GroupQuery<T>(target);
	}

	/**
	 * 注意：会对原集合直接排序
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <K extends Comparable<? super K>> ListQuery<T> orderByAsc() {
		Collections.sort((List<K>) this.list);
		return this;
	}

	/**
	 * 注意：会对原集合直接排序
	 * 
	 * @return
	 */
	public <K extends Comparable<? super K>> ListQuery<T> orderByDesc() {
		orderByAsc();
		this.reverse();
		return this;
	}

	/**
	 * 注意：会对原集合直接排序
	 * 
	 * @return
	 */
	public <K extends Comparable<? super K>> ListQuery<T> orderByAsc(final Function<T, K> function) {
		Collections.sort(this.list, new Comparator<T>() {
			@Override
			public int compare(T o1, T o2) {
				return function.apply(o1).compareTo(function.apply(o2));
			}
		});
		return this;
	}

	/**
	 * 注意：会对原集合直接排序
	 * 
	 * @return
	 */
	public <K extends Comparable<? super K>> ListQuery<T> orderByDesc(final Function<T, K> function) {
		Collections.sort(this.list, new Comparator<T>() {
			@Override
			public int compare(T o1, T o2) {
				return function.apply(o1).compareTo(function.apply(o2));
			}
		});
		return this;
	}

	/**
	 * 转换集合
	 * 
	 * @param function
	 * @return
	 */
	public <K> ListQuery<K> transform(Function<T, K> function) {
		List<K> tempList = Lists.newArrayList(Lists.transform(this.list, function));
		return new ListQuery<K>(tempList);
	}

	/**
	 * 注意：会对原集合直接反转
	 * 
	 * @return
	 */
	public ListQuery<T> reverse() {
		Collections.reverse(this.list);
		return this;
	}

	/**
	 * 限制集合长度
	 * 
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	public ListQuery<T> limit(int startIndex, int endIndex) {
		if (endIndex > 0 && endIndex >= startIndex) {
			List<T> tempList = Lists.newArrayList();
			for (int i = 0; i < this.list.size(); i++) {
				if (i >= startIndex && i < endIndex) {
					tempList.add(this.list.get(i));
				}
			}
			this.list = tempList;
		}else {
			this.list = Collections.EMPTY_LIST;
		}
		return this;
	}

	/**
	 * 限制集合长度，从0开始
	 * 
	 * @param length
	 * @return
	 */
	public ListQuery<T> limit(int length) {
		this.limit(0, length);
		return this;
	}

	/**
	 * 限制集合长度，从第几个索引开始
	 * 
	 * @param startIndex
	 * @return
	 */
	public ListQuery<T> limitAt(int startIndex) {
		this.limit(startIndex, this.list.size());
		return this;
	}

	/**
	 * 复制生成新的List，防止更新操作对缓存变量影响
	 * 
	 * @return
	 */
	public ListQuery<T> clone() {
		return new ListQuery<T>(new ArrayList<T>(this.list));
	}

	@Override
	public List<T> done() {
		return list;
	}
}
