package com.ls.parcel.pojo;

public class Company {
    private String comname;
    private int comid;
    private String comphone;
    private String comemail;

    public Company() {
    }

    public Company(String comname, int comid, String comphone, String comemail) {
        this.comname = comname;
        this.comid = comid;
        this.comphone = comphone;
        this.comemail = comemail;
    }

    public String getComname() {
        return comname;
    }

    public void setComname(String comname) {
        this.comname = comname;
    }

    public int getComid() {
        return comid;
    }

    public void setComid(int comid) {
        this.comid = comid;
    }

    public String getComphone() {
        return comphone;
    }

    public void setComphone(String comphone) {
        this.comphone = comphone;
    }

    public String getComemail() {
        return comemail;
    }

    public void setComemail(String comemail) {
        this.comemail = comemail;
    }

    @Override
    public String toString() {
        return "Company{" +
                "comname='" + comname + '\'' +
                ", comid=" + comid +
                ", comphone='" + comphone + '\'' +
                ", comemail='" + comemail + '\'' +
                '}';
    }
}
