package com.comparision;


import java.io.IOException;

public class Main {

    public static void main(String[] args ) throws IOException {
        DatasetOntologyOperations.datasetOntologyOperations();
        DatasetLabelURIOperations.datasetLabelURIOperations();
        PreparePHASE1Report.preparePHASE1Report();
        CreateOntology.createOntology();



    }

}
