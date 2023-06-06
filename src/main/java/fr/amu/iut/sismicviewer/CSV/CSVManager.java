package fr.amu.iut.sismicviewer.CSV;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import com.opencsv.CSVReader;


public class CSVManager {

    private int nombre_de_donnees = 0;
    private int nombre_attributs = 0;
    private ArrayList<String[]> data = new ArrayList<String[]>();

    public CSVManager(File file) {
        try {
            FileReader filereader = new FileReader(file);
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                data.add(nextRecord);
                ++nombre_de_donnees;
            }
            nombre_attributs = data.get(0).length;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CSVManager() {
    }

    public void loadCsv(File file) {
        nombre_de_donnees = 0;
        try {
            FileReader filereader = new FileReader(file);
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                data.add(nextRecord);
                ++nombre_de_donnees;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
}