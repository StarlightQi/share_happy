package com.share.happy.dao;


import com.share.happy.model.ReArticle;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ReArticleMapper {

    //获取用户的全部删除的文章
    @Select("SELECT *FROM rearticle where userid=#{userid}")
    List<ReArticle> getAllReArticle(String userid);

    @Delete("DELETE FROM `rearticle` WHERE `articleid` = #{aid}")
    int deleteReArticle(String aid);
}