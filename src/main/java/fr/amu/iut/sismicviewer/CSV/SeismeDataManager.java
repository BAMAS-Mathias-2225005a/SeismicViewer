package fr.amu.iut.sismicviewer.CSV;

import fr.amu.iut.sismicviewer.Seisme;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 *Cette classe permet de manipuler les données des objets de type Seisme
 * @author BAMAS Mathias
 * @author BEDDIAF Miloud
 * @author BENDJEDDOU Rayan
 * @author LOUARN Mathis
 * @version 1.0
 */

public class SeismeDataManager {

    /**
     * Permet d'obtenir une ArrayList de Seisme en fonction d'une intervalle d'année
     * @param data ArrayList de Seisme à trier
     * @param from Intervalle de départ, valeur de type Int
     * @param to Intervalle de fin, valeur de type Int
     * @return Nouvelle ArrayList triée, de valeurs de type Seisme
     */
    public ArrayList<Seisme> getAnneeFromTo(ArrayList<Seisme> data, int from, int to){
        ArrayList<Seisme> seismeTries = new ArrayList<Seisme>();
        for(Seisme seisme : data){
            if(seisme.getAnnee() >= from && seisme.getAnnee() <= to){
                seismeTries.add(seisme);
            }
        }
        return seismeTries;
    }

    /**
     * Permet d'obtenir des Seisme proche dans le rayon d'un autre Seisme
     * @param data ArrayList de Seisme à traiter
     * @param latitude Latitude du Seisme choisit (valeur de type Double)
     * @param longitude Longitude du Seisme choisit (valeur de type Double)
     * @param rayon Rayon autour du Seisme choisit (valeur de type Double)
     * @return Nouvelle ArrayList triée, de valeurs de type Seisme
     */
    public ArrayList<Seisme> getSeismeDansRayon(ArrayList<Seisme> data, double latitude, double longitude, double rayon){
        ArrayList<Seisme> seismeTries = new ArrayList<Seisme>();
        for(Seisme seisme : data){
            if(Math.sqrt(Math.pow(seisme.getLatitude() - latitude, 2) + Math.pow(seisme.getLongitude() - longitude, 2)) <= rayon){
                seismeTries.add(seisme);
            }
        }
        return seismeTries;
    }

    /**
     * Permet d'obtenir une ArrayList de Seisme en fonction d'une intervalle de magnitude
     * @param data ArrayList de Seisme à traiter
     * @param magnitudeMin Intervalle de départ, valeur de type Double
     * @param magnitudeMax Intervalle de fin, valeur de type Double
     * @return Nouvelle ArrayList triée, de valeurs de type Seisme
     */
    public ArrayList<Seisme> getSeismeParMagnitude(ArrayList<Seisme> data, double magnitudeMin, double magnitudeMax){
        ArrayList<Seisme> seismeTries = new ArrayList<Seisme>();
        for(Seisme seisme : data){
            if(seisme.getMagnitude() >= magnitudeMin && seisme.getMagnitude() <= magnitudeMax){
                seismeTries.add(seisme);
            }
        }
        return seismeTries;
    }

    /**
     * Permet d'obtenir une ArrayList de Seisme en fonction des regions
     * @param data ArrayList de Seisme à trater
     * @param region ObservableList de String,
     * @return Nouvelle ArrayList triée, de valeurs de type Seisme
     */
    public ArrayList<Seisme> getSeismeParRegion(ArrayList<Seisme> data, ObservableList<String> region){
        ArrayList<Seisme> seismeTries = new ArrayList<Seisme>();
        for(Seisme seisme : data){
            if(region.contains(seisme.getRegion())){
                seismeTries.add(seisme);
            }
        }
        return seismeTries;
    }
}
