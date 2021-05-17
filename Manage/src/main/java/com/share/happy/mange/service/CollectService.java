package com.share.happy.mange.service;

import com.share.happy.api.CollectApi;
import com.share.happy.common.model.response.*;
import com.share.happy.dao.CollectMapper;
import com.share.happy.mange.ulist.Utils;
import com.share.happy.model.Collect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CollectService implements CollectApi {

    @Autowired
    private CollectMapper collectMapper;

    @Override
    public ResponseResult addCollect(Collect collect) {
        collect.setUserid(Utils.getTokenUserMain());
        collect.setCreateTime(new Date());
        collect.setUpdateTime(new Date());
        return Utils.getResult(collectMapper.addCollect(collect));
    }

    @Override
    public ResponseResult getAllUserCollect() {
        return new QueryResults<>(CommonCode.SUCCESS,collectMapper.getAllUserCollect(Utils.getTokenUserMain()));
    }

    @Override
    public ResponseResult deleteCollect(String cid) {
        if (!collectMapper.getUserId(cid).equals(Utils.getTokenUserMain()))
            return new ResponseResults<>(CommonCode.UNAUTHORISE,"你不是该收藏的注入你想做什么！");
        return Utils.getResult(collectMapper.deleteCollect(cid));
    }
}
