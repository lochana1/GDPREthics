package com.comparision;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/*
 * This class extracts the items from the vocabularies in the Bag of words
 */

public class BagOfWords {

    static void bagOfWordsTerms(String folderName, String vocabPath)
            throws IOException {
        List<String> vocab1 = Files.readAllLines(Paths.get((folderName+vocabPath)));

        for(String ln: vocab1)
        {
            String termName = vocabPath.substring(0,vocabPath.length()-4);
            Util.BOW_Terms.put(ln,termName);
        }

    }


    static void bagOfWordsVocab(String folderName , String vocabPath )
            throws IOException {
        List<String> vocab1 = Files.readAllLines(Paths.get((folderName+vocabPath)));

        for(String ln: vocab1)
        {
            String vocabName = vocabPath.substring(0,vocabPath.length()-4);
            Util.BOW_Vocabs.put(ln,vocabName);
        }

    }




}
