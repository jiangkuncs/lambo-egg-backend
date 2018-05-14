package com.lambo.rest.manage.model;

import java.util.Date;

public class RestSetting {
    private String restId;

    private String restName;

    private String url;

    private String operationType;

    private String datasource;

    private String note;

    private Date createTime;

    private Date updateTime;

    private String createUser;

    private String restSql;

    private String mockData;

    public String getRestId() {
        return restId;
    }

    public void setRestId(String restId) {
        this.restId = restId == null ? null : restId.trim();
    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName == null ? null : restName.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType == null ? null : operationType.trim();
    }

    public String getDatasource() {
        return datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource == null ? null : datasource.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public String getRestSql() {
        return restSql;
    }

    public void setRestSql(String restSql) {
        this.restSql = restSql == null ? null : restSql.trim();
    }

    public String getMockData() {
        return mockData;
    }

    public void setMockData(String mockData) {
        this.mockData = mockData == null ? null : mockData.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", restId=").append(restId);
        sb.append(", restName=").append(restName);
        sb.append(", url=").append(url);
        sb.append(", operationType=").append(operationType);
        sb.append(", datasource=").append(datasource);
        sb.append(", note=").append(note);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", restSql=").append(restSql);
        sb.append(", mockData=").append(mockData);
        sb.append("]");
        return sb.toString();
    }
}