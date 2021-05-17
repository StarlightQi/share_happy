package com.share.happy.model.article;

import com.share.happy.model.user.UserInfoShow;
import lombok.Data;

@Data
public class Article extends  BasicsArticle {
    private String title;
    private UserInfoShow userid;
    private Integer articletypeid;
    private Byte articlepermission;
    private Boolean reprint;
    private Integer readcount;
    private Integer likecount;
    private Integer articlecore;
    private Boolean status;
    private String articlecontext;
}