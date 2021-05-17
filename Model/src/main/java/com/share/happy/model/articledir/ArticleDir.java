package com.share.happy.model.articledir;

import com.share.happy.model.BasicsModel;
import lombok.Data;

import java.util.Date;


@Data
public class ArticleDir extends BasicsModel {
    private String aticledirname;
    private String authorid;
    private Integer aticleID;

    public ArticleDir(){}
    public ArticleDir(String aticledirname, String authorid, Integer aticleid) {
        super.setCreateTime(new Date());
        super.setUpdateTime(new Date());
        this.aticledirname = aticledirname;
        this.authorid = authorid;
        this.aticleID = aticleid;
    }
}