package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.crypto.dsig.TransformException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FormBook {
    public static MenuBar menuBar;

    public static void MenuBar(Stage stage){
        menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        Menu menuAbout = new Menu("About");

        MenuItem menuExit = new MenuItem("Exit");
        menuExit.setOnAction((ActionEvent t)->{
            stage.close();
        });
        MenuItem menuDiag = new MenuItem("Diagram");
        menuDiag.setOnAction((ActionEvent t)->{
            FormDiag();
        });


        MenuItem About = new MenuItem("About");
        About.setOnAction((ActionEvent t)->{
            FormAbout();
        });


        menuAbout.getItems().add(About);

        menuFile.getItems().addAll(menuDiag,menuExit);

        menuBar.getMenus().addAll(menuFile, menuAbout);
    }

    public static void FormDiag(){
        Stage stage = new Stage();
        StackPane stackPane = new StackPane();

        Label lbl = new Label("Diagram");

        stackPane.getChildren().addAll(lbl);

        stage.setTitle("Add form");
        stage.setX(100);
        stage.setY(100);

        Scene scene = new Scene(stackPane, 300,300);
        stage.setScene(scene);
        stage.show();
    }

    public static void FormAbout(){
        Stage stage = new Stage();


        Label lbl = new Label("Shabunov Artur RDIR61");
        Label text = new Label("Praktika Java_2\n" +
                "Loo JAVAFX Application ja vorminda objektid CSS abil\n" +
                "Genereeri programmis algandmed raamatude infoga (kategooria, raamat, ilmumuse aasta, hind), min  5tk.\n" +
                "Programmis realiseeri:\n" +
                "\n" +
                "Uue raamatu lisamine\n" +
                "Kuva kõik raamatud\n" +
                "Diagramm, mis näitab mitu raamatut on igas kategoorias\n" +
                "Diagramm, mis näitab mitu raamatut ilmusid igas aastas\n" +
                "\n");

        VBox vb = new VBox();

        vb.getChildren().addAll(lbl,text);

        stage.setTitle("About form");
        stage.setX(100);
        stage.setY(100);

        Scene scene = new Scene(vb, 300,300);
        stage.setScene(scene);
        stage.show();
    }

    public static void FormAdd(Stage stage){
        MenuBar(stage);
        StackPane stackPane = new StackPane();

        Label lbl = new Label("Add new book");

        VBox vbx = new VBox();
        vbx.setAlignment(Pos.TOP_CENTER);
        vbx.setSpacing(10);

        HBox hbx = new HBox();
        hbx.setAlignment(Pos.CENTER);
        hbx.setSpacing(10);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));


        final TextField author = new TextField();
        Label authortext = new Label("Author:");

        final TextField title = new TextField();
        Label titletext = new Label("Title:");

        ChoiceBox<String> genrelist = new ChoiceBox();
        ObservableList<String> items = FXCollections.observableArrayList("Computer","Romance", "Fantasy");
        genrelist.setItems(items);
        genrelist.setPrefSize(220,10);


        final TextField price = new TextField();
        Label pricetext = new Label("Price:");

        DatePicker pubdate = new DatePicker();
        Label pubdatetext = new Label("Date publish:");
        pubdatetext.setMinWidth(Region.USE_PREF_SIZE);//fixed size


        final TextArea desc = new TextArea();
        Label desctext = new Label("Description");
        grid.add(author,1,0);
        grid.add(authortext,0,0);
        grid.add(title,1,1);
        grid.add(titletext,0,1);
        grid.add(genrelist,1,2);
        grid.add(price,1,3);
        grid.add(pricetext,0,3);
        grid.add(pubdate,1,4);
        grid.add(pubdatetext,0,4);
        grid.add(desc,1,5);
        grid.add(desctext,0,5);

        Button cancel = new Button();
        cancel.setText("Cancel");
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               Main.MainScreen(stage);
            }
        });

        Button send = new Button();
        send.setText("Send");
        send.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //send to xml file
                try {
                    String FILENAME = "books.xml";
                    File xmlParse = new File(System.getProperty("user.dir")+File.separator + FILENAME);
                    String formatdate = pubdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    ObservableList<Book> data = FXCollections.observableArrayList();
                    data.add(new Book("bk113", author.getText(), title.getText(),genrelist.getValue(), price.getText(),formatdate,desc.getText()));
                    TransformerFactory tf = TransformerFactory.newInstance();
                    Transformer trans = tf.newTransformer();
                    OutputStream out = new FileOutputStream(xmlParse);
                    trans.transform(new DOMSource((Node) data),new StreamResult(out));

                } catch (TransformerException | FileNotFoundException ex){
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });


        vbx.getChildren().addAll(menuBar,lbl,grid,hbx);
        hbx.getChildren().addAll(cancel,send);

        stage.setTitle("Add form");
        stage.setX(100);
        stage.setY(100);

        Scene scene = new Scene(vbx, 900,650);
        stage.setScene(scene);
        stage.show();
    }


}
