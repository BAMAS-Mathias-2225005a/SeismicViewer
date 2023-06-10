package fr.amu.iut.sismicviewer.Gluon;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import fr.amu.iut.sismicviewer.Seisme;
import javafx.beans.binding.Binding;
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
            Circle circle = new Circle(5);
            circle.setCenterX(point2D.getX());
            circle.setCenterY(point2D.getY());

            if(seisme.getMagnitude() < 3.5) {
                circle.setFill(Color.GREEN);
                circle.setRadius(2);
            }else if(seisme.getMagnitude() < 5.5) {
                circle.setFill(Color.YELLOW);
                circle.setRadius(4);
            }
            else if(seisme.getMagnitude() < 7.5) {
                circle.setFill(Color.ORANGE);
                circle.setRadius(6);
            }
            else {
                circle.setFill(Color.RED);
                circle.setRadius(8);
            }

            this.getChildren().add(circle);
        }
    }

    public void clearLayer(){
        this.getChildren().clear();
    }
}
