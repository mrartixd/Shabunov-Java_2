/**
 * RDIR61 Artur Shabunov
 */

package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


public class Main extends Application {

@Override
    public void start(Stage primaryStage){
        MainScreen(primaryStage);
    }

    public static void MainScreen(Stage primaryStage){
        FormBook.MenuBar(primaryStage);
        final TableView mytable = new TableView();

        TableColumn bookidCol = new TableColumn("Book ID");
        TableColumn authorCol = new TableColumn("Author");
        TableColumn titleCol = new TableColumn("Title");
        TableColumn genreCol = new TableColumn("Genre");
        TableColumn priceCol = new TableColumn("Price");
        TableColumn publishCol = new TableColumn("Publish-date");
        TableColumn descCol = new TableColumn("Description");


        bookidCol.setCellValueFactory(new PropertyValueFactory<Book,String>("bookId"));
        authorCol.setCellValueFactory(new PropertyValueFactory<Book,String>("author"));
        titleCol.setCellValueFactory(new PropertyValueFactory<Book,String>("title"));
        genreCol.setCellValueFactory(new PropertyValueFactory<Book,String>("genre"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Book,String>("price"));
        publishCol.setCellValueFactory(new PropertyValueFactory<Book,String>("date"));
        descCol.setCellValueFactory(new PropertyValueFactory<Book,String>("desc"));


        mytable.setItems(XMLpars.data);
        mytable.getColumns().addAll(bookidCol,authorCol,titleCol,genreCol, priceCol, publishCol, descCol);


        VBox top = new VBox();
        top.setAlignment(Pos.TOP_CENTER);



        VBox center = new VBox();
        center.setAlignment(Pos.CENTER);
        center.setSpacing(30);



        HBox buttom = new HBox();
        buttom.setAlignment(Pos.BOTTOM_CENTER);




        Button btnnb = new Button();
        btnnb.setText("Add book");
        btnnb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FormBook.FormAdd(primaryStage);
            }
        });

        Button refresh = new Button();
        refresh.setText("Refresh");
        refresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mytable.refresh();
            }
        });

        mytable.prefHeightProperty().bind(primaryStage.heightProperty());

        top.getChildren().addAll(FormBook.menuBar,center);
        center.getChildren().addAll(mytable,buttom);
        buttom.getChildren().addAll(btnnb, refresh);
        buttom.setMargin(btnnb, new Insets(20.0));
        buttom.setMargin(refresh, new Insets(20.0));
        Scene scene = new Scene(top, 1300,500);

        primaryStage.setTitle("Main");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        XMLpars.printBook();
        launch(args);
    }
}
