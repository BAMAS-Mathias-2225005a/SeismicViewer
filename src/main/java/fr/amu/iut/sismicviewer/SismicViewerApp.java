package fr.amu.iut.sismicviewer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SismicViewerApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        HBox hbox = new HBox();
        Scene scene = new Scene(hbox);
        hbox.setPrefSize(400,400);
        stage.setScene(scene);
        stage.show();
    }
}
