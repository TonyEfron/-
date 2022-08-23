package com.ls.parcel.controller;

import com.ls.parcel.mapper.CompanyMapper;
import com.ls.parcel.mapper.ParcelMapper;
import com.ls.parcel.mapper.SendParcelMapper;
import com.ls.parcel.mapper.StudentMapper;
import com.ls.parcel.pojo.Company;
import com.ls.parcel.pojo.Parcel;
import com.ls.parcel.pojo.Sendparcel;
import com.ls.parcel.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;

@Controller
public class StuController {
    @Autowired
    private StudentMapper studentMapper;
    private ArrayList<Parcel> parcels;
    @Autowired
    private ParcelMapper parcelMapper;
    @Autowired
    private SendParcelMapper sendParcelMapper;
    @Autowired
    private CompanyMapper companyMapper;

    @GetMapping("/StuAllParcel/{id}")
    public String toStuAllParcel(@PathVariable("id")int studentid, HttpServletRequest model){
        parcels=parcelMapper.findParcelByStuId(studentid);
        Student student=studentMapper.findStuById(studentid);
        model.setAttribute("Allparcel",parcels);
        model.setAttribute("loginStudent",student);
        System.out.println("进入主界面");
        return "stuindex";
    }
    @GetMapping("/StuPartCard/{id}")
    public String toStuPartCard(@PathVariable("id")int studentid, HttpServletRequest model){
        parcels=parcelMapper.findParcelByStuId(studentid);
        Student student=studentMapper.findStuById(studentid);
        model.setAttribute("Allparcel",parcels);
        model.setAttribute("loginStudent",student);
        System.out.println("进入待取界面");
        return "stupartcard";
    }








    @GetMapping("/StuForm/{id}")
    public String toStuForm(@PathVariable("id")int studentid, HttpServletRequest model){
        Student student=studentMapper.findStuById(studentid);
        ArrayList<Company> companies= companyMapper.findAllCompany();
        model.setAttribute("companies",companies);
        model.setAttribute("loginStudent",student);
        System.out.println("进入提交寄件界面");
        return "stuform";
    }


    @PostMapping("/SendParcelSign/{id}")
    public String toSendParcelSign(@PathVariable("id")int studentid,Sendparcel sendparcel,HttpServletRequest model){
        System.out.println(sendparcel);
        sendparcel.setSendsatus("0");
        sendparcel.setStuid(studentid);
        Student student=studentMapper.findStuById(studentid);
        int rows = sendParcelMapper.StuSignParcel(sendparcel);
        model.setAttribute("loginStudent",student);
        System.out.println("学号"+studentid+"提交寄件");
        return "stuform";
    }

    @GetMapping("/StuSendCard/{id}")
    public String toStuSendCard(@PathVariable("id")int studentid,HttpServletRequest model){
        Student student=studentMapper.findStuById(studentid);
        model.setAttribute("loginStudent",student);

        ArrayList<Sendparcel> sendparcels=sendParcelMapper.findSendParcelByStuId(studentid);
        model.setAttribute("sendparcels",sendparcels);
        System.out.println("进入全部寄件界面");
        return "stusendcard";
    }

    @GetMapping("/StuUserCard/{id}")
    public String toStuUserCard(@PathVariable("id")int studentid,HttpServletRequest model){
        Student student=studentMapper.findStuById(studentid);
        model.setAttribute("loginStudent",student);


        System.out.println("进入个人界面");
        return "stuusercard";
    }






}
