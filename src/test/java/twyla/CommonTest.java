package twyla;

import org.junit.Assert;
import twyla.books.Book;
import twyla.comments.Comment;
import twyla.comments.CommentsByUser;

public abstract class CommonTest {

    protected Comment getComment() {

        Comment comment = new Comment();
        comment.setBookId("001");
        comment.setUser("Admin");
        comment.setRating("5");
        comment.setComment("Excellent");

        return comment;

    }

    protected CommentsByUser getCommentByUser() {

        CommentsByUser commentsByUser = new CommentsByUser();
        commentsByUser.setBookId("001");
        commentsByUser.setTitle("Clean Code");
        commentsByUser.setRating("5");
        commentsByUser.setComment("Good");
        commentsByUser.setUser("Admin");

        return commentsByUser;

    }

    protected Book getBook() {

        Book book = new Book();
        book.setUser("admin");
        book.setTitle("Head First Java");
        book.setIsbnId("001");

        return book;
    }

    protected void verify(Comment expected, Comment actual) {
        Assert.assertEquals(expected.getBookId(), actual.getBookId());
        Assert.assertEquals(expected.getComment(), actual.getComment());
        Assert.assertEquals(expected.getUser(), actual.getUser());
        Assert.assertEquals(expected.getRating(), actual.getRating());
    }

    protected void verify(CommentsByUser expected, CommentsByUser actual) {
        Assert.assertEquals(expected.getBookId(), actual.getBookId());
        Assert.assertEquals(expected.getTitle(), actual.getTitle());
        Assert.assertEquals(expected.getComment(), actual.getComment());
        Assert.assertEquals(expected.getUser(), actual.getUser());
        Assert.assertEquals(expected.getRating(), actual.getRating());
    }

    protected void verify(Book expected, Book actual) {
        Assert.assertEquals(expected.getIsbnId(), actual.getIsbnId());
        Assert.assertEquals(expected.getTitle(), actual.getTitle());
        Assert.assertEquals(expected.getUser(), actual.getUser());
    }
}
