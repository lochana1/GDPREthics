package com.comparision;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/*
 * Performs the Matching Operations involving the Bag of Words, Vocabulary prefixes and GDPR Areas on Dataset Labels
 */


public class DatasetLabelURIOperations {


    static void datasetLabelURIOperations() throws IOException {
        getAllVocabPrefixesFromBagOfWords();
        SetPrefixesAndVocabBow.setVocabBowAndPrefixes();
        prepareDatasetLabelURIList();
//        GDPRAreasInVocab.setGdprAreasInVocabs(); Already called in Ontology Operations
//        getAllVocabPrefixesFromBagOfWords();
//        The below mentioned function needs to be called only once
//            getAllvocabDetails();

        setVocabsAreasPresentInDatasetLabelURI();
        setGDPRAreasInDatasetLabelURI();
        prepareReport();


    }


    //    Prepares a list from the file containing the list of dataset URIs and Labels
    static void prepareDatasetLabelURIList() throws IOException {
        Util.DatasetLabelURIList = Files.readAllLines(Paths.get(Util.DatasetLabelURIFile));

    }


    //    Get the list of all vocab prefixes in all
    static void getAllVocabPrefixesFromBagOfWords() throws IOException {
        Path path;
        List<String> vocabAreas = FileListsInFolder.listFilesForFolder(new File(Util.bowFolder));
        for (String BOW_file : vocabAreas) {
            path = Paths.get(Util.bowFolder + BOW_file);
            Util.BOWVocabs.addAll(Files.readAllLines(path));
        }

    }

//    this is called only once to fetch the vocab details from the LOV API
    static void getAllvocabDetails(){
        for(String s: Util.BOWVocabs){
            FetchVocabDetails.getLOVVocabDetails(s);
        }
    }

    static boolean checkDatasetlabelInVocabDetails(String DatasetLabel, String vocabPrefix) throws IOException {

        List<String> lines;
        //check if the vocab is present in vocab_Details_lov
        //It will not be present if an entire term is used.
        List<String> vocabInLOVDetails = FileListsInFolder.listFilesForFolder(new File(Util.VOCAB_DETAILS_LOV));
        if(!vocabInLOVDetails.contains(vocabPrefix))
            return false;
        Path path = Paths.get(Util.VOCAB_DETAILS_LOV + vocabPrefix);
        lines = Files.readAllLines(path);
        for (String ln : lines) {
            if (ln.indexOf(DatasetLabel) > 0) {
                return true;
            }
        }
        return false;

    }


    static boolean checkDatasetlabelInBOWVocabArea(String DatasetLabel, String vocabBow) throws IOException {
        List<String> lines;
        Path path = Paths.get(Util.bowFolder + vocabBow);
        lines = Files.readAllLines(path);
        for (String ln : lines) {
            if (checkDatasetlabelInVocabDetails(DatasetLabel, ln))
                return true;
        }
        return false;
    }


    //    Prepares a map of Datasets and vocabs prefixes present in them
    static void setVocabsPresentInDatasetLabelURI() throws IOException {


        int i = 0;
        Set<String> vocabinDatasetLabelSet = new HashSet<>();
        for (String ds : Util.DatasetLabelURIList) {
            for(String vocabPrefix: Util.BOWVocabs ){
                     if (checkDatasetlabelInVocabDetails(ds, vocabPrefix)) {
                         vocabinDatasetLabelSet.add(vocabPrefix);
                        }
                    }
           if (vocabinDatasetLabelSet.size() > 0)
                Util.VocabInDatasetLabel.put(ds, vocabinDatasetLabelSet);
            System.out.println("ALL LABELS & VOCABS one by One:" +i++ +Util.VocabInDatasetLabel.get(ds));
        }
    }



    //    Prepares a map of Datasets and vocabs Areas (BOW) present in them
    static void setVocabsAreasPresentInDatasetLabelURI() throws IOException {

        setVocabsPresentInDatasetLabelURI();
        Set<String> vocabAreainLabel = new HashSet<>();
        for (String ds : Util.DatasetLabelURIList) {
            for(String vocabPrefix: Util.VocabInDatasetLabel.get(ds) ){
                String Bow = Util.PrefixesAndVocabBow.get(vocabPrefix);

                vocabAreainLabel.add(Bow);

                }

        }
    }



    //  Prepares a map of Datasets and GDPRAreas present in them
    static void setGDPRAreasInDatasetLabelURI() {
        List<String> gdprinDatasetLabelVocabs = new ArrayList<>();
        List<String> vocabAreasinOntLabel = new ArrayList<>();
        for (String ds : Util.DatasetLabelURIList) {
            for (String v : Util.VocabInDatasetLabel.get(ds)) {
                String BowVocab = SetPrefixesAndVocabBow.getVocabBowAndPrefixes(v);

                BowVocab = BowVocab.substring(0,BowVocab.length()-4);
                gdprinDatasetLabelVocabs = GDPRAreasInVocab.getGdprAreasInVocabs(BowVocab);
                vocabAreasinOntLabel.add(BowVocab);

            }
            Util.OntRep.put(ds,vocabAreasinOntLabel );
            if (gdprinDatasetLabelVocabs != null)
                Util.GDPRAreasInDatasetLabel.put(ds, gdprinDatasetLabelVocabs);
        }


    }


    static List<String> getGDPRAreasInDatasetLabelURI(String DatasetLabel) {
        return Util.GDPRAreasInDatasetLabel.get(DatasetLabel);

    }


    static List<String> getVocabsAreasPresentInDatasetLabelURI(String DatasetLabel) {
//        System.out.println("\n\ngetVocabsAreasPresentInDatasetLabelURI  "+ Util.OntRep);
        Set<String> temp = new HashSet<>();
        temp.addAll(Util.OntRep.get(DatasetLabel));
        List<String> temp2 = new ArrayList<>();
        temp2.addAll(temp);
        return temp2;
    }

    static List<String> getVocabsPresentInDatasetLabelURI(String DatasetLabel) {

        List<String> temp = new ArrayList<>();
        temp.addAll(Util.VocabInDatasetLabel.get(DatasetLabel));

//        System.out.println("\n\ngetVocabsAreasPresentInDatasetLabelURI  "+ Util.VocabInDatasetLabel);
        return (temp);


    }


    static void prepareReport() {


        System.out.println("VocabAreasInDatasetLabel: "+ Util.VocabAreasInDatasetLabel);
        for (Map.Entry<String, List<String>> on : Util.GDPRAreasInDatasetLabel.entrySet()) {
            ReportPhase1 rep2 = new ReportPhase1();

            //      Adding Dataset Label
            rep2.setName(on.getKey());

            //      Adding Dataset Description
            rep2.setDesc("**This is a Dataset Label**");

            //      Adding the found vocabularies
            rep2.setFoundVocabs(getVocabsPresentInDatasetLabelURI(on.getKey()));

            //      Adding the found vocabularies Areas
            rep2.setFoundVocabsAreas(getVocabsAreasPresentInDatasetLabelURI(on.getKey()));



            //       Adding the corresponding GDPR Areas
            rep2.setFoundGdprAreas(getGDPRAreasInDatasetLabelURI(on.getKey()));
            Util.report.add(rep2);

        }

    }

}


