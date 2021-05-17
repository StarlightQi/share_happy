package com.share.happy.model;

import lombok.Data;

@Data
public class Comment extends BasicsModel {
    private Integer commentid;
    private String userid;
    private String articleid;
    private String content;
}