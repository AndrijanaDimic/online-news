package rs.raf.demo.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.security.KeyStore;
import java.util.Date;
import java.util.List;

public class News {

    private int newsId;
    private String title;
    private String content;
    private Date createdDate;
    private long numberOfVisitors;
    private String author;
    private int commentId;
    private int categoryId;
    private int total;
    public News(){

    }
    public News(int newsId, String title, String content, Date createdDate, long numberOfVisitors, String author, int categoryId ) {
        this.newsId = newsId;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.numberOfVisitors = numberOfVisitors;
        this.author = author;
        this.categoryId = categoryId;
    }

    public int getNewsId() {
        return newsId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setNumberOfVisitors(long numberOfVisitors) {
        this.numberOfVisitors = numberOfVisitors;
    }

    public String getAuthor() {
        return author;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public long getNumberOfVisitors() {
        return numberOfVisitors;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "News{" +
                "newsId=" + newsId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdDate=" + createdDate +
                ", numberOfVisitors=" + numberOfVisitors +
                ", author='" + author + '\'' +
                ", commentId=" + commentId +
                ", categoryId=" + categoryId +
                '}';
    }
}
