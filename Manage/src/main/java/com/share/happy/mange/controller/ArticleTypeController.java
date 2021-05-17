package com.share.happy.mange.controller;

import com.share.happy.api.ArticleTypeApi;
import com.share.happy.common.model.response.ResponseResult;
import com.share.happy.mange.service.ArticleTypeService;
import com.share.happy.mange.ulist.UserLoginToken;
import com.share.happy.model.ArticleType;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/articleType")
@ResponseBody
@UserLoginToken
public class ArticleTypeController implements ArticleTypeApi {
    @Autowired
    private ArticleTypeService articleTypeService;

    @PostMapping("/addType")
    @Override
    public ResponseResult addArticleType(ArticleType articleType) {
        return articleTypeService.addArticleType(articleType);
    }

    @GetMapping("/getAllType")
    @Override
    public ResponseResult getAllArticleType() {
        return articleTypeService.getAllArticleType();
    }

    @PutMapping("updateType")
    @Override
    public ResponseResult modArticleType(ArticleType articleType) {
        return articleTypeService.modArticleType(articleType);
    }

    @Delete("/deleteType")
    @Override
    public ResponseResult deleteArtTypeId(String articleID) {
        return articleTypeService.deleteArtTypeId(articleID);
    }
}
