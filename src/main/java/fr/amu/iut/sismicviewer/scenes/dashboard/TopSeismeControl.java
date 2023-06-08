package fr.amu.iut.sismicviewer.scenes.dashboard;

import fr.amu.iut.sismicviewer.Seisme;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class TopSeismeControl {

    public ObservableList<Seisme> loadData(ArrayList<Seisme> data, int date) {
        ArrayList<Seisme> dataSorted = new ArrayList<>(data);
        for (Seisme seisme : data) {
            if (seisme.getAnnee() < date || seisme.getMagnitude() < 6 ) {
                dataSorted.remove(seisme);
            }
        }
        return FXCollections.observableList(dataSorted);
    }

    public void loadData(ArrayList<Seisme> data, VBox listeTopSeismes) {
        loadData(data, 0);
    }
}
