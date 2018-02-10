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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

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
        author.setText("Author: ");

        final TextField title = new TextField();
        title.setText("Title: ");

        ChoiceBox<String> genrelist = new ChoiceBox();
        ObservableList<String> items = FXCollections.observableArrayList("Computer","Romance", "Fantasy");
        genrelist.setItems(items);
        genrelist.setPrefSize(200,10);


        final TextField price = new TextField();
        price.setText("Price: ");


        final TextField pubdate = new TextField();
        pubdate.setText("Publish date: ");

        final TextArea desc = new TextArea();
        desc.setText("Description: ");

        grid.add(author,0,0);
        grid.add(title,0,1);
        grid.add(genrelist,0,2);
        grid.add(price,0,3);
        grid.add(pubdate,0,4);

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
            }
        });


        vbx.getChildren().addAll(menuBar,lbl,grid,hbx);
        hbx.getChildren().addAll(cancel,send);

        stage.setTitle("Add form");
        stage.setX(100);
        stage.setY(100);

        Scene scene = new Scene(vbx, 600,700);
        stage.setScene(scene);
        stage.show();
    }


}
