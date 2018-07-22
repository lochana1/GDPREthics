package com.comparision;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/*
1. Reads from File to set the GDPR Areas to the Vocabulary Areas. This File has been prepared consulting an SME
2. Returns the List of GDPR Areas for a vocabulary area (BOW)
*/

public class GDPRAreasInVocab {


    static void setGdprAreasInVocabs() throws IOException {


        List<String> VocabgdprFiles = FileListsInFolder.listFilesForFolder(new File(Util.gdprTagFolder));
        String gdprVocabTagName = "";
        for(String s : VocabgdprFiles){
            List<String> gdprTags = Files.readAllLines(Paths.get(Util.gdprTagFolder+s));
            gdprVocabTagName = s.substring(0,s.length()-10);
            Util.GdprInVocab.put(gdprVocabTagName, gdprTags);
        }
    }




    static List<String> getGdprAreasInVocabs(String vocabAreaName){   // Tested
        List<String> areas = Util.GdprInVocab.get(vocabAreaName);
        return areas;
    }
}
