package com.share.happy.dao;


import com.share.happy.model.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommentMapper {
    @Insert("INSERT INTO `comment` (`userid`, `articleID`, `content`, `createTime`, `updateTime`) VALUES (#{userid}, #{articleID}, #{content},#{createTime}, #{updateTime});")
    int sendComment(Comment comment);

    //获取该用户全部评论
    @Select("SELECT * form comment WHERE userid=#{uid} ")
    List<Comment> getUserComment(String uid);

    //获取本篇文章的全部评论
    @Select("SELECT *from comment WHERE articleID={aid}")
    List<Comment> getArticleComment(String aid);

    //评论的获取用户名称
    @Select("SELECT userid from comment WHERE `commentID` = #{cid} ")
    String getCommentUser(String cid);

    @Delete("DELETE FROM `comment` WHERE `commentID` = #{cid}")
    int deleteComment(String cid);


}