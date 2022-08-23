package com.ls.parcel.mapper;

import com.ls.parcel.pojo.Company;
import com.ls.parcel.pojo.Student;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface CompanyMapper  {
    @Select("select * from company")
    public ArrayList<Company> findAllCompany();

    @Select("select * from company where comid=#{comid}")
    public Company findComById(int comid);

    @Delete("delete from company where comid=#{comid}")
    public int deleteCom(int comid);

    @Update("update company set comname=#{comname},comphone=#{comphone},comemail=#{comemail} where comid=#{comid}")
    public int updateCom(Company company);

    @Insert("insert into company(comname,comphone,comid,comemail) values(#{comname},#{comphone},#{comid},#{comemail})")
    public int addCom(Company company);

    @Select("select comid from company where comid=#{comid}")
    public String findComIdById(int comid);
}
