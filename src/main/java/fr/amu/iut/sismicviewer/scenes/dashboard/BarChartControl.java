package fr.amu.iut.sismicviewer.scenes.dashboard;

import fr.amu.iut.sismicviewer.CSV.CSVManager;
import fr.amu.iut.sismicviewer.Seisme;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe qui contrôle la BarChart présente dans la fenêtre DashBoard
 * @author BAMAS Mathias
 * @author BEDDIAF Miloud
 * @author BENDJEDDOU Rayan
 * @author LOUARN Mathis
 * @version 1.0
 */
public class BarChartControl {
    private ArrayList<Seisme> data = new ArrayList<>(CSVManager.getListeSeisme());
    private HashMap<String,Integer> dicoPourGraph = new HashMap<>();

    /**
     * Constructeur de classe BarChart
     * @param graphique Node de type BarChart
     */
    public BarChartControl(BarChart graphique){
        graphique.getData().clear();
        for(Seisme seisme : data){
            dicoPourGraph.merge(String.valueOf(seisme.getAnnee()),1,(a,b) -> a+b); // si il n'y a pas la clé année, l'a créer et là met a 1, sinon ça fait une incrémentation
        }
        HashMap<String,Integer> dicoPourGraphSorted = dicoPourGraph.entrySet().stream().sorted(Map.Entry.comparingByKey()).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new)); // trie dans l'ordre naturel les clés du Hashmap (par date)
        XYChart.Series series = new XYChart.Series<String,Integer>();
        for (Map.Entry<String,Integer> entry : dicoPourGraphSorted.entrySet()){ // met les données dans le barchart
            series.getData().add(new XYChart.Data<>(entry.getKey(),entry.getValue()));
        }
        graphique.getData().add(series);

    }
}
