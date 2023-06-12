package fr.amu.iut.sismicviewer.scenes.carte;

import fr.amu.iut.sismicviewer.controllers.TopBarController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.text.BreakIterator;
import java.util.ResourceBundle;

public class CarteController implements Initializable {

    @FXML
    private Button carte;

    @FXML
    private Button dashboard;

    @FXML
    private Button stats;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TopBarController topBarController = new TopBarController();
        topBarController.initTopBar(carte, dashboard, stats);
    }

    public Button getCarteButton() {
        return carte;
    }

    public Button getDashboardButton() {
        return dashboard;
    }

    public Button getStatsButton() {
        return stats;
    }

    public BreakIterator getIntensiteLabel() {
        return null;
    }

    public BreakIterator getDateLabel() {
        return null;
    }

    public BreakIterator getChocLabel() {
        return null;
    }

    public BreakIterator getLieuLabel() {
        return null;
    }
}