package com.share.happy.model;

import lombok.Data;

@Data
public class Collect extends BasicsModel {
    private Integer collectid;
    private String articleid;
    private String userid;
}