package com.comparision;

import java.util.*;



// Looping thru the dataset Ontology and checking if vocab is present or not

public class SearchingDatasetOntology {


    static void checkInOntology(String ontologyName){
        List<String> foundVocabs = new ArrayList<>();
        List<String> foundVocabsTerms = new ArrayList<>();

        for (Map.Entry<String, String> bw: Util.BOW_Vocabs.entrySet()  ) {

            for(String ln : Util.Onts.get(ontologyName)){
                String searchTerm = bw.getKey()+":";
                if(ln.indexOf(searchTerm)>0 && !foundVocabs.contains(bw.getValue())) {
                    foundVocabsTerms.add(bw.getValue() + ":" + bw.getKey());
                    foundVocabs.add(bw.getValue());


                }

            }

        }
        Set<String> fb = new HashSet<>(foundVocabs);
        Set<String> ft = new HashSet<>(foundVocabsTerms);

        List<String> allTermsVocab =  new ArrayList<>();
        List<String> allTermsTags =  new ArrayList<>();

        allTermsVocab.addAll(fb);
        allTermsTags.addAll(ft);

        Util.OntRep.put(ontologyName, allTermsVocab);
        Util.OntTagsVocabs.put(ontologyName, allTermsTags);

        for (Map.Entry<String, String> bw: Util.BOW_Terms.entrySet()  ) {

            for(String ln : Util.Onts.get(ontologyName)){
                String searchTerm = ":" + bw.getKey();
                if(ln.indexOf(searchTerm)>0 && !foundVocabs.contains(bw.getValue())) {
                    foundVocabsTerms.add(bw.getValue() + ":" + bw.getKey());
                }

            }

        }

        ft.addAll(foundVocabsTerms);
        allTermsTags.addAll(ft);


    }
}
