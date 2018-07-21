package com.comparision;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Util {

     static Map<String, String> BOW_Vocabs = new HashMap<>();
     static Map<String, String> BOW_Terms = new HashMap<>();
     static Map<String, List<String>> Onts = new HashMap<>();
     static Map<String, List<String>> OntRep = new HashMap<>();
     static Map<String, List<String>> OntTagsVocabs = new HashMap<>();
     static Map<String, List<String>> GdprInVocab = new HashMap<>();


     static List<String> OntLines = new ArrayList<>();
     static List<String> vocabs = new ArrayList<>();
     static List<String> bowFiles = new ArrayList<>();
     static List<String> bowTagsFiles = new ArrayList<>();
     static List<String> ontologyFiles = new ArrayList<>();
     static final String bowFolder = "BAG_OF_WORDS/";
     static final String ontFolder = "DATASET_ONTOLOGY/";
     static final String gdprTagFolder = "GDPR_TAGS/";
     static final String bowTagsFolder = "PROPERTY_TERMS/";

}
