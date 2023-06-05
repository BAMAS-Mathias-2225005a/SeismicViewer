package fr.amu.iut.sismicviewer.scenes.stats;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StatsWindow extends Application {
    private static Scene scene;
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("StatsView.fxml"));
        scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }
}

