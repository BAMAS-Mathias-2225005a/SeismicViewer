package fr.amu.iut.sismicviewer.scenes.dashboard;
import java.io.File;


import fr.amu.iut.sismicviewer.CSV.CSVManager;
import fr.amu.iut.sismicviewer.CSV.SeismeDataManager;
import fr.amu.iut.sismicviewer.Gluon.MainMapLayer;
import fr.amu.iut.sismicviewer.SismicViewerApp;
import fr.amu.iut.sismicviewer.controllers.TopBarController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.controlsfx.control.RangeSlider;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
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

    @FXML
    private RangeSlider mainRangeSlider;

    @FXML
    private VBox listeTopSeisme;

    private ChangeListener<Number> sliderChangeListener;

    private MainMapLayer mainMapLayer;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialisation du controlleur..");
        TopBarController topBarController = new TopBarController();
        topBarController.initTopBar(carte, dashboard, stats);
        initListeners();
        initMap();
        initButton();

        mainMapLayer = new MainMapLayer();
        mapView.addLayer(mainMapLayer);
    }

    /* Initialise la map */
    public void initMap(){
        mapView.addEventFilter(MouseEvent.ANY, event -> event.consume());
        mapView.addEventFilter(ScrollEvent.ANY, event -> event.consume());
        MapPoint mapPoint = new MapPoint(46.727638, 2.213749);
        mapView.setZoom(5.1);
        mapView.flyTo(0, mapPoint, 0.1);

    }

    public void initListeners() {
        sliderChangeListener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                CSVManager csvManager = new CSVManager();
                SeismeDataManager seismeDataManager = new SeismeDataManager();
                csvManager.loadCsv(SismicViewerApp.getCsvFile());
                ArrayList<HashMap<String, String>> dataAnnee = seismeDataManager.getAnneeFromTo(csvManager.getData(), mainRangeSlider.getLowValue(), mainRangeSlider.getHighValue());
                mainMapLayer.updateLayer(dataAnnee);
            }
        };

        mainRangeSlider.highValueProperty().addListener(sliderChangeListener);
        mainRangeSlider.lowValueProperty().addListener(sliderChangeListener);

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
            SismicViewerApp.setCsvFile(file);
            topSeisme40ans();
        }
    }

    public void topSeisme10ans(){
        CSVManager csvManager = new CSVManager();
        csvManager.loadCsv(SismicViewerApp.getCsvFile());
        TopSeismeControl topSeismeControl = new TopSeismeControl();
        topSeismeControl.loadData(csvManager.getData(), listeTopSeisme,2013);
    }
    public void topSeisme40ans(){
        CSVManager csvManager = new CSVManager();
        csvManager.loadCsv(SismicViewerApp.getCsvFile());
        TopSeismeControl topSeismeControl = new TopSeismeControl();
        topSeismeControl.loadData(csvManager.getData(), listeTopSeisme,1983);
    }
    public void topSeisme100ans(){
        CSVManager csvManager = new CSVManager();
        csvManager.loadCsv(SismicViewerApp.getCsvFile());
        TopSeismeControl topSeismeControl = new TopSeismeControl();
        topSeismeControl.loadData(csvManager.getData(), listeTopSeisme, 1923);
    }



}
