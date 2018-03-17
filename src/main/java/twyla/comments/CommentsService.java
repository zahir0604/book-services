package twyla.comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twyla.dataStore.BooksDao;

import java.util.List;

@Service
public class CommentsService {

    @Autowired
    private BooksDao booksDao;

    public List<Comment> getComments(String bookId) {

        return booksDao.getComments(bookId);
    }

    public List<CommentsByUser> getCommentsByUser(String user) {

        return booksDao.getCommentsByUser(user);
    }

    public List<Comment> addComment(Comment comment) {
        booksDao.addComments(comment);
        return booksDao.getComments(comment.getBookId());
    }
}
