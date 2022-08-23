package com.ls.parcel.controller;

import com.ls.parcel.mapper.*;
import com.ls.parcel.pojo.*;
import com.ls.parcel.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Controller
public class AdminController {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ParcelMapper parcelMapper;
    @Autowired
    private SendParcelMapper sendParcelMapper;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private AdminMapper adminMapper;
    private ArrayList<Parcel> parcels;
    private ArrayList<Sendparcel> sendparcels;
    private ArrayList<Student> students;
    private ArrayList<Company> companies;

    @Value("${spring.mail.username}")
    private String from;
    @Resource
    private SendEmailService sendSimpleEmail;



    private String path="D:/IDEA/project/parcel/src/main/resources/static/stubackcard/img/";

    @PostMapping("/admin/index")
    public String toadindex(Admin admin,HttpServletRequest model) {
        Admin admin1=adminMapper.findAdminById(admin.getAdid());
        if(admin1==null){
           return "admin";
        }
        if(admin1.getPwd().equals(admin.getPwd())) {
            model.setAttribute("loginAdmin", admin);
        }
        ArrayList<Company> companies= companyMapper.findAllCompany();
        model.setAttribute("companies",companies);
        parcels=parcelMapper.findAllParcel();
        model.setAttribute("Allparcel",parcels);
        System.out.println("直接进入管理员界面");
        return "adindex";
    }
    @GetMapping("/admin/list")
    public String toadlist(Model model) {
        Student student = new Student();
        student.setStuid(2);
        model.addAttribute("loginStudent", student);
        System.out.println("直接进入无导航管理员界面");
        return "admin/list";
    }
    @GetMapping("/admin/parceldetail/{id}")
    public String toparceldetail(@PathVariable("id")int adid,HttpServletRequest model){
        Admin admin=adminMapper.findAdminById(adid);
        model.setAttribute("loginAdmin",admin);

        ArrayList<Company> companies= companyMapper.findAllCompany();
        model.setAttribute("companies",companies);
        System.out.println("直接进入快递详情界面");
        return "admin/parceldetail";
    }










    @GetMapping("/admin/Index/{id}")
    public String toAdminIndex(@PathVariable("id")int adid, HttpServletRequest model){
        Admin admin=adminMapper.findAdminById(adid);
        model.setAttribute("loginAdmin",admin);

        parcels=parcelMapper.findAllParcel();
        model.setAttribute("Allparcel",parcels);
        System.out.println("进入管理界面");
        return "adindex";
    }

    @GetMapping("/admin/parceladd/{id}")
    public String toAdminparceladd(@PathVariable("id")int adid,HttpServletRequest model){
        Admin admin=adminMapper.findAdminById(adid);
        model.setAttribute("loginAdmin",admin);

        ArrayList<Company> companies= companyMapper.findAllCompany();
        model.setAttribute("companies",companies);
        System.out.println("进入快递添加界面");
        return "admin/parceladd";
    }

    @RequestMapping("/admin/addparcel/{id}")
    public String toAddParcel(@PathVariable("id")int adid, Parcel parcel, MultipartFile myfile,HttpServletRequest model)throws IllegalStateException, IOException {
        File file= new File(path);

        if (!file.exists()){
            file.mkdir();
        }
        String oldFileName=myfile.getOriginalFilename();
        File newFileName=new File(path+oldFileName);
        myfile.transferTo(newFileName);
        parcel.setPic(oldFileName);
        System.out.println(myfile);



        int rows=parcelMapper.addParcel(parcel);
        Admin admin=adminMapper.findAdminById(adid);
        model.setAttribute("loginAdmin",admin);
        parcels=parcelMapper.findAllParcel();
        model.setAttribute("Allparcel",parcels);



        System.out.println(parcel);
        System.out.println("添加快递");
        return "adindex";
    }

    @GetMapping("/admin/detailparcel/{id}&{parcelid}")
    public String todetailparcel(@PathVariable("id")int adid,@PathVariable("parcelid")int parcelid,HttpServletRequest model){
        Admin admin=adminMapper.findAdminById(adid);
        model.setAttribute("loginAdmin",admin);

        Parcel parcel=parcelMapper.findParcelByParcelId(parcelid);
        model.setAttribute("parcel",parcel);
        ArrayList<Company> companies= companyMapper.findAllCompany();
        model.setAttribute("companies",companies);
        System.out.println("进入快递详情界面");
        return "admin/parceldetail";
    }


