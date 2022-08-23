package com.ls.parcel.mapper;

import com.ls.parcel.pojo.Parcel;
import com.ls.parcel.pojo.Sendparcel;
import com.ls.parcel.pojo.Student;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface SendParcelMapper {
    @Insert("insert into sendparcel(sparcelname,sendaddress,sendsatus,stuid,tip,comid) values(#{sparcelname},#{sendaddress},#{sendsatus},#{stuid},#{tip},#{comid})")
    public int StuSignParcel(Sendparcel sendparcel);

    @Select("select * from sendparcel where stuid=#{stuid}")
    public ArrayList<Sendparcel> findSendParcelByStuId(int stuid);

    @Select("select * from sendparcel where sparcelid=#{sparcelid}")
    public Sendparcel findSendParcelByParcelId(int parcelid);

    @Select("select * from sendparcel ")
    public ArrayList<Sendparcel> findAllSendParcel();

    @Delete("delete from sendparcel where sparcelid=#{sparcelid}")
    public int deleteSendParcel(int sparcelid);

    @Update("update sendparcel set sparcelname=#{sparcelname},stuid=#{stuid},sendsatus=#{sendsatus},sendaddress=#{sendaddress},comid=#{comid},tip=#{tip} where sparcelid=#{sparcelid}")
    public int updateSendParcel(Sendparcel sendparcel);
}
