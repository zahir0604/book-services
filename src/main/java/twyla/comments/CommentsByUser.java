package twyla.comments;


public class CommentsByUser extends Comment {

    private String title;

    public CommentsByUser() {
    }

    public CommentsByUser(String bookId, String comment, String rating, String user, String title) {
        super(bookId, comment, rating, user);
        this.title = title;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
