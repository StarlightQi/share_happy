package com.share.happy.model.articledir;

import com.share.happy.model.article.ArtShowMenu;
import lombok.Data;

import java.util.List;

@Data
public class ArticleDirAll {
    private Integer aticledirid;
    private String aticledirname;
    private List<ArtShowMenu> aticleid;
}
