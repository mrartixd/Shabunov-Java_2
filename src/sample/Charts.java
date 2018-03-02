/**
 * Artur Shabunov RDIR61
 */

package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Charts {
    public static void ShowTabs() throws Exception{
        Stage stage = new Stage();
        stage.setTitle("Charts");
        TabPane tabPane = new TabPane();
        Tab tab1 = new Tab();
        tab1.setText("Category piechart");

        XMLpars.ParseFile();
        NodeList genre = XMLpars.doc.getElementsByTagName("genre");
        NodeList year = XMLpars.doc.getElementsByTagName("publish_date");

        ObservableSet<String> genobservableSet = FXCollections.observableSet();
        ObservableSet<String> yearobservableSet = FXCollections.observableSet();
        ArrayList<String> genres = new ArrayList<>();
        ArrayList<String> years = new ArrayList<>();


        for (int i = 0; i<year.getLength(); i++){
            Node yer = year.item(i);
            yearobservableSet.add(yer.getTextContent().substring(0,4));
            years.add(yer.getTextContent().substring(0,4));
        }

        for (int i=0;i<genre.getLength();i++){
            Node gen = genre.item(i);
            genobservableSet.add(gen.getTextContent());
            genres.add(gen.getTextContent());
        }
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (String observableSets: genobservableSet) {
            pieChartData.add(new PieChart.Data(observableSets, Collections.frequency(genres,observableSets)));
        }



//Piechart-------------------------------


        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Categorys book");
        chart.setLabelLineLength(10);
        chart.setLegendSide(Side.LEFT);

        final Label caption = new Label("");
        caption.setTextFill(Color.BLACK);
        caption.setStyle("-fx-font: 24 arial");

        for (final PieChart.Data data : chart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                    new EventHandler<javafx.scene.input.MouseEvent>() {
                        @Override
                        public void handle(javafx.scene.input.MouseEvent e) {
                            caption.setTranslateX(e.getSceneX());
                            caption.setTranslateY(e.getSceneY());
                            caption.setText(String.valueOf(data.getPieValue()) + "%");
                        }
                    });
        }
//--------------------------------------------
        tab1.setContent(chart);
        Tab tab2 = new Tab();
        tab2.setText("Years category");
//BarChart-------------------------------
        //---change to loop


        String numbercat[] = new String[yearobservableSet.size()];
        int c = 0;
        for (String observableSets: yearobservableSet) {
            numbercat[c] = observableSets;
            c++;
        }

        final CategoryAxis categoryAxis = new CategoryAxis();
        final NumberAxis numberAxis = new NumberAxis();
        final BarChart<String, Number> bc = new BarChart<>(categoryAxis, numberAxis);
        bc.setTitle("Books summary");
        categoryAxis.setLabel("Years");
        numberAxis.setLabel("Count");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("1900-2020");//year
        int j = 0;
            for (String observableSets: yearobservableSet) {
                series1.getData().add(new XYChart.Data(numbercat[j], Collections.frequency(years,observableSets)));
                j++;
            }


        bc.getData().addAll(series1);
//------------------------------
        tab2.setContent(bc);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.getTabs().addAll(tab1, tab2);
        Scene scene = new Scene(tabPane, 600, 300);
        stage.setScene(scene);
        stage.show();
    }
}
