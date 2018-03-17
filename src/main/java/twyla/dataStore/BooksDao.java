package twyla.dataStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import twyla.books.Book;
import twyla.comments.Comment;
import twyla.comments.CommentsByUser;
import twyla.exceptions.BadRequestException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class BooksDao {

    @Autowired
    private DataSource dataSource;

    public List<Book> getBooksByUser(String user) {

        String query = "select * from BOOK B WHERE B.USER = ? ORDER BY DATE_TIME DESC";
        List<String> params = Arrays.asList(user);
        return getBooks(executeSelect(query, params));
    }

    public List<Book> getAllOtherBooks(String user) {

        String query = "select * from BOOK B WHERE B.USER <> ? ORDER BY DATE_TIME DESC";

        List<String> params = Arrays.asList(user);
        return getBooks(executeSelect(query, params));
    }

    public int addBooks(Book book) {

        String query = "INSERT INTO BOOK"
            + "(ISBN_ID, TITLE, USER, DATE_TIME) VALUES"
            + "(?,?,?,?)";

        List<String> params = Arrays.asList(book.getIsbnId(), book.getTitle(), book.getUser());
        return executeInsert(query, params);

    }

    public int addComments(Comment comment) {

        String query = "INSERT INTO COMMENTS"
            + "(BOOK_ID, COMMENT, RATING,USER, DATE_TIME) VALUES"
            + "(?,?,?,?,?)";

        List<String> params = Arrays.asList(comment.getBookId(), comment.getComment(), comment.getRating(), comment.getUser());
        return executeInsert(query, params);

    }

    public List<Comment> getComments(String bookId) {

        String query = "select * from COMMENTS where BOOK_ID = ? ORDER BY DATE_TIME DESC";
        List<String> params = Arrays.asList(bookId);
        return getComments(executeSelect(query, params));
    }

    public List<CommentsByUser> getCommentsByUser(String user) {

        String query = "select C.BOOK_ID, B.TITLE, C.COMMENT, C.RATING from COMMENTS C, BOOK B "
            + "where C.BOOK_ID = B.ISBN_ID AND C.USER = ? ORDER BY DATE_TIME DESC";
        List<String> params = Arrays.asList(user);

        return getCommentsByUser(executeSelect(query, params));
    }

    private List<Book> getBooks(ResultSet resultSet) {

        List<Book> books = new ArrayList<>();
        Book book = null;
        try {
            while (resultSet.next()) {
                book = new Book();
                book.setIsbnId(resultSet.getString("ISBN_ID"));
                book.setTitle(resultSet.getString("TITLE"));
                book.setUser(resultSet.getString("USER"));
                books.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return books;
    }

    private List<Comment> getComments(ResultSet resultSet) {

        List<Comment> comments = new ArrayList<>();
        Comment comment = null;
        try {
            while (resultSet.next()) {
                comment = new Comment();
                comment.setBookId(resultSet.getString("BOOK_ID"));
                comment.setComment(resultSet.getString("COMMENT"));
                comment.setRating(resultSet.getString("RATING"));
                comment.setUser(resultSet.getString("USER"));
                comments.add(comment);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return comments;
    }

    private List<CommentsByUser> getCommentsByUser(ResultSet resultSet) {

        List<CommentsByUser> comments = new ArrayList<>();
        CommentsByUser commentsByUser = null;
        try {
            while (resultSet.next()) {
                commentsByUser = new CommentsByUser();
                commentsByUser.setTitle(resultSet.getString("TITLE"));
                commentsByUser.setBookId(resultSet.getString("BOOK_ID"));
                commentsByUser.setComment(resultSet.getString("COMMENT"));
                commentsByUser.setRating(resultSet.getString("RATING"));
                comments.add(commentsByUser);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return comments;
    }

    private ResultSet executeSelect(String query, List<String> params) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = dataSource.getConnection().prepareStatement(query);
            int position = 1;
            for (String param : params) {
                preparedStatement.setString(position, param);
                position++;
            }
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                preparedStatement.close();
                dataSource.getConnection().close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

        return resultSet;
    }

    private int executeInsert(String query, List<String> params) {

        PreparedStatement preparedStatement = null;
        int result;
        try {
            preparedStatement = dataSource.getConnection().prepareStatement(query);

            int position = 1;
            for (String param : params) {
                preparedStatement.setString(position, param);
                position++;
            }

            preparedStatement.setTimestamp(position, getCurrentTimeStamp());
            result = preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new BadRequestException("Book " + params.get(0) + " is already added");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                preparedStatement.close();
                dataSource.getConnection().close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
            return result;
    }

    private java.sql.Timestamp getCurrentTimeStamp() {

        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());

    }

}
