package com.share.happy.mange.controller;

import com.share.happy.api.FansApi;
import com.share.happy.common.model.response.ResponseResult;
import com.share.happy.mange.service.FansService;
import com.share.happy.mange.ulist.UserLoginToken;
import com.share.happy.model.Fans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/fans")
@ResponseBody
@UserLoginToken
public class FansController implements FansApi {
    @Autowired
    private FansService fansService;

    @PostMapping("/addUserFans")
    @Override
    public ResponseResult addUserFans(Fans fans) {
        return fansService.addUserFans(fans);
    }

    @GetMapping("/getUserFans")
    @Override
    public ResponseResult getUserFans() {
        return fansService.getUserFans();
    }

    @GetMapping("/getForFans")
    @Override
    public ResponseResult getForFans(String aid) {
        return fansService.getForFans(aid);
    }

    @GetMapping("/deleteUserFans")
    @Override
    public ResponseResult deleteUserFans(int fid) {
        return fansService.deleteUserFans(fid);
    }
}
