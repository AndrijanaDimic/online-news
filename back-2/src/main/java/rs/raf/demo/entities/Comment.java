package rs.raf.demo.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class Comment {

    private int commentId;
    private String author;
    private String text;
    private Date commentDate;
    private int newsId;

    public Comment(){

    }
    public Comment(int commentId, String name, String text, Date commentDate, int newsId) {
        this.author = name;
        this.text = text;
        this.commentDate = commentDate;
        this.commentId = commentId;
        this.newsId = newsId;
    }

    public int getCommentId() {
        return commentId;
    }

    public String getName() {
        return author;
    }

    public String getText() {
        return text;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", author='" + author + '\'' +
                ", text='" + text + '\'' +
                ", commentDate=" + commentDate +
                ", newsId=" + newsId +
                '}';
    }
}
