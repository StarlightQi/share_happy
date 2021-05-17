package com.share.happy.model.user;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer userid;
    private String username;
    private String userImg;
    private Boolean gender;
    private String useremain;
    private Integer usergrade;
    private Integer userscore;
    private Byte userpermission;
    private String userphone;
    private String userinfo;
    private Date createtime;
    private Date updatetime;
    private String userpassword;
    private String token;

}