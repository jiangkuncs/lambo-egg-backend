package com.lambo.common.utils.excel;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;


/**
 * @author xiliang.xiao
 * @date 2015年2月5日 下午4:50:11
 **/
@SuppressWarnings({"unchecked", "rawtypes"})
public class GenerateXSSFExcel {

    private Map<Integer, Sheet> sheetMap = new HashMap<Integer, Sheet>();
    private Map<Integer, SheetBean> sheetBeanMap = new HashMap<Integer, SheetBean>();
    /**
     * 合并单元格占用的表格
     */
    private List<int[]> merged = new ArrayList<int[]>();
    /**
     * 表格的总行数,总列数
     */
    private ArrayList m_arraylist = new ArrayList();
    /**
     * 默认单元格样式： 居左-居中-不加粗-无背景色-只读-字体9-自动换行
     */
    public static final String DEFAULT_STYLE = "left-center-n-blank-y-9-y";


    /**
     * 样式ID定义规则:水平对齐-垂直对齐-是否加粗-背景颜色-是否只读-字体大小-是否自动换行
     * 默认表头样式： 居中-居中-加粗-背景色（LIGHT_CORNFLOWER_BLUE）-只读-字体9-自动换行
     */
    public static final String DEFAULT_HEAD_STYLE = "head";
    public static final short DEFAULT_FONT_SIZE = 9;
    public static final short DEFAULT_FONT_HEADSIZE = 9;
    /**
     * 存放枚举颜色 key为颜色枚举字符串,值为颜色对应index
     */
    public static Map colorMap = new HashMap();
    /**
     * 已使用样式
     */
    private Map styleMap = new HashMap();
    private Workbook workbook;

    private int defaultRowAccessWindowSize = 15;

    /**
     * 初始化颜色枚举
     */
    static {
        Map m = HSSFColor.getMutableIndexHash();
        for (Iterator iterator = m.keySet().iterator(); iterator.hasNext(); ) {
            Object key = iterator.next();
            HSSFColor col = (HSSFColor) m.get(key);
            colorMap.put(col.getClass().getName().split("\\$")[1], col.getIndex() + "");
            // System.out.println(""+s[0]+","+s[1]+","+s[2]+"\t"+col.getClass().getName().split("\\$")[1]);
        }
    }

    /**
     * 构造函数
     */
    public GenerateXSSFExcel() {
        init(defaultRowAccessWindowSize);
    }

    /**
     * 构造函数
     */
    public GenerateXSSFExcel(int rowAccessWindowSize) {
        init(rowAccessWindowSize);
    }

    public void setArraylist(ArrayList arraylist) {
        m_arraylist.add(arraylist);
    }

    /**
     * 插入的图片二进制流数组(BASE64字符串)
     */

    private ArrayList m_arrayListPictureBitArray = new ArrayList();


    public void setarrayListPictureBitArray(ArrayList arrayListPictureBitArray) {
        m_arrayListPictureBitArray.add(arrayListPictureBitArray);
    }

    /**
     * 插入的图片尺寸(宽，高)
     */

    private ArrayList m_arrayListPictureSize = new ArrayList();


    public void setarrayListPictureSize(ArrayList arrayListPictureSize) {
        m_arrayListPictureSize.add(arrayListPictureSize);
    }


    //初始化工作区等
    private void init(int rowAccessWindowSize) {
        //建立缓存工作区，当1000保存有一1000行时缓存到磁盘
        this.workbook = new SXSSFWorkbook(rowAccessWindowSize);
        initStyle();
    }

