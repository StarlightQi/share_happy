package com.share.happy.model;

import lombok.Data;

@Data
public class ReArticle extends BasicsModel{
    private Integer articleid;
    private String title;
    private Integer userid;
    private Integer articletypeid;
    private Byte articlepermission;
    private Byte reprint;
    private Integer readcount;
    private Integer likecount;
    private Integer articlecore;
    private Byte status;
    private String articlecontext;
}