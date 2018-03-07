package com.lambo.common.base;

import com.lambo.common.util.PropertiesFileUtil;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.InvalidSessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 控制器基类
 * Created by lambo on 2017/2/4.
 */
public abstract class BaseController {

	private final static Logger logger = LoggerFactory.getLogger(BaseController.class);

	public static final String RESULT_ROWS = "rows";

	public static final String RESULT_TOTLAL = "total";

	/**
	 * 统一异常处理
	 * @param request
	 * @param response
	 * @param exception
	 */
	@ExceptionHandler
	@ResponseBody
	public Object exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) {
		logger.error("统一异常处理：", exception);

		// shiro没有权限异常
		if (exception instanceof UnauthorizedException) {
			logger.error("403错误");
			return new BaseResult(0,"没有权限访问此资源",exception);
		}
		// shiro会话已过期异常
		if (exception instanceof InvalidSessionException) {
			logger.error("会话已过期异常");
			return new BaseResult(0,"会话已过期",exception);
		}

		return new BaseResult(0,"未捕获的异常",exception);
	}


}
