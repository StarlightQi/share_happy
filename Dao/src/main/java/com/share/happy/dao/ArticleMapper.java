package com.share.happy.dao;


import com.share.happy.model.article.*;
import com.share.happy.model.user.UserInfoShow;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ArticleMapper {
    @Insert("INSERT INTO `article` (`title`, `userid`, `articlecontext`, `articletypeid`, `createTime`, `updateTime`) VALUES (#{title}, #{userid}, #{articlecontext}, #{articletypeid}, #{createTime}, #{updateTime}); ")
    @Options(useGeneratedKeys = true, keyProperty = "articleid", keyColumn="articleid")
    int addArticle(AddArticle article);

    //显示作者全部文章
    @Select("SELECT *FROM article WHERE userid=#{authorId}")
    List<Article> getAuthorArticle(int authorId);

    @Select("SELECT *FROM article WHERE articleid=#{authorId}")
    @Results({
            @Result(property = "userid", column = "userid", javaType = UserInfoShow.class, one = @One(select = "com.share.happy.dao.UserMapper.findUserInfo")),
    })
    List<Article> findArticle(int authorId);

    @Select("SELECT *FROM article WHERE `authorId`=#{id}")
    String getArtUserId(int id);

    @Select("SELECT *FROM article WHERE articleid=#{articleid}")
    List<ArtShowMenu> findArticleInfo(int articleid);

    //显示点赞前10的 ,并且已经发布的 通过多表查询获取用户信息
    @Select("SELECT * FROM article WHERE status=1 ORDER BY likeCount LIMIT 0, 10 ")
    @Results({
            @Result(property = "userid", column = "userid", javaType = UserInfoShow.class, one = @One(select = "com.share.happy.dao.UserMapper.findUserInfo")),
    })
    List<Article> articleLike();
    //显示浏览前10的
    @Select("SELECT * FROM article WHERE status=1 ORDER BY readcount LIMIT 0, 10")
    @Results({
            @Result(property = "userid", column = "userid", javaType = UserInfoShow.class, one = @One(select = "com.share.happy.dao.UserMapper.findUserInfo")),
    })
    List<Article> articleBrowse();
    //显示收藏前10的,查询的数据在搜藏表里边
    @Select("SELECT *FROM `collect`  GROUP BY `articleID` ORDER BY `articleID`")
    List<Article> articleCollect();

    @Update("UPDATE article SET `title` = #{title} , `articlecontext` = #{articlecontext} , `articletypeid` = #{articletypeid} , `updateTime` = #{updateTime} WHERE `articleid` = #{articleid};")
    int modArticle(ModArticle article);

    //修改文章权限,已经在构造函数赋予了时间
    @Update("UPDATE article SET articlepermission=#{articlepermission},`updateTime` = #{updateTime}  WHERE `articleid` = #{articleid}; ")
    int modArtPermission(ArtPermission artPermission);

    //修改是否允许转载
    @Update("UPDATE article SET reprint=#{reprint},`updateTime` = #{updateTime}  WHERE `articleid` = #{articleid}; ")
    int modArtReprint(ArtReprint artReprint);

    //修改阅读数量 同一个IP一天内阅读数量
    @Update("UPDATE article SET readcount=#{readcount},`updateTime` = #{updateTime}  WHERE `articleid` = #{articleid}; ")
    int modArtReadCount(ArtReadCount artReadCount);

    //修改阅读文章需要多少书币,只有大神级别有权限修改
    @Update("UPDATE article SET articleCore=#{articleCore},`updateTime` = #{updateTime}  WHERE `articleid` = #{articleid}; ")
    int modArtCore(ArtCore artCore);

    //修改文章状态，是否发布
    @Update("UPDATE article SET status=#{status},`updateTime` = #{updateTime}  WHERE `articleid` = #{articleid}; ")
    int modArtStatus(ArtStatus artStatus);

    @Delete("DELETE FROM `article` WHERE `articleid` = #{articleId}")
    int delArticle(int articleId);



}