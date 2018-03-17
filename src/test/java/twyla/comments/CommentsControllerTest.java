package twyla.comments;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import twyla.CommonTest;
import twyla.config.WebAppConfig;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebAppConfig.class })
@WebAppConfiguration
public class CommentsControllerTest extends CommonTest {

    private MockMvc mockMvc;

    @Mock
    private CommentsService service;

    @InjectMocks
    private CommentsController controller;

    private String json;

    private ObjectMapper mapper = new ObjectMapper();

    private Comment comment = getComment();

    private CommentsByUser commentsByUser = getCommentByUser();

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        json = mapper.writeValueAsString(getBook());
    }

    @Test
    public void testMock() throws Exception {
        Assert.assertNotNull(service);
    }

    @Test
    public void testGetCommentsForBook() throws Exception {

        when(service.getComments(anyString())).thenReturn(Arrays.asList(comment));
        mockMvc.perform(get("/comments/001")).andExpect(status().isOk());
    }

    @Test
    public void testGetCommentsByUser() throws Exception {

        when(service.getCommentsByUser(anyString())).thenReturn(Arrays.asList(commentsByUser));
        mockMvc.perform(get("/comments/user/Admin")).andExpect(status().isOk());
    }

    @Test
    public void testAddComment() throws Exception {

        when(service.addComment(any(Comment.class))).thenReturn(Arrays.asList(comment));
        mockMvc.perform(post("/comments").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
    }

    @Test
    public void testDeleteComment() throws Exception {

        mockMvc.perform(delete("/comments")).andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void testPutComment() throws Exception {

        mockMvc.perform(put("/comments")).andExpect(status().isMethodNotAllowed());
    }
}