    /**
     * 将缓存excel写到输出流
     *
     * @param os
     * @throws IOException
     */
    public void write(OutputStream os) throws IOException {
        //sheet善后处理
        if (sheetMap != null && !sheetMap.isEmpty() && sheetBeanMap != null && !sheetBeanMap.isEmpty()) {
            for (Integer i : sheetMap.keySet()) {
                Sheet sheet = sheetMap.get(i);
                SheetBean sheetBean = sheetBeanMap.get(i);
                if (m_arrayListPictureBitArray != null && m_arrayListPictureBitArray.size() > 0
                        && m_arrayListPictureBitArray.get(i) != null) {
                    String str = m_arraylist.get(i) == null ? "" : m_arraylist.get(i).toString();
                    // 总行数，总列数
                    String[] s = str.replaceAll("\\[", "").replaceAll("\\]", "").split(",");
                    if (s.length == 2) {
                        // 图片尺寸：宽，高
                        str = m_arrayListPictureSize.get(i) == null ? "" : m_arrayListPictureSize
                                .get(i).toString();
                        String[] sSize = str.replaceAll("\\[", "").replaceAll("\\]", "").split(",");
                        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
                        byte[] bt = decoder.decodeBuffer(m_arrayListPictureBitArray.get(i).toString()
                                .replaceAll("\\[", "").replaceAll("\\]", ""));
                        short cols = 15;
                        if (sSize.length == 2) {
                            cols = (short) Math.ceil(Double.valueOf(sSize[0]).doubleValue() / 65);
                        }
                        processPicture(sheet, bt, XSSFWorkbook.PICTURE_TYPE_JPEG, 0, Integer
                                        .valueOf(s[0]).intValue(), Short.valueOf(s[1]).shortValue(),
                                (short) (cols + Short.valueOf(s[1]).shortValue()));
                    }
                }
                if (sheetBean.isReadonly()) {
                    //sheet.protectSheet(StringSupport.generateID32());
                }
                if (!sheetBean.isDisplaygridlines()) {
                    sheet.setDisplayGridlines(false);
                }
                // 重新计算公式
                sheet.setForceFormulaRecalculation(true);
            }
        }
        this.workbook.write(os);
    }


    /**
     * 写入excel文件内容
     *
     * @param sheetBean
     * @param rowList
     * @throws Exception
     */
    public void processWorkSheet(SheetBean sheetBean, List<RowBean> rowList) throws Exception {
        processWorkSheet(sheetBean, rowList, null);
    }

    /**
     * 写入excel文件内容
     *
     * @param sheetBean
     * @param rowList
     * @throws Exception
     */
    public void processWorkSheet(SheetBean sheetBean, List<RowBean> rowList, CellStyle cellStyle) throws Exception {
        if (sheetBean == null) {
            throw new Exception("sheet not init!");
        }

        Sheet sheet = null;
        if (!sheetMap.containsKey(sheetBean.getIndex())) {
            //初始化sheet
            sheet = workbook.createSheet(sheetBean.getName() == null ? "sheet" : sheetBean.getName());
            sheetMap.put(sheetBean.getIndex(), sheet);
            //新加sheet时，将前sheet单元格占用表格清空，
            merged.clear();
        } else {
            sheet = sheetMap.get(sheetBean.getIndex());
        }

        if (sheetBean.getRowList() != null && sheetBean.getRowList().size() > 0) {
            if (rowList == null) {
                rowList = new ArrayList<>();
            }
            rowList.addAll(sheetBean.getRowList());
            sheetBean.getRowList().clear();
        }

        if (rowList != null && rowList.size() > 0) {
            int rowIndex = sheet.getPhysicalNumberOfRows() == 0 ? 0 : sheet.getLastRowNum() + 1;
            for (RowBean row : rowList) {
                pocessRow(row, sheet, rowIndex, cellStyle);
                rowIndex++;
            }
        }
    }

    /**
     * 行数据写入
     *
     * @param r
     * @param sheet
     * @param rowIndex
     * @param cellStyle
     * @throws Exception
     */
    private void pocessRow(RowBean r, Sheet sheet, int rowIndex, CellStyle cellStyle) throws Exception {
        Row row = sheet.createRow(rowIndex);
        if (r.getHeight() != 0) {
            row.setHeightInPoints(r.getHeight());
        }
        if (r.getCellList() != null && r.getCellList().size() > 0) {
            pocessCell(r, row, sheet, rowIndex, cellStyle);
        }
    }

