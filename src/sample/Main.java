/**
 * RDIR61 Artur Shabunov
 */

package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        FormBook.MenuBar();
        final TableView mytable = new TableView();

        TableColumn numberCol = new TableColumn("#");
        TableColumn bookidCol = new TableColumn("Book ID");
        TableColumn authorCol = new TableColumn("Author");
        TableColumn titleCol = new TableColumn("Title");
        TableColumn genreCol = new TableColumn("Genre");
        TableColumn priceCol = new TableColumn("Price");
        TableColumn publishCol = new TableColumn("Publish-date");
        TableColumn descCol = new TableColumn("Description");



        numberCol.setCellValueFactory(new PropertyValueFactory<Book,String>("count"));
        bookidCol.setCellValueFactory(new PropertyValueFactory<Book,String>("bookId"));
        authorCol.setCellValueFactory(new PropertyValueFactory<Book,String>("author"));
        titleCol.setCellValueFactory(new PropertyValueFactory<Book,String>("title"));
        genreCol.setCellValueFactory(new PropertyValueFactory<Book,String>("genre"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Book,String>("price"));
        publishCol.setCellValueFactory(new PropertyValueFactory<Book,String>("date"));
        descCol.setCellValueFactory(new PropertyValueFactory<Book,String>("desc"));


        mytable.setItems(XMLpars.data);
        mytable.getColumns().addAll(numberCol,bookidCol,authorCol,titleCol,genreCol, priceCol, publishCol, descCol);

        VBox vbx = new VBox();
        vbx.setAlignment(Pos.CENTER);
        vbx.setSpacing(10);

        HBox hbx = new HBox();
        hbx.setAlignment(Pos.CENTER);
        hbx.setSpacing(10);



        Button btnnb = new Button();
        btnnb.setText("Add book");
        btnnb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FormBook.FormAdd(primaryStage);
            }
        });




        vbx.getChildren().addAll(FormBook.menuBar,mytable,hbx);
        hbx.getChildren().addAll(btnnb);

        Scene scene = new Scene(vbx, 1300,500);
        primaryStage.setTitle("Main");
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        XMLpars.printBook();
        launch(args);
    }
}
