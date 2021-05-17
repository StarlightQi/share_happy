package com.share.happy.mange.controller;


import com.share.happy.api.ArticleApi;
import com.share.happy.common.model.response.ResponseResult;
import com.share.happy.mange.service.ArticleService;
import com.share.happy.mange.ulist.PassToken;
import com.share.happy.mange.ulist.UserLoginToken;
import com.share.happy.model.article.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/article")
@ResponseBody
@UserLoginToken
public class ArticleController implements ArticleApi {
    @Autowired
    private ArticleService articleService;

    @PostMapping("/addArticle")
    @Override
    public ResponseResult addArticle(@RequestBody AddArticle article) {
        return articleService.addArticle(article);
    }


    @GetMapping("/getAuthorArticleShow")
    @Override
    public ResponseResult getAuthorArticleShow(int authorId) {
        return articleService.getAuthorArticleShow(authorId);
    }

    @GetMapping("/findArticle")
    @Override
    public ResponseResult findArticle(@RequestParam int articleId) {
        return articleService.findArticle(articleId);
    }

    //不需要登录
    @GetMapping("/articleLike")
    @Override
    @PassToken
    public ResponseResult articleLike() {
        return articleService.articleLike();
    }

    //不需要登录
    @GetMapping("/articleBrowse")
    @Override
    @PassToken
    public ResponseResult articleBrowse() {
        return articleService.articleBrowse();
    }

    //不需要登录
    @GetMapping("/articleCollect")
    @Override
    @PassToken
    public ResponseResult articleCollect() {
        return articleService.articleCollect();
    }

    @PutMapping("/modArticle")
    @Override
    public ResponseResult modArticle(@RequestBody ModArticle article) {
        return articleService.modArticle(article);
    }

    @PutMapping("/modArtPermission")
    @Override
    public ResponseResult modArtPermission(@RequestBody ArtPermission artPermission) {
        return articleService.modArtPermission(artPermission);
    }

    @PutMapping("/modArtReprint")
    @Override
    public ResponseResult modArtReprint(@RequestBody ArtReprint artReprint) {
        return articleService.modArtReprint(artReprint);
    }

    @PutMapping("/modArtCore")
    @Override
    public ResponseResult modArtCore(@RequestBody ArtCore artCore) {
        return articleService.modArtCore(artCore);
    }

    @PutMapping("/modArtStatus")
    @Override
    public ResponseResult modArtStatus(@RequestBody ArtStatus artStatus) {
        return articleService.modArtStatus(artStatus);
    }

    @DeleteMapping("/deleteArticle")
    @Override
    public ResponseResult delArticle(@RequestParam int articleId) {
        return articleService.delArticle(articleId);
    }
}
