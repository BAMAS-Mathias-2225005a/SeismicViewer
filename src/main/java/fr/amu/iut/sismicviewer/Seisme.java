package fr.amu.iut.sismicviewer;

/**
 * Classe définissant l'objet "Seisme". Cet objet à pour but d'accéder plus facilement aux données du fichier CSV
 *
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
    private int identifiant;

    public Seisme() {
        annee = 0;
        region = "";
        ville = "";
        latitude = 0;
        longitude = 0;
        magnitude = -1;
    }

    /**
     * Constructeur de la classe Seisme
     *
     * @param values Colonne de donnée du fichier CSV que l'on souhaite exploiter
     */

    public Seisme(String[] values) {
        region = values[4];

        try {
            identifiant = Integer.parseInt(values[0]);
        } catch (NumberFormatException e) {
            identifiant = -1;
        }
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
            if (Double.parseDouble(values[10]) > 10) {
                throw new NumberFormatException("La magnitude ne peut pas etre supérieure a 10");
            }
            magnitude = Double.parseDouble(values[10]);
        } catch (NumberFormatException e) {
            magnitude = -1;
        }
    }

    /**
     * Retourne l'année du séisme
     *
     * @return valeur de type Int
     */
    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    /**
     * Retourne la région du séisme
     *
     * @return String
     */
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * Retourne la latidude du séisme
     *
     * @return valeur de type Double
     */
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Retourne la longitude du séisme
     *
     * @return valeur de type Double
     */
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Retoune la magnitude du séisme
     *
     * @return valeur de type Double
     */
    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(int i) {
        if (i >= 0 && i <= 10)
            this.magnitude = i;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getIdentifiant() {
        return identifiant;
    }
}