    @RequestMapping("/admin/editparcel/{id}&{parcelid}")
    public String toEditParcel(@PathVariable("id")int adid,@PathVariable("parcelid")int parcelid, Parcel parcel, MultipartFile myfile,HttpServletRequest model)throws IllegalStateException, IOException {
        File file= new File(path);

        if (!file.exists()){
            file.mkdir();
        }
        String oldFileName=myfile.getOriginalFilename();
        File newFileName=new File(path+oldFileName);
        myfile.transferTo(newFileName);
        parcel.setPic(oldFileName);
        System.out.println(myfile);

        parcel.setParcelid(parcelid);
        int rows=parcelMapper.updateParcel(parcel);


        Admin admin=adminMapper.findAdminById(adid);
        model.setAttribute("loginAdmin",admin);
        parcels=parcelMapper.findAllParcel();
        model.setAttribute("Allparcel",parcels);



        //发送邮件
        Student student=studentMapper.findStuById(parcel.getStuid());

        if(parcel.getStatus().equals("1")){
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
            String currentDate=simpleDateFormat.format(new Date());
            sendSimpleEmail.sendSimpleEmail(from,student.getStuemail(),"快递成功签收！","亲爱的  "+student.getStuname()+"  您的快递于"+currentDate+"成功签收！");
        }else if(parcel.getStatus().equals("2")){
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
            String currentDate=simpleDateFormat.format(new Date());
            sendSimpleEmail.sendSimpleEmail(from,student.getStuemail(),"您有快递已抵达驿站！","亲爱的  "+student.getStuname()+"  您的快递"+parcel.getParcelname()+"于"+currentDate+"成功抵达驿站，请尽快！");
        }







        System.out.println(rows);
        System.out.println(parcel);
        System.out.println("修改快递");
        return "adindex";
    }

    @GetMapping("/admin/deleteparcel/{id}&{parcelid}")
    public String todeleteparcel(@PathVariable("id")int adid,@PathVariable("parcelid")int parcelid,HttpServletRequest model){
        Admin admin=adminMapper.findAdminById(adid);
        model.setAttribute("loginAdmin",admin);

        int rows=parcelMapper.deleteParcel(parcelid);
        parcels=parcelMapper.findAllParcel();
        model.setAttribute("Allparcel",parcels);
        System.out.println("删除快递");
        return "adindex";
    }

//---------------------------------------------------------
//---------------------------------------------------------
//---------------------------------------------------------


    @GetMapping("/admin/send/{id}")
    public String toAdminSend(@PathVariable("id")int adid, HttpServletRequest model){
        Admin admin=adminMapper.findAdminById(adid);
        model.setAttribute("loginAdmin",admin);

        sendparcels= sendParcelMapper.findAllSendParcel();
        model.setAttribute("AllSparcel",sendparcels);
        ArrayList<Company> companies= companyMapper.findAllCompany();
        model.setAttribute("companies",companies);
        System.out.println("进入寄件管理界面");
        return "adsend";
    }

    @GetMapping("/admin/sendadd/{id}")
    public String toAdminsendadd(@PathVariable("id")int adid,HttpServletRequest model){
        Admin admin=adminMapper.findAdminById(adid);
        model.setAttribute("loginAdmin",admin);

        ArrayList<Company> companies= companyMapper.findAllCompany();
        model.setAttribute("companies",companies);
        System.out.println("进入寄件添加界面");
        return "admin/sendadd";
    }

    @RequestMapping("/admin/addsendparcel/{id}")
    public String toAddSendParcel(@PathVariable("id")int adid, Sendparcel sendparcel,HttpServletRequest model)throws IllegalStateException, IOException {

        Admin admin=adminMapper.findAdminById(adid);
        model.setAttribute("loginAdmin",admin);

        int rows=sendParcelMapper.StuSignParcel(sendparcel);

        sendparcels= sendParcelMapper.findAllSendParcel();
        model.setAttribute("AllSparcel",sendparcels);
        ArrayList<Company> companies= companyMapper.findAllCompany();
        model.setAttribute("companies",companies);
        System.out.println(sendparcel);
        System.out.println("添加寄件");
        return "adsend";
    }


    @GetMapping("/admin/deletesendparcel/{id}&{sparcelid}")
    public String todeletesenparcel(@PathVariable("id")int adid,@PathVariable("sparcelid")int sparcelid,HttpServletRequest model){
        Admin admin=adminMapper.findAdminById(adid);
        model.setAttribute("loginAdmin",admin);

        int rows=sendParcelMapper.deleteSendParcel(sparcelid);
        sendparcels= sendParcelMapper.findAllSendParcel();
        model.setAttribute("AllSparcel",sendparcels);
        ArrayList<Company> companies= companyMapper.findAllCompany();
        model.setAttribute("companies",companies);
        System.out.println("删除寄件");
        return "adsend";
    }

