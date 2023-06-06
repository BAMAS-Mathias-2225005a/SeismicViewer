package fr.amu.iut.sismicviewer.CSV;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import com.opencsv.CSVReader;


public class CSVConverter {

    public ArrayList<String[]> getCsv(File file) {
        ArrayList<String[]> data = new ArrayList<String[]>();
        CSVConverter csvConverter = new CSVConverter();

        try {
            FileReader filereader = new FileReader(file);
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                data.add(nextRecord);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

}