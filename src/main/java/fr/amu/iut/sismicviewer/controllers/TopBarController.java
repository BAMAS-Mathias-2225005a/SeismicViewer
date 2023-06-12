package fr.amu.iut.sismicviewer.controllers;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class TopBarController {

    public void initTopBar(Button carte, Button dashboard, Button stats) {
        carte.setOnMouseClicked(event -> {
            changeScene("CarteView.fxml", carte);
        });
        dashboard.setOnMouseClicked(actionEvent -> {
            changeScene("Dashboard.fxml", dashboard);
        });
        stats.setOnMouseClicked(actionEvent -> {
            changeScene("StatsView.fxml", stats);
        });
    }

    public void changeScene(String sceneName, Button button){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(sceneName));
            Stage stage = (Stage)  button.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
        public void topbar
    }
}
