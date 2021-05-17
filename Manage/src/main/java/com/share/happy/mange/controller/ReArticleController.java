package com.share.happy.mange.controller;

import com.share.happy.api.ReArticleApi;
import com.share.happy.common.model.response.ResponseResult;
import com.share.happy.mange.service.ReArticleService;
import com.share.happy.mange.ulist.UserLoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/reArticle")
@ResponseBody
@UserLoginToken
public class ReArticleController implements ReArticleApi {
    @Autowired
    private ReArticleService reArticleService;

    @GetMapping("/getReArticle")
    @Override
    public ResponseResult getReArticle() {
        return reArticleService.getReArticle();
    }

    @DeleteMapping("/deleteReArticle")
    @Override
    public ResponseResult deleteReArticle(String aid) {
        return reArticleService.deleteReArticle(aid);
    }
}
