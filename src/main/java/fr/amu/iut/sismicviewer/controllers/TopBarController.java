package fr.amu.iut.sismicviewer.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Cette classe gère les 3 boutons qui sont en haut de l'écran, ils permettent de changer de fenêtre
 * @author BAMAS Mathias
 * @author BEDDIAF Miloud
 * @author BENDJEDDOU Rayan
 * @author LOUARN Mathis
 * @version 1.0
 */
public class TopBarController {

    /**
     * Assigne aux boutons l'évènement de changer de scene lors d'un clique
     * @param carte Bouton qui sert à aller à la fenêtre "Carte"
     * @param dashboard Bouton qui sert à aller à la fenêtre "DashBoard"
     * @param stats Bouton qui sert à aller à la fenêtre "Statistique"
     */
    public void initTopBar(Button carte, Button dashboard, Button stats) {
        carte.setOnMouseClicked(event -> {
            changeScene("views/CarteView.fxml", carte);
        });
        dashboard.setOnMouseClicked(actionEvent -> {
            changeScene("views/Dashboard.fxml", dashboard);
        });
        stats.setOnMouseClicked(actionEvent -> {
            changeScene("views/StatsView.fxml", stats);
        });
    }


    /**
     * Méthode qui sert à changer de scene
     * @param sceneName Nom de la scene à charger
     * @param button Bouton qui sert à charger une scène
     */
    public void changeScene(String sceneName, Button button) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(sceneName));
            Stage stage = (Stage) button.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
