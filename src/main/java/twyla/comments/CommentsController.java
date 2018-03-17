package twyla.comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentsController {

    @Autowired
    private CommentsService service;

    @RequestMapping(method = RequestMethod.GET, value = "/comments/{bookId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Comment> getComments(@PathVariable String bookId) {

        return service.getComments(bookId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/comments/user/{user}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<CommentsByUser> getCommentsByUser(@PathVariable String user) {

        return service.getCommentsByUser(user);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/comments", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Comment> addComment(@RequestBody Comment comment) {

        return service.addComment(comment);
    }
}
