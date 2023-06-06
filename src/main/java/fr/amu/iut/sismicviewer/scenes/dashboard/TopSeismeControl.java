package fr.amu.iut.sismicviewer.scenes.dashboard;

import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class TopSeismeControl {
    public void loadData(ArrayList<String[]> data, VBox listeTopSeismes) {
        String magnitude = new String();
        for (String[] ligne : data) {
            if (ligne[10].isEmpty()) {
                magnitude = "Inconnue";
            } else {
                magnitude = ligne[10];
            }

            listeTopSeismes.getChildren().add(new Label("" + ligne[4] + " le " + ligne[1] + " magnitude : " + magnitude));
            listeTopSeismes.getChildren().add(new Separator());
        }
    }
}
