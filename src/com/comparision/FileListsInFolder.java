package com.comparision;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileListsInFolder {

    private File fodername;
//    FileListsInFolder(File fodername){
//
//        this.fodername = fodername;
//
//    }

    static List<String> listFilesForFolder(File fodername) {
        List<String> fls = new ArrayList<>();
        for (final File fileEntry : fodername.listFiles()) {
            fls.add(fileEntry.getName());
//                System.out.println(fileEntry.getName());

        }
        return fls;
    }
}
