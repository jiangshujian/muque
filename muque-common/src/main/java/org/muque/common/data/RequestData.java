package org.muque.common.data;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 请求数据
 * 
 * @author sj.jiang
 *
 */
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestData implements Serializable {
	private static final long serialVersionUID = 4558403016388493829L;

	private String partner;

	private String charset;

	private String sign;

	/**
	 * 序列化后的业务参数(JSON)
	 */
	private String data;

	public static final String DEFAULT_CHARSET = "UTF-8";
}
