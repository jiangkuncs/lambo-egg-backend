package com.lambo.ndp.controller;

import com.lambo.common.base.BaseController;
import com.lambo.ndp.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * 测试controller
 * Created by lambo on 2017/3/21.
 */
@Controller
public class IndexController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private DemoService demoService;

	/**
	 * jsp视图
	 * @return
	 */
	@RequestMapping(value = "/jsp", method = RequestMethod.GET)
	public String jsp() {
		return "/index.jsp";
	}
}