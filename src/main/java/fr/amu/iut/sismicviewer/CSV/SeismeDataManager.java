package fr.amu.iut.sismicviewer.CSV;

import fr.amu.iut.sismicviewer.Seisme;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * Cette classe permet de manipuler les données des objets de type Seisme
 *
 * @author BAMAS Mathias
 * @author BEDDIAF Miloud
 * @author BENDJEDDOU Rayan
 * @author LOUARN Mathis
 * @version 1.0
 */

public class SeismeDataManager {

    /**
     * Permet d'obtenir une ArrayList de Seisme en fonction d'une intervalle d'année
     *
     * @param data ArrayList de Seisme à trier
     * @param from Intervalle de départ, valeur de type Int
     * @param to   Intervalle de fin, valeur de type Int
     * @return Nouvelle ArrayList triée, de valeurs de type Seisme
     */
    public ArrayList<Seisme> getAnneeFromTo(ArrayList<Seisme> data, int from, int to) {
        ArrayList<Seisme> seismeTries = new ArrayList<Seisme>();
        for (Seisme seisme : data) {
            if (seisme.getAnnee() >= from && seisme.getAnnee() <= to) {
                seismeTries.add(seisme);
            }
        }
        return seismeTries;
    }

    /**
     * Permet d'obtenir des Seisme proche dans le rayon d'un autre Seisme
     *
     * @param data      ArrayList de Seisme à traiter
     * @param latitude  Latitude du Seisme choisit (valeur de type Double)
     * @param longitude Longitude du Seisme choisit (valeur de type Double)
     * @param rayon     Rayon autour du Seisme choisit (valeur de type Double)
     * @return Nouvelle ArrayList triée, de valeurs de type Seisme
     */
    public ArrayList<Seisme> getSeismeDansRayon(ArrayList<Seisme> data, double latitude, double longitude, double rayon) {
        ArrayList<Seisme> seismeTries = new ArrayList<Seisme>();
        for (Seisme seisme : data) {
            if (Math.sqrt(Math.pow(seisme.getLatitude() - latitude, 2) + Math.pow(seisme.getLongitude() - longitude, 2)) <= rayon) {
                seismeTries.add(seisme);
            }
        }
        return seismeTries;
    }

    /**
     * Permet d'obtenir une ArrayList de Seisme en fonction d'une intervalle de magnitude
     *
     * @param data         ArrayList de Seisme à traiter
     * @param magnitudeMin Intervalle de départ, valeur de type Double
     * @param magnitudeMax Intervalle de fin, valeur de type Double
     * @return Nouvelle ArrayList triée, de valeurs de type Seisme
     */
    public ArrayList<Seisme> getSeismeParMagnitude(ArrayList<Seisme> data, double magnitudeMin, double magnitudeMax) {
        ArrayList<Seisme> seismeTries = new ArrayList<Seisme>();
        for (Seisme seisme : data) {
            if (seisme.getMagnitude() >= magnitudeMin && seisme.getMagnitude() <= magnitudeMax) {
                seismeTries.add(seisme);
            }
        }
        return seismeTries;
    }

    /**
     * Permet d'obtenir une ArrayList de Seisme en fonction des regions
     *
     * @param data   ArrayList de Seisme à trater
     * @param region ObservableList de String,
     * @return Nouvelle ArrayList triée, de valeurs de type Seisme
     */
    public ArrayList<Seisme> getSeismeParRegion(ArrayList<Seisme> data, ObservableList<String> region) {
        ArrayList<Seisme> seismeTries = new ArrayList<Seisme>();
        for (Seisme seisme : data) {
            if (region.contains(seisme.getRegion())) {
                seismeTries.add(seisme);
            }
        }
        return seismeTries;
    }

    /**
     * Permet de récupérer le séisme avec la plus grosse magnitude, pas de gestion de doublon
     *
     * @param data Arraylist de Seisme
     * @return Seisme
     */
    public Seisme getSeismeMax(ArrayList<Seisme> data) {
        Seisme seismeMax = data.get(0);
        for (Seisme seisme : data) {
            if (seisme.getMagnitude() > seismeMax.getMagnitude()) {
                seismeMax = seisme;
            }
        }
        return seismeMax;
    }

