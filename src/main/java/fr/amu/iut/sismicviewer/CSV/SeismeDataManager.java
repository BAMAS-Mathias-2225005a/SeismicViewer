package fr.amu.iut.sismicviewer.CSV;

import fr.amu.iut.sismicviewer.Seisme;

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
}
