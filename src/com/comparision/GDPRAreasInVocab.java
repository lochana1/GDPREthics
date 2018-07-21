package com.comparision;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GDPRAreasInVocab {


    static void setGdprAreasInVocabs() throws IOException {   //Tested

//        list of the vocab name areas
//        run thru this list and add vocab name and the list as the doc content

        List<String> VocabgdprFiles = FileListsInFolder.listFilesForFolder(new File(Util.gdprTagFolder));
        String gdprVocabTagName = "";
        for(String s : VocabgdprFiles){
            List<String> gdprTags = Files.readAllLines(Paths.get(Util.gdprTagFolder+s));
            gdprVocabTagName = s.substring(0,s.length()-10);
            Util.GdprInVocab.put(gdprVocabTagName, gdprTags);
        }
    }




    static List<String> getGdprAreasInVocabs(String vocabAreaName){   // Tested
        List<String> areas = new ArrayList<>();
        areas = Util.GdprInVocab.get(vocabAreaName);
        return areas;
    }
}
