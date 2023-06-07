package fr.amu.iut.sismicviewer.scenes.dashboard;
import java.io.File;



import com.gluonhq.maps.MapLayer;
import fr.amu.iut.sismicviewer.CSV.CSVManager;
import fr.amu.iut.sismicviewer.Gluon.CustomCircleMarkerLayer;
import fr.amu.iut.sismicviewer.controllers.TopBarController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardControl implements Initializable{

    @FXML
    private Button carte;

    @FXML
    private Button dashboard;

    @FXML
    private Button stats;

    @FXML
    private BorderPane mainBP;

    @FXML
    private MapView mapView;

    @FXML
    private Button importCSVButton;

    @FXML
    private HBox CSVErrorBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialisation du controlleur..");
        TopBarController topBarController = new TopBarController();
        topBarController.initTopBar(carte, dashboard, stats);
        initMap();
        initButton();


    }

    /* Initialise la map */
    public void initMap(){
        mapView.addEventFilter(MouseEvent.ANY, event -> event.consume());
        mapView.addEventFilter(ScrollEvent.ANY, event -> event.consume());
        MapPoint mapPoint = new MapPoint(46.727638, 2.213749);
        mapView.setZoom(5.1);
        mapView.flyTo(0, mapPoint, 0.1);
        MapLayer mapLayer = new CustomCircleMarkerLayer(mapPoint);
        mapView.addLayer(mapLayer);
    }

    public void initButton(){
        importCSVButton.setOnMouseClicked(event -> openCSVFileChooser());
    }

    public void openCSVFileChooser(){
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);

        if(file == null || !file.getName().contains(".csv"))
            CSVErrorBox.setVisible(true);
        else{
            CSVErrorBox.setVisible(false);
            CSVManager csvManager = new CSVManager(file);
            csvManager.getData(5,10);
        }

    }



}
