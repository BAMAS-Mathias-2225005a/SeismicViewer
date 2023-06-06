package fr.amu.iut.sismicviewer.scenes.dashboard;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



import com.gluonhq.maps.MapLayer;
import fr.amu.iut.sismicviewer.Gluon.CustomCircleMarkerLayer;
import fr.amu.iut.sismicviewer.controllers.TopBarController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;
import fr.amu.iut.sismicviewer.CSV.CSVConverter;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialisation du controlleur..");
        TopBarController topBarController = new TopBarController();
        topBarController.initTopBar(carte, dashboard, stats);
        initMap();
    }

    /* Initialise la map */
    public void initMap(){
        MapPoint mapPoint = new MapPoint(46.227638, 2.213749);
        mapView.setZoom(5);
        mapView.flyTo(0, mapPoint, 0.1);
        MapLayer mapLayer = new CustomCircleMarkerLayer(mapPoint);
        mapView.addLayer(mapLayer);
    }



}
