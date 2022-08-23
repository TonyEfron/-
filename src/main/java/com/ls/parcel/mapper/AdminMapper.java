package com.ls.parcel.mapper;

import com.ls.parcel.pojo.Admin;
import com.ls.parcel.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {
    @Select("select * from admin where adid=#{adid}")
    public Admin findAdminById(int adid);
}
