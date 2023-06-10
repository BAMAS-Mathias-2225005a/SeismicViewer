package fr.amu.iut.sismicviewer.scenes.carte;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;
import fr.amu.iut.sismicviewer.CSV.CSVManager;
import fr.amu.iut.sismicviewer.CSV.SeismeDataManager;
import fr.amu.iut.sismicviewer.Gluon.MainMapLayer;
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
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.shape.Circle;
import org.controlsfx.control.RangeSlider;
import org.controlsfx.control.textfield.TextFields;

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
    private ComboBox<Integer> dateDe;
    @FXML
    private ComboBox<Integer> dateA;
    @FXML
    private TextField latitude;
    @FXML
    private TextField longitude;
    @FXML
    private TextField rayon;
    @FXML
    private RangeSlider magnitude;

    @FXML TextField region;

    private MainMapLayer mapLayer;

    private ChangeListener<Number> magnitudeSliderChange;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TopBarController topBarController = new TopBarController();
        topBarController.initTopBar(carte, dashboard, stats);
        initMap();
        initComboBox();
        System.out.println("Initialisation du contrôleur de carte..");

        mapLayer = new MainMapLayer();
        mapView.addLayer(mapLayer);

        dateDe.setOnAction((event) -> {
            updateMap();
        });
        dateA.setOnAction((event) -> {
            updateMap();
        });

        TextFields.bindAutoCompletion(region, CSVManager.getAllRegion());

        initListener();
    }

    /* Initialise la map */
    public void initMap(){
        MapPoint mapPoint = new MapPoint(46.727638, 2.213749);
        mapView.setZoom(5.8);
        mapView.flyTo(0, mapPoint, 0.1);

    }

    public void initComboBox(){
        for(int i = 1800; i < 2030; i += 10 ){
            dateDe.getItems().add(i);
            dateA.getItems().add(i);
        }
    }

    public void updateMap(){
        ArrayList<Seisme> listeSeismeTries = (ArrayList<Seisme>) CSVManager.getListeSeisme().clone();
        SeismeDataManager seismeDataManager = new SeismeDataManager();

        if(dateDe.getValue() != null && dateA.getValue() != null) {
            listeSeismeTries = seismeDataManager.getAnneeFromTo(listeSeismeTries, dateDe.getValue(), dateA.getValue());
        }

        if(!latitude.getText().isEmpty() && !longitude.getText().isEmpty() && !rayon.getText().isEmpty()){
            listeSeismeTries = seismeDataManager.getSeismeDansRayon(listeSeismeTries, Double.parseDouble(latitude.getText()), Double.parseDouble(longitude.getText()), Double.parseDouble(rayon.getText()));
        }

        listeSeismeTries = seismeDataManager.getSeismeParMagnitude(listeSeismeTries, magnitude.getLowValue(), magnitude.getHighValue());

        mapLayer.updateLayer(listeSeismeTries);
    }

    public void initListener(){
        mapView.setOnMouseClicked(mouseEvent -> {
            MapPoint x = mapView.getMapPosition(mouseEvent.getX(), mouseEvent.getY());
            latitude.setText(String.format("%.5f",(x.getLatitude())).replace(',', '.'));
            longitude.setText(String.format("%.5f", (x.getLongitude())).replace(',', '.'));
        });

        magnitudeSliderChange = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                updateMap();
            }
        };

        magnitude.lowValueProperty().addListener(magnitudeSliderChange);
        magnitude.highValueProperty().addListener(magnitudeSliderChange);
    }

}
