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

    public static void loadCsv(File file) {
        listeSeisme.clear();
        try {
            FileReader filereader = new FileReader(file);
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                listeSeisme.add(new Seisme(nextRecord));
            }
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