package twyla.books;

import org.springframework.beans.factory.annotation.Autowired;
import twyla.dataStore.BooksDao;

import java.util.List;

@org.springframework.stereotype.Service
public class BooksService {

    @Autowired
    private BooksDao booksDao;

    public List<Book> getBooksByUser(String user) {
        return booksDao.getBooksByUser(user);
    }

    public List<Book> getAllOtherBooks(String user) {
        return booksDao.getAllOtherBooks(user);
    }

    public List<Book> addBook(Book book) {

        booksDao.addBooks(book);
        return booksDao.getBooksByUser(book.getUser());
    }
}
