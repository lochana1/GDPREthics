package com.comparision;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileListsInFolder {

    static List<String> listFilesForFolder(File foldername) {
        List<String> fls = new ArrayList<>();
        for (final File fileEntry : foldername.listFiles()) {
            fls.add(fileEntry.getName());
        }
        return fls;
    }
}
