package com.ls.parcel.controller;

import com.ls.parcel.mapper.ParcelMapper;
import com.ls.parcel.mapper.StudentMapper;
import com.ls.parcel.pojo.Parcel;
import com.ls.parcel.pojo.Student;
import com.ls.parcel.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Controller
public class IndexController {
    @Autowired
    private StudentMapper studentMapper;
    private ArrayList<Parcel> parcels;
    @Autowired
    private ParcelMapper parcelMapper;
    @Value("${spring.mail.username}")
    private String from;
    @Resource
    private SendEmailService sendSimpleEmail;


    @GetMapping("/index")
    public String toIndex(Model model){
        model.addAttribute("currentYear", Calendar.getInstance().get(Calendar.YEAR));
        System.out.println("进入首页面");
        return "index";
    }

    @GetMapping("/login")
    public String toLogin(Model model){
        model.addAttribute("currentYear", Calendar.getInstance().get(Calendar.YEAR));
        System.out.println("进入登录界面");
        return "login";
    }
    @GetMapping("/login1")
    public String toLogin1(Model model){
        model.addAttribute("currentYear", Calendar.getInstance().get(Calendar.YEAR));
        System.out.println("进入登录界面2");
        return "login1";
    }
    @GetMapping("/stuindex")
    public String toStuindex(Model model){
        model.addAttribute("currentYear", Calendar.getInstance().get(Calendar.YEAR));
        System.out.println("进入学生端主界面");
        return "stuindex";
    }
    @GetMapping("/stupanel/card")
    public String toStupanelcard(Model model){
        model.addAttribute("currentYear", Calendar.getInstance().get(Calendar.YEAR));
        System.out.println("进入无菜单主界面");
        return "stupanel/card";
    }


    @GetMapping("/admin")
    public String toAdmin(Model model){
        System.out.println("进入管理登录界面");
        return "admin";
    }

    @GetMapping("/form")
    public String toForm(Model model){
        System.out.println("进入无菜单表单界面");
        return "stupanel/form";
    }
    @GetMapping("/stuform")
    public String toStuForm(Model model){
        Student student = new Student();
        student.setStuid(2);
        model.addAttribute("loginStudent",student);
        System.out.println("直接进入学生端表单界面");
        return "stuform";
    }
    @GetMapping("/stusendcard")
    public String toStuSendCard(Model model){
        Student student = new Student();
        student.setStuid(2);
        model.addAttribute("loginStudent",student);
        System.out.println("直接进入学生端预约卡界面");
        return "stusendcard";
    }
    @GetMapping("/sendcard")
    public String toSendCard(Model model){
        Student student = new Student();
        student.setStuid(2);
        model.addAttribute("loginStudent",student);
        System.out.println("直接进入无导航栏预约卡界面");
        return "stupanel/sendcard";
    }

    @GetMapping("/partcard")
    public String toPartCard(Model model){
        Student student = new Student();
        student.setStuid(2);
        model.addAttribute("loginStudent",student);
        System.out.println("直接进入无导航栏待取卡界面");
        return "stupanel/partcard";
    }
    @GetMapping("/stupartcard")
    public String toStuPartCard(Model model){
        Student student = new Student();
        student.setStuid(2);
        model.addAttribute("loginStudent",student);
        System.out.println("直接进入待取卡界面");
        return "stupartcard";
    }
    @GetMapping("/usercard")
    public String toUserCard(Model model){
        Student student = new Student();
        student.setStuid(2);
        model.addAttribute("loginStudent",student);
        System.out.println("直接进入无导航栏待取卡界面");
        return "stupanel/usercard";
    }
    @GetMapping("/stuusercard")
    public String toStuUserCard(Model model){
        Student student = new Student();
        student.setStuid(2);
        model.addAttribute("loginStudent",student);
        System.out.println("直接进入无导航栏待取卡界面");
        return "stuusercard";
    }












    @PostMapping("/login")
    public String doLogin(Student student, HttpServletRequest model){
        if(student.getStupassword().equals(studentMapper.findPwdById(student.getStuid()))){
            parcels=parcelMapper.findParcelByStuId(student.getStuid());
            model.setAttribute("Allparcel",parcels);
            model.setAttribute("loginStudent",student);
            System.out.println("登录后进入主界面");
            return "stuindex";
        }
        System.out.println("账号密码错误重回登录界面");
        return "login";
    }


    @PostMapping("/sign")
    public String doSign(Student student, HttpServletRequest model){
        int idexist;
        System.out.println("student");
        System.out.println(student);
        if(student.getStuid()==0){

        }else if(studentMapper.findStuIdById(student.getStuid())!=null){//id已存在
            idexist=1;
            model.setAttribute("idexist",idexist);
            System.out.println("由于id已存在刷新登录界面");
            return "login";
        }
        int rows=studentMapper.addStu(student);
        idexist=2;
        model.setAttribute("idexist",idexist);

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
        String currentDate=simpleDateFormat.format(new Date());
        sendSimpleEmail.sendSimpleEmail(from,student.getStuemail(),"账号注册成功！","亲爱的  "+student.getStuname()+"您于"+currentDate+"  成功注册广软快递系统账号，祝您使用愉快！");

        System.out.println("注册成功刷新登录界面");
        return "login";
    }





}
