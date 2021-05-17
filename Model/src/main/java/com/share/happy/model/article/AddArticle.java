package com.share.happy.model.article;

import com.share.happy.model.BasicsModel;
import lombok.Data;
@Data
public class AddArticle extends BasicsModel {
    private int articleid;
    private String title;
    private String userid;
    private Integer articletypeid;
    private String articlecontext;
    private String articleDir;
}
