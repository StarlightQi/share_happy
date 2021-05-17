package com.share.happy.api;

import com.share.happy.common.model.response.ResponseResult;
import com.share.happy.dao.ArticleMapper;
import com.share.happy.model.article.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@Api(value = "文章操作",description = "对文章增删改查等")
public interface ArticleApi {

    @ApiOperation("添加文章操作")
    ResponseResult addArticle(AddArticle article);

    @ApiOperation("通过ID获取该用户的全部文章")
    ResponseResult getAuthorArticleShow(int authorId);

    @ApiOperation("查找文章")
    ResponseResult findArticle(int articleId);

    @ApiOperation("获取点赞前10的文章")
    ResponseResult articleLike();

    @ApiOperation("获取浏览量前10的文章")
    ResponseResult articleBrowse();

    @ApiOperation("获取收藏量前10的文章")
    ResponseResult articleCollect();

    @ApiOperation("修改文章内容")
    ResponseResult modArticle(ModArticle article);

    @ApiOperation("修改文章查看权限")
    ResponseResult modArtPermission(ArtPermission artPermission);

    @ApiOperation("修改文章是否可以进行转载")
    ResponseResult modArtReprint(ArtReprint artReprint);

//    @ApiOperation("修改文章阅读数量")
//    ResponseResult modArtReadCount(ArtReadCount artReadCount);

    @ApiOperation("修改文章可查阅的积分")
    ResponseResult modArtCore(ArtCore artCore);

    @ApiOperation("修改文章的状态")
    ResponseResult modArtStatus(ArtStatus artStatus);

    @ApiOperation("删除文章")
    ResponseResult delArticle(int articleId);



}
