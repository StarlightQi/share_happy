package com.share.happy.api;

import com.share.happy.common.model.response.ResponseResult;
import com.share.happy.dao.ArticleTypeMapper;
import com.share.happy.model.ArticleType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@Api(value = "添加类型，只有管理员可以做",description = "添加类型，只有管理员可以")
public interface ArticleTypeApi{

    @ApiOperation("添加文章类型")
    ResponseResult addArticleType(ArticleType articleType);

    @ApiOperation("获取文章类型所有")
    ResponseResult getAllArticleType();

    @ApiOperation("修改类型")
    ResponseResult modArticleType(ArticleType articleType);

    @ApiOperation("删除文章类型")
    ResponseResult deleteArtTypeId(String articleID);
}
