package com.comparision;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * This class refines the terms extracted from the LOV library and gives the relevant vocab prefixes fromt the terms
 */

public class Refining_BOW {

    static List<String> fls = new ArrayList<>();
    static List<String> terms = new ArrayList<>();
    static Set<String> newterms = new HashSet<>();
    static File foldername = new File("Temp/bg2/");

    public static void main(String [] args) throws IOException {
        getFileList();
        int i=1;
        for(String file: fls){
            terms.addAll(Files.readAllLines(Paths.get(foldername + "/"+file)));
            changeFile();
//            System.out.println(terms);
//            System.out.println(newterms);
            terms.clear();
            writeToFile(newterms, i);
            i++;
            newterms.clear();

        }



    }

    private static void writeToFile(Set<String> newterms, int i) {

        String filename = "Temp/bg2/Changed_Vocab";

        StringBuilder rep = new  StringBuilder();
        for(String r: newterms){

            rep.append(r);
            rep.append("\n");

        }
        try{
            filename = filename + i;

            Files.write(Paths.get(filename), rep.toString().getBytes());
        }
        catch(Exception e){
            System.out.println("There has been an errror encountered while writing the file.\nException: "+e);
            e.printStackTrace();
        }


    }





    private static void changeFile() throws IOException {

        for(String t: terms )  {

            String s = t;

            int pos = s.indexOf(":");
            if(pos>=0)
                s=s.substring(0,pos);
            newterms.add(s);
        }


    }

    static void getFileList(){


        for (final File fileEntry : foldername.listFiles()) {
            fls.add(fileEntry.getName());
        }
    }




}
