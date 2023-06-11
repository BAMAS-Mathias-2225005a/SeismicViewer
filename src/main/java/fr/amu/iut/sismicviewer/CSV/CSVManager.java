package fr.amu.iut.sismicviewer.CSV;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import com.opencsv.CSVReader;
import fr.amu.iut.sismicviewer.Seisme;


public class CSVManager {

    private static ArrayList<Seisme> listeSeisme = new ArrayList<Seisme>();

    private static double magnitudeMoyenne = 0;

    private static int nombreDeSeisme = 0;
    private static int nombreDeSeismeAvecMagnitudeConnue = 0;

    private static String plusGrosSeismeVille;
    private static double plusGrosSeismeValeur;
    private static String plusPetitSeismeVille;
    private static double plusPetitSeismeValeur;

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
                    if(Double.parseDouble(nextRecord[10]) > magnitudeMax){
                        plusGrosSeismeVille = nextRecord[4];
                        plusGrosSeismeValeur = Double.parseDouble(nextRecord[10]);
                        magnitudeMax = plusGrosSeismeValeur;
                    }
                    if(Double.parseDouble(nextRecord[10]) < magnitudeMin){
                        plusPetitSeismeVille = nextRecord[4];
                        plusPetitSeismeValeur = Double.parseDouble(nextRecord[10]);
                        magnitudeMin = plusPetitSeismeValeur;
                    }
                } catch (NumberFormatException e){
                    System.out.println("Le séisme " + nextRecord[0] + " a une magnitude null \n \t Nombre de séisme null : " + String.valueOf(nombreDeSeisme-nombreDeSeismeAvecMagnitudeConnue+1));
                }
                ++nombreDeSeisme;
            }
            magnitudeMoyenne = magnitudeMoyenne/nombreDeSeismeAvecMagnitudeConnue;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Seisme> getListeSeisme() {
        return listeSeisme;
    }

    public static double getMagnitudeMoyenne() {
        return magnitudeMoyenne;
    }

    public static int getNombreDeSeisme() {
        return nombreDeSeisme;
    }

    public static String getPlusGrosSeismeVille() {
        return plusGrosSeismeVille;
    }

    public static double getPlusGrosSeismeValeur() {
        return plusGrosSeismeValeur;
    }

    public static String getPlusPetitSeismeVille() {
        return plusPetitSeismeVille;
    }

    public static double getPlusPetitSeismeValeur() {
        return plusPetitSeismeValeur;
    }

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