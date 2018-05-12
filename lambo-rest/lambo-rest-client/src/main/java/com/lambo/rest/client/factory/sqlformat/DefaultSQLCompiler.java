package com.lambo.rest.client.factory.sqlformat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DefaultSQLCompiler implements SQLCompiler {

	private ParamGhost ghost;
	@Override
	public List<String> compile(List<String> contexts, List<Object> values) throws CompileException {
		if(contexts == null){
			return Collections.emptyList();
		}
		if(contexts.size() != values.size()){
			throw new CompileException("contexts的长度与values的长度不一致");
		}
		List<String> results = new ArrayList<String>();
		
		for (int i = 0; i < contexts.size(); i++) {
			results.add(replaceValue(contexts.get(i), (String)values.get(i)));
		}
		return results;
	}

	private String replaceValue(String old, String value){
		return old.replace("?", ghost.recover(value));
	}
	@Override
	public void setParamGhost(ParamGhost ghost) {
		this.ghost = ghost;
	}

}
