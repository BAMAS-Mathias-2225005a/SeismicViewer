package fr.amu.iut.sismicviewer.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class TopBarController {

    public void initTopBar(Button carte, Button dashboard, Button stats) {
        carte.setOnAction(actionEvent -> {
            changeScene("CarteView.fxml", carte);
        });
        dashboard.setOnAction(actionEvent -> {
            changeScene("Dashboard.fxml", dashboard);
        });
        stats.setOnAction(actionEvent -> {
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
    }
}
