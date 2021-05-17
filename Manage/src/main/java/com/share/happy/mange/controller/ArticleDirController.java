package com.share.happy.mange.controller;

import com.share.happy.api.ArticleDirApi;
import com.share.happy.common.model.response.ResponseResult;
import com.share.happy.mange.service.ArticleDirService;
import com.share.happy.mange.service.ArticleService;
import com.share.happy.mange.ulist.UserLoginToken;
import com.share.happy.model.articledir.ArticleDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/articleDir")
@ResponseBody
@UserLoginToken
public class ArticleDirController implements ArticleDirApi {

    @Autowired
    private ArticleDirService articleService;

    @GetMapping("/findUserDir")
    @Override
    public ResponseResult findArticleDirAll() {
        return articleService.findArticleDirAll();
    }

    @Override
    @PutMapping("/changeAriDir")
    public ResponseResult changeArticleDir(ArticleDir articleDir) {
        return null;
    }
}
