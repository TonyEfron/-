package com.ls.parcel.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class DumpSQLService {
    @Resource
    private SendEmailService sendSimpleEmail;

    @Value("${spring.mail.username}")
    private String from;


    public void dataBaseDump(String host, String port, String username, String password, String dbName, String currentDate) throws Exception {
//定义备份路径文件夹

        String path="D:/IDEA/project/parcel/src/main/resources/static/databasesave/";
        File file=new File(path);
        if (!file.exists()){
            file.mkdir();
        }

        String fileName=dbName+currentDate+".sql";
        String datafile=password+fileName;

        //拼接备份的cmd命令
        String cmd="cmd /c mysqldump -h" + host + " -P" + port + " -u " + username + " -p" + password + " " + dbName +" t_user"+ " > " + datafile;
       try {
           Process exec = Runtime.getRuntime().exec(cmd);
           System.out.println(fileName+"数据库备份成功");
       }catch (Exception e){
           System.out.println("数据库备份失败"+e.getMessage());
       }

    }

//@Scheduled(cron ="0 * * * * *")
    public void dump() throws Exception {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
        String currentDate=simpleDateFormat.format(new Date());

        dataBaseDump("localhost","3306","root","123123","parcel",currentDate);
        sendSimpleEmail.sendSimpleEmail(from,"lishuostc@163.com","数据库备份成功",currentDate+"数据库备份成功");
    }
}