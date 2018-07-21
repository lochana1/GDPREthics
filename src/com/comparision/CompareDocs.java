package com.comparision;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CompareDocs {

    public static void main(String [] args) throws IOException {
//        inputs: bag of words documents & Ontology document
//        store the bag of words - TERMS in a hashMap <Term, BowName>
//        store the bag of words - VOCABS in a hashMap <vocab, BowName>


//        Input Ontology hashMap(Ontology, <ArrayList<String>>)
//        Input Ontology parse every line and then check for BOW_Vocabs terms IndexOf(Ontology strings)

//        Output : 1. List of VOCABS Matched  2. List of Terms Matched 3.Ontology Name

//        file read & write

//        Gets the list of all BOW_Vocabs Files from the Bag of words Folder
        Util.bowFiles = FileListsInFolder.listFilesForFolder(new File(Util.bowFolder));
        Util.ontologyFiles = FileListsInFolder.listFilesForFolder(new File(Util.ontFolder));
        Util.bowTagsFiles = FileListsInFolder.listFilesForFolder(new File(Util.bowTagsFolder));


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
            System.out.println(on.getKey()+" : "+ on.getValue());
            System.out.println("Found Matching Vocab Terms: "+ on.getValue().size());
            System.out.println("Found Corresponding GDPR Terms: "+ DataFromDatasetOntology.getGdprAreasInOntology(on.getKey()));
            int s = DataFromDatasetOntology.getGdprAreasInOntology(on.getKey()).size();
            s = s==1 ? 0 :s;
            System.out.println("Number of GDPR Terms: "+ s);
            System.out.println("\n\n");
        }


        int i = 1, j=1;

//        Just printing the terms & vocabularies
//        for(Map.Entry<String,String> mp : BOW_Vocabs.entrySet()){
//            System.out.println(i+" "+ mp.getKey()+" :"+ mp.getValue());
//            i++;
//        }

//        Just printing the lines in Ontologies
        for(Map.Entry<String,List<String>> en: Util.Onts.entrySet() ){
//            System.out.println(en.getKey());
            for(String ln: en.getValue()) {
//                System.out.println(i + ":"+j+ " - :" + en.getKey() + " " + ln);
                j++;
            }
            i++;
        }

//        System.out.println(getAllTermsAndVocabsInOntology("disgenet_void"));



    }



}
