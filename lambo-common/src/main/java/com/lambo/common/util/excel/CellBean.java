package com.lambo.common.util.excel;

/**
 * @author xiliang.xiao
 * @date 2015年2月6日 上午9:11:50
 **/
public class CellBean {

    /**
     * 宽度*36.55
     */
    private int width;
    /**
     * 宽度，比width优先
     */
    private int widthn;
    private Object value;
    /**
     * 公式
     */
    private String formula;
    /**
     * 合并单元格 由4个数字,号分割fromRow, fromCol, toRow, toCol
     */
    private String merge;
    /**
     * 水平位置 left,right,center
     */
    private String align;
    /**
     * 垂直位置 top,bottom,center
     */
    private String valign;
    /**
     * 字体加粗
     */
    private boolean border = false;
    /**
     * 字体颜色
     */
    private String fontColor;
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
     * 是否可编辑
     */
    private boolean readonly = true;
    /**
     * 列属性-边框上CellStyle.BORDER*
     */
    private String bordertop;
    private String borderleft;
    private String borderbottom;
    private String borderright;
    /**
     * 字体 大小
     */
    private String size;
    /**
     * 是否隐藏
     */
    private boolean hidden = false;
    /**
     * 是否换行
     */
    private boolean wrap = true;
    /**
     * 单元格样式
     */
    private String style;


    public CellBean() {
        super();
    }

    public CellBean(Object value) {
        super();
        this.value = value;
    }

    public CellBean(int width, Object value) {
        super();
        this.width = width;
        this.value = value;
    }

    public CellBean(Object value, String merge) {
        super();
        this.value = value;
        this.merge = merge;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidthn() {
        return widthn;
    }

    public void setWidthn(int widthn) {
        this.widthn = widthn;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getMerge() {
        return merge;
    }

    public void setMerge(String merge) {
        this.merge = merge;
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

    public boolean isBorder() {
        return border;
    }

    public void setBorder(boolean border) {
        this.border = border;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
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

    public boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }

    public String getBordertop() {
        return bordertop;
    }

    public void setBordertop(String bordertop) {
        this.bordertop = bordertop;
    }

    public String getBorderleft() {
        return borderleft;
    }

    public void setBorderleft(String borderleft) {
        this.borderleft = borderleft;
    }

    public String getBorderbottom() {
        return borderbottom;
    }

    public void setBorderbottom(String borderbottom) {
        this.borderbottom = borderbottom;
    }

    public String getBorderright() {
        return borderright;
    }

    public void setBorderright(String borderright) {
        this.borderright = borderright;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isWrap() {
        return wrap;
    }

    public void setWrap(boolean wrap) {
        this.wrap = wrap;
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


    public CellBean(int width, int widthn, Object value, String formula,
                    String merge, String align, String valign, boolean border,
                    String fontColor, String fontName, String bgcolor, String bgcolorn,
                    boolean readonly, String bordertop, String borderleft,
                    String borderbottom, String borderright, String size,
                    boolean hidden, boolean wrap, String style) {
        super();
        this.width = width;
        this.widthn = widthn;
        this.value = value;
        this.formula = formula;
        this.merge = merge;
        this.align = align;
        this.valign = valign;
        this.border = border;
        this.fontColor = fontColor;
        this.fontName = fontName;
        this.bgcolor = bgcolor;
        this.bgcolorn = bgcolorn;
        this.readonly = readonly;
        this.bordertop = bordertop;
        this.borderleft = borderleft;
        this.borderbottom = borderbottom;
        this.borderright = borderright;
        this.size = size;
        this.hidden = hidden;
        this.wrap = wrap;
        this.style = style;
    }

    /**
     * @param width
     * @param value
     * @param merge
     */
    public CellBean(int width, Object value, String merge) {
        super();
        this.width = width;
        this.value = value;
        this.merge = merge;
    }

    /**
     * @param value
     * @param border
     */
    public CellBean(Object value, boolean border) {
        super();
        this.value = value;
        this.border = border;
    }


}