package com.comparision;


import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



 public class FetchVocabDetails {


     public static void getLOVVocabDetails(String vocab){
         String uri = Util.VOCAB_DETAILS_FROM_LOV + vocab;
         URI u = URI.create(uri);
         String file = Util.VOCAB_DETAILS_LOV + vocab;
         Path path = Paths.get(file);
         try(InputStream in= u.toURL().openStream()){
             Files.copy(in,path);


         }
         catch(Exception e){
             e.printStackTrace();
         }

     }

}


