package fr.amu.iut.sismicviewer.CSV;

import fr.amu.iut.sismicviewer.Seisme;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class SeismeDataManager {

    public ArrayList<Seisme> getAnneeFromTo(ArrayList<Seisme> data, int from, int to){
        ArrayList<Seisme> seismeTries = new ArrayList<Seisme>();
        for(Seisme seisme : data){
            if(seisme.getAnnee() >= from && seisme.getAnnee() <= to){
                seismeTries.add(seisme);
            }
        }
        return seismeTries;
    }

    public ArrayList<Seisme> getSeismeDansRayon(ArrayList<Seisme> data, double latitude, double longitude, double rayon){
        ArrayList<Seisme> seismeTries = new ArrayList<Seisme>();
        for(Seisme seisme : data){
            if(Math.sqrt(Math.pow(seisme.getLatitude() - latitude, 2) + Math.pow(seisme.getLongitude() - longitude, 2)) <= rayon){
                seismeTries.add(seisme);
            }
        }
        return seismeTries;
    }

    public ArrayList<Seisme> getSeismeParMagnitude(ArrayList<Seisme> data, double magnitudeMin, double magnitudeMax){
        ArrayList<Seisme> seismeTries = new ArrayList<Seisme>();
        for(Seisme seisme : data){
            if(seisme.getMagnitude() >= magnitudeMin && seisme.getMagnitude() <= magnitudeMax){
                seismeTries.add(seisme);
            }
        }
        return seismeTries;
    }

    public ArrayList<Seisme> getSeismeParRegion(ArrayList<Seisme> data, ObservableList<String> region) {
        ArrayList<Seisme> seismeTries = new ArrayList<Seisme>();
        for (Seisme seisme : data) {
            if (region.contains(seisme.getRegion())) {
                seismeTries.add(seisme);
            }
        }
        return seismeTries;
    }

    public String[] getSeismeMax(ArrayList<Seisme> data) {
        String[] resultat = new String[2];
        double magnitudeMax = data.get(0).getMagnitude();
        for (Seisme seisme : data) {
            if (seisme.getMagnitude() > magnitudeMax) {
                magnitudeMax = seisme.getMagnitude();
                resultat[0] = seisme.getRegion();
                resultat[1] = String.valueOf(seisme.getMagnitude());
            }
        }
        return resultat;
    }

    public String[] getSeismeMin(ArrayList<Seisme> data) {
        String[] resultat = new String[2];
        double magnitudeMin = 10;
        for (Seisme seisme : data) {
            if (seisme.getMagnitude() < magnitudeMin && seisme.getMagnitude() > 0) {
                magnitudeMin = seisme.getMagnitude();
                resultat[0] = seisme.getRegion();
                resultat[1] = String.valueOf(seisme.getMagnitude());
            }
        }
        return resultat;
    }

    public int getNombreSeisme(ArrayList<Seisme> data) {
        return data.size();
    }

    public int getNombreSeismeAvecMagnitudeConnue(ArrayList<Seisme> data) {
        int nombreSeisme = 0;
        for (Seisme seisme : data) {
            if (seisme.getMagnitude() != -1) {
                nombreSeisme = nombreSeisme + 1;
            }
        }
        return nombreSeisme;
    }

    public double getMagnitudeMoyenne(ArrayList<Seisme> data) {
        double magnitudeTotale = 0;
        for (Seisme seisme : data) {
            magnitudeTotale = magnitudeTotale + seisme.getMagnitude();
        }
        return magnitudeTotale / getNombreSeismeAvecMagnitudeConnue(data);
    }
}
