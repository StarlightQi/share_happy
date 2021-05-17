package com.share.happy.mange.service;

import com.share.happy.api.ArticleTypeApi;
import com.share.happy.common.model.response.*;
import com.share.happy.dao.ArticleTypeMapper;
import com.share.happy.dao.UserMapper;
import com.share.happy.mange.ulist.Utils;
import com.share.happy.model.ArticleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ArticleTypeService implements ArticleTypeApi {
    @Autowired
    private ArticleTypeMapper articleTypeMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseResult addArticleType(ArticleType articleType) {
        if (userMapper.getUserPerMission(Utils.getTokenUserMain()).equals("1"))
            return new ResponseResults<>(CommonCode.UNAUTHORISE,"您不是超级管理员你想做什么");
        articleType.setCreateTime(new Date());
        articleType.setUpdateTime(new Date());
        return Utils.getResult(articleTypeMapper.addArticleType(articleType));
    }

    @Override
    public ResponseResult getAllArticleType() {
        return new QueryResults<>(CommonCode.SUCCESS,articleTypeMapper.findAllArticleType());
    }

    @Override
    public ResponseResult modArticleType(ArticleType articleType) {
        if (userMapper.getUserPerMission(Utils.getTokenUserMain()).equals("1"))
            return new ResponseResults<>(CommonCode.UNAUTHORISE,"您不是超级管理员你想做什么");
        articleType.setUpdateTime(new Date());
        return Utils.getResult(articleTypeMapper.modArticleType(articleType));
    }

    @Override
    public ResponseResult deleteArtTypeId(String articleID) {
        if (userMapper.getUserPerMission(Utils.getTokenUserMain()).equals("1"))
            return new ResponseResults<>(CommonCode.UNAUTHORISE,"您不是超级管理员你想做什么");
        return Utils.getResult(articleTypeMapper.deleteArtTypeId(articleID));
    }
}
