package rs.raf.demo.repositories.comments;

import rs.raf.demo.entities.Comment;
import rs.raf.demo.entities.Subject;
import rs.raf.demo.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlCommentRepository extends MySqlAbstractRepository implements CommentRepository {
    @Override
    public Comment addComment(Comment comment) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"commentId"};

            preparedStatement = connection.prepareStatement("INSERT INTO comments (author,comment, createdDate, newsId) VALUES(?, ?, ?, ?) ", generatedColumns);
            preparedStatement.setString(1, comment.getAuthor());
            preparedStatement.setString(2, comment.getText());
            preparedStatement.setDate(3, Date.valueOf(java.time.LocalDate.now()));
            preparedStatement.setInt(4, comment.getNewsId());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                comment.setCommentId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return comment;
    }

    @Override
    public List<Comment> allComments(int newsId) {

        List<Comment> comments = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("select * from comments WHERE newsId = ?");
            preparedStatement.setInt(1, newsId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                comments.add(new Comment(resultSet.getInt("commentId"), resultSet.getString("author"), resultSet.getString("comment"), resultSet.getDate("createdDate"), resultSet.getInt("newsId")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return comments;
    }
}
