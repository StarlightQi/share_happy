package com.share.happy.dao;


import com.share.happy.model.Fans;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FansMapper {
    @Insert("INSERT INTO `fans` (`aothorid`, `userid`, `createTime`, `updateTime`) VALUES (#{aothorid}, #{userid}, #{createTime}, #{updateTime});")
    int addUserFans(Fans fans);

    @Select("SELECT *from fans WHERE userid=#{uid}") //获取关注列表
    List<Fans> getUserFans(String uid);

    @Select("SELECT *from fans WHERE aothorid=#{aid}")//获取被关注的
    List<Fans> getForFans(String aid);

    @Select("SELECT userid from fans WHERE fansid=#{fid}")
    String getUserId(String fid);

    @Delete("DELETE FROM `fans` WHERE `fansid` =#{fid}; ")//取消关注
    int deleteUserFans(int fid);
}