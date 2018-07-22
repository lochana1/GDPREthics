package com.comparision;

import java.nio.file.Files;

/*
 * Creates the report from the ReportDataset class object
 */

public class PreparePHASE1Report {

    public static void preparePHASE1Report() {
        StringBuilder rep = new  StringBuilder();
        rep.append("Report \n\n");
        for(ReportDataset r: Util.report){

        rep.append(r.toString());
        rep.append("\n");

        }
        try{
            Files.write(Util.REPORT_PATH, rep.toString().getBytes());
        }
        catch(Exception e){
            System.out.println("There has been an errror encountered while preparing the report.\nException: "+e);
            }


    }
}
