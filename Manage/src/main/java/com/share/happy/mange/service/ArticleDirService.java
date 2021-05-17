package com.share.happy.mange.service;
import com.share.happy.api.ArticleDirApi;
import com.share.happy.common.model.response.CommonCode;
import com.share.happy.common.model.response.ResponseResult;
import com.share.happy.common.model.response.ResponseResults;
import com.share.happy.dao.ArticleDirMapper;
import com.share.happy.mange.ulist.Utils;
import com.share.happy.model.article.ArtShowMenu;
import com.share.happy.model.articledir.ArticleDir;
import com.share.happy.model.articledir.ArticleDirAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class ArticleDirService implements ArticleDirApi {

    @Autowired
    private ArticleDirMapper articleDirMapper;

    @Override
    public ResponseResult findArticleDirAll() {
        List<ArticleDirAll> articleDirAll = articleDirMapper.findArticleDirAll(Utils.getTokenUserMain());
        HashMap<String, List<ArticleDirAll>> data=new HashMap<>();
        for(int i=0;articleDirAll.size()>i;i++){
            ArticleDirAll dirAll = articleDirAll.get(i);
            String name=dirAll.getAticledirname();
            if(!data.containsKey(name)){
                List<ArticleDirAll> att=new ArrayList<>();
                data.put(name, att);
                data.get(name).add(dirAll);
            }
            else {
                data.get(name).add(dirAll);
            }
        }
        return new ResponseResults<>(CommonCode.SUCCESS,data);
    }

    @Override
    public ResponseResult changeArticleDir(ArticleDir articleDir) {
        articleDir.setUpdateTime(new Date());
        return Utils.getResult(articleDirMapper.ChangArticleDir(articleDir));
    }
}
