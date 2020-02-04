package org.muque.common.files;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.type.TypeReference;
import org.muque.common.mix.JsonUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Json 数据配置加载
 * 
 * @author sj.jiang
 * @date 2017年12月27日 下午6:13:51
 * @version V1.0
 */
@Slf4j
public class JsonFileLoadUtil {
	/**
	 * 加载数据
	 *
	 * @param filePath
	 * @param typeRef
	 * @return
	 */
	public static <T> T load(String filePath, TypeReference<T> typeRef) {
		try {
			String json = IOUtils.toString(JsonFileLoadUtil.class.getResourceAsStream(filePath), "UTF-8");
			T data = JsonUtil.fromJson(json, typeRef);
			log.info("JSON数据[{}]加载成功: {}", filePath, data);
			return data;
		} catch (Exception e) {
			log.error("JSON数据[{}]加载失败", filePath, e);
		}
		return null;
	}
}
