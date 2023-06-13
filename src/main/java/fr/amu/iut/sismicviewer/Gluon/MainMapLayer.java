package fr.amu.iut.sismicviewer.Gluon;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import fr.amu.iut.sismicviewer.Seisme;
import javafx.beans.binding.Binding;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

/**
 * Cette classe définit une couche personnalisée pour les cartes dans le logiciel,
 * grâce à cela, il est facilement possible de placer des points sur la carte (possibilité de placer des points
 * de couleur différentes, de tailles différentes etc...)
 *
 * @author BAMAS Mathias
 * @author BEDDIAF Miloud
 * @author BENDJEDDOU Rayan
 * @author LOUARN Mathis
 * @version 1.0
 */
public class MainMapLayer extends MapLayer {


    /**
     * Met à jour la couche à chaque appelle de la fonction
     *
     * @param listeSeismes Liste de séisme à fournir pour placer les points
     */
    public void updateLayer(ArrayList<Seisme> listeSeismes) {
        clearLayer();
        for (Seisme seisme : listeSeismes) {
            MapPoint point = new MapPoint(seisme.getLatitude(), seisme.getLongitude());
            System.out.println(point.getLatitude() + " " + point.getLongitude());
            Point2D point2D = getMapPoint(point.getLatitude(), point.getLongitude());

            if(point2D == null) continue;

            Circle circle = new Circle(5);
            circle.setCenterX(point2D.getX());
            circle.setCenterY(point2D.getY());

            if (seisme.getMagnitude() < 3.5) {
                circle.setFill(Color.GREEN);
                circle.setRadius(seisme.getMagnitude());
            } else if (seisme.getMagnitude() < 5.5) {
                circle.setRadius(seisme.getMagnitude() * 1.115);
                circle.setFill(Color.YELLOW);
            } else if (seisme.getMagnitude() < 7.5) {
                circle.setFill(Color.ORANGE);
                circle.setRadius(seisme.getMagnitude() * 1.25);
            } else {
                circle.setFill(Color.RED);
                circle.setRadius(seisme.getMagnitude() * 2);
            }

            circle.setOpacity(0.5);

            this.getChildren().add(circle);
        }
    }

    /**
     * Supprime tous les points de la couche
     */
    public void clearLayer() {
        this.getChildren().clear();
    }
}
