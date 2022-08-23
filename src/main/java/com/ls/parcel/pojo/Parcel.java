package com.ls.parcel.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

public class Parcel {
    private String parcelname;
    private int parcelid;
    private int stuid;
    private String status;
    private String address;
    private String cname;
    private Date arrtime;
    private String pic;

    public Parcel() {
    }

    public Parcel(String parcelname, int parcelid, int stuid, String status, String address, String cname, Date arrtime, String pic) {
        this.parcelname = parcelname;
        this.parcelid = parcelid;
        this.stuid = stuid;
        this.status = status;
        this.address = address;
        this.cname = cname;
        this.arrtime = arrtime;
        this.pic = pic;
    }

    public String getParcelname() {
        return parcelname;
    }

    public void setParcelname(String parcelname) {
        this.parcelname = parcelname;
    }

    public int getParcelid() {
        return parcelid;
    }

    public void setParcelid(int parcelid) {
        this.parcelid = parcelid;
    }

    public int getStuid() {
        return stuid;
    }

    public void setStuid(int stuid) {
        this.stuid = stuid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Date getArrtime() {
        return arrtime;
    }

    public void setArrtime(Date arrtime) {
        this.arrtime = arrtime;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "Parcel{" +
                "parcelname='" + parcelname + '\'' +
                ", parcelid=" + parcelid +
                ", stuid=" + stuid +
                ", status='" + status + '\'' +
                ", address='" + address + '\'' +
                ", cname='" + cname + '\'' +
                ", arrtime=" + arrtime +
                ", pic='" + pic + '\'' +
                '}';
    }
}
