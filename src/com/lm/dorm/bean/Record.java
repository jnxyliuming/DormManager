package com.lm.dorm.bean;


public class Record {
    private Integer id;
    private Integer StuId;
    private String date;
    private String remark;
    private Integer disabled;

    public Record() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public Integer getStuId() {
        return StuId;
    }

    public String getDate() {
        return date;
    }

    public String getRemark() {
        return remark;
    }

    public Integer getDisabled() {
        return disabled;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStuId(Integer stuId) {
        StuId = stuId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setDisabled(Integer disabled) {
        this.disabled = disabled;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", StuId=" + StuId +
                ", date='" + date + '\'' +
                ", remark='" + remark + '\'' +
                ", disabled=" + disabled +
                '}';
    }

    public Record(Integer stuId, String date, String remark, Integer disabled) {
        StuId = stuId;
        this.date = date;
        this.remark = remark;
        this.disabled = disabled;
    }

    public Record(Integer stuId, String date, String remark) {
        StuId = stuId;
        this.date = date;
        this.remark = remark;
    }
}