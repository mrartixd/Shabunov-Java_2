/**
 * Artur Shabunov RDIR61
 */

package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import sample.Charts;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class FormBook {
    public static MenuBar menuBar;
    public static Label add = new Label();
    //public static Label newadd = new Label();
    public static String newbk;

    public static void MenuBar(Stage stage) {
        menuBar = new MenuBar();
        Menu menuFile = new Menu("File");

        MenuItem menuExit = new MenuItem("Exit");
        menuExit.setOnAction((ActionEvent t) -> {
            stage.close();
        });
        MenuItem menuDiag = new MenuItem("Diagram");
        menuDiag.setOnAction((ActionEvent t) -> {
           //add charts
            try {
                Charts.ShowTabs();
            }catch (Exception e){
                e.printStackTrace();
            }

        });


        menuFile.getItems().addAll(menuDiag, menuExit);

        menuBar.getMenus().addAll(menuFile);
    }



    public static void FormAdd(Stage stage) throws Exception {
        MenuBar(stage);
        add.setText("");
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
        grid.setPadding(new Insets(25, 25, 25, 25));


        final TextField author = new TextField();
        Label authortext = new Label("Author:");

        final TextField title = new TextField();
        Label titletext = new Label("Title:");

        ChoiceBox<String> genrelist = new ChoiceBox();
        ObservableList<String> items = FXCollections.observableArrayList("Computer", "Romance", "Fantasy");
        genrelist.setItems(items);
        genrelist.setPrefSize(220, 10);


        final TextField price = new TextField();
        Label pricetext = new Label("Price:");

        DatePicker pubdate = new DatePicker();
        Label pubdatetext = new Label("Date publish:");
        pubdatetext.setMinWidth(Region.USE_PREF_SIZE);//fixed size


        final TextArea desc = new TextArea();
        Label desctext = new Label("Description");


        grid.add(author, 1, 0);
        grid.add(authortext, 0, 0);
        grid.add(title, 1, 1);
        grid.add(titletext, 0, 1);
        grid.add(genrelist, 1, 2);
        grid.add(price, 1, 3);
        grid.add(pricetext, 0, 3);
        grid.add(pubdate, 1, 4);
        grid.add(pubdatetext, 0, 4);
        grid.add(desc, 1, 5);
        grid.add(desctext, 0, 5);
        grid.add(add, 1, 6);
        //grid.add(newadd, 1, 7);

        Button cancel = new Button();
        cancel.setText("Cancel");
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    XMLpars.data.clear();
                    XMLpars.printBook();
                    Main.MainScreen(stage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        Button send = new Button();
        send.setText("Send");

        XMLpars.ParseFile();
        NodeList nList = XMLpars.doc.getElementsByTagName("book");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nNode;
                //add.setText("Last: "+element.getAttribute("id"));
                int num = Integer.parseInt(element.getAttribute("id").replaceAll("[bk]", ""));
                num++;
                newbk = "bk".concat(String.valueOf(num));
                //newadd.setText("New: "+newbk);
            }
        }

        send.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (author.getText().trim().isEmpty() || title.getText().trim().isEmpty() || genrelist.getItems().isEmpty() || price.getText().trim().isEmpty() || pubdate.getValue().toString().trim().isEmpty() || desc.getText().trim().isEmpty()) {
                        add.setText("Error check values");
                    } else {

                        String formatdate = pubdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        Element bookid = XMLpars.doc.createElement("book");
                        bookid.setAttribute("id", newbk);
                        XMLpars.doc.getDocumentElement().appendChild(bookid);//получение root tag и добавление в него book

                        Element authorbook = XMLpars.doc.createElement("author");
                        authorbook.appendChild(XMLpars.doc.createTextNode(author.getText()));
                        bookid.appendChild(authorbook);

                        Element titlebook = XMLpars.doc.createElement("title");
                        titlebook.appendChild(XMLpars.doc.createTextNode(title.getText()));

                        bookid.appendChild(titlebook);
                        Element genrebook = XMLpars.doc.createElement("genre");
                        genrebook.appendChild(XMLpars.doc.createTextNode(genrelist.getValue()));

                        bookid.appendChild(genrebook);
                        Element pricebook = XMLpars.doc.createElement("price");
                        pricebook.appendChild(XMLpars.doc.createTextNode(price.getText()));

                        bookid.appendChild(pricebook);
                        Element pubdatebook = XMLpars.doc.createElement("publish_date");
                        pubdatebook.appendChild(XMLpars.doc.createTextNode(formatdate));

                        bookid.appendChild(pubdatebook);
                        Element descbook = XMLpars.doc.createElement("description");
                        descbook.appendChild(XMLpars.doc.createTextNode(desc.getText()));
                        bookid.appendChild(descbook);

                        TransformerFactory transformerFactory = TransformerFactory.newInstance();
                        Transformer transformer = transformerFactory.newTransformer();
                        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                        DOMSource source = new DOMSource(XMLpars.doc);
                        StreamResult result = new StreamResult(XMLpars.xmlParse);
                        transformer.transform(source, result);
                        add.setText("Add new row!");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });
        vbx.getChildren().addAll(menuBar, lbl, grid, hbx);
        hbx.getChildren().addAll(cancel, send);

        stage.setTitle("Add form");
        stage.setX(100);
        stage.setY(100);

        Scene scene = new Scene(vbx, 900, 650);
        scene.getStylesheets().add("main.css");
        stage.setScene(scene);
        stage.show();
    }


}
