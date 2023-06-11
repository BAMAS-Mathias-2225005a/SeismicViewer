package fr.amu.iut.sismicviewer.scenes.dashboard;

import fr.amu.iut.sismicviewer.Seisme;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class TopSeismeControl {

    public ObservableList<Seisme> loadData(ArrayList<Seisme> data, int date) {
        ArrayList<Seisme> dataSorted = new ArrayList<>(data);
        for (Seisme seisme : data) {
            if (seisme.getAnnee() < date || seisme.getMagnitude() < 6) {
                dataSorted.remove(seisme);
            }
        }
        return FXCollections.observableList(dataSorted);
    }
}
