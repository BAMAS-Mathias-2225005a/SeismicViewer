package fr.amu.iut.sismicviewer.scenes.carte;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class CarteWindow extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("CarteView.fxml"));
        scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }


}
