package twyla.datastore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import twyla.books.Book;
import twyla.comments.Comment;
import twyla.dataStore.BooksDao;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BooksDaoTest {

    @Mock
    private BooksDao booksDao;

    private Book book;

    @Before
    public void setUp() throws Exception {

        book = new Book();
        book.setUser("admin");
        book.setTitle("Head First Java");
        book.setIsbnId("001");

    }

    @Test
    public void testMockDao() throws Exception {
        Assert.assertNotNull(booksDao);
    }

    @Test
    public void testGetBooksByUser() throws Exception {
        when(booksDao.getBooksByUser(any(String.class))).thenReturn(Arrays.asList(book));
        List<Book> books = booksDao.getBooksByUser("admin");
        Assert.assertEquals(1, books.size());
        verify(book, books.get(0));
    }

    @Test
    public void testGetAllOtherBooks() throws Exception {
        when(booksDao.getAllOtherBooks(any(String.class))).thenReturn(Arrays.asList(book));
        List<Book> books = booksDao.getAllOtherBooks("admin");
        Assert.assertEquals(1, books.size());
        verify(book, books.get(0));
    }

    @Test
    public void testGetCommentsForABook() throws Exception {
        Comment comment = new Comment();
        comment.setBookId("001");
        comment.setUser("Admin");
        comment.setRating("5");
        comment.setComment("Excellent");

        when(booksDao.getComments(any(String.class))).thenReturn(Arrays.asList(comment));
        List<Comment> comments = booksDao.getComments("admin");
        Assert.assertEquals(1, comments.size());
        Assert.assertEquals(comment.getBookId(), comments.get(0).getBookId());
        Assert.assertEquals(comment.getComment(), comments.get(0).getComment());
        Assert.assertEquals(comment.getUser(), comments.get(0).getUser());
        Assert.assertEquals(comment.getRating(), comments.get(0).getRating());

    }

    @Test
    public void testAddBook() throws Exception {

        when(booksDao.addBooks(any(Book.class))).thenReturn(1);
        Assert.assertEquals(1, booksDao.addBooks(new Book()));
    }

    @Test
    public void testAddComment() throws Exception {
        when(booksDao.addComments(any(Comment.class))).thenReturn(1);
        Assert.assertEquals(1, booksDao.addComments(new Comment()));

    }

    private void verify(Book expected, Book actual) {
        Assert.assertEquals(expected.getIsbnId(), actual.getIsbnId());
        Assert.assertEquals(expected.getTitle(), actual.getTitle());
        Assert.assertEquals(expected.getUser(), actual.getUser());
    }

}