    @GetMapping("/admin/detailsendparcel/{id}&{sparcelid}")
    public String todetailsendparcel(@PathVariable("id")int adid,@PathVariable("sparcelid")int sparcelid,HttpServletRequest model){
        Admin admin=adminMapper.findAdminById(adid);
        model.setAttribute("loginAdmin",admin);

        Sendparcel sendparcel=sendParcelMapper.findSendParcelByParcelId(sparcelid);
        model.setAttribute("parcel",sendparcel);
        ArrayList<Company> companies= companyMapper.findAllCompany();
        model.setAttribute("companies",companies);
        System.out.println("进入寄件详情界面");
        return "admin/senddetail";
    }


    @RequestMapping("/admin/editsparcel/{id}&{sparcelid}")
    public String toEditSParcel(@PathVariable("id")int adid,@PathVariable("sparcelid")int sparcelid, Sendparcel sendparcel,HttpServletRequest model)throws IllegalStateException, IOException {

        Admin admin=adminMapper.findAdminById(adid);
        model.setAttribute("loginAdmin",admin);
        int rows=sendParcelMapper.updateSendParcel(sendparcel);

        sendparcels= sendParcelMapper.findAllSendParcel();
        model.setAttribute("AllSparcel",sendparcels);
        ArrayList<Company> companies= companyMapper.findAllCompany();
        model.setAttribute("companies",companies);


        //发送邮件
        Student student=studentMapper.findStuById(sendparcel.getStuid());


        if(sendparcel.getSendsatus().equals("1")){
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
            String currentDate=simpleDateFormat.format(new Date());
            sendSimpleEmail.sendSimpleEmail(from,student.getStuemail(),"寄件申请成功！","亲爱的  "+student.getStuname()+"  您的寄件申请于"+currentDate+"成功受理！");
        }else if(sendparcel.getSendsatus().equals("2")){
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
            String currentDate=simpleDateFormat.format(new Date());
            sendSimpleEmail.sendSimpleEmail(from,student.getStuemail(),"寄件成功抵达！","亲爱的  "+student.getStuname()+"  您的寄件于"+currentDate+"成功抵达目的地！");
        }

        System.out.println(rows);
        System.out.println(sendparcel);
        System.out.println("修改寄件");
        return "adsend";
    }

//---------------------------------------------------------
//---------------------------------------------------------
//---------------------------------------------------------

    @GetMapping("/admin/stu/{id}")
    public String toAdminStu(@PathVariable("id")int adid, HttpServletRequest model){
        Admin admin=adminMapper.findAdminById(adid);
        model.setAttribute("loginAdmin",admin);

        students=studentMapper.findAllStu();
        model.setAttribute("AllStu",students);

        System.out.println("进入学生账号管理界面");
        return "adstulist";
    }

    @GetMapping("/admin/stuadd/{id}")
    public String toAdminstuadd(@PathVariable("id")int adid,HttpServletRequest model){
        Admin admin=adminMapper.findAdminById(adid);
        model.setAttribute("loginAdmin",admin);

        int idexist=2;
        model.setAttribute("idexist",idexist);

        System.out.println("进入学生账号添加界面");
        return "admin/stuadd";
    }
    @RequestMapping("/admin/addstu/{id}")
    public String toAddStu(@PathVariable("id")int adid, Student student,HttpServletRequest model)throws IllegalStateException, IOException {
        int idexist;
        if(studentMapper.findStuIdById(student.getStuid())!=null){//id已存在
            idexist=1;
            model.setAttribute("idexist",idexist);
            System.out.println("由于id已存在刷新登录界面");

            Admin admin=adminMapper.findAdminById(adid);
            model.setAttribute("loginAdmin",admin);

            return "admin/stuadd";
        }

        Admin admin=adminMapper.findAdminById(adid);
        model.setAttribute("loginAdmin",admin);

        int rows=studentMapper.addStu(student);

        students=studentMapper.findAllStu();
        model.setAttribute("AllStu",students);
        System.out.println(student);
        System.out.println("添加学生");
        return "adstulist";
    }

    @GetMapping("/admin/deletestu/{id}&{stuid}")
    public String todeletestu(@PathVariable("id")int adid,@PathVariable("stuid")int stuid,HttpServletRequest model){
        Admin admin=adminMapper.findAdminById(adid);
        model.setAttribute("loginAdmin",admin);

        int rows=studentMapper.deleteStu(stuid);


        students=studentMapper.findAllStu();
        model.setAttribute("AllStu",students);
        System.out.println("删除学生");
        return "adstulist";
    }


