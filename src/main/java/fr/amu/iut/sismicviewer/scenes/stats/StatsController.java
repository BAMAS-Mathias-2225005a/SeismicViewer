package fr.amu.iut.sismicviewer.scenes.stats;

import fr.amu.iut.sismicviewer.CSV.CSVManager;
import fr.amu.iut.sismicviewer.CSV.SeismeDataManager;
import fr.amu.iut.sismicviewer.Seisme;
import fr.amu.iut.sismicviewer.controllers.TopBarController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StatsController implements Initializable {

    @FXML
    private Button carte;
    @FXML
    private Button dashboard;
    @FXML
    private Button stats;

    @FXML
    private BarChart barChartStats;
    @FXML
    private LineChart lineChartStats;
    @FXML
    private Label seismeMagnitudeInconnuValueLabel;
    @FXML
    private Label seismeQuiOnCauseDesDegats;
    @FXML
    private Label seismeEnFrance;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TopBarController topBarController = new TopBarController();
        topBarController.initTopBar(carte, dashboard, stats);
        initBarChart(barChartStats);
        initLineChart(lineChartStats);
        initStats();
    }

    public void initBarChart(BarChart graphique) {
        BarChartControl barChartControl = new BarChartControl(graphique);
    }

    public void initLineChart(LineChart graphique){
        LineChartControl lineChartControl = new LineChartControl(graphique);
    }
    public void initStats(){
        SeismeDataManager seismeDataManager = new SeismeDataManager();
        ArrayList<Seisme> data = CSVManager.getListeSeisme();
        seismeMagnitudeInconnuValueLabel.setText(String.valueOf(seismeDataManager.getNombreSeisme(data) - seismeDataManager.getNombreSeismeAvecMagnitudeConnue(data)));
        seismeQuiOnCauseDesDegats.setText(String.valueOf(seismeDataManager.getSeismeParMagnitude(data,6,10).size()));
        seismeEnFrance.setText(String.valueOf(seismeDataManager.getSeismeEnFrance(data).size()));
    }
}
