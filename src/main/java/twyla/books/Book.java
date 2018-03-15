package twyla.books;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {

    public String isbnId;

    public String title;

    public Book() {
    }

    public Book(String isbnId, String title) {
        this.isbnId = isbnId;
        this.title = title;
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
