package com.share.happy.api;

import com.share.happy.common.model.response.ResponseResult;
import com.share.happy.model.articledir.ArticleDir;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "文件夹管理",description = "用户文件夹的")
public interface ArticleDirApi {
    @ApiOperation("获取用户全部文件夹")
    ResponseResult findArticleDirAll();

    @ApiOperation("修改文件夹,传文件夹id和名称")
    ResponseResult changeArticleDir(ArticleDir articleDir);
}
