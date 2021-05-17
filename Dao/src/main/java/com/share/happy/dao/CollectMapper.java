package com.share.happy.dao;

import com.share.happy.model.ArticleType;
import com.share.happy.model.Collect;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CollectMapper {
    @Insert("INSERT INTO `collect` (`articleID`, `Userid`, `createTime`, `updateTime`) VALUES (#{articleID}, #{userid}', #{createTime}, #{createTime});")
    int addCollect(Collect collect);

    @Select("SELECT *from collect")
    List<Collect> getAllUserCollect(String uid);

    @Select("SELECT userid from collect where collectID=#{cid}")
    String getUserId(String uid);

    @Delete("DELETE FROM `collect` WHERE `collectID` = #{cid}")
    int deleteCollect(String cid);

}