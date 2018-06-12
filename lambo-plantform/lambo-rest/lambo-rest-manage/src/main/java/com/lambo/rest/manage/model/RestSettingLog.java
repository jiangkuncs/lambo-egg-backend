package com.lambo.rest.manage.model;

public class RestSettingLog {
    private String logId;

    private String restId;

    private String url;

    private String datasource;

    private String note;

    private String time;

    private String user;

    private String hostIp;

    private String hostName;

    private String hostMac;

    private String restSql;

    private String mockData;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user == null ? null : user.trim();
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp == null ? null : hostIp.trim();
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName == null ? null : hostName.trim();
    }

    public String getHostMac() {
        return hostMac;
    }

    public void setHostMac(String hostMac) {
        this.hostMac = hostMac == null ? null : hostMac.trim();
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
        sb.append(", logId=").append(logId);
        sb.append(", restId=").append(restId);
        sb.append(", url=").append(url);
        sb.append(", datasource=").append(datasource);
        sb.append(", note=").append(note);
        sb.append(", time=").append(time);
        sb.append(", user=").append(user);
        sb.append(", hostIp=").append(hostIp);
        sb.append(", hostName=").append(hostName);
        sb.append(", hostMac=").append(hostMac);
        sb.append(", restSql=").append(restSql);
        sb.append(", mockData=").append(mockData);
        sb.append("]");
        return sb.toString();
    }
}