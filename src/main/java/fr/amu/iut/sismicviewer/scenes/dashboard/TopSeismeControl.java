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
        Comparator<HashMap<String, String>> comparator = Comparator.comparingInt(map -> Integer.parseInt(map.get("Intensité épicentrale")));
        Collections.sort(dataSorted, comparator.reversed());
        for (HashMap<String, String> ligne : dataSorted){
            listeTopSeismes.getChildren().add(new Label(ligne.get("Date (AAAA/MM/JJ)") + " " + ligne.get("Région épicentrale") + " intensité :" + ligne.get("Intensité épicentrale")));
        }

    }
}
