package fr.amu.iut.sismicviewer.scenes.dashboard;

import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class TopSeismeControl {
    public void loadData(ArrayList<HashMap<String, String>> data, VBox listeTopSeismes) {
        ArrayList<HashMap<String, String>> dataSorted = data;
        ArrayList<String> dataLabel = new ArrayList<>();
        for (HashMap<String, String> ligne : dataSorted){
            if(ligne.get("Intensité").isEmpty()){
                ligne.put("Intensité", "0");
            }
        }
        Comparator<HashMap<String, String>> comparator = Comparator.comparingDouble(map -> Double.parseDouble(map.get("Intensité")));
        Collections.sort(dataSorted, comparator.reversed());
        for (HashMap<String, String> ligne : dataSorted){
            if(ligne.get("Intensité") == "0"){
                ligne.put("Intensité", "Inconnu");
            }
            listeTopSeismes.getChildren().add(new Label(ligne.get("Date") + " " + ligne.get("Région") + " intensité : " + ligne.get("Intensité")));
        }

    }
}
