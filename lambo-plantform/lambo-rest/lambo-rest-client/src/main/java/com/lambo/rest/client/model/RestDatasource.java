package com.lambo.rest.client.model;

public class RestDatasource {
    private String dsId;

    private String dsName;

    private String dsType;

    private String dsIp;

    private Integer dsPort;

    private String dsDatabase;

    private String dsUser;

    private String dsPassword;

    private String note;

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

    public String getDsIp() {
        return dsIp;
    }

    public void setDsIp(String dsIp) {
        this.dsIp = dsIp == null ? null : dsIp.trim();
    }

    public Integer getDsPort() {
        return dsPort;
    }

    public void setDsPort(Integer dsPort) {
        this.dsPort = dsPort;
    }

    public String getDsDatabase() {
        return dsDatabase;
    }

    public void setDsDatabase(String dsDatabase) {
        this.dsDatabase = dsDatabase == null ? null : dsDatabase.trim();
    }

    public String getDsUser() {
        return dsUser;
    }

    public void setDsUser(String dsUser) {
        this.dsUser = dsUser == null ? null : dsUser.trim();
    }

    public String getDsPassword() {
        return dsPassword;
    }

    public void setDsPassword(String dsPassword) {
        this.dsPassword = dsPassword == null ? null : dsPassword.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
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
        sb.append(", dsIp=").append(dsIp);
        sb.append(", dsPort=").append(dsPort);
        sb.append(", dsDatabase=").append(dsDatabase);
        sb.append(", dsUser=").append(dsUser);
        sb.append(", dsPassword=").append(dsPassword);
        sb.append(", note=").append(note);
        sb.append("]");
        return sb.toString();
    }
}