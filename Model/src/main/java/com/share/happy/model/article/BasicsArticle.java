package com.share.happy.model.article;

import lombok.Data;

import java.util.Date;

@Data
public class BasicsArticle {
    private int articleid;
    private Date updateTime;
    public BasicsArticle(){
        this.updateTime=new Date();
    }
}
