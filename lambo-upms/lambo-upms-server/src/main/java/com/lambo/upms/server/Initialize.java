package com.lambo.upms.server;

import com.lambo.common.base.BaseInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 系统接口
 * Created by lambo on 2017/6/13.
 */
public class Initialize implements BaseInterface {

	private static Logger logger = LoggerFactory.getLogger(Initialize.class);

	@Override
	public void init() {
		logger.info(">>>>> 系统初始化");
	}

}
