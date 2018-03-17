package twyla.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BooksController {

    @Autowired
    private BooksService service;

    @RequestMapping(method = RequestMethod.GET, value = "/books/{user}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Book> getAllByBooks(@PathVariable String user) {

        return service.getBooksByUser(user);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/books", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Book> addBook(@RequestBody Book book) {

        return service.addBook(book);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/allotherbooks/{user}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Book> getAllOtherBooks(@PathVariable String user) {

        return service.getAllOtherBooks(user);
    }
}
