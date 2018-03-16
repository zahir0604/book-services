package twyla.datastore;

import org.junit.Test;
import twyla.books.Book;
import twyla.dataStore.BooksDao;

public class BooksDaoTest {

    @Test
    public void testGetBooks() throws Exception {

        System.out.println(BooksDao.getBooksByUser().size());
    }

    @Test
    public void addBooks() throws Exception {

       BooksDao.addBooks(new Book("02", "Titile", "Admin"));

        System.out.println( BooksDao.getBooksByUser().size());
    }
}
