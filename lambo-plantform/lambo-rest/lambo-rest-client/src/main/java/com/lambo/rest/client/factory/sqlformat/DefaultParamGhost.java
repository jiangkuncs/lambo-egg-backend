package com.lambo.rest.client.factory.sqlformat;

/**
 * 转换器
 */
public class DefaultParamGhost implements ParamGhost {

	@Override
	public String recover(String value) {
		//如果是数值类型，则原样替换
//		if(value.matches("-?[0-9]*")){
//			return value;
//		}
		//如果是字符类型，则两端加上单引号之后替换
		return " \'"+value+"\' ";
	}

}
