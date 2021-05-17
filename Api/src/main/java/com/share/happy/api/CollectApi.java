package com.share.happy.api;

import com.share.happy.common.model.response.ResponseResult;
import com.share.happy.model.Collect;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "用户收藏",description = "用户收藏")
public interface CollectApi {
    @ApiOperation("添加用户收藏")
    ResponseResult addCollect(Collect collect);

    @ApiOperation("获取全部用户收藏")
    ResponseResult getAllUserCollect();

    @ApiOperation("删除收藏")
    ResponseResult deleteCollect(String cid);
}
