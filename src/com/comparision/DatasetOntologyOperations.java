package com.comparision;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CompareEngine {

    public static void compareEngine() throws IOException {

        System.out.println("Starting Tool Engine....\n Process will take a few seconds....");

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





/******************** Preparing Report Object ************************************/
            ReportDataset rep = new ReportDataset();

            //            Adding Dataset ontology name
            rep.setName(on.getKey());
            //            Adding the found vocabularies
            rep.setFoundVocabs(on.getValue());
            //              Adding the corresponding GDPR Areas
            rep.setFoundGdprAreas(DataFromDatasetOntology.getGdprAreasInOntology(on.getKey()));
            Util.report.add(rep);

        }

/****************** Printing Report Object ************************************/

        for(ReportDataset r: Util.report){
            System.out.println(r.toString());
        }





    }



}
