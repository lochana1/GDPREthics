package com.comparision;

import java.io.IOException;

public class Main {

    public static void main(String[] args ) throws IOException {
        CompareEngine.compareEngine();
        PreparePHASE1Report.preparePHASE1Report();
        CreateOntology.createOntology();

    }

}
