package com.share.happy.mange.service;

import com.share.happy.api.ReArticleApi;
import com.share.happy.common.model.response.CommonCode;
import com.share.happy.common.model.response.QueryResults;
import com.share.happy.common.model.response.ResponseResult;
import com.share.happy.dao.ReArticleMapper;
import com.share.happy.mange.ulist.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReArticleService implements ReArticleApi {
    @Autowired
    private ReArticleMapper reArticleMapper;

    @Override
    public ResponseResult getReArticle() {
        return new QueryResults<>(CommonCode.SUCCESS,reArticleMapper.getAllReArticle(Utils.getTokenUserMain()));
    }

    @Override
    public ResponseResult deleteReArticle(String aid) {
        return Utils.getResult(reArticleMapper.deleteReArticle(aid));
    }
}
