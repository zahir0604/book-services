package twyla.books;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {

    private String isbnId;

    private String title;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    private String user;

    public Book() {
    }

    public Book(String isbnId, String title, String user) {
        this.isbnId = isbnId;
        this.title = title;
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    @JsonProperty
    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbnId() {
        return isbnId;
    }

    @JsonProperty
    public void setIsbnId(String isbnId) {
        this.isbnId = isbnId;
    }
}
