package fr.amu.iut.sismicviewer.scenes.stats;

import fr.amu.iut.sismicviewer.Seisme;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class TableviewController {
    public static class SeismeConcat{
        private final SimpleStringProperty identifiant;
        private final SimpleStringProperty region;
        private final SimpleStringProperty magnitude;

        public SeismeConcat(SimpleStringProperty identifiant, SimpleStringProperty region, SimpleStringProperty magnitude) {
            this.identifiant = identifiant;
            this.region = region;
            this.magnitude = magnitude;
        }
    }
    public TableviewController(TableView tableau, ArrayList<Seisme> data){
        ObservableList<SeismeConcat> listeSeisme = FXCollections.observableArrayList();
        for (Seisme seisme : data){
            listeSeisme.add(new SeismeConcat(new SimpleStringProperty(String.valueOf(seisme.getIdentifiant())),
                                                new SimpleStringProperty(String.valueOf(seisme.getRegion())),
                                                new SimpleStringProperty(String.valueOf(seisme.getMagnitude()))));
        }
        TableColumn identifiant = new TableColumn("Identifiant");
        TableColumn region = new TableColumn("Région");
        TableColumn magnitude = new TableColumn("Magnitude");
        tableau.getColumns().addAll(identifiant,region,magnitude);
        tableau.setItems(listeSeisme);
    }
}
