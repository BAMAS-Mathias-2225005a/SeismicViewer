package fr.amu.iut.sismicviewer.scenes.carte;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;
import fr.amu.iut.sismicviewer.CSV.CSVManager;
import fr.amu.iut.sismicviewer.CSV.SeismeDataManager;
import fr.amu.iut.sismicviewer.Seisme;
import fr.amu.iut.sismicviewer.SismicViewerApp;
import fr.amu.iut.sismicviewer.controllers.TopBarController;
import fr.amu.iut.sismicviewer.scenes.dashboard.TopSeismeControl;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
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
    private TableColumn lieu;
    @FXML
    private TableColumn intensite;
    @FXML
    private TableColumn latitude;
    @FXML
    private TableColumn longitude;
    @FXML
    private TableColumn annee;
    @FXML
    private Label msgErreur;

    private File csvFile;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TopBarController topBarController = new TopBarController();
        SeismeDataManager seismeDataManager = new SeismeDataManager();
        topBarController.initTopBar(carte, dashboard, stats);
        initMap();
        initTableView();



        System.out.println("Initialisation du contrôleur de carte..");
    }

    /* Initialise la map */
    public void initMap(){
        mapView.addEventFilter(MouseEvent.ANY, event -> event.consume());
        mapView.addEventFilter(ScrollEvent.ANY, event -> event.consume());
        MapPoint mapPoint = new MapPoint(46.727638, 2.213749);
        mapView.setZoom(5.8);
        mapView.flyTo(0, mapPoint, 0.1);
    }

    public void initTableView() {
        tableView.getSelectionModel().setCellSelectionEnabled(true);
        CSVManager csvManager = new CSVManager();
        csvManager.loadCsv(SismicViewerApp.getCsvFile());
        tableView.setItems(FXCollections.observableList(csvManager.getListeSeisme()));
    }
}
