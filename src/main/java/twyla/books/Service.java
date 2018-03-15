package twyla.books;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    private static List<Book> books = new ArrayList<>();

    public static List<Book> getBooks() {
        if (books.isEmpty()){
            books.add(new Book("1","test"));
        }
        return books;
    }

    public  static List<Book> addBook(Book book) {
        books.add(book);

        return books;
    }
}
