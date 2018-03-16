package twyla.comments;

import twyla.dataStore.BooksDao;

import java.util.List;

public class CommentsService {


    public static List<Comment> getComments(String bookId) {

        return BooksDao.getComments(bookId);
    }

    public static List<CommentsByUser> getCommentsByUser(String user) {

        return BooksDao.getCommentsByUser(user);
    }


    public static List<Comment> addComment(Comment comment) {
        BooksDao.addComments(comment);

        return BooksDao.getComments(comment.getBookId());
    }
}
