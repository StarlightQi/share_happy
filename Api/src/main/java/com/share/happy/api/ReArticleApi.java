package com.share.happy.api;

import com.share.happy.common.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "回收站管理",description = "回收站")
public interface ReArticleApi {

    @ApiOperation("获取回收站列表")
    ResponseResult getReArticle();

    @ApiOperation("删除回收站信息")
    ResponseResult deleteReArticle(String aid);

}
