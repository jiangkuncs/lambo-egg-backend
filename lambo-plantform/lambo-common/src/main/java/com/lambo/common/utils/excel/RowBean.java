package com.lambo.common.utils.excel;

import java.util.List;

/**
 * @author xiliang.xiao
 * @date 2015年2月6日 上午9:11:59
 **/
public class RowBean {

    private int height;
    /**
     * 水平位置 left,right,center
     */
    private String align;
    /**
     * 垂直位置 top,bottom,center
     */
    private String valign;
    /**
     * 字体颜色
     */
    private String fontColor;
    /**
     * 字体加粗
     */
    private boolean border = false;
    /**
     * 字体
     */
    private String fontName;
    /**
     * 背景颜色
     */
    private String bgcolor;
    /**
     * 背景颜色,比bgcolor优先
     */
    private String bgcolorn;
    /**
     * 字体 大小
     */
    private String size;
    /**
     * 单元格样式
     */
    private String style;
    private List<CellBean> cellList;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<CellBean> getCellList() {
        return cellList;
    }

    public void setCellList(List<CellBean> cellList) {
        this.cellList = cellList;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public String getValign() {
        return valign;
    }

    public void setValign(String valign) {
        this.valign = valign;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public boolean isBorder() {
        return border;
    }

    public void setBorder(boolean border) {
        this.border = border;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public String getBgcolor() {
        return bgcolor;
    }

    public void setBgcolor(String bgcolor) {
        this.bgcolor = bgcolor;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getBgcolorn() {
        return bgcolorn;
    }

    public void setBgcolorn(String bgcolorn) {
        this.bgcolorn = bgcolorn;
    }

}