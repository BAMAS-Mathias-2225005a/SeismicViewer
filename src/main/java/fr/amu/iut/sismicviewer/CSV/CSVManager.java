package fr.amu.iut.sismicviewer.CSV;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
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
    /*
    public String[] getData(int ligne) {
        if (ligne > nombre_de_donnees) {
            throw new IllegalArgumentException("L'indice spécifié dépasse le nombre d'entrée dans le fichier CSV");
        } else if (ligne < 1) {
            throw new IllegalArgumentException("L'indice ne peut être négatif");
        }
        for (String donnee : data.get((ligne))) {
            System.out.print(donnee + " ; ");
        }
        System.out.println();
        return data.get(ligne);
    }

    public ArrayList<String[]> getData(int ligne_dep, int ligne_fin) {
        ArrayList<String[]> data_coupe = new ArrayList<String[]>();
        for (int i = ligne_dep; i < ligne_fin; ++i) {
            data_coupe.add(this.getData(i));
        }
        return data_coupe;
    }

    public ArrayList<String[]> getData() {
        return getData(1, nombre_de_donnees);
    }

    public int getNombre_de_donnees() {
        return nombre_de_donnees - 1;
    }

    public int getNombre_attributs() {
        return nombre_attributs;
    }

    public String[] getListeAttributs() {
        return data.get(0);
    }

     */
}