package com.share.happy.mange.service;

import com.share.happy.api.ArticleApi;
import com.share.happy.common.model.response.*;
import com.share.happy.dao.ArticleDirMapper;
import com.share.happy.dao.ArticleMapper;
import com.share.happy.dao.UserMapper;
import com.share.happy.mange.ulist.Utils;
import com.share.happy.model.article.*;
import com.share.happy.model.articledir.ArticleDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArticleService implements ArticleApi {
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ArticleDirMapper articleDirMapper;

    @Override
    public ResponseResult addArticle(AddArticle article) {
        String uid=Utils.getTokenUserMain();
        String perMission=userMapper.getUserPerMission(uid);
        if (perMission.equals("3"))
            return new ResponseResults<>(CommonCode.UNAUTHORISE,"您还不是作者，占时无法发布文章！");
        article.setUserid(uid);
        article.setCreateTime(new Date());
        article.setUpdateTime(new Date());

        //添加文章目录 先执行的是 添加目录而 添加的文章id还未生成，所有通过触发器修改表格
        if (articleMapper.addArticle(article)==0)
            return new ResponseResult(CommonCode.FAIL);
        else {
            ArticleDir articleDir=new ArticleDir(article.getArticleDir(),article.getUserid(),article.getArticleid());
            articleDirMapper.inArticleDir(articleDir);
            return new ResponseResult(CommonCode.SUCCESS);
        }

    }

    // 还需要显示作者的一些基础信息
    @Override
    public ResponseResult getAuthorArticleShow(int authorId) {
        List<Article> authorArticle = articleMapper.getAuthorArticle(authorId);
        if (Utils.getTokenUserMain().equals(authorId+""))
            return new QueryResults<>(CommonCode.SUCCESS,authorArticle);
        else
            return new QueryResults<>(CommonCode.UNAUTHORISE,authorArticle);
    }

    // 还需要显示作者的一些基础信息
    @Override
    public ResponseResult findArticle(int articleId) {
        return new ResponseResults<>(CommonCode.SUCCESS,articleMapper.findArticle(articleId));
    }

    @Override
    public ResponseResult articleLike() {
        List<Article> articles=articleMapper.articleLike();
        return new QueryResults<>(CommonCode.SUCCESS,articles);
    }

    @Override
    public ResponseResult articleBrowse() {
        List<Article> articles=articleMapper.articleBrowse();
        return new QueryResults<>(CommonCode.SUCCESS,articles);
    }

    @Override
    public ResponseResult articleCollect() {
        List<Article> articles=articleMapper.articleCollect();
        return new QueryResults<>(CommonCode.SUCCESS,articles);
    }

    @Override
    public ResponseResult modArticle(ModArticle article) {
        //需要指定用户可以操作
        return Utils.getResult(articleMapper.modArticle(article));
    }

    @Override
    public ResponseResult modArtPermission(ArtPermission artPermission) {
        return Utils.getResult(articleMapper.modArtPermission(artPermission));
    }

    @Override
    public ResponseResult modArtReprint(ArtReprint artReprint) {
       return Utils.getResult(articleMapper.modArtReprint(artReprint));
    }

    @Override
    public ResponseResult modArtCore(ArtCore artCore) {
        return Utils.getResult(articleMapper.modArtCore(artCore));
    }

    @Override
    public ResponseResult modArtStatus(ArtStatus artStatus) {
        return Utils.getResult(articleMapper.modArtStatus(artStatus));
    }

    @Override
    public ResponseResult delArticle(int articleId) {
        // 判断文章为同一个作者才可以执行删除 token 和作者id一样才可以删除
        if(Utils.getTokenUserMain().equals(articleMapper.getArtUserId(articleId)))
            return Utils.getResult(articleMapper.delArticle(articleId));
        else
            return new ResponseResults<>(CommonCode.UNAUTHORISE,"您不是该文章的作者无法删除该文章");
    }
}
