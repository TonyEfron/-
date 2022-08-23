package com.ls.parcel.pojo;

import java.sql.Date;

public class Sendparcel {
    private String sparcelname;
    private String sendaddress;
    private int sparcelid;
    private String sendsatus;
    private int stuid;
    private String tip;
    private int comid;

    public Sendparcel() {
    }

    public Sendparcel(String sparcelname, String sendaddress, int sparcelid, String sendsatus, int stuid, String tip, int comid) {
        this.sparcelname = sparcelname;
        this.sendaddress = sendaddress;
        this.sparcelid = sparcelid;
        this.sendsatus = sendsatus;
        this.stuid = stuid;
        this.tip = tip;
        this.comid = comid;
    }

    public String getSparcelname() {
        return sparcelname;
    }

    public void setSparcelname(String sparcelname) {
        this.sparcelname = sparcelname;
    }

    public String getSendaddress() {
        return sendaddress;
    }

    public void setSendaddress(String sendaddress) {
        this.sendaddress = sendaddress;
    }

    public int getSparcelid() {
        return sparcelid;
    }

    public void setSparcelid(int sparcelid) {
        this.sparcelid = sparcelid;
    }

    public String getSendsatus() {
        return sendsatus;
    }

    public void setSendsatus(String snedsatus) {
        this.sendsatus = snedsatus;
    }

    public int getStuid() {
        return stuid;
    }

    public void setStuid(int stuid) {
        this.stuid = stuid;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public int getComid() {
        return comid;
    }

    public void setComid(int comid) {
        this.comid = comid;
    }

    @Override
    public String toString() {
        return "Sendparcel{" +
                "sparcelname='" + sparcelname + '\'' +
                ", sendaddress='" + sendaddress + '\'' +
                ", sparcelid=" + sparcelid +
                ", sendsatus='" + sendsatus + '\'' +
                ", stuid=" + stuid +
                ", tip='" + tip + '\'' +
                ", comid=" + comid +
                '}';
    }
}
