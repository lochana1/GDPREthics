package com.comparision;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CompareDocs {

    private static Map<String, String> BOW_Vocabs = new HashMap<>();
    private static Map<String, String> BOW_Terms = new HashMap<>();
    private static Map<String, List<String>> Onts = new HashMap<>();
    private static Map<String, List<String>> OntRep = new HashMap<>();
    private static Map<String, List<String>> OntTagsVocabs = new HashMap<>();
    private static Map<String, List<String>> GdprInVocab = new HashMap<>();


    private static List<String> OntLines = new ArrayList<>();
    private static List<String> vocabs = new ArrayList<>();
    private static List<String> bowFiles = new ArrayList<>();
    private static List<String> bowTagsFiles = new ArrayList<>();
    private static List<String> ontologyFiles = new ArrayList<>();
    private static final String bowFolder = "BAG_OF_WORDS/";
    private static final String ontFolder = "DATASET_ONTOLOGY/";
    private static final String gdprTagFolder = "GDPR_TAGS/";
    private static final String bowTagsFolder = "PROPERTY_TERMS/";




    public static void main(String [] args) throws IOException {
//        inputs: bag of words documents & Ontology document
//        store the bag of words - TERMS in a hashMap <Term, BowName>
//        store the bag of words - VOCABS in a hashMap <vocab, BowName>


//        Input Ontology hashMap(Ontology, <ArrayList<String>>)
//        Input Ontology parse every line and then check for BOW_Vocabs terms IndexOf(Ontology strings)

//        Output : 1. List of VOCABS Matched  2. List of Terms Matched 3.Ontology Name

//        file read & write

//        Gets the list of all BOW_Vocabs Files from the Bag of words Folder
        bowFiles = listFilesForFolder(new File(bowFolder));
        ontologyFiles = listFilesForFolder(new File(ontFolder));
        bowTagsFiles = listFilesForFolder(new File(bowTagsFolder));


        for (String v : bowFiles) {
            bagOfWordsVocab(bowFolder, v);
        }

        for (String v : bowTagsFiles) {
            bagOfWordsTerms(bowTagsFolder, v);
        }

        for (String oFile : ontologyFiles) {

            List<String> ontLines = Files.readAllLines(Paths.get((ontFolder+oFile)));

            String oFileName = oFile.substring(0,oFile.length()-4);
            Onts.put(oFileName,ontLines);

        }

        for(Map.Entry<String, List<String>> on : Onts.entrySet()){
            checkInOntology(on.getKey());
        }


        setGdprAreasInVocabs();
        for(Map.Entry<String, List<String>> on : OntRep.entrySet()){
            System.out.println(on.getKey()+" : "+ on.getValue());
            System.out.println("Found Matching Vocab Terms: "+ on.getValue().size());
            System.out.println("Found Corresponding GDPR Terms: "+ getGdprAreasInOntology(on.getKey()));
            int s = getGdprAreasInOntology(on.getKey()).size();
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
        for(Map.Entry<String,List<String>> en: Onts.entrySet() ){
//            System.out.println(en.getKey());
            for(String ln: en.getValue()) {
//                System.out.println(i + ":"+j+ " - :" + en.getKey() + " " + ln);
                j++;
            }
            i++;
        }

//        System.out.println(getAllTermsAndVocabsInOntology("disgenet_void"));



    }






//    Gets the list of files in the folder
    private static List<String> listFilesForFolder(File folder) {
        List<String> fls = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) {
                fls.add(fileEntry.getName());
//                System.out.println(fileEntry.getName());

        }
        return fls;
    }






//    reads from file and puts in the hashmap
    private static void bagOfWordsVocab(String folderName , String vocabPath )
            throws IOException {
        List<String> vocab1 = Files.readAllLines(Paths.get((folderName+vocabPath)));

        for(String ln: vocab1)
        {
            String vocabName = vocabPath.substring(0,vocabPath.length()-4);
            BOW_Vocabs.put(ln,vocabName);
        }

    }


    private static void bagOfWordsTerms(String folderName , String vocabPath )
            throws IOException {
        List<String> vocab1 = Files.readAllLines(Paths.get((folderName+vocabPath)));

        for(String ln: vocab1)
        {
            String termName = vocabPath.substring(0,vocabPath.length()-4);
            BOW_Terms.put(ln,termName);
        }

    }



    private static List<String> ontologyParsed(String folderName, String ontPath) throws IOException{
//        ontLines = Files.readAllLines(Paths.get((folderName+ontPath)));
        List<String> ont = Files.readAllLines(Paths.get((folderName+ontPath)));
        return ont;

    }





// Looping thru the dataset Ontology and checking if vocab is present or not
    private static void checkInOntology(String ontologyName){
        List<String> foundVocabs = new ArrayList<>();
        List<String> foundVocabsTerms = new ArrayList<>();

        for (Map.Entry<String, String> bw: BOW_Vocabs.entrySet()  ) {

//            for(Map.Entry<String, List<String>> on: Onts.entrySet()){

                for(String ln : Onts.get(ontologyName)){
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

        OntRep.put(ontologyName, allTermsVocab);
        OntTagsVocabs.put(ontologyName, allTermsTags);

        for (Map.Entry<String, String> bw: BOW_Terms.entrySet()  ) {

//            for(Map.Entry<String, List<String>> on: Onts.entrySet()){

            for(String ln : Onts.get(ontologyName)){
                String searchTerm = ":" + bw.getKey();
                if(ln.indexOf(searchTerm)>0 && !foundVocabs.contains(bw.getValue())) {
                    foundVocabsTerms.add(bw.getValue() + ":" + bw.getKey());
                }

            }

        }

        ft.addAll(foundVocabsTerms);
        allTermsTags.addAll(ft);


    }





    private static void setGdprAreasInVocabs() throws IOException {   //Tested

//        list of the vocab name areas
//        run thru this list and add vocab name and the list as the doc content

          List<String> VocabgdprFiles = listFilesForFolder(new File(gdprTagFolder));
          String gdprVocabTagName = "";
          for(String s : VocabgdprFiles){
              List<String> gdprTags = Files.readAllLines(Paths.get(gdprTagFolder+s));
              gdprVocabTagName = s.substring(0,s.length()-10);
              GdprInVocab.put(gdprVocabTagName, gdprTags);

          }

        }






    private static List<String> getGdprAreasInVocabs(String vocabAreaName){   // Tested
        List<String> areas = new ArrayList<>();
        areas = GdprInVocab.get(vocabAreaName);
        return areas;
        }







    private static List<String> getVocabListInOntology(String ontName){  //Tested
            List<String> VocabListInOnt = OntRep.get(ontName);
            return VocabListInOnt;

        }


        private static List<String> getAllTermsAndVocabsInOntology(String ontName){
            List<String> TermsListInOnt = OntRep.get(ontName);
            return TermsListInOnt;

        }




    private static List<String> getGdprAreasInOntology(String OntName){

            List<String> gdprAreas = new ArrayList<>();
            List<String> vocabList;
            List<String> VocabListInOnt = getVocabListInOntology(OntName);
            for(String vocabFile : VocabListInOnt){
                vocabList = getGdprAreasInVocabs(vocabFile);
                gdprAreas.addAll(vocabList);
            }
            Set<String> areasInOntology = new HashSet<>(gdprAreas);
            List<String> uniqueAreas = new ArrayList<>();
            uniqueAreas.addAll(areasInOntology);
            return uniqueAreas;


        }





}
