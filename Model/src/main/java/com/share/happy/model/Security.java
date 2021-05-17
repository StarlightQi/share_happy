package com.share.happy.model;

import lombok.Data;

import java.util.Date;

@Data
public class Security {
    private String userId;
    private String userIp;
    private String browser;
    private String equipment;
    private Date createTime;
    private Date updateTime;

    public Security() {
    }

    public Security(String userId, String userIp, String browser, String equipment, Date createTime, Date updateTime) {
        this.userId = userId;
        this.userIp = userIp;
        this.browser = browser;
        this.equipment = equipment;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
