package com.lambo.common.util.excel;

import java.util.List;

/**
 * @author xiliang.xiao
 * @date 2015年2月6日 上午9:37:26
 **/
public class SheetBean {

    /**
     * sheet 位置
     */
    private int index;
    /**
     * 是否可编辑
     */
    private boolean readonly = false;
    /**
     * 是否显示表格
     */
    private boolean displaygridlines = true;
    /**
     * sheet名字
     */
    private String name;

    private List<RowBean> rowList;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }

    public boolean isDisplaygridlines() {
        return displaygridlines;
    }

    public void setDisplaygridlines(boolean displaygridlines) {
        this.displaygridlines = displaygridlines;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RowBean> getRowList() {
        return rowList;
    }

    public void setRowList(List<RowBean> rowList) {
        this.rowList = rowList;
    }


}