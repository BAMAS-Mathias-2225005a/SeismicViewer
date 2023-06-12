package fr.amu.iut.sismicviewer;

/**
 * Classe définissant l'objet "Seisme". Cet objet à pour but d'accéder plus facilement aux données du fichier CSV
 * @author BAMAS Mathias
 * @author BEDDIAF Miloud
 * @author BENDJEDDOU Rayan
 * @author LOUARN Mathis
 * @version 1.0
 */

public class Seisme {
    private int annee;
    private String region;
    private String ville;
    private double latitude;
    private double longitude;
    private double magnitude;

    /**
     * Constructeur de la classe Seisme
     * @param values Colonne de donnée du fichier CSV que l'on souhaite exploiter
     */
    public Seisme(String[] values) {
        region = values[4];

        try {
            annee = Integer.parseInt(values[1].substring(0, 4));
        } catch (NumberFormatException e) {
            annee = 0;
        }

        try {
            ville = values[3];
        } catch (NumberFormatException e) {
            ville = "";
        }

        try {
            latitude = Double.parseDouble(values[8]);
        } catch (NumberFormatException e) {
            latitude = 0;
        }

        try {
            longitude = Double.parseDouble(values[9]);
        } catch (NumberFormatException e) {
            longitude = 0;
        }

        try {
            magnitude = Double.parseDouble(values[10]);
        } catch (NumberFormatException e) {
            magnitude = -1;
        }
    }

    /**
     * Retourne l'année du séisme
     * @return valeur de type Int
     */
    public int getAnnee() {
        return annee;
    }

    /**
     * Retourne la région du séisme
     * @return String
     */
    public String getRegion() {
        return region;
    }

    /**
     * Retourne la latidude du séisme
     * @return valeur de type Double
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Retourne la longitude du séisme
     * @return valeur de type Double
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Retoune la magnitude du séisme
     * @return valeur de type Double
     */
    public double getMagnitude() {
        return magnitude;
    }

    public String getVille() {
        return ville;
    }

    public void setMagnitude(int i) {
        this.magnitude = i;
    }
}
