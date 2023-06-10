package fr.amu.iut.sismicviewer.Gluon;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import fr.amu.iut.sismicviewer.Seisme;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class MainMapLayer extends MapLayer {

    public void updateLayer(ArrayList<Seisme> listeSeismes){
        clearLayer();
        for(Seisme seisme : listeSeismes){
            MapPoint point = new MapPoint(seisme.getLatitude(), seisme.getLongitude());
            Point2D point2D = getMapPoint(point.getLatitude(),point.getLongitude());
            Circle circle = new Circle(3, Color.RED);
            circle.setCenterX(point2D.getX());
            circle.setCenterY(point2D.getY());
            this.getChildren().add(circle);
        }
    }

    public void clearLayer(){
        this.getChildren().clear();
    }
}