    /**
     * Permet de récupérer le séisme avec la plus petit magnitude, pas de gestion de doublon
     *
     * @param data Arraylist de Seisme
     * @return Seisme
     */
    public Seisme getSeismeMin(ArrayList<Seisme> data) {
        Seisme seismeMin = new Seisme();
        seismeMin.setMagnitude(10);
        for (Seisme seisme : data) {
            if (seisme.getMagnitude() < seismeMin.getMagnitude() && seisme.getMagnitude() > 0) {
                seismeMin = seisme;
            }
        }
        return seismeMin;
    }

    /**
     * Renvoi le nombre d'entré dans la liste de séisme
     *
     * @param data Arraylist de Seisme
     * @return int
     */
    public int getNombreSeisme(ArrayList<Seisme> data) {
        return data.size();
    }

    /**
     * Renvoi le nombre de séisme avec une magnitude non null (initialisé a -1 si c'est null)
     *
     * @param data Arraylist de Seisme
     * @return int
     */
    public int getNombreSeismeAvecMagnitudeConnue(ArrayList<Seisme> data) {
        int nombreSeisme = 0;
        for (Seisme seisme : data) {
            if (seisme.getMagnitude() != -1) {
                nombreSeisme = nombreSeisme + 1;
            }
        }
        return nombreSeisme;
    }

    /**
     * Permet d'avoir la magnitude moyenne des séisme, utilise la fonction getNombreSeismeAvecMagnitudeConnue pour ne pas compter les séismes avec une magnitude non connue
     *
     * @param data Arraylist de Seisme
     * @return double
     */
    public double getMagnitudeMoyenne(ArrayList<Seisme> data) {
        double magnitudeTotale = 0;
        for (Seisme seisme : data) {
            magnitudeTotale = magnitudeTotale + seisme.getMagnitude();
        }
        return magnitudeTotale / getNombreSeismeAvecMagnitudeConnue(data);
    }

    /**
     * Permet d'avoir la date du séisme le plus vieux
     *
     * @param data Arraylist de Seisme
     * @return Seisme
     */
    public Seisme getSeismeLePlusVieux(ArrayList<Seisme> data) {
        Seisme seismeLePlusVieux = data.get(1);
        for (Seisme seisme : data) {
            if (seisme.getAnnee() < seismeLePlusVieux.getAnnee() && seisme.getAnnee() > 1) {
                seismeLePlusVieux = seisme;
            }
        }
        return seismeLePlusVieux;
    }

    /**
     * Permet d'avoir la date du séisme le plus récent
     *
     * @param data Arraylist de Seisme
     * @return Seisme
     */
    public Seisme getSeismeLePlusRecent(ArrayList<Seisme> data) {
        Seisme seismeLePlusRecent = data.get(0);
        for (Seisme seisme : data) {
            if (seisme.getAnnee() > seismeLePlusRecent.getAnnee() && seisme.getAnnee() > 1) {
                seismeLePlusRecent = seisme;
            }
        }
        return seismeLePlusRecent;
    }

    /**
     * Permet d'avoir la liste des séisme se trouvant sur le territoire Français
     *
     * @param data Arraylist de Seisme
     * @return Arraylist de Seisme
     */
    public ArrayList<Seisme> getSeismeEnFrance(ArrayList<Seisme> data) {
        ArrayList<Seisme> listeSeismeEnFrance = new ArrayList<>();
        for (Seisme seisme : data) {
            if (
                    !seisme.getRegion().contains("ALLEMAGNE") &&
                            !seisme.getRegion().contains("AUTRICHE") &&
                            !seisme.getRegion().contains("BELGIQUE") &&
                            !seisme.getRegion().contains("ESPAGNE") &&
                            !seisme.getRegion().contains("HOLLANDE") &&
                            !seisme.getRegion().contains("ITALIE") &&
                            !seisme.getRegion().contains("SUISSE")) {
                listeSeismeEnFrance.add(seisme);
            }
        }
        return listeSeismeEnFrance;
    }
}