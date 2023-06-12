package fr.amu.iut.sismicviewer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe main du logiciel.
 * @author BAMAS Mathias
 * @author BEDDIAF Miloud
 * @author BENDJEDDOU Rayan
 * @author LOUARN Mathis
 * @version 1.0
 */

public class SismicViewerApp extends Application {

    private static Scene scene;

    /**
     * Permet de charger le fichier fxml principale ainsi que toutes les données, méthodes qui s'en suit
     * @param stage Fenêtre du logiciel
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Dashboard.fxml"));
        System.setProperty("http.agent", "Gluon Mobile/1.0.3");
        scene = new Scene(loader.load());
        stage.setMaximized(true);
        stage.setTitle("Sismic Viewer");
        stage.setScene(scene);
        stage.show();
    }
}
