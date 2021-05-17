package com.share.happy.mange.service;

import com.share.happy.api.FansApi;
import com.share.happy.common.model.response.CommonCode;
import com.share.happy.common.model.response.ResponseResult;
import com.share.happy.common.model.response.ResponseResults;
import com.share.happy.dao.FansMapper;
import com.share.happy.mange.ulist.Utils;
import com.share.happy.model.Fans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FansService implements FansApi {
    @Autowired
    private FansMapper fansMapper;

    @Override
    public ResponseResult addUserFans(Fans fans) {
        fans.setUserid(Utils.getTokenUserMain());
        fans.setCreateTime(new Date());
        fans.setUpdateTime(new Date());
        return Utils.getResult(fansMapper.addUserFans(fans));
    }

    @Override
    public ResponseResult getUserFans() {
        return  new ResponseResults<>(CommonCode.SUCCESS,fansMapper.getUserFans(Utils.getTokenUserMain()));
    }

    @Override
    public ResponseResult getForFans(String aid) {
        return new ResponseResults<>(CommonCode.SUCCESS,fansMapper.getForFans(aid));
    }

    @Override
    public ResponseResult deleteUserFans(int fid) {
        if (!fansMapper.getUserId(fid+"").equals(Utils.getTokenUserMain()))
            return new ResponseResults<>(CommonCode.UNAUTHORISE,"别动歪心思，嘿嘿");
        return Utils.getResult(fansMapper.deleteUserFans(fid));
    }
}
