package com.share.happy.mange.controller;

import com.share.happy.api.CommentApi;
import com.share.happy.common.model.response.ResponseResult;
import com.share.happy.mange.service.CommentService;
import com.share.happy.mange.ulist.UserLoginToken;
import com.share.happy.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/comment")
@ResponseBody
@UserLoginToken
public class CommentController implements CommentApi {
    @Autowired
    private CommentService commentService;

    @PostMapping("/sendComment")
    @Override
    public ResponseResult sendComment(Comment comment) {
        return commentService.sendComment(comment);
    }

    @GetMapping("/getUserComment")
    @Override
    public ResponseResult getUserComment() {
        return commentService.getUserComment();
    }



    @PutMapping("/getArticleComment")
    @Override
    public ResponseResult getArticleComment(String aid) {
        return commentService.getArticleComment(aid);
    }

    @DeleteMapping("/deleteComment")
    @Override
    public ResponseResult deleteComment(String cid) {
        return commentService.deleteComment(cid);
    }
}
