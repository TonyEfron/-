package com.ls.parcel.pojo;

public class Student {
    private String stuname;
    private String stupassword;
    private int stuid;
    private String stuemail;

    public Student() {
    }

    public Student(String stuname, String stupassword, int stuid, String stuemail) {
        this.stuname = stuname;
        this.stupassword = stupassword;
        this.stuid = stuid;
        this.stuemail = stuemail;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public String getStupassword() {
        return stupassword;
    }

    public void setStupassword(String stupassword) {
        this.stupassword = stupassword;
    }

    public int getStuid() {
        return stuid;
    }

    public void setStuid(int stuid) {
        this.stuid = stuid;
    }

    public String getStuemail() {
        return stuemail;
    }

    public void setStuemail(String stuemail) {
        this.stuemail = stuemail;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuname='" + stuname + '\'' +
                ", stupassword='" + stupassword + '\'' +
                ", stuid=" + stuid +
                ", stuemail='" + stuemail + '\'' +
                '}';
    }
}
