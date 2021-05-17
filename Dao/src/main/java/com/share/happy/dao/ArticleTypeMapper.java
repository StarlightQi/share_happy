package com.share.happy.dao;


import com.share.happy.model.ArticleType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ArticleTypeMapper {
    @Insert("INSERT INTO `articletype` (`articleType`, `createTime`, `updateTime`) VALUES (#{articleType}, #{createTime}, #{updateTime});")
    int addArticleType(ArticleType articleType);

    @Select("SELECT *from articletype")
    List<ArticleType> findAllArticleType();

    @Update("UPDATE `articletype` SET `articleType` =#{articleType} , `updateTime` =#{updateTime} WHERE `articleID` = #{articleID}; ")
    int modArticleType(ArticleType articleType);

    @Delete("DELETE FROM `sharehappy`.`articletype` WHERE `articleID` = #{articleID}")
    int deleteArtTypeId(String articleID);

}