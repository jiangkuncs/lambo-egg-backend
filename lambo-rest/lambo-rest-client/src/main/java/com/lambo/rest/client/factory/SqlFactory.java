package com.lambo.rest.client.factory;

import com.lambo.rest.client.factory.sqlformat.AnalyzeContext;
import com.lambo.rest.client.factory.sqltemplate.SqlMeta;
import com.lambo.rest.client.factory.sqltemplate.SqlTemplate;
import com.lambo.rest.client.factory.sqltemplate.SqlTemplateEngin;

import java.util.Map;

/**
 * @ClassName SqlFactory
 * @Descirption TODO
 * @Author sunzhen
 * @Date 2018/5/10 11:10
 **/
public class SqlFactory {


    public static String generateSql(String sqlStr,Map paramMap){

        SqlTemplateEngin sqlTemplateEngin = new SqlTemplateEngin();
        SqlTemplate sqlTemplate = sqlTemplateEngin.getSqlTemplate(sqlStr) ;

        SqlMeta sqlMeta = sqlTemplate.process(paramMap) ;

        String result = AnalyzeContext.getContext().analyze(sqlMeta.getSql(),sqlMeta.getParameter());

        return result;
    }
}
