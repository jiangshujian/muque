package org.muque.lark.common.data;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * 请求数据构建
 * 
 * @author sj.jiang
 * 
 */
public class RequestDataBuilder {

	private static final ObjectMapper objectMapper = new ObjectMapper();
	private static final String DATA_FORMAT = "yyyy-MM-dd HH:mm:ss";

	static {
		// 序列化配置
		objectMapper.setSerializationConfig(objectMapper
				.copySerializationConfig()
				.withSerializationInclusion(JsonSerialize.Inclusion.NON_NULL)
				.without(Feature.FAIL_ON_EMPTY_BEANS));
		objectMapper
				.setDeserializationConfig(objectMapper
						.copyDeserializationConfig()
						.without(
								DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES));
		objectMapper.setDateFormat(new SimpleDateFormat(DATA_FORMAT));
	}

	/**
	 * 构建请求数据
	 * 
	 * @param partner
	 * @param data
	 * @return
	 */
	public static RequestData build(String partner, String data) {
		RequestData target = new RequestData();
		target.setPartner(partner);
		target.setCharset(RequestData.DEFAULT_CHARSET);
		target.setData(data);
		return target;
	}

	/**
	 * 构建请求数据
	 * 
	 * @param partner
	 * @param data
	 * @return
	 */
	public static <T> RequestData build(String partner, T data) {
		RequestData target = build(partner, toJson(data));
		return target;
	}

	/**
	 * 构建请求数据
	 * 
	 * @param partner
	 * @param key
	 * @param data
	 * @return
	 */
	public static <T> RequestData build(String partner, String key, T data) {
		if (StringUtils.isEmpty(key)) {
			throw new RuntimeException("key不可为空");
		}

		RequestData target = build(partner, data);
		String sign = DigestUtils.md5Hex(target.getData() + key);
		target.setSign(sign);

		return target;
	}

	/**
	 * 构建参数Map
	 * 
	 * @param partner
	 * @param key
	 * @param data
	 * @return
	 */
	public static <T> Map<String, String> buildMap(String partner, String key,
			T data) {
		if (StringUtils.isEmpty(key)) {
			throw new RuntimeException("key不可为空");
		}

		String json = toJson(data);
		String sign = DigestUtils.md5Hex(json + key);

		Map<String, String> map = new HashMap<>();
		map.put("partner", partner);
		map.put("key", key);
		map.put("sign", sign);
		map.put("data", json);

		return map;
	}

	/**
	 * 实体转json
	 * 
	 * @param t
	 * @return
	 */
	public static <T> String toJson(T t) {
		try {
			return objectMapper.writeValueAsString(t);
		} catch (IOException e) {
			throw new RuntimeException("to json error", e);
		}
	}
}
