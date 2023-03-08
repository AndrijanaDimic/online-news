package rs.raf.demo.repositories.comments;

import rs.raf.demo.entities.Comment;

import java.util.List;

public interface CommentRepository {

    public Comment addComment(Comment comment);
    public List<Comment> allComments(int newsId);

}
