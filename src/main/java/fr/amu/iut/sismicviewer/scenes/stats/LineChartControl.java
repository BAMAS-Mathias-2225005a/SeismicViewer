package fr.amu.iut.sismicviewer.scenes.stats;

import fr.amu.iut.sismicviewer.CSV.CSVManager;
import fr.amu.iut.sismicviewer.CSV.SeismeDataManager;
import fr.amu.iut.sismicviewer.Seisme;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;

public class LineChartControl {

    private ArrayList<Seisme> data = new ArrayList<>(CSVManager.getListeSeisme());

    public LineChartControl(LineChart graphique) {
        SeismeDataManager seismeDataManager = new SeismeDataManager();
        System.out.println(seismeDataManager.getSeismeLePlusVieux(data).getAnnee());
        XYChart.Series series = new XYChart.Series<String, Double>();
        for (int i = seismeDataManager.getSeismeLePlusVieux(data).getAnnee(); i < seismeDataManager.getSeismeLePlusRecent(data).getAnnee()+1; ++i) {
            series.getData().add(new XYChart.Data<>(String.valueOf(i), seismeDataManager.getMagnitudeMoyenne(seismeDataManager.getAnneeFromTo(data, i, i))));
        }
        graphique.getData().add(series);
    }
}
