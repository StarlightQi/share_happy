package com.share.happy.mange.controller;

import com.share.happy.api.CollectApi;
import com.share.happy.common.model.response.ResponseResult;
import com.share.happy.mange.service.CollectService;
import com.share.happy.mange.ulist.UserLoginToken;
import com.share.happy.model.Collect;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/collect")
@ResponseBody
@UserLoginToken
public class CollectController implements CollectApi {

    @Autowired
    private CollectService collectService;

    @PostMapping("/addCollect")
    @Override
    public ResponseResult addCollect(Collect collect) {
        return collectService.addCollect(collect);
    }

    @GetMapping("/getUserCollect")
    @Override
    public ResponseResult getAllUserCollect() {
        return collectService.getAllUserCollect();
    }

    @Delete("/deleteCollect")
    @Override
    public ResponseResult deleteCollect(String cid) {
        return collectService.deleteCollect(cid);
    }
}
