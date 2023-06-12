package fr.amu.iut.sismicviewer.scenes.stats;

import fr.amu.iut.sismicviewer.CSV.CSVManager;
import fr.amu.iut.sismicviewer.CSV.SeismeDataManager;
import fr.amu.iut.sismicviewer.Seisme;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;

public class BarChartControl {
    private ArrayList<Seisme> data = new ArrayList<>(CSVManager.getListeSeisme());

    public BarChartControl(BarChart graphique) {
        SeismeDataManager seismeDataManager = new SeismeDataManager();
        XYChart.Series series = new XYChart.Series<String, Integer>();
        for (double i = 2; i < 9.5; i = i + 0.5) {
            series.getData().add(new XYChart.Data<>(String.valueOf(i), seismeDataManager.getSeismeParMagnitude(data, i, i).size()));
        }
        graphique.getData().add(series);
    }
}