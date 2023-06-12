package fr.amu.iut.sismicviewer.scenes.dashboard;

import fr.amu.iut.sismicviewer.CSV.CSVManager;
import fr.amu.iut.sismicviewer.CSV.SeismeDataManager;
import fr.amu.iut.sismicviewer.Seisme;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PieChartControl {
    HashMap<String,Integer> seismeParRegion = new HashMap<>();
    ObservableList<PieChart.Data> seismeParRegionList = FXCollections.observableArrayList();
    public void loadPieChartData(ArrayList<Seisme> data, PieChart graphique){
        for(Seisme seisme : data){
            System.out.println(seisme.getRegion());
            seismeParRegion.merge(String.valueOf(seisme.getRegion()),1,(a,b) -> a+b); // si il n'y a pas la clé année, l'a créer et là met a 1, sinon ça fait une incrémentation
        }
        for (Map.Entry<String,Integer> entry : seismeParRegion.entrySet()){ // met les données dans la obervablelist qui va ensuite être mise dans le piechart
            seismeParRegionList.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }
        graphique.resize(300,300);
        graphique.setData(seismeParRegionList);
    }
}
