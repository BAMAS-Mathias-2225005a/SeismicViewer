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
            nombre_attributs = nextRecord.length;

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
}