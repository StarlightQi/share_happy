package com.share.happy.dao;

import com.share.happy.model.Security;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SecurityMapper {

    @Insert("INSERT INTO `security` (`userId`, `userIp`, `browser`,`equipment`, `createTime`, `updateTime`) VALUES (#{userId}, #{userIp}, #{browser},#{equipment}, #{createTime}, #{updateTime})")
    int addSecurityNapper(Security security);

    @Select("SELECT * FROM `security` WHERE userId=#{usid} ORDER BY `createTime` DESC")
    List<Security> getSecurity(String usid);
}
