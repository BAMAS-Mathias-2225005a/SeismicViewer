package fr.amu.iut.sismicviewer;

public class Seisme {
    private int annee;
    private String region;
    private double latitude;
    private double longitude;
    private double magnitude;

    public Seisme(String[] values) {
        region = values[4];

        try {
            annee = Integer.parseInt(values[1].substring(0, 4));
        } catch (NumberFormatException e) {
            annee = 0;
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

    public int getAnnee() {
        return annee;
    }

    public String getRegion() {
        return region;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getMagnitude() {
        return magnitude;
    }
}
