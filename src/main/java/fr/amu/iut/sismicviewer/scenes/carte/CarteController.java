package fr.amu.iut.sismicviewer.scenes.carte;

import fr.amu.iut.sismicviewer.controllers.TopBarController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
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
}
