package com.comparision;

/*
 * This class will find the presence of all vocabs (From LOV) in the Ontology dataset and prepare another report
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;



public class Evaluation {

    public static void evluateOntologyDatasets() throws IOException {
//    public static void main(String [] args) throws IOException {
        getOntologyListFromFIle();
//        System.out.println("evalList:"+ Util.EvalReport );
        writeToFile();
    }



    /*
    Iterate thru the List of the Ontology Files
     */
    private static void getOntologyListFromFIle() throws IOException {


        for(Map.Entry<String,List<String>> entry : Util.Onts.entrySet()){
//            System.out.println("Name:"+entry.getKey());
            ReportPhase1 eval = new ReportPhase1();
            eval.setDesc("Evaluation for Dataset Ontology");
            eval.setName(entry.getKey());
            List<String> foundvocabs = getListOfVocabInOntology(entry.getKey(),entry.getValue());
            eval.setFoundVocabs(foundvocabs);
            List<String> vocabAreas = getVocabAreasInOntology(foundvocabs);
            eval.setFoundVocabsAreas(vocabAreas);
            List<String> foundgdprAreas = getGDPRAreasInOntology(entry.getKey(), vocabAreas);

            eval.setFoundGdprAreas(foundgdprAreas);
//
            Util.EvalReport.add(eval);


        }
//        System.out.println("evalList:"+ Util.EvalReport );

    }

/*  Write in a file
    get a list of all vocab :
*/

    private static List<String> getListOfAllLOVVocabs(){
        List<String> vocabInLOVDetails = FileListsInFolder.listFilesForFolder(new File(Util.VOCAB_DETAILS_LOV));
        return vocabInLOVDetails;
    }

/*    Get the smaller list of voacbs present in the intology
 *    Read a line in an ontology
 *    input: ontology output: list of vocab prefixes matched from the given list
 */

    private static List<String> getListOfVocabInOntology(String OntName, List<String> lines) throws IOException {

        List<String> vocab  = getListOfAllLOVVocabs();
        List<String> vocabPrefixInOnt = new ArrayList<>();
        for(String v: vocab){
            for(String s: lines){
                if(s.indexOf(v)>=0){
                    vocabPrefixInOnt.add(v);
                }
            }
        }
//        writeToFile(OntName, vocabPrefixInOnt);
        Set<String> vocabPrefix = new HashSet<>();
        vocabPrefix.addAll(vocabPrefixInOnt);
        vocabPrefixInOnt.clear();
        vocabPrefixInOnt.addAll(vocabPrefix);
        return vocabPrefixInOnt;


    }
/* Writing to file
 *
 */

//Find the vocab area based on the vocab list
//    Find gdpr area
//    Prepare EvalReport object
//    Write to File

    private static List<String> getVocabAreasInOntology(List<String> vocabPrefixes){
        List<String> foundVocabsAreas = new ArrayList<>();
        for(String vp: vocabPrefixes){
            if(Util.PrefixesAndVocabBow.containsKey(vp) && !foundVocabsAreas.contains(Util.PrefixesAndVocabBow.get(vp)))
                foundVocabsAreas.add(Util.PrefixesAndVocabBow.get(vp));
        }
        return foundVocabsAreas;
    }

    private static List<String> getGDPRAreasInOntology(String ontName, List<String> vocabAreas){
        List<String> gdprAreasInOnt = new ArrayList<>();
//        System.out.println("Vocab Areas for GDPR:" + vocabAreas);
        for(String v : vocabAreas){
            String temp = v.substring(0,v.length()-4);
            if(GDPRAreasInVocab.getGdprAreasInVocabs(temp).size()>=0)
                gdprAreasInOnt.addAll(GDPRAreasInVocab.getGdprAreasInVocabs(temp));
        }
//        System.out.println("GDPR Areas found:" + gdprAreasInOnt);
        return gdprAreasInOnt;
    }



    private static void writeToFile() {
        StringBuilder rep = new  StringBuilder();
        rep.append("Report \n\n");
        for(ReportPhase1 r: Util.EvalReport){

            rep.append(r.toString());
            rep.append("\n");

        }
        try{
            Files.write(Util.EVALUATION_PATH, rep.toString().getBytes());
        }
        catch(Exception e){
            System.out.println("There has been an errror encountered while preparing the report.\nException: "+e);
            e.printStackTrace();
        }


    }


}
