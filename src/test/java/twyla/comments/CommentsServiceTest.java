package twyla.comments;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import twyla.CommonTest;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CommentsServiceTest extends CommonTest {

    @Mock
    private CommentsService service;

    private Comment comment = getComment();

    private CommentsByUser commentsByUser = getCommentByUser();

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

        when(service.getCommentsByUser(any(String.class))).thenReturn(Arrays.asList(commentsByUser));
        List<CommentsByUser> commentsByUsers = service.getCommentsByUser("Admin");
        Assert.assertEquals(1, commentsByUsers.size());
        verify(commentsByUser, commentsByUsers.get(0));

    }

    @Test
    public void testAddComments() throws Exception {

        when(service.addComment(any(Comment.class))).thenReturn(Arrays.asList(comment));
        List<Comment> comments = service.addComment(getComment());
        Assert.assertEquals(1, comments.size());

    }

}
