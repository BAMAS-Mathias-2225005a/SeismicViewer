package fr.amu.iut.sismicviewer.scenes.carte;

import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;
import fr.amu.iut.sismicviewer.CSV.CSVManager;
import fr.amu.iut.sismicviewer.CSV.SeismeDataManager;
import fr.amu.iut.sismicviewer.Gluon.MainMapLayer;
import fr.amu.iut.sismicviewer.Seisme;
import fr.amu.iut.sismicviewer.controllers.TopBarController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.RangeSlider;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Cette classe est le contrôleur de la fenêtre Carte du logiciel,
 * elle permet de pouvoir intéragir avec les différents nodes présent sur la fenêtre Carte (boutons, champ de textes, etc..)
 *
 * @author BAMAS Mathias
 * @author BEDDIAF Miloud
 * @author BENDJEDDOU Rayan
 * @author LOUARN Mathis
 * @version 1.0
 */

public class CarteController implements Initializable {

    /* Déclaration de toutes les variables (connexion entre les nodes FXML) */
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

    @FXML
    private CheckComboBox<String> region;

    private MainMapLayer mapLayer;

    private ChangeListener<Number> magnitudeSliderChange;

    /**
     * Initialise le contrôleur de la fenêtre Carte
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TopBarController topBarController = new TopBarController();
        topBarController.initTopBar(carte, dashboard, stats);
        initMap();
        initComboBox();
        System.out.println("Initialisation du contrôleur de carte..");

        mapLayer = new MainMapLayer();
        mapView.addLayer(mapLayer);

        region.getItems().add("TOUTES LES REGIONS");
        region.getItems().addAll(CSVManager.getAllRegion());

        region.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> change) {
                updateMap();
            }
        });

        dateDe.setOnAction((event) -> {
            updateMap();
        });
        dateA.setOnAction((event) -> {
            updateMap();
        });

        initListener();
    }

    /**
     * Initialise la carte de la fenêtre
     *
     */
    public void initMap(){
        MapPoint mapPoint = new MapPoint(46.727638, 2.213749);
        mapView.setZoom(5.8);
        mapView.flyTo(0, mapPoint, 0.1);

    }

    /**
     * Initialise la ComboBox des dates
     */
    public void initComboBox(){
        for(int i = 1800; i < 2030; i += 10 ){
            dateDe.getItems().add(i);
            dateA.getItems().add(i);
        }
    }

    /**
     * Permet de mettre à jour la carte avec des nouvelles données
     */
    public void updateMap(){
        ArrayList<Seisme> listeSeismeTries = (ArrayList<Seisme>) CSVManager.getListeSeisme().clone();
        SeismeDataManager seismeDataManager = new SeismeDataManager();

        if(dateDe.getValue() != null && dateA.getValue() != null) {
            listeSeismeTries = seismeDataManager.getAnneeFromTo(listeSeismeTries, dateDe.getValue(), dateA.getValue());
        }

        if(!latitude.getText().isEmpty() && !longitude.getText().isEmpty() && !rayon.getText().isEmpty()){
            listeSeismeTries = seismeDataManager.getSeismeDansRayon(listeSeismeTries, Double.parseDouble(latitude.getText()), Double.parseDouble(longitude.getText()), Double.parseDouble(rayon.getText()));
        }

        if(!region.getCheckModel().getCheckedItems().isEmpty() && !region.getCheckModel().getCheckedItems().contains("TOUTES LES REGIONS")){
            listeSeismeTries = seismeDataManager.getSeismeParRegion(listeSeismeTries, region.getCheckModel().getCheckedItems());
        }

        listeSeismeTries = seismeDataManager.getSeismeParMagnitude(listeSeismeTries, magnitude.getLowValue(), magnitude.getHighValue());

        mapLayer.updateLayer(listeSeismeTries);
    }

    /**
     * Permet d'enregistrer les coordonnées (latitude et longitude) d'un point lors d'un clique sur la carte
     */
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
