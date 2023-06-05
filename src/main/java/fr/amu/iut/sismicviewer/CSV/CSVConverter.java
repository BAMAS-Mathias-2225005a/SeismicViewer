package fr.amu.iut.sismicviewer.CSV;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class CSVConverter {

    public ArrayList<String[]> getCsv(){
        String csvFile = "dataSeisme.csv";
        String line = "";
        String csvDelimiter = ",";
        ArrayList<String[]> data = new ArrayList<String[]>();


        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                data.add(line.split(csvDelimiter));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}