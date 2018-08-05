package com.comparision;

import java.util.*;

/*
 * Match operations in the Dataset Ontology
 */

public class SearchingDatasetOntology {

    public static List<String> foundVocabsAreas = new ArrayList<>();
    static void checkInOntology(String ontologyName){

        List<String> foundVocabsPrefixes = new ArrayList<>();
        List<String> foundVocabsTerms = new ArrayList<>();

        for (Map.Entry<String, String> bw: Util.BOW_Vocabs.entrySet()  ) {

            for(String ln : Util.Onts.get(ontologyName)){
                String searchTerm = bw.getKey()+":";
                if(ln.indexOf(searchTerm)>0 && !foundVocabsAreas.contains(bw.getValue())) {
                    foundVocabsTerms.add(bw.getValue() + ":" + bw.getKey());
                    foundVocabsAreas.add(bw.getValue());
                    foundVocabsPrefixes.add(bw.getKey());
                }
            }
        }

        Set<String> fb = new HashSet<>(foundVocabsAreas);
        Set<String> ft = new HashSet<>(foundVocabsTerms);
        Set<String> fv = new HashSet<>(foundVocabsPrefixes);

        List<String> allTermsVocab =  new ArrayList<>();
        List<String> allTermsTags =  new ArrayList<>();
        List<String> allVocabPrefix =  new ArrayList<>();

        allTermsVocab.addAll(fb);
        allTermsTags.addAll(ft);
        allVocabPrefix.addAll(fv);

        Util.OntRep.put(ontologyName, allTermsVocab);
        Util.OntTagsVocabs.put(ontologyName, allTermsTags);
        Util.OntVocabPrefixes.put(ontologyName,allVocabPrefix );

        for (Map.Entry<String, String> bw: Util.BOW_Terms.entrySet()  ) {

            for(String ln : Util.Onts.get(ontologyName)){
                String searchTerm = ":" + bw.getKey();
                if(ln.indexOf(searchTerm)>0 && !foundVocabsAreas.contains(bw.getValue())) {
                    foundVocabsTerms.add(bw.getValue() + ":" + bw.getKey());
                }
            }
        }

        ft.addAll(foundVocabsTerms);
        allTermsTags.addAll(ft);
    }
}
