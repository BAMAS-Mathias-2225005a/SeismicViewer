package fr.amu.iut.sismicviewer.scenes.dashboard;
import java.io.File;


import fr.amu.iut.sismicviewer.CSV.CSVManager;
import fr.amu.iut.sismicviewer.CSV.SeismeDataManager;
import fr.amu.iut.sismicviewer.Gluon.MainMapLayer;
import fr.amu.iut.sismicviewer.Seisme;
import fr.amu.iut.sismicviewer.SismicViewerApp;
import fr.amu.iut.sismicviewer.controllers.TopBarController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import org.controlsfx.control.RangeSlider;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
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
    private TableView<Seisme> seismeTop;

    private ChangeListener<Number> sliderChangeListener;

    private MainMapLayer mainMapLayer;

    @FXML
    private Label totalSeismeLabel;

    @FXML
    private Label moyenneMagnitudeLabel;

    @FXML
    private Label magnitudePlusGrosSeismeLabel;

    @FXML
    private Label villePlusGrosSeismeLabel;

    @FXML
    private Label magnitudePlusPetitSeismeLabel;

    @FXML
    private Label villePlusPetitSeismeLabel;

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
                SeismeDataManager seismeDataManager = new SeismeDataManager();
                ArrayList<Seisme> dataAnnee = seismeDataManager.getAnneeFromTo(CSVManager.getListeSeisme(), (int) mainRangeSlider.getLowValue(), (int) mainRangeSlider.getHighValue());
                mainMapLayer.updateLayer(dataAnnee);
            }
        };

        mainRangeSlider.lowValueProperty().addListener(sliderChangeListener);
        mainRangeSlider.highValueProperty().addListener(sliderChangeListener);

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
            CSVManager.loadCsv(file);
            mainMapLayer.updateLayer(CSVManager.getListeSeisme());
            totalSeismeLabel.setText(String.valueOf(CSVManager.getNombreDeSeisme()));
            moyenneMagnitudeLabel.setText(String.valueOf((CSVManager.getMagnitudeMoyenne())).substring(0,4));
            villePlusGrosSeismeLabel.setText(CSVManager.getPlusGrosSeismeVille());
            magnitudePlusGrosSeismeLabel.setText(String.valueOf(CSVManager.getPlusGrosSeismeValeur()));
            villePlusPetitSeismeLabel.setText(CSVManager.getPlusPetitSeismeVille());
            magnitudePlusPetitSeismeLabel.setText(String.valueOf(CSVManager.getPlusPetitSeismeValeur()));

        }
    }

    public void topSeisme10ans(){
        CSVManager csvManager = new CSVManager();
        TopSeismeControl topSeismeControl = new TopSeismeControl();
        seismeTop.setItems(topSeismeControl.loadData(CSVManager.getListeSeisme(), 2013));
    }
    public void topSeisme40ans(){
        CSVManager csvManager = new CSVManager();
        TopSeismeControl topSeismeControl = new TopSeismeControl();
        seismeTop.setItems(topSeismeControl.loadData(CSVManager.getListeSeisme(), 1983));

    }
    public void topSeisme100ans(){
        CSVManager csvManager = new CSVManager();
        TopSeismeControl topSeismeControl = new TopSeismeControl();
        seismeTop.setItems(topSeismeControl.loadData(CSVManager.getListeSeisme(), 1923));
    }



}
