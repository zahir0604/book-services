package twyla.books;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BooksServiceTest {

    @Mock
    private BooksService service;

    private Book book;

    @Before
    public void setUp() throws Exception {

        book = new Book();
        book.setUser("admin");
        book.setTitle("Head First Java");
        book.setIsbnId("001");

    }

    @Test
    public void testMock() throws Exception {
        Assert.assertNotNull(service);
    }

    @Test
    public void testGetBooksByUser() throws Exception {

        when(service.getBooksByUser(any(String.class))).thenReturn(Arrays.asList(book));
        List<Book> books = service.getBooksByUser("Admin");
        Assert.assertEquals(1, books.size());
        verify(book, books.get(0));

    }

    @Test
    public void testGetAllOtherBooks() throws Exception {

        when(service.getAllOtherBooks(any(String.class))).thenReturn(Arrays.asList(book));
        List<Book> books = service.getAllOtherBooks("Admin");
        Assert.assertEquals(1, books.size());
        verify(book, books.get(0));

    }

    @Test
    public void testAddBook() throws Exception {

        when(service.addBook(any(Book.class))).thenReturn(Arrays.asList(book));
        List<Book> books = service.addBook(book);
        Assert.assertEquals(1, books.size());
        verify(book, books.get(0));

    }

    private void verify(Book expected, Book actual) {
        Assert.assertEquals(expected.getIsbnId(), actual.getIsbnId());
        Assert.assertEquals(expected.getTitle(), actual.getTitle());
        Assert.assertEquals(expected.getUser(), actual.getUser());
    }
}
