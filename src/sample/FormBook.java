package sample;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class FormBook {
    public static MenuBar menuBar;

    public static void MenuBar(){
        menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        Menu menuAbout = new Menu("About");

        MenuItem menuExit = new MenuItem("Exit");
        menuExit.setOnAction((ActionEvent t)->{
            System.exit(0);
        });
        MenuItem menuDiag = new MenuItem("Diagram");
        menuDiag.setOnAction((ActionEvent t)->{
            FormDiag();
        });

        MenuItem About = new MenuItem("About");
        menuDiag.setOnAction((ActionEvent t)->{
            FormAbout();
        });


        menuFile.getItems().add(About);

        menuFile.getItems().addAll(menuExit,menuDiag);

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
        StackPane stackPane = new StackPane();

        Label lbl = new Label("About");

        stackPane.getChildren().addAll(lbl);

        stage.setTitle("Add form");
        stage.setX(100);
        stage.setY(100);

        Scene scene = new Scene(stackPane, 300,300);
        stage.setScene(scene);
        stage.show();
    }

    public static void FormAdd(Stage stage){
        MenuBar();
        StackPane stackPane = new StackPane();

        Label lbl = new Label("Add new book");

        stackPane.getChildren().addAll(menuBar,lbl);

        stage.setTitle("Add form");
        stage.setX(100);
        stage.setY(100);

        Scene scene = new Scene(stackPane, 300,300);
        stage.setScene(scene);
        stage.show();
    }


}
