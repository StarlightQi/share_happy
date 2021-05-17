package com.share.happy.dao;


import com.share.happy.model.article.ArtShowMenu;

import com.share.happy.model.article.Article;
import com.share.happy.model.articledir.ArticleDir;
import com.share.happy.model.articledir.ArticleDirAll;

import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ArticleDirMapper {
    //多表查询获取该用户的全部目录文章信息
    @Select("SELECT *FROM articledir WHERE authorid=#{uid}")
    @Results({
            @Result(property = "aticleid", column = "aticleid", javaType = List.class, one = @One(select = "com.share.happy.dao.ArticleMapper.findArticleInfo")),
    })
    List<ArticleDirAll> findArticleDirAll(String uid);

    @Insert("INSERT INTO `articledir` ( `aticledirname`, `authorid`, `aticleID`, `createTime`, `updateTime`) VALUES (#{aticledirname}, #{authorid}, #{aticleID}, #{createTime},#{updateTime}); ")
    void inArticleDir(ArticleDir articleDir);

    //改变文件夹名称，到时候直接输入文件夹名称，或者下拉的，需要传文件夹名称，和id即可，可以删除文件夹，其实就全部改名为默认文件夹等
    @Update("UPDATE `articledir` SET `aticledirname` = #{aticledirname}, `updateTime` = #{updateTime} WHERE `aticledirid` = #{aticledirid}; ")
    int ChangArticleDir(ArticleDir articleDir);



}