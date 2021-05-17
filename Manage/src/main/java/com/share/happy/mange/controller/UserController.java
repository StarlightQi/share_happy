package com.share.happy.mange.controller;

import com.share.happy.api.UserApi;
import com.share.happy.common.model.response.ResponseResult;
import com.share.happy.mange.service.UserServer;
import com.share.happy.mange.ulist.UserLoginToken;
import com.share.happy.model.ClickLick;
import com.share.happy.model.user.RegisterUser;
import com.share.happy.model.user.UpUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/authorAdmin")
@ResponseBody
public class UserController implements UserApi {
    @Autowired
    private UserServer userServer;

    @Override
    @PostMapping("/userLogin")
    public ResponseResult userLogin(@RequestParam String mail, @RequestParam String password, Device device) {
        return userServer.userLogin(mail,password,device);
    }

    @Override
    @PostMapping("/register")
    public ResponseResult register(@RequestBody RegisterUser user) {
        return userServer.register(user);
    }

    @Override
    @GetMapping("/getUserInfo")
    @UserLoginToken
    public ResponseResult getUserInfo() {
        return userServer.getUserInfo();
    }

    @Override
    @PutMapping("/updateUserInfo")
    @UserLoginToken
    public ResponseResult updateUserInfo(@RequestBody UpUserInfo user) {
        return userServer.updateUserInfo(user);
    }

    @Override
    @PostMapping("/updateUserPassword")
    @UserLoginToken
    public ResponseResult updateUserPassword(@RequestParam String oldPassword, @RequestParam String newPassword) {
        return userServer.updateUserPassword(oldPassword,newPassword);
    }

    @Override
    @GetMapping("/getMenus")
    @UserLoginToken
    public ResponseResult getMenus() {
        return userServer.getMenus();
    }

    @Override
    @PostMapping("/uploadFile")
    public ResponseResult uploadFile(MultipartFile file) {
        return userServer.uploadFile(file);
    }

    @Override
    @GetMapping("/getSecurity")
    @UserLoginToken
    public ResponseResult getSecurity() {
        return userServer.getSecurity();
    }

    @Override
    @GetMapping("/clickLick")
    @UserLoginToken
    public ResponseResult clickLick(ClickLick clickLick) {
        return userServer.clickLick(clickLick);
    }

    @Override
    @GetMapping("/isClick")
    @UserLoginToken
    public ResponseResult isLick(@RequestParam  String aid) {
        return userServer.isLick(aid);
    }

    @Override
    @GetMapping("/cancelClick")
    @UserLoginToken
    public ResponseResult CancelClick(String aid) {
        return userServer.CancelClick(aid);
    }

    @PostMapping("/sendCode")
    @Override
    public ResponseResult sendCode(@RequestParam String email) throws MessagingException, UnsupportedEncodingException {
        return userServer.sendCode(email);
    }


}
