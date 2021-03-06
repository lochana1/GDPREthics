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
        long start = System.currentTimeMillis();
        DatasetOntologyOperations.datasetOntologyOperations();
        DatasetLabelURIOperations.datasetLabelURIOperations();
        PrepareReportPhase1.preparePHASE1Report();
        Evaluation.evluateOntologyDatasets();
        CreateOntology.createOntology();

        QueryWindow window = new QueryWindow();
        window.launch();
        System.out.println("Total Run Time: "+(System.currentTimeMillis()-start));
    }
}
