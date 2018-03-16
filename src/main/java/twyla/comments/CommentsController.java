package twyla.comments;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentsController {

    @RequestMapping(method = RequestMethod.GET, value = "/comments/{bookId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Comment> getComments(@PathVariable String bookId) {

        return CommentsService.getComments(bookId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/comments/user/{user}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<CommentsByUser> getCommentsByUser(@PathVariable String user) {

        return CommentsService.getCommentsByUser(user);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/comments", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Comment> addComment(@RequestBody Comment comment) {

        return CommentsService.addComment(comment);
    }
}
