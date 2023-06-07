package fr.amu.iut.sismicviewer.Gluon;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.HashMap;

public class MainMapLayer extends MapLayer {

    public void updateLayer(ArrayList<HashMap<String,String>> listeSeismes){
        clearLayer();
        int i = 0;
        for(HashMap<String, String> seisme : listeSeismes){
            try {
                MapPoint point = new MapPoint(Double.parseDouble(seisme.get("Latitude")), Double.parseDouble(seisme.get("Longitude")));
                Point2D point2D = getMapPoint(point.getLatitude(),point.getLongitude());
                System.out.println(i++);
                Circle circle = new Circle(5, Color.RED);
                circle.setCenterX(point2D.getX());
                circle.setCenterY(point2D.getY());
                this.getChildren().add(circle);
            } catch (NumberFormatException e){
                continue;
            }
        }
    }

    public void clearLayer(){
        this.getChildren().clear();
    }
}
