package rs.raf.demo.services;

import rs.raf.demo.entities.Comment;
import rs.raf.demo.entities.Subject;
import rs.raf.demo.repositories.comments.CommentRepository;
import rs.raf.demo.repositories.subject.SubjectRepository;

import javax.inject.Inject;
import java.util.List;

public class CommentService {

    public CommentService() {
        System.out.println(this);
    }

    @Inject
    private CommentRepository commentRepository;

    public Comment addComments(Comment comment) {
        return this.commentRepository.addComment(comment);
    }

    public List<Comment> allComments(int newsId) {
        return this.commentRepository.allComments(newsId);
    }
}
