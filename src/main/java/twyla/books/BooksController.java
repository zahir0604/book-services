package twyla.books;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BooksController {

    @RequestMapping(method = RequestMethod.GET, value = "/books/{user}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Book> getAllByBooks(@PathVariable String user) {

        return BooksService.getBooksByUser(user);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/books", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Book> addBook(@RequestBody Book book) {

        return BooksService.addBook(book);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/allotherbooks/{user}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Book> getAllOtherBooks(@PathVariable String user) {

        return BooksService.getAllOtherBooks(user);
    }
}