    @GetMapping("/admin/detailstu/{id}&{stuid}")
    public String todetailstu(@PathVariable("id")int adid,@PathVariable("stuid")int stuid,HttpServletRequest model){
        Admin admin=adminMapper.findAdminById(adid);
        model.setAttribute("loginAdmin",admin);

        Student student=studentMapper.findStuById(stuid);
        model.setAttribute("stu",student);


        System.out.println("进入学生详情界面");
        return "admin/studetail";
    }
    @RequestMapping("/admin/editstu/{id}&{stuid}")
    public String toEditStu(@PathVariable("id")int adid,@PathVariable("stuid")int stuid, Student student,HttpServletRequest model)throws IllegalStateException, IOException {

        Admin admin=adminMapper.findAdminById(adid);
        model.setAttribute("loginAdmin",admin);
        int rows=studentMapper.updateStu(student);

        students=studentMapper.findAllStu();
        model.setAttribute("AllStu",students);

        System.out.println(rows);
        System.out.println(student);
        System.out.println("修改学生信息");
        return "adstulist";
    }

//---------------------------------------------------------
//---------------------------------------------------------
//---------------------------------------------------------

    @GetMapping("/admin/com/{id}")
    public String toAdminCom(@PathVariable("id")int adid, HttpServletRequest model){
        Admin admin=adminMapper.findAdminById(adid);
        model.setAttribute("loginAdmin",admin);

        companies=companyMapper.findAllCompany();
        model.setAttribute("AllCom",companies);

        System.out.println("进入快递账号管理界面");
        return "adcomlist";
    }
    @GetMapping("/admin/comadd/{id}")
    public String toAdmincomadd(@PathVariable("id")int adid,HttpServletRequest model){
        Admin admin=adminMapper.findAdminById(adid);
        model.setAttribute("loginAdmin",admin);

        int idexist=2;
        model.setAttribute("idexist",idexist);

        System.out.println("进入企业账号添加界面");
        return "admin/comadd";
    }
    @RequestMapping("/admin/addcom/{id}")
    public String toAddCom(@PathVariable("id")int adid, Company company,HttpServletRequest model)throws IllegalStateException, IOException {
        int idexist;

        if(companyMapper.findComById(company.getComid())!=null){//id已存在
            idexist=1;
            model.setAttribute("idexist",idexist);
            System.out.println("由于id已存在刷新登录界面");

            Admin admin=adminMapper.findAdminById(adid);
            model.setAttribute("loginAdmin",admin);

            return "admin/comadd";
        }

        Admin admin=adminMapper.findAdminById(adid);
        model.setAttribute("loginAdmin",admin);

        int rows=companyMapper.addCom(company);

        companies=companyMapper.findAllCompany();
        model.setAttribute("AllCom",companies);
        System.out.println(company);
        System.out.println("添加企业");
        return "adcomlist";
    }


    @GetMapping("/admin/deletecom/{id}&{comid}")
    public String todeletecom(@PathVariable("id")int adid,@PathVariable("comid")int comid,HttpServletRequest model){
        Admin admin=adminMapper.findAdminById(adid);
        model.setAttribute("loginAdmin",admin);

        int rows=companyMapper.deleteCom(comid);


        companies=companyMapper.findAllCompany();
        model.setAttribute("AllCom",companies);
        System.out.println("删除企业");
        return "adcomlist";
    }


    @GetMapping("/admin/detailcom/{id}&{comid}")
    public String todetailcom(@PathVariable("id")int adid,@PathVariable("comid")int comid,HttpServletRequest model){
        Admin admin=adminMapper.findAdminById(adid);
        model.setAttribute("loginAdmin",admin);

        Company company=companyMapper.findComById(comid);
        model.setAttribute("com",company);


        System.out.println("进入企业详情界面");
        return "admin/comdetail";
    }
    @RequestMapping("/admin/editcom/{id}&{comid}")
    public String toEditCom(@PathVariable("id")int adid,@PathVariable("comid")int comid, Company company,HttpServletRequest model)throws IllegalStateException, IOException {

        Admin admin=adminMapper.findAdminById(adid);
        model.setAttribute("loginAdmin",admin);
        int rows=companyMapper.updateCom(company);

        companies=companyMapper.findAllCompany();
        model.setAttribute("AllCom",companies);

        System.out.println(rows);
        System.out.println(company);
        System.out.println("修改企业信息");
        return "adcomlist";
    }





}
