package twyla.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {


    @RequestMapping(method = RequestMethod.GET, value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Book> getAllBooks() {

        return Service.getBooks();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/books", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Book> addBook(@RequestBody Book book) {

        return Service.addBook(book);
    }
}
