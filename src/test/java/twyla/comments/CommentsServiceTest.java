package twyla.comments;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CommentsServiceTest {

    @Mock
    private CommentsService service;

    private Comment comment;

    @Before
    public void setUp() throws Exception {
        comment = new Comment();
        comment.setBookId("001");
        comment.setUser("Admin");
        comment.setRating("5");
        comment.setComment("Excellent");
    }

    @Test
    public void testMock() throws Exception {
        Assert.assertNotNull(service);
    }

    @Test
    public void testGetComments() throws Exception {
        when(service.getComments(any(String.class))).thenReturn(Arrays.asList(comment));

        List<Comment> comments = service.getComments("001");
        Assert.assertEquals(1, comments.size());
        verify(comment, comments.get(0));
    }

    @Test
    public void testGetCommentsByUser() throws Exception {

        CommentsByUser commentsByUser = new CommentsByUser();
        commentsByUser.setBookId("001");
        commentsByUser.setTitle("Clean Code");
        commentsByUser.setRating("5");
        commentsByUser.setComment("Good");
        commentsByUser.setUser("Admin");

        when(service.getCommentsByUser(any(String.class))).thenReturn(Arrays.asList(commentsByUser));
        List<CommentsByUser> commentsByUsers = service.getCommentsByUser("Admin");
        Assert.assertEquals(1, commentsByUsers.size());

    }

    @Test
    public void testAddComments() throws Exception {

        when(service.addComment(any(Comment.class))).thenReturn(Arrays.asList(comment));
        List<Comment> comments = service.addComment(comment);
        Assert.assertEquals(1, comments.size());

    }

    private void verify(Comment expected, Comment actual) {
        Assert.assertEquals(expected.getBookId(), actual.getBookId());
        Assert.assertEquals(expected.getComment(), actual.getComment());
        Assert.assertEquals(expected.getUser(), actual.getUser());
        Assert.assertEquals(expected.getRating(), actual.getRating());
    }
}