    /**
     * 写入单元格数据
     *
     * @param row
     * @throws Exception
     */
    private void pocessCell(RowBean r, Row row, Sheet sheet, int rowIndex, CellStyle cellStyle) throws Exception {
        int cellIndex = 0;
        for (int i = 0; i < r.getCellList().size(); i++) {
            CellBean cb = r.getCellList().get(i);
            cellIndex = checkMerged(rowIndex, cellIndex);

            Cell cell = row.createCell(cellIndex);

            String type = "String";
            Object value = cb.getValue();
            if (value != null) {
                type = value.getClass().getSimpleName();
            }
            try {
                // 设置单元格公式
                if (!"".equals(cb.getFormula())) {
                    cell.setCellFormula(cb.getFormula());
                }
                // 设置单元格值
                if (value != null) {
                    if ("Double".equalsIgnoreCase(type) && !"".equals(value)) {
                        cell.setCellType(CellType.NUMERIC);
                        cell.setCellValue(Double.parseDouble(value.toString()));
                    }
                    if ("Integer".equalsIgnoreCase(type) && !"".equals(value)) {
                        cell.setCellType(CellType.NUMERIC);
                        cell.setCellValue(Integer.parseInt(value.toString()));
                    } else {
                        cell.setCellType(CellType.STRING);
                        cell.setCellValue(new XSSFRichTextString(value.toString()));
                    }
                } else {
                    cell.setCellType(CellType.STRING);
                }
            } catch (Exception e) {
            }

            if (cb.getMerge() != null) {
                String[] mergeds = cb.getMerge().toUpperCase()
                        .split(",");
                if (mergeds.length == 4) {
                    int fromRow = Integer.parseInt(mergeds[0]) - 1;
                    short fromCol = (short) (Integer.parseInt(mergeds[1]) - 1);
                    int toRow = Integer.parseInt(mergeds[2]) - 1;
                    short toCol = (short) (Integer.parseInt(mergeds[3]) - 1);
                    sheet.addMergedRegion(new CellRangeAddress(fromRow, toRow, fromCol, toCol));
                    //记录已占用的表格
                    merged.add(new int[]{fromRow, fromCol, toRow, toCol});
                } else {
                    throw new Exception("Merge cells is not configured correctly:"
                            + cb.getMerge());
                }
            }
            // 设置单元格宽度，不乘以系数
            if (cb.getWidthn() != 0) {
                sheet.setColumnWidth(cellIndex, cb.getWidthn());
            } else if (cb.getWidth() != 0) {
                // 设置单元格宽度
                sheet.setColumnWidth(cellIndex, (short) (cb.getWidth() * 36.55));
            }
            // 单元格是否可可见
            if (cb.isHidden()) {
                sheet.setColumnHidden(cellIndex, true);
            }
            cell.setCellStyle(cellStyle == null ? creatCellStyle(cb, r) : cellStyle);

            cellIndex++;
        }
    }

    /**
     * 检查当前表格是否在已合并单元格所占用的表格中，并返回可用的cellIndex
     *
     * @param rowIndex
     * @param cellIndex
     * @return
     */
    private int checkMerged(int rowIndex, int cellIndex) {
        if (merged != null && !merged.isEmpty()) {
            //fromRow, fromCol, toRow, toCol
            for (int[] m : merged) {
                //当前行大于或小于最大占用行时，肯定不会在单元格中
                if (rowIndex > m[2] || rowIndex < m[0]) {
                    continue;
                }
                //当前列大于或小于最大占用行时，肯定不会在单元格中
                if (cellIndex > m[3] || cellIndex < m[1]) {
                    continue;
                }
                //否则当前表格从合并单元格最大列开始
                cellIndex = m[3] + 1;
                //递归检查
                cellIndex = checkMerged(rowIndex, cellIndex);
            }
        }
        return cellIndex;
    }

    /**
     * 根据cellBean rowBena创建cellStyle
     *
     * @param cb
     * @param r
     * @return
     * @throws Exception
     */
    public CellStyle creatCellStyle(CellBean cb, RowBean r) throws Exception {
        // 根据配置设置单元格样式
        Map<String, String> styleAtts = new HashMap<String, String>();
        StringBuffer styleStr = new StringBuffer();
        // 水平对齐 left-center-right
        if (r.getAlign() != null || cb.getAlign() != null) {
            String align = "";
            if (cb.getAlign() != null) {
                align = cb.getAlign();
            } else {
                align = r.getAlign();
            }
            styleAtts.put("align", align);
            styleStr.append(align);
        } else {
            styleAtts.put("align", "left");
            styleStr.append("left");
        }
        // 垂直对齐 top-center-bottom
        if (r.getValign() != null
                || cb.getValign() != null) {
            String valign = "";
            if (cb.getValign() != null) {
                valign = cb.getValign();
            } else {
                valign = r.getValign();
            }
            styleAtts.put("valign", valign);
            styleStr.append("-" + valign);
        } else {
            styleAtts.put("valign", "center");
            styleStr.append("-center");
        }
        // 字体加粗
        if (cb.isBorder()) {
            styleAtts.put("border", "y");
            styleStr.append("-y");
        } else {
            styleAtts.put("border", "n");
            styleStr.append("-n");
        }
        // 字体颜色
        if (r.getFontColor() != null || cb.getFontColor() != null) {
            String font_color = "";
            if (cb.getFontColor() != null) {
                font_color = cb.getFontColor();
            } else {
                font_color = r.getFontColor();
            }
            styleAtts.put("font_color", font_color);
            styleStr.append("-" + font_color);
        } else {
            styleAtts.put("font_color", XSSFFont.COLOR_NORMAL + "");
            styleStr.append("-" + XSSFFont.COLOR_NORMAL);
        }
        // 字体
        if (r.getFontName() != null
                || cb.getFontName() != null) {
            String font_name = "";
            if (cb.getFontName() != null) {
                font_name = cb.getFontName();
            } else {
                font_name = r.getFontName();
            }
            styleAtts.put("font_name", font_name);
            styleStr.append("-" + font_name);
        } else {
            styleAtts.put("font_name", "宋体");
            styleStr.append("-宋体");
        }
        // 背景颜色-不需要转换颜色
        if (r.getBgcolorn() != null
                || cb.getBgcolorn() != null) {
            String bgcolorn = "";
            if (cb.getBgcolorn() != null) {
                bgcolorn = cb.getBgcolorn();
            } else {
                bgcolorn = r.getBgcolorn();
            }
            styleAtts.put("bgcolorn", bgcolorn);
            styleStr.append("-" + bgcolorn);
        } else if (r.getBgcolor() != null
                || cb.getBgcolor() != null) {
            String bgcolor = "";
            if (cb.getBgcolor() != null) {
                bgcolor = cb.getBgcolor();
            } else {
                bgcolor = r.getBgcolor();
            }
            styleAtts.put("bgcolor", bgcolor);
            styleStr.append("-" + bgcolor);
        } else {
            styleAtts.put("bgcolor", "blank");
            styleStr.append("-blank");
        }
        // 单元格是否可编辑
        if (!cb.isReadonly()) {
            styleAtts.put("readonly", "n");
            styleStr.append("-n");
        } else {
            styleAtts.put("readonly", "y");
            styleStr.append("-y");
        }
        // 列属性-边框上
        if (cb.getBordertop() != null) {
            String bordertop = cb.getBordertop();
            styleAtts.put("bordertop", bordertop);
            styleStr.append("-" + bordertop);
        } else {
            styleAtts.put("bordertop", BorderStyle.THIN + "");
            styleStr.append("-" + BorderStyle.THIN);
        }
        // 列属性-边框下
        if (cb.getBorderbottom() != null) {
            String borderbottom = cb.getBorderbottom();
            styleAtts.put("borderbottom", borderbottom);
            styleStr.append("-" + borderbottom);
        } else {
            styleAtts.put("borderbottom", BorderStyle.THIN + "");
            styleStr.append("-" + BorderStyle.THIN);
        }
        // 列属性-边框左
        if (cb.getBorderleft() != null) {
            String borderleft = cb.getBorderleft();
            styleAtts.put("borderleft", borderleft);
            styleStr.append("-" + borderleft);
        } else {
            styleAtts.put("borderleft", BorderStyle.THIN + "");
            styleStr.append("-" + BorderStyle.THIN);
        }
        // 列属性-边框右
        if (cb.getBorderright() != null) {
            String borderright = cb.getBorderright();
            styleAtts.put("borderright", borderright);
            styleStr.append("-" + borderright);
        } else {
            styleAtts.put("borderright", BorderStyle.THIN + "");
            styleStr.append("-" + BorderStyle.THIN);
        }
        // 字体大小
        if (cb.getSize() != null || r.getSize() != null) {
            String size = "";
            if (cb.getSize() != null) {
                size = cb.getSize();
            } else {
                size = r.getSize();
            }
            styleAtts.put("size", size);
            styleStr.append("-" + size);
        } else {
            styleAtts.put("size", DEFAULT_FONT_SIZE + "");
            styleStr.append("-" + DEFAULT_FONT_SIZE);
        }

        // 单元格是否自动换行
        if (!cb.isWrap()) {
            styleAtts.put("wrap", "n");
            styleStr.append("-n");
        } else {
            styleAtts.put("wrap", "y");
            styleStr.append("-y");
        }

        // 生成单元格样式
        if (r.getStyle() != null || cb.getStyle() != null) {
            String style = "";
            if (cb.getStyle() != null) {
                style = cb.getStyle();
            } else {
                style = r.getStyle();
            }
            if ("head".equalsIgnoreCase(style)) {
                return (XSSFCellStyle) styleMap.get(DEFAULT_HEAD_STYLE);
            } else {
                return getCellStyle(styleAtts, styleStr.toString());
            }
        } else {
            return getCellStyle(styleAtts, styleStr.toString());
        }
    }

    /**
     * 获取CELL样式
     *
     * @param stylsAtts
     * @param styleStr
     * @return
     * @throws Exception
     */
    private CellStyle getCellStyle(Map stylsAtts, String styleStr) throws Exception {

        if (styleStr == null || "".equalsIgnoreCase(styleStr)
                || DEFAULT_STYLE.equalsIgnoreCase(styleStr)) {
            return (XSSFCellStyle) styleMap.get(DEFAULT_STYLE);
        }


        if (styleMap.get(styleStr) != null) {
            return (XSSFCellStyle) styleMap.get(styleStr);
        }
        CellStyle cellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        cellStyle.setWrapText(true);

        for (Iterator i = stylsAtts.keySet().iterator(); i.hasNext(); ) {
            String key = (String) i.next();
            String value = stylsAtts.get(key).toString();
            if ("align".equalsIgnoreCase(key)) {
                // 水平对齐
                if (value.equalsIgnoreCase("left")) {
                    cellStyle.setAlignment(HorizontalAlignment.LEFT);
                } else if (value.equalsIgnoreCase("center")) {
                    cellStyle.setAlignment(HorizontalAlignment.CENTER);
                } else if (value.equalsIgnoreCase("right")) {
                    cellStyle.setAlignment(HorizontalAlignment.RIGHT);
                } else {
                    cellStyle.setAlignment(HorizontalAlignment.LEFT);
                }
            } else if ("valign".equalsIgnoreCase(key)) {
                // 垂直对齐
                if (value.equalsIgnoreCase("top")) {
                    cellStyle.setVerticalAlignment(VerticalAlignment.TOP);
                } else if (value.equalsIgnoreCase("center")) {
                    cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                } else if (value.equalsIgnoreCase("bottom")) {
                    cellStyle.setVerticalAlignment(VerticalAlignment.BOTTOM);
                } else {
                    cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                }
            } else if ("border".equalsIgnoreCase(key)) {
                // 是否加粗
                if ("y".equalsIgnoreCase(value)) {
                    font.setBold(true);
                }
            } else if ("bgcolor".equalsIgnoreCase(key)) {
                // 单元格背景颜色
                cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                value = value.toUpperCase();

                Object color = colorMap.get(value);
                if (color != null) {
                    cellStyle.setFillForegroundColor(Short.parseShort(color.toString()));
                } else {
                    cellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
                }
            } else if ("bgcolorn".equalsIgnoreCase(key)) {
                // 单元格背景颜色-不需要转换颜色
                cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                cellStyle.setFillForegroundColor(Short.parseShort(value));
            } else if ("readonly".equalsIgnoreCase(key)) {
                // 设置单元格是否可编辑
                if ("y".equals(value)) {
                    cellStyle.setLocked(true);
                } else {
                    cellStyle.setLocked(false);
                }
            } else if ("size".equalsIgnoreCase(key)) {
                // 单元格字体大小
                font.setFontHeightInPoints(Short.parseShort(value));
            } else if ("font_color".equalsIgnoreCase(key)) {
                // 单元格字体颜色
                font.setColor(Short.parseShort(value));
            } else if ("font_name".equalsIgnoreCase(key)) {
                // 单元格字体
                font.setFontName(value);
            } else if ("bordertop".equalsIgnoreCase(key)) {
                // 边框上
                cellStyle.setBorderTop(BorderStyle.valueOf(value));
            } else if ("borderbottom".equalsIgnoreCase(key)) {
                // 边框下
                cellStyle.setBorderBottom(BorderStyle.valueOf(value));
            } else if ("borderleft".equalsIgnoreCase(key)) {
                // 边框左
                cellStyle.setBorderLeft(BorderStyle.valueOf(value));
            } else if ("borderright".equalsIgnoreCase(key)) {
                // 边框右
                cellStyle.setBorderRight(BorderStyle.valueOf(value));
            } else if ("wrap".equalsIgnoreCase(key)) {
                // 单元格自动换行
                if ("n".equalsIgnoreCase(value)) {
                    cellStyle.setWrapText(false);
                } else {
                    cellStyle.setWrapText(true);
                }

            }
        }
        cellStyle.setFont(font);
        styleMap.put(styleStr, cellStyle);
        return cellStyle;
    }

