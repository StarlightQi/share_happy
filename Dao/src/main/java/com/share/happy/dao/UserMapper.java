package com.share.happy.dao;


import com.share.happy.model.user.User;
import com.share.happy.model.user.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;


public interface UserMapper {
    @Insert("INSERT INTO user (`username`, `userpassword`, `useremain`, `createTime`, `updateTime`) VALUES (#{username}, #{userpassword}, #{useremain}, #{createTime}, #{updateTime}); ")
    int insert(RegisterUser record);

    @Select("SELECT *FROM user WHERE useremain=#{mail}")
    User userLogin(String mail);

    @Select("SELECT *FROM user WHERE userid=#{id}")
    User findUserId(String id);

    @Select("SELECT *FROM user WHERE userid=#{id}")
    UserInfoShow findUserInfo(String id);

    @Select("SELECT  userpermission FROM USER WHERE userid=#{id}")
    String getUserPerMission(String id); // 获取用户权限

    @Select("SELECT *FROM user")
    List<User> getAllUser();

    @Update("UPDATE user SET `username` = #{username} , `userphone` =#{userphone},`gender`=#{gender} ,`userImg`=#{userImg}, `userinfo` = #{userinfo} , `updatetime` = #{updatetime} WHERE `userid` = #{userid}")
    int upUserInfo(UpUserInfo user);

    @Update("UPDATE user SET `userpassword`=#{password} , `updatetime` = #{date} WHERE `userid` = #{userid}")
    int upUserPassword(@Param("userid")String userid, @Param("password")String password, @Param("date")Date date);

    @Update("UPDATE user SET `usergrade`=#{usergrade} , `updatetime` = #{updatetime} WHERE `userid` = #{userid}")
    int upUserGrade(UserGrade userGrade);

    @Update("UPDATE user SET `userscore`=#{userscore} , `updatetime` = #{updatetime} WHERE `userid` = #{userid}")
    int upUserCore(UsersCore usersCore);

    @Update("UPDATE user SET `userpermission`=#{userpermission} , `updatetime` = #{updatetime} WHERE `userid` = #{userid}")
    int upUserPerMission(UserPermission permission);


}