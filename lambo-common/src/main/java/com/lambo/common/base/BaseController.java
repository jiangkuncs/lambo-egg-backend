package com.lambo.common.base;

import com.lambo.common.util.PropertiesFileUtil;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.InvalidSessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 控制器基类
 * Created by lambo on 2017/2/4.
 */
public abstract class BaseController {

	private final static Logger logger = LoggerFactory.getLogger(BaseController.class);

	/**
	 * 统一异常处理
	 * @param request
	 * @param response
	 * @param exception
	 */
	@ExceptionHandler
	public String exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) {
		logger.error("统一异常处理：", exception);
		request.setAttribute("ex", exception);
		if (null != request.getHeader("X-Requested-With") && "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))) {
			request.setAttribute("requestHeader", "ajax");
		}
		// shiro没有权限异常
		if (exception instanceof UnauthorizedException) {
			logger.error("403错误");
			return "/403.jsp";
		}
		// shiro会话已过期异常
		if (exception instanceof InvalidSessionException) {
			logger.error("会话已过期异常");
			return "/error.jsp";
		}
		logger.error("未捕获的异常");
		return "/error.jsp";
	}

	/**
	 * 返回jsp视图
	 * @param path
	 * @return
	 */
	public static String jsp(String path) {
		return path.concat(".jsp");
	}

}
