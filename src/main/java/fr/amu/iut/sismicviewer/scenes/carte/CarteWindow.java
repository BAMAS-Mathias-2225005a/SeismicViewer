package fr.amu.iut.sismicviewer.scenes.carte;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CarteWindow extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("CarteView.fxml"));
        Parent root = loader.load();

        CarteController controller = loader.getController();

        controller.getIntensiteLabel().setText("Intensité du séisme : 7.2");
        controller.getLieuLabel().setText("Lieu du séisme : San Francisco");
        controller.getChocLabel().setText("Choc du séisme : Tectonique des plaques");
        controller.getDateLabel().setText("Date du séisme : 2023-06-07");




        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
