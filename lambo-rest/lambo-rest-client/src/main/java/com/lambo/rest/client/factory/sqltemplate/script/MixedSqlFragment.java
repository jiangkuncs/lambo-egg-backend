package com.lambo.rest.client.factory.sqltemplate.script;

import java.util.List;

import com.lambo.rest.client.factory.sqltemplate.Context;

/**
 * 
 * @author Wen
 *
 */
public class MixedSqlFragment implements SqlFragment {
	
	private List<SqlFragment> contents ;
	
	public MixedSqlFragment(List<SqlFragment> contents){
		this.contents  = contents ;
	}

	@Override
	public boolean apply(Context context) {
		
		for(SqlFragment sf : contents){
			sf.apply(context);
		}
		
		return true;
	}
	
	
	
	

}
