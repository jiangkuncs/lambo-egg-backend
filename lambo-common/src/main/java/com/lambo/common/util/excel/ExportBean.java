package com.lambo.common.util.excel;

import java.util.List;

/**
 * @author SmartMan
 * @description 海量数据导出，参数
 */
public class ExportBean {

    /**
     * 表头信息
     */
    private List headList;
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 查询sql
     */
    private String sql;

    public List getHeadList() {
        return headList;
    }

    public void setHeadList(List headList) {
        this.headList = headList;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}