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
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Charts {
    public static void ShowTabs() throws Exception{
        Stage stage = new Stage();
        stage.setTitle("Charts");
        TabPane tabPane = new TabPane();
        Tab tab1 = new Tab();
        tab1.setText("Category piechart");

        String FILENAME = "newbooks.xml";
        File xmlParse = new File(System.getProperty("user.dir") + File.separator + FILENAME);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(xmlParse);
        NodeList links = doc.getElementsByTagName("genre");
        ObservableSet<String> observableSet = FXCollections.observableSet();
        for (int i=0;i<links.getLength();i++){
            Node link = links.item(i);
            observableSet.add(link.getTextContent());
        }

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (String observableSets: observableSet) {
            pieChartData.add(new PieChart.Data(observableSets,3));
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
        tab2.setText("Years barchart");
//BarChart-------------------------------
        //---change to loop
        final String test1 = "Book1";//category
        final String test2 = "Book2";
        final String test3 = "Book3";

        final CategoryAxis categoryAxis = new CategoryAxis();
        final NumberAxis numberAxis = new NumberAxis();
        final BarChart<String, Number> bc = new BarChart<>(categoryAxis, numberAxis);
        bc.setTitle("Books summary");
        categoryAxis.setLabel("Years");
        numberAxis.setLabel("Count");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("");//year
        series1.getData().add(new XYChart.Data(test1, 3));
        series1.getData().add(new XYChart.Data(test2, 4));
        series1.getData().add(new XYChart.Data(test3, 5));

        bc.getData().addAll(series1);
//------------------------------
        tab2.setContent(bc);
        tabPane.getTabs().addAll(tab1, tab2);
        Scene scene = new Scene(tabPane, 600, 300);
        stage.setScene(scene);
        stage.show();
    }
}
