package com.lambo.rest.manage.model;

public class RestDatasource {
    private String dsId;

    private String dsName;

    private String dsType;

    public String getDsId() {
        return dsId;
    }

    public void setDsId(String dsId) {
        this.dsId = dsId == null ? null : dsId.trim();
    }

    public String getDsName() {
        return dsName;
    }

    public void setDsName(String dsName) {
        this.dsName = dsName == null ? null : dsName.trim();
    }

    public String getDsType() {
        return dsType;
    }

    public void setDsType(String dsType) {
        this.dsType = dsType == null ? null : dsType.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", dsId=").append(dsId);
        sb.append(", dsName=").append(dsName);
        sb.append(", dsType=").append(dsType);
        sb.append("]");
        return sb.toString();
    }
}