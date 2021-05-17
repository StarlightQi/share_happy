package com.share.happy.api;

import com.share.happy.common.model.response.ResponseResult;
import com.share.happy.dao.FansMapper;
import com.share.happy.model.Fans;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "粉丝管理",description = "粉丝管理")
public interface FansApi{

    @ApiOperation("添加粉丝")
    ResponseResult addUserFans(Fans fans);

    @ApiOperation("获取用户自己关注的粉丝")
    ResponseResult getUserFans();

    @ApiOperation("作者获取自己的粉丝列表")
    ResponseResult getForFans(String aid);

    @ApiOperation("删除关注")
    ResponseResult deleteUserFans(int fid);
}
