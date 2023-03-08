package rs.raf.demo.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class News_Tag {

    private  int newsId;
    private  int tagId;
    private int news_tagId;

    public News_Tag(){

    }
    public News_Tag(int news_tagId, int newsId, int tagId){
        this.news_tagId = news_tagId;
        this.tagId  =tagId;
        this.newsId = newsId;
    }

    public int getTagId() {
        return tagId;
    }

    public int getNews_tagId() {
        return news_tagId;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public void setNews_tagId(int news_tagId) {
        this.news_tagId = news_tagId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }
}
