package com.lambo.rest.manage.model;

public class RestSettingParamsLog {
    private String logId;

    private String restId;

    private String paramKey;

    private String paramName;

    private String necessary;

    private String defaultValue;

    private String note;

    private Integer orderSeq;

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId == null ? null : logId.trim();
    }

    public String getRestId() {
        return restId;
    }

    public void setRestId(String restId) {
        this.restId = restId == null ? null : restId.trim();
    }

    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey == null ? null : paramKey.trim();
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName == null ? null : paramName.trim();
    }

    public String getNecessary() {
        return necessary;
    }

    public void setNecessary(String necessary) {
        this.necessary = necessary == null ? null : necessary.trim();
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue == null ? null : defaultValue.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public Integer getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(Integer orderSeq) {
        this.orderSeq = orderSeq;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", logId=").append(logId);
        sb.append(", restId=").append(restId);
        sb.append(", paramKey=").append(paramKey);
        sb.append(", paramName=").append(paramName);
        sb.append(", necessary=").append(necessary);
        sb.append(", defaultValue=").append(defaultValue);
        sb.append(", note=").append(note);
        sb.append(", orderSeq=").append(orderSeq);
        sb.append("]");
        return sb.toString();
    }
}