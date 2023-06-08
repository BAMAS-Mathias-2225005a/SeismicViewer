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
        ArrayList<HashMap<String, String>> dataSorted = new ArrayList<>(data);
        for (HashMap<String, String> ligne : data) {
            if (ligne.get("Intensité").isEmpty() || Double.parseDouble(ligne.get("Intensité")) < 6 ) {
                dataSorted.remove(ligne);
            }
        }
        triAjout(listeTopSeismes, dataSorted);
    }

    public void loadData(ArrayList<HashMap<String, String>> data, VBox listeTopSeismes, int date) {
        ArrayList<HashMap<String, String>> dataSorted = new ArrayList<>(data);
        for (HashMap<String, String> ligne : data) {
            if (Integer.parseInt(ligne.get("Date").substring(0, 4)) < date || ligne.get("Intensité").isEmpty() || Double.parseDouble(ligne.get("Intensité")) < 6 ) {
                dataSorted.remove(ligne);
            }
        }
        triAjout(listeTopSeismes, dataSorted);
    }

    private void triAjout(VBox listeTopSeismes, ArrayList<HashMap<String, String>> dataSorted) {
        listeTopSeismes.getChildren().clear();
        Comparator<HashMap<String, String>> comparator = Comparator.comparingDouble(map -> Double.parseDouble(map.get("Intensité")));
        Collections.sort(dataSorted, comparator.reversed());
        for (HashMap<String, String> ligne : dataSorted) {
            listeTopSeismes.getChildren().add(new Label(ligne.get("Date") + " " + ligne.get("Région") + " intensité : " + ligne.get("Intensité")));
            listeTopSeismes.getChildren().add((new Separator()));
        }
    }
}
