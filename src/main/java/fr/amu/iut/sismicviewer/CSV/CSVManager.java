package fr.amu.iut.sismicviewer.CSV;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import com.opencsv.CSVReader;
import fr.amu.iut.sismicviewer.Seisme;

/**
 * Cette classe permet de lire un fichier CSV puis de créer des objets "Seisme" en tenant compte aux données contenues
 * dans le fichier csv.
 * @author BAMAS Mathias
 * @author BEDDIAF Miloud
 * @author BENDJEDDOU Rayan
 * @author LOUARN Mathis
 * @version 1.0
 */

public class CSVManager {

    private static ArrayList<Seisme> listeSeisme = new ArrayList<Seisme>();

    private static double magnitudeMoyenne = 0;

    private static int nombreDeSeisme = 0;
    private static int nombreDeSeismeAvecMagnitudeConnue = 0;

    private static String plusGrosSeismeVille;
    private static double plusGrosSeismeValeur;
    private static String plusPetitSeismeVille;
    private static double plusPetitSeismeValeur;

    /**
     * Charge un fichier CSV pour le lire
     * @param file Fichier CSV à lire
     */
    public static void loadCsv(File file) {
        try {
            FileReader filereader = new FileReader(file);
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;
            String[] cles = csvReader.readNext();
            double magnitudeMax = 0;
            double magnitudeMin = 10;
            while ((nextRecord = csvReader.readNext()) != null) {
                HashMap<String, String> cleValeurHashmap = new HashMap<>();
                listeSeisme.add(new Seisme(nextRecord));
                try {
                    magnitudeMoyenne = magnitudeMoyenne + Double.parseDouble(nextRecord[10]);
                    nombreDeSeismeAvecMagnitudeConnue = nombreDeSeismeAvecMagnitudeConnue + 1;
                    if (Double.parseDouble(nextRecord[10]) > magnitudeMax) {
                        plusGrosSeismeVille = nextRecord[4];
                        plusGrosSeismeValeur = Double.parseDouble(nextRecord[10]);
                        magnitudeMax = plusGrosSeismeValeur;
                    }
                    if (Double.parseDouble(nextRecord[10]) < magnitudeMin) {
                        plusPetitSeismeVille = nextRecord[4];
                        plusPetitSeismeValeur = Double.parseDouble(nextRecord[10]);
                        magnitudeMin = plusPetitSeismeValeur;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Le séisme " + nextRecord[0] + " a une magnitude null \n \t Nombre de séisme null : " + String.valueOf(nombreDeSeisme - nombreDeSeismeAvecMagnitudeConnue + 1));
                }
                ++nombreDeSeisme;
            }
            magnitudeMoyenne = magnitudeMoyenne / nombreDeSeismeAvecMagnitudeConnue;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retourne une liste de Seisme
     * @return ArrayList de valeurs de type Seisme
     */
    public static ArrayList<Seisme> getListeSeisme() {
        return listeSeisme;
    }

    /**
     * Retourne la magnitude moyenne des données du fichier CSV
     * @return valeur de type Double
     */
    public static double getMagnitudeMoyenne() {
        return magnitudeMoyenne;
    }

    /**
     * Retourne le nombre total de Seisme
     * @return valeur de type Int
     */
    public static int getNombreDeSeisme() {
        return nombreDeSeisme;
    }

    /**
     * Retourne la ville ayant un seisme avec la plus haute magnitude
     * @return String
     */
    public static String getPlusGrosSeismeVille() {
        return plusGrosSeismeVille;
    }

    /**
     * Retourne la valeur de la magnitude de la ville ayant le seisme le plus puissant
     * @return valeur de type Double
     */
    public static double getPlusGrosSeismeValeur() {
        return plusGrosSeismeValeur;
    }

    /**
     * Retourne le nom de la ville ayant un seisme de magnitude la plus petite
     * @return String
     */
    public static String getPlusPetitSeismeVille() {
        return plusPetitSeismeVille;
    }

    /**
     * Retourne la valeur de la magnitude de la ville ayant le seisme le moins puissant
     * @return valeur de type Double
     */
    public static double getPlusPetitSeismeValeur() {
        return plusPetitSeismeValeur;
    }

    /**
     * Permet d'obtenir toutes les régions lu dans le fichier CSV, stockées dans une ArrayList de type String
     * @return ArrayList de valeurs de type String
     */
    public static ArrayList<String> getAllRegion(){
        ArrayList<String> regions = new ArrayList<String>();
        for(Seisme seisme : listeSeisme){
            if(!regions.contains(seisme.getRegion())){
                regions.add(seisme.getRegion());
            }
        }
        Collections.sort(regions);
        return regions;
    }
}