package com.lambo.ndp.model;

public class Subject {
    private Integer subjectId;

    private Integer categoryId;

    private String subjectName;

    private String subjectDesc;

    private Integer tableId;

    private String tableCode;

    private String createTime;

    private String periodType;

    private String organType;

    private String subjectType;

    private Integer rateCount;

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName == null ? null : subjectName.trim();
    }

    public String getSubjectDesc() {
        return subjectDesc;
    }

    public void setSubjectDesc(String subjectDesc) {
        this.subjectDesc = subjectDesc == null ? null : subjectDesc.trim();
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public String getTableCode() {
        return tableCode;
    }

    public void setTableCode(String tableCode) {
        this.tableCode = tableCode == null ? null : tableCode.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getPeriodType() {
        return periodType;
    }

    public void setPeriodType(String periodType) {
        this.periodType = periodType == null ? null : periodType.trim();
    }

    public String getOrganType() {
        return organType;
    }

    public void setOrganType(String organType) {
        this.organType = organType == null ? null : organType.trim();
    }

    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType == null ? null : subjectType.trim();
    }

    public Integer getRateCount() {
        return rateCount;
    }

    public void setRateCount(Integer rateCount) {
        this.rateCount = rateCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", subjectId=").append(subjectId);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", subjectName=").append(subjectName);
        sb.append(", subjectDesc=").append(subjectDesc);
        sb.append(", tableId=").append(tableId);
        sb.append(", tableCode=").append(tableCode);
        sb.append(", createTime=").append(createTime);
        sb.append(", periodType=").append(periodType);
        sb.append(", organType=").append(organType);
        sb.append(", subjectType=").append(subjectType);
        sb.append(", rateCount=").append(rateCount);
        sb.append("]");
        return sb.toString();
    }
}