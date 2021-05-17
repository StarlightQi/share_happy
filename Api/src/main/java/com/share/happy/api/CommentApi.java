package com.share.happy.api;

import com.share.happy.common.model.response.ResponseResult;
import com.share.happy.model.Comment;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "评论功能",description = "评论功能")
public interface CommentApi {

    @ApiOperation("发布评论")
    ResponseResult sendComment(Comment comment);


    @ApiOperation("获取用户的全部评论")
    ResponseResult getUserComment();

    @ApiOperation("获取文字的全部评论")
    ResponseResult getArticleComment(String aid);

    @ApiOperation("删除文章的全部评论")
    ResponseResult deleteComment(String cid);
}
