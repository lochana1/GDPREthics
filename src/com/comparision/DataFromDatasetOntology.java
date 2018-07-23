package com.comparision;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * This class extracts the data from the Dataset Ontology provided as Input
 */

public class DataFromDatasetOntology {

    static List<String> getVocabAreaListInOntology(String ontName){  //Tested
        List<String> VocabListInOnt = Util.OntRep.get(ontName);
        return VocabListInOnt;
    }

    private static List<String> getAllTermsAndVocabsInOntology(String ontName){
        List<String> TermsListInOnt = Util.OntRep.get(ontName);
        return TermsListInOnt;
    }

    private static List<String> ontologyParsed(String folderName, String ontPath) throws IOException {
        List<String> ont = Files.readAllLines(Paths.get((folderName+ontPath)));
        return ont;
    }


    static List<String> getGdprAreasInOntology(String OntName){

        List<String> gdprAreas = new ArrayList<>();
        List<String> vocabList;
        List<String> VocabListInOnt = getVocabAreaListInOntology(OntName);
        for(String vocabFile : VocabListInOnt){
            vocabList = GDPRAreasInVocab.getGdprAreasInVocabs(vocabFile);
            gdprAreas.addAll(vocabList);
        }

        Set<String> areasInOntology = new HashSet<>(gdprAreas);
        List<String> uniqueAreas = new ArrayList<>();
        uniqueAreas.addAll(areasInOntology);
        return uniqueAreas;
    }
}
