package com.ls.parcel.mapper;

import com.ls.parcel.pojo.Parcel;
import com.ls.parcel.pojo.Student;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface ParcelMapper {
    @Select("select * from parcel where stuid=#{stuid}")
    public ArrayList<Parcel> findParcelByStuId(int stuid);

    @Select("select * from parcel where parcelid=#{parcelid}")
    public Parcel findParcelByParcelId(int parcelid);

    @Select("select * from parcel ")
    public ArrayList<Parcel> findAllParcel();

    @Insert("insert into Parcel(parcelname,stuid,status,address,cname,pic,arrtime) values(#{parcelname},#{stuid},#{status},#{address},#{cname},#{pic},#{arrtime})")
    public int addParcel(Parcel parcel);

    @Update("update parcel set parcelname=#{parcelname},stuid=#{stuid},status=#{status},address=#{address},cname=#{cname},pic=#{pic},arrtime=#{arrtime} where parcelid=#{parcelid}")
    public int updateParcel(Parcel parcel);

    @Delete("delete from parcel where parcelid=#{parcelid}")
    public int deleteParcel(int parcelid);
}
