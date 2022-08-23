package com.ls.parcel.mapper;

import com.ls.parcel.pojo.Parcel;
import com.ls.parcel.pojo.Student;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface StudentMapper {
    @Select("select stupassword from student where stuid=#{stuid}")
    public String findPwdById(int stuid);

    @Select("select stuid from student where stuid=#{stuid}")
    public String findStuIdById(int stuid);

    @Insert("insert into student(stuname,stupassword,stuid,stuemail) values(#{stuname},#{stupassword},#{stuid},#{stuemail})")
    public int addStu(Student student);

    @Select("select * from student where stuid=#{stuid}")
    public Student findStuById(int stuid);

    @Select("select * from student")
    public ArrayList<Student> findAllStu();

    @Delete("delete from student where stuid=#{stuid}")
    public int deleteStu(int stuid);

    @Update("update student set stuname=#{stuname},stupassword=#{stupassword},stuemail=#{stuemail} where stuid=#{stuid}")
    public int updateStu(Student student);

}
