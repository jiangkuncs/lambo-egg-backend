package com.lambo.ndp.model;

import java.util.Date;

public class FeedBack {
    private Integer recordid;

    private String recordobjectid;

    private String type;

    private String title;

    private Integer ratecount;

    private String recorduser;

    private Date recorddate;

    private String comment;

    public Integer getRecordid() {
        return recordid;
    }

    public void setRecordid(Integer recordid) {
        this.recordid = recordid;
    }

    public String getRecordobjectid() {
        return recordobjectid;
    }

    public void setRecordobjectid(String recordobjectid) {
        this.recordobjectid = recordobjectid == null ? null : recordobjectid.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getRatecount() {
        return ratecount;
    }

    public void setRatecount(Integer ratecount) {
        this.ratecount = ratecount;
    }

    public String getRecorduser() {
        return recorduser;
    }

    public void setRecorduser(String recorduser) {
        this.recorduser = recorduser == null ? null : recorduser.trim();
    }

    public Date getRecorddate() {
        return recorddate;
    }

    public void setRecorddate(Date recorddate) {
        this.recorddate = recorddate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", recordid=").append(recordid);
        sb.append(", recordobjectid=").append(recordobjectid);
        sb.append(", type=").append(type);
        sb.append(", title=").append(title);
        sb.append(", ratecount=").append(ratecount);
        sb.append(", recorduser=").append(recorduser);
        sb.append(", recorddate=").append(recorddate);
        sb.append(", comment=").append(comment);
        sb.append("]");
        return sb.toString();
    }
}