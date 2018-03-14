package com.lambo.ndp.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="用户对象")
public class TableCell {

    private Integer cellId;
    private Integer tableId;
    @ApiModelProperty(value="标识",example="Legth")
    private String cellCode;
    @ApiModelProperty(value="名称",example="重量")
    private String cellName;
    public Integer getCellId() {
        return cellId;
    }

    public void setDataDesc(String dataDesc) {
        this.dataDesc = dataDesc;
    }

    public String getDataDesc() {
        return dataDesc;
    }

    public void setCellId(Integer cellId) {
        this.cellId = cellId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public void setCellCode(String cellCode) {
        this.cellCode = cellCode;
    }

    public void setCellName(String cellName) {
        this.cellName = cellName;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    public void setDataUnit(String dataUnit) {
        this.dataUnit = dataUnit;
    }



    public Integer getTableId() {
        return tableId;

    }

    public String getCellCode() {
        return cellCode;
    }

    public String getCellName() {
        return cellName;
    }

    public String getDictId() {
        return dictId;
    }

    public String getDataUnit() {
        return dataUnit;
    }


    @ApiModelProperty(value="字典",example="length")
    private String dictId;
    @ApiModelProperty(value="计量单位",example="米")
    private String dataUnit;
    @ApiModelProperty(value="描述", example="长度")
    private String dataDesc;


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cellId=").append(cellId);
        sb.append(", tableId=").append(tableId);
        sb.append(", cellCode=").append(cellCode);
        sb.append(", cellName=").append(getCellName());
        sb.append(", dictId=").append(dictId);
        sb.append(", dataUnit=").append(dataUnit);
        sb.append(", dataDesc=").append(dataDesc);
        sb.append("]");
        return sb.toString();
    }
}