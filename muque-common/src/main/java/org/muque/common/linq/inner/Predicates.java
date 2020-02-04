package org.muque.common.linq.inner;

import com.google.common.base.Predicate;

public class Predicates {
	/**
	 * apply always return false
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> Predicate<T> or(final Predicate<T>... predicates) {
		return new Predicate<T>() {
			@Override
			public boolean apply(T input) {
				boolean target = false;
				for (Predicate<T> item : predicates) {
					target = target || item.apply(input);
				}
				return target;
			}
		};
	}

	/**
	 * apply always return false
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> Predicate<T> and(final Predicate<T>... predicates) {
		return new Predicate<T>() {
			@Override
			public boolean apply(T input) {
				boolean target = true;
				for (Predicate<T> item : predicates) {
					target = target && item.apply(input);
				}
				return target;
			}
		};
	}

	/**
	 * apply always return false
	 * 
	 * @return
	 */
	public static <T> Predicate<T> newFalse() {
		return new Predicate<T>() {
			@Override
			public boolean apply(T input) {
				return false;
			}
		};
	}
	
	/**
	 * apply always return false
	 * 
	 * @return
	 */
	public static <T> Predicate<T> newTrue() {
		return new Predicate<T>() {
			@Override
			public boolean apply(T input) {
				return true;
			}
		};
	}

}
