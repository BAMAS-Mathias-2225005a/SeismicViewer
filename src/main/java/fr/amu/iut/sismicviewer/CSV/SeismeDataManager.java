package fr.amu.iut.sismicviewer.CSV;

import java.util.ArrayList;
import java.util.HashMap;

public class SeismeDataManager {

    public ArrayList<HashMap<String,String>> getAnneeFromTo(ArrayList<HashMap<String,String>> data, double from, double to){
        ArrayList<HashMap<String,String>> dataTriees = new ArrayList<HashMap<String, String>>();
        for(HashMap<String,String> seisme : data){
            int date = Integer.parseInt(seisme.get("Date").substring(0,4));
            if(date >= from && date <= to){
                dataTriees.add(seisme);
            }
        }
        return dataTriees;
    }
}
