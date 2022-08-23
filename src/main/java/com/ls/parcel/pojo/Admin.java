package com.ls.parcel.pojo;

public class Admin {
    private String admin;
    private String pwd;
    private int adid;

    public Admin() {
    }

    public Admin(String admin, String pwd, int adid) {
        this.admin = admin;
        this.pwd = pwd;
        this.adid = adid;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getAdid() {
        return adid;
    }

    public void setAdid(int adid) {
        this.adid = adid;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "admin='" + admin + '\'' +
                ", pwd='" + pwd + '\'' +
                ", adid=" + adid +
                '}';
    }
}
