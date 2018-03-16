package twyla.books;

import twyla.dataStore.BooksDao;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class BooksService {


    public static List<Book> getBooks() {
        return BooksDao.getBooks();
    }

    public  static List<Book> addBook(Book book) {
        BooksDao.addBooks(book);

        return BooksDao.getBooks();
    }
}
