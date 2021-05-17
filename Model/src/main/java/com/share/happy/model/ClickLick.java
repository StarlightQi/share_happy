package com.share.happy.model;

import lombok.Data;

import java.util.Date;

@Data
public class ClickLick {
    private String lickId;
    private String lickuId;
    private String lickArtId;
    private Date updateTime;
    private Date createTime;
}
