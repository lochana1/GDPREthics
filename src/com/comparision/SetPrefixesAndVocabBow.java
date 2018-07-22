package com.comparision;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/*
 * 1. Sets a map with key value as vocabulary prefix an Vocabulary Area(BOW)
 * 2. Returns the Vocabulary Area(BOW) for a particular vocabulary prefix
 */

public class SetPrefixesAndVocabBow {

    static void setVocabBowAndPrefixes() throws IOException {
        for(String bowFile: Util.bowFiles){
            List<String> bowprefixes = Files.readAllLines(Paths.get(Util.bowFolder+bowFile)) ;

            for(String prefix: Util.BOWVocabs){
                if(bowprefixes.contains(prefix)){
                    Util.PrefixesAndVocabBow.put(prefix,bowFile);
                }
            }
        }
    }

    static String getVocabBowAndPrefixes(String prefix){
        return Util.PrefixesAndVocabBow.get(prefix);
    }
}
