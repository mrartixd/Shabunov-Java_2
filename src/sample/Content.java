package sample;


import java.util.ArrayList;
import java.util.List;


public class Content {

    private List<Book> book = new ArrayList<>();

    public List<Book> getBooks() {
        return book;
    }

    public void setBooks(List<Book> books) {
        this.book = books;
    }
}
