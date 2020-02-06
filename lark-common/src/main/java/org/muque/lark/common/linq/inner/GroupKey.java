package org.muque.lark.common.linq.inner;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GroupKey {
	private static final String KEY_SPLIT = "-";
	@Getter
	private String key;

	public static GroupKey newGroupKey(Object... fields) {
		String key = "";
		for (Object item : fields) {
			if (null != item)
				key = key.concat(KEY_SPLIT).concat(item.toString());
			else
				key = key.concat(KEY_SPLIT).concat("");
		}
		return new GroupKey(key);
	}

	@Override
	public String toString() {
		return key;
	}

}
