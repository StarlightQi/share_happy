package com.share.happy.model.user;

import lombok.Data;

import java.util.Date;

@Data
public class UpUserInfo {
    private String userid;
    private String useremain;
    private String userinfo;
    private String username;
    private String userImg;
    private Boolean gender;
    private String userphone;
    private Date updatetime;
}
