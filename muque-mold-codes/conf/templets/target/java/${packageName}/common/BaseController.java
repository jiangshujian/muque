package ${packageName}.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author jiangshujian
 * 
 */
public class BaseController {

	/**
	 * 获取http request
	 * 
	 * @param args
	 */
	protected HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		return request;
	}

	/**
	 * 获取http request getParameter
	 * 
	 * @param name
	 * @return
	 */
	protected String getParameter(String name) {
		return this.getRequest().getParameter(name);
	}

}
