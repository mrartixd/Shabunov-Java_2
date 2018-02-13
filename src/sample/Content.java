package sample;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Content {
    @XmlElement
    private List<Book> book = new ArrayList<>();

    public List<Book> getBooks() {
        return book;
    }

    public void setBooks(List<Book> books) {
        this.book = books;
    }
}
