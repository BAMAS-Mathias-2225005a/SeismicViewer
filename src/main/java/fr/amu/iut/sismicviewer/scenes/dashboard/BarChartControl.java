package fr.amu.iut.sismicviewer.scenes.dashboard;

import fr.amu.iut.sismicviewer.CSV.CSVManager;
import fr.amu.iut.sismicviewer.Seisme;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class BarChartControl {
    private ArrayList<Seisme> data = new ArrayList<>(CSVManager.getListeSeisme());
    private HashMap<String,Integer> dicoPourGraph = new HashMap<>();
    public BarChartControl(BarChart graphique){
        for(Seisme seisme : data){
            dicoPourGraph.merge(String.valueOf(seisme.getAnnee()),1,(a,b) -> a+b); // si il n'y a pas la clé année, l'a créer et là met a 1, sinon ça fait une incrémentation
        }
        HashMap<String,Integer> dicoPourGraphSorted = dicoPourGraph.entrySet().stream().sorted((i1,i2) -> i1.getKey().compareTo(i2.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new)); // tah le ouf
        XYChart.Series series = new XYChart.Series<String,Integer>();
        for (Map.Entry<String,Integer> entry : dicoPourGraphSorted.entrySet()){
            series.getData().add(new XYChart.Data<>(entry.getKey(),entry.getValue()));
        }
        graphique.getData().add(series);

    }
}
