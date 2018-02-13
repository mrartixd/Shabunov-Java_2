package sample;


import javafx.beans.property.SimpleStringProperty;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Book {

    @XmlAttribute
    private final SimpleStringProperty id;
    @XmlElement
    private final SimpleStringProperty author;
    @XmlElement
    private final SimpleStringProperty title;
    @XmlElement
    private final SimpleStringProperty genre;
    @XmlElement
    private final SimpleStringProperty price;
    @XmlElement
    private final SimpleStringProperty publish_date;
    @XmlElement
    private final SimpleStringProperty description;


    Book(String idb, String authorb, String titleb, String genreb, String priceb, String date, String desc){
        this.id = new SimpleStringProperty(idb);
        this.author = new SimpleStringProperty(authorb);
        this.title = new SimpleStringProperty(titleb);
        this.genre = new SimpleStringProperty(genreb);
        this.price = new SimpleStringProperty(priceb);
        this.publish_date = new SimpleStringProperty(date);
        this.description = new SimpleStringProperty(desc);
    }

    public String getBookId() {
        return id.get();
    }

    public String getAuthor(){
        return author.get();
    }

    public String getTitle(){
        return title.get();
    }

    public String getGenre(){
        return genre.get();
    }

    public String getPrice(){
        return price.get();
    }

    public String getDate(){
        return publish_date.get();
    }

    public String getDesc(){
        return description.get();
    }

    public void setBookId(String ids){
        id.set(ids);
    }

    public void setAuthor(String authors){
        author.set(authors);
    }

    public void setTitle(String titles){
        title.set(titles);
    }

    public void setGenre(String genres){
        genre.set(genres);
    }

    public void setPrice(String prices){
        price.set(prices);
    }

    public void setDate(String dates){
        publish_date.set(dates);
    }

    public void setDescr(String descrs){
        description.set(descrs);
    }
}
