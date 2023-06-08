package fr.amu.iut.sismicviewer.scenes.carte;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;
import fr.amu.iut.sismicviewer.Gluon.CustomCircleMarkerLayer;
import fr.amu.iut.sismicviewer.controllers.TopBarController;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class CarteController implements Initializable {

    @FXML
    private Button carte;
    @FXML
    private Button dashboard;
    @FXML
    private Button stats;
    @FXML
    private MapView mapView;
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn region;
    @FXML
    private TableColumn intensite;
    @FXML
    private TableColumn qie;
    @FXML
    private TableColumn choc;
    @FXML
    private TableColumn date;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TopBarController topBarController = new TopBarController();
        topBarController.initTopBar(carte, dashboard, stats);
        initMap();
        System.out.println("Initialisation du contrôleur de carte..");
    }

    /* Initialise la map */
    public void initMap(){
        mapView.addEventFilter(MouseEvent.ANY, event -> event.consume());
        mapView.addEventFilter(ScrollEvent.ANY, event -> event.consume());
        MapPoint mapPoint = new MapPoint(46.727638, 2.213749);
        mapView.setZoom(5.8);
        mapView.flyTo(0, mapPoint, 0.1);
        MapLayer mapLayer = new CustomCircleMarkerLayer(mapPoint);
        mapView.addLayer(mapLayer);
    }
}
