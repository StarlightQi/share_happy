package com.share.happy.mange.service;

import com.share.happy.api.CommentApi;
import com.share.happy.common.model.response.CommonCode;
import com.share.happy.common.model.response.QueryResults;
import com.share.happy.common.model.response.ResponseResult;
import com.share.happy.common.model.response.ResponseResults;
import com.share.happy.dao.CommentMapper;
import com.share.happy.mange.ulist.Utils;
import com.share.happy.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentService implements CommentApi {
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public ResponseResult sendComment(Comment comment) {
        comment.setUserid(Utils.getTokenUserMain());
        comment.setCreateTime(new Date());
        comment.setUpdateTime(new Date());
        return Utils.getResult(commentMapper.sendComment(comment));
    }

    @Override
    public ResponseResult getUserComment() {
        return new QueryResults<>(CommonCode.SUCCESS,commentMapper.getUserComment(Utils.getTokenUserMain()));
    }

    @Override
    public ResponseResult getArticleComment(String aid) {
        return new QueryResults<>(CommonCode.SUCCESS,commentMapper.getArticleComment(aid));
    }

    @Override
    public ResponseResult deleteComment(String cid) {
        if (!Utils.getTokenUserMain().equals(commentMapper.getCommentUser(cid)))
            return new ResponseResults<>(CommonCode.UNAUTHORISE,"你想做什么，想非法删除做坏事吗");
        return Utils.getResult(commentMapper.deleteComment(cid));
    }
}
