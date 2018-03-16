package twyla.books;

import twyla.dataStore.BooksDao;

import java.util.List;

@org.springframework.stereotype.Service
public class BooksService {


    public static List<Book> getBooksByUser(String user) {
        return BooksDao.getBooksByUser(user);
    }

    public static List<Book> getAllOtherBooks(String user) {
        return BooksDao.getAllOtherBooks(user);
    }

    public  static List<Book> addBook(Book book) {
        BooksDao.addBooks(book);

        return BooksDao.getBooksByUser(book.getUser());
    }
}
