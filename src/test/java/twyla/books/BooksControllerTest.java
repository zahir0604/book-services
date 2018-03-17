package twyla.books;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import twyla.CommonTest;
import twyla.comments.Comment;
import twyla.comments.CommentsByUser;
import twyla.comments.CommentsController;
import twyla.comments.CommentsService;
import twyla.exceptions.BadRequestException;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class BooksControllerTest extends CommonTest{

    private MockMvc mockMvc;

    @Mock
    private BooksService service;

    @InjectMocks
    private BooksController controller;

    private String json;

    private ObjectMapper mapper = new ObjectMapper();

    private Book book = getBook();


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        json = mapper.writeValueAsString(book);
    }

    @Test
    public void testMock() throws Exception {
        Assert.assertNotNull(service);
    }

    @Test
    public void testGetBookByUser() throws Exception {

        when(service.getBooksByUser(anyString())).thenReturn(Arrays.asList(book));
        mockMvc.perform(get("/books/admin")).andExpect(status().isOk());
    }

    @Test
    public void testGetAllOtherBooks() throws Exception {

        when(service.getAllOtherBooks(anyString())).thenReturn(Arrays.asList(book));
        mockMvc.perform(get("/allotherbooks/admin")).andExpect(status().isOk());
    }

    @Test
    public void testAddBook() throws Exception {

        when(service.addBook(any(Book.class))).thenReturn(Arrays.asList(book));
        mockMvc.perform(post("/books").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
    }

    @Test(expected = Exception.class)
    public void testAddExistingBook() throws Exception {

        when(service.addBook(any(Book.class))).thenThrow(new BadRequestException("Book Already exists"));
        mockMvc.perform(post("/books").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteBook() throws Exception {

        mockMvc.perform(delete("/books")).andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void testPutBook() throws Exception {

        mockMvc.perform(put("/books")).andExpect(status().isMethodNotAllowed());
    }

}
