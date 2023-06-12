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
    private TreeMap<String,Integer> dicoPourGraph = new TreeMap<>(Collections.reverseOrder());

    /**
     * Constructeur de classe BarChart
     * @param graphique Node de type BarChart
     */
    public BarChartControl(BarChart graphique){
        graphique.getData().clear();
        for(Seisme seisme : data){
            dicoPourGraph.merge(String.valueOf(seisme.getAnnee()),1,(a,b) -> a+b); // si il n'y a pas la clé année, l'a créer et là met a 1, sinon ça fait une incrémentation
        }

        XYChart.Series series = new XYChart.Series<String,Integer>();
        TreeMap<String, Integer> last50 = new TreeMap<>();
        for(Map.Entry<String,Integer> entry : dicoPourGraph.entrySet()){
            if(last50.size() < 50)
                last50.put(entry.getKey(),entry.getValue());
            else
                break;
        }

        for (String key : last50.keySet()){ // met les données dans le barchart
            series.getData().add(new XYChart.Data<>(key, last50.get(key)));
        }

        graphique.getData().add(series);

    }
}
