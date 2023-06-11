package fr.amu.iut.sismicviewer.CSV;

import fr.amu.iut.sismicviewer.Seisme;

import java.util.ArrayList;

public class SeismeDataManager {

    public ArrayList<Seisme> getAnneeFromTo(ArrayList<Seisme> data, double from, double to) {
        ArrayList<Seisme> seismeTries = new ArrayList<Seisme>();
        for (Seisme seisme : data) {
            if (seisme.getAnnee() >= from && seisme.getAnnee() <= to) {
                seismeTries.add(seisme);
            }
        }
        return seismeTries;
    }
}
