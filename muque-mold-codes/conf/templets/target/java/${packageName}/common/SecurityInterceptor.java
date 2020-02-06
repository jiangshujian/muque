package ${packageName}.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.saber.easy.util.MD5Util;
import com.saber.easy.util.Result;

/**
 * token 验证
 * 
 * @author sj.jiang
 * 
 */
public class SecurityInterceptor implements HandlerInterceptor {

	private String serverToken;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (null == serverToken) {
			return true;
		}
		String token = request.getHeader("token");
		if (null != token && token.equals(serverToken)) {
			return true;
		} else {
			Result result = new Result(false, Result.CODE_TOKEN_WRONG,
					"token wrong");
			response.setHeader("Encode-type", "application/json;charset=utf-8");
			response.getWriter().write(JSON.toJSONString(result));
			return false;
		}

	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

	public String getServerToken() {
		return serverToken;
	}

	public void setServerToken(String serverToken) {
		this.serverToken = MD5Util.md5(serverToken);
	}

}
