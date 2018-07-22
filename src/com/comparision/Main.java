package com.comparision;


import java.io.IOException;
/**
1. Calls classes for Dataset Ontology Operations
2. Calls classes for Dataset Labels Operations
3. Prepares the Report of phase 1
4. Calls class to create the Ontology
 */

public class Main {

    public static void main(String[] args ) throws IOException {
        DatasetOntologyOperations.datasetOntologyOperations();
        DatasetLabelURIOperations.datasetLabelURIOperations();
        PreparePHASE1Report.preparePHASE1Report();
        CreateOntology.createOntology();
    }

}
