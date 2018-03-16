package twyla.comments;

public class Comment {

    private String bookId;
    private String comment;
    private String rating;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    private String user;

    public Comment() {
    }

    public Comment(String bookId, String comment, String rating, String user) {
        this.bookId = bookId;
        this.comment = comment;
        this.rating = rating;
        this.user = user;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