    private void processPicture(Sheet workSheet, byte[] btPic, int picType, int startRow,
                                int endRow, short startCol, short endCol) {
        Drawing patriarch = workSheet.createDrawingPatriarch();
        XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 512, 255, (short) startCol, startRow,
                (short) endCol, endRow);
        patriarch.createPicture(anchor, workbook.addPicture(btPic, picType));
    }

    /**
     * 初始化默认样式
     */
    private void initStyle() {
        CellStyle defaultStyle = workbook.createCellStyle();
        Font defaultFont = workbook.createFont();
        defaultStyle.setBorderBottom(BorderStyle.THIN);
        defaultStyle.setBorderLeft(BorderStyle.THIN);
        defaultStyle.setBorderRight(BorderStyle.THIN);
        defaultStyle.setBorderTop(BorderStyle.THIN);
        defaultStyle.setWrapText(true);
        defaultFont.setFontHeightInPoints((short) DEFAULT_FONT_SIZE);
        defaultStyle.setFont(defaultFont);
        styleMap.put(DEFAULT_STYLE, defaultStyle);

        CellStyle defaultHeadStyle = workbook.createCellStyle();
        Font defaultHeadFont = workbook.createFont();
        defaultHeadStyle.setBorderBottom(BorderStyle.THIN);
        defaultHeadStyle.setBorderLeft(BorderStyle.THIN);
        defaultHeadStyle.setBorderRight(BorderStyle.THIN);
        defaultHeadStyle.setBorderTop(BorderStyle.THIN);
        defaultHeadStyle.setAlignment(HorizontalAlignment.CENTER);
        defaultHeadStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        defaultHeadStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        defaultHeadStyle.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        defaultHeadStyle.setLocked(true);
        defaultHeadStyle.setWrapText(true);

        defaultHeadFont.setBold(true);
        defaultHeadFont.setFontHeightInPoints((short) DEFAULT_FONT_HEADSIZE);
        defaultHeadStyle.setFont(defaultHeadFont);

        styleMap.put(DEFAULT_HEAD_STYLE, defaultHeadStyle);
    }


    public Workbook getWorkbook() {
        return workbook;
    }

    public void setWorkbook(Workbook workbook) {
        this.workbook = workbook;
    }

    public static void main(String[] args) throws Exception {
        GenerateXSSFExcel g = new GenerateXSSFExcel();

        SheetBean sb = new SheetBean();
        for (int n = 0; n < 2; n++) {
            sb.setIndex(n);
            sb.setName("huge test" + n);
            List<RowBean> rList = new ArrayList<RowBean>();
            for (int i = 0; i < 11; i++) {
                RowBean rb = new RowBean();
                List<CellBean> cList = new ArrayList<CellBean>();
                for (int j = 0; j < 4; j++) {
                    CellBean cb = new CellBean();
                    cb.setValue(i + " @ " + j);
                    if (i == 1 && j == 1) {
                        cb.setMerge("1,1,3,3");
                        cList.add(cb);
                    }
                    if (!(i >= 0 && i <= 2 && j >= 0 && j <= 2)) {
                        cList.add(cb);
                    }
                }
                rb.setCellList(cList);
                rList.add(rb);
            }
            g.processWorkSheet(sb, rList);
            rList.clear();
        }
        FileOutputStream os = new FileOutputStream(new File("d:/huge2.xlsx"));
        g.write(os);
        os.flush();
    }

}