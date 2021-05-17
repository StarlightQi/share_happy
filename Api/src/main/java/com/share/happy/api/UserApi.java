package com.share.happy.api;

import com.share.happy.common.model.response.ResponseResult;
import com.share.happy.model.ClickLick;
import com.share.happy.model.user.RegisterUser;
import com.share.happy.model.user.UpUserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.mobile.device.Device;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;


@Api(value = "用户操作",description = "用户的各种操作")
public interface UserApi {
    @ApiOperation("用户登录")
    public ResponseResult userLogin(String phone, String password, Device device);

    @ApiOperation("用户注册")
    public ResponseResult register(RegisterUser user);

    @ApiOperation("获取用户信息")
    public ResponseResult getUserInfo();

    @ApiOperation("用户个人信息修改")
    public ResponseResult updateUserInfo(UpUserInfo user);

    @ApiOperation("用户密码修改")
    public ResponseResult updateUserPassword(String oldPassword, String password);

    @ApiOperation("获取用户菜单")
    public ResponseResult getMenus();

    @ApiOperation("上传文件")
    ResponseResult uploadFile(MultipartFile file);

    @ApiOperation("获取登录安全信息")
    ResponseResult getSecurity();

    @ApiOperation("用户点赞")
    ResponseResult clickLick(ClickLick clickLick);

    @ApiOperation("用户是否点赞过作品")
    ResponseResult isLick(String aid);

    @ApiOperation("取消点赞")
    ResponseResult CancelClick(String aid);

    @ApiOperation("发送邮箱验证码")
    ResponseResult sendCode(String email) throws MessagingException, UnsupportedEncodingException;

}
