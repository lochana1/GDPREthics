package com.comparision;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/*
 * Performs the Matching Operations involving the Bag of Words, Vocabulary prefixes and GDPR Areas on Dataset Ontologies
 */

public class DatasetOntologyOperations {

    public static void datasetOntologyOperations() throws IOException {

        System.out.println("Starting Tool Engine....\n\nProcess will take a few seconds....");
        for (String v : Util.bowFiles) {
            BagOfWords.bagOfWordsVocab(Util.bowFolder, v);
        }

        for (String v : Util.bowTagsFiles) {
            BagOfWords.bagOfWordsTerms(Util.bowTagsFolder, v);
        }

        for (String oFile : Util.ontologyFiles) {
            List<String> ontLines = Files.readAllLines(Paths.get((Util.ontFolder+oFile)));
            String oFileName = oFile.substring(0,oFile.length()-4);
            Util.Onts.put(oFileName,ontLines);
        }

        for(Map.Entry<String, List<String>> on : Util.Onts.entrySet()){
            SearchingDatasetOntology.checkInOntology(on.getKey());
        }


        GDPRAreasInVocab.setGdprAreasInVocabs();
        for(Map.Entry<String, List<String>> on : Util.OntRep.entrySet()){
            System.out.println(on.getKey()+" : "+ on.getValue() + Util.OntTagsVocabs.get(on.getKey()));

//            System.out.println("Found Matching Vocab Terms: "+ on.getValue().size());
//            System.out.println("\n\nBow Terms:  "+ Util.BOW_Terms);
//            System.out.println("\n\nGdprInVocab:  "+ Util.GdprInVocab);
//            System.out.println("\n\nONT REP:  "+ Util.OntRep);
//            System.out.println("\n\nBOW_VOCABS:  "+ Util.BOW_Vocabs);
//            System.out.println("\n\nONT Vocab prefixes:  "+ Util.OntVocabPrefixes);

//            System.out.println("Found Corresponding GDPR Terms: "+ DataFromDatasetOntology.getGdprAreasInOntology(on.getKey()));
            int s = DataFromDatasetOntology.getGdprAreasInOntology(on.getKey()).size();
            s = s==1 ? 0 :s;
//            System.out.println("Number of GDPR Terms: "+ s);
//            System.out.println("\n\n");





/******************** Preparing Report Object ************************************/
            ReportPhase1 rep = new ReportPhase1();

            rep.setDesc("**This is a Dataset Ontology**");

            //            Adding Dataset ontology name
            rep.setName(on.getKey());

            //            Adding the found vocabularies
            rep.setFoundVocabs(Util.OntVocabPrefixes.get(on.getKey()));

            //            Adding the found Vocabularies Areas
            rep.setFoundVocabsAreas(on.getValue());

            //              Adding the corresponding GDPR Areas
            rep.setFoundGdprAreas(DataFromDatasetOntology.getGdprAreasInOntology(on.getKey()));
            Util.report.add(rep);

        }

/****************** Printing Report Object ************************************/

//        for(ReportPhase1 r: Util.report){
//            System.out.println(r.toString());
//        }

    }



}
