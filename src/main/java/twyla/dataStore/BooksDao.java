package twyla.dataStore;

import twyla.books.Book;
import twyla.comments.Comment;
import twyla.comments.CommentsByUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BooksDao {

    public static List<Book> getBooksByUser(String user) {

        String query = "select * from BOOK B WHERE B.USER = ? ORDER BY DATE_TIME DESC";

        PreparedStatement preparedStatement = getPreparedStatement(query);
        ResultSet resultSet = null;
        try {
            preparedStatement.setString(1,user);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return getBooks(resultSet);
    }

    public static List<Book> getAllOtherBooks(String user) {

        String query = "select * from BOOK B WHERE B.USER <> ? ORDER BY DATE_TIME DESC";

        PreparedStatement preparedStatement = getPreparedStatement(query);
        ResultSet resultSet = null;
        try {
            preparedStatement.setString(1,user);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return getBooks(resultSet);
    }

    private static List<Book> getBooks(ResultSet resultSet) {

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

    public static void addBooks(Book book) {

        String query = "INSERT INTO BOOK"
            + "(ISBN_ID, TITLE, USER, DATE_TIME) VALUES"
            + "(?,?,?,?)";

        PreparedStatement preparedStatement = getPreparedStatement(query);
        try {
            preparedStatement.setString(1, book.getIsbnId());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getUser());
            preparedStatement.setTimestamp(4,getCurrentTimeStamp());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void addComments(Comment comment) {

        String query = "INSERT INTO COMMENTS"
            + "(BOOK_ID, COMMENT, RATING,USER, DATE_TIME) VALUES"
            + "(?,?,?,?,?)";

        PreparedStatement preparedStatement = getPreparedStatement(query);
        ResultSet resultSet = null;
        try {
            preparedStatement.setString(1, comment.getBookId());
            preparedStatement.setString(2, comment.getComment());
            preparedStatement.setString(3, comment.getRating());
            preparedStatement.setString(4, comment.getUser());
            preparedStatement.setTimestamp(5,getCurrentTimeStamp());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public static List<Comment> getComments(String bookId) {

        String query = "select * from COMMENTS where BOOK_ID = ? ORDER BY DATE_TIME DESC";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = getPreparedStatement(query);
            preparedStatement.setString(1, bookId);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return getComments(resultSet);
    }

    public static List<CommentsByUser> getCommentsByUser(String user) {

        String query = "select C.BOOK_ID, B.TITLE, C.COMMENT, C.RATING from COMMENTS C, BOOK B "
            + "where C.BOOK_ID = B.ISBN_ID AND C.USER = ? ORDER BY DATE_TIME DESC";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = getPreparedStatement(query);
            preparedStatement.setString(1, user);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return getCommentsByUser(resultSet);
    }

    private static List<Comment> getComments(ResultSet resultSet) {

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
    private static List<CommentsByUser> getCommentsByUser(ResultSet resultSet) {

        List<CommentsByUser> comments = new ArrayList<>();
        CommentsByUser commentsByUser = null;
        try {
            while (resultSet.next()) {
                commentsByUser= new CommentsByUser();
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

    private static Connection connection;

    private static Connection getConnection() {

        if (connection == null) {
            try {
                Class.forName("org.hsqldb.jdbc.JDBCDriver");
                //Creating the connection with HSQLDB
                connection = DriverManager.getConnection("jdbc:hsqldb:file:C:/Workspace/Twyla/book-services/books", "sa", "");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

        }
        return connection;
    }

    private static PreparedStatement preparedStatement;

    private static PreparedStatement getPreparedStatement(String query) {
        try {
            preparedStatement = getConnection().prepareStatement(query);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return preparedStatement;
    }

    private static java.sql.Timestamp getCurrentTimeStamp() {

        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());

    }

}
