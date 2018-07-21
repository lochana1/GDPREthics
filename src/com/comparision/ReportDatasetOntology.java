package com.comparision;

import java.util.List;

public class ReportDatasetOntology {
    private String ontologyName;
    private List<String> foundVocabs;
    private List<String> foundGdprAreas;


    public ReportDatasetOntology(){}

    public ReportDatasetOntology(String ontologyName, List<String> foundVocabs, List<String> foundGdprAreas ){
        this.ontologyName = ontologyName;
        this.foundVocabs = foundVocabs;
        this.foundGdprAreas = foundGdprAreas;

    }

    public String getOntologyName() {
        return ontologyName;
    }

    public void setOntologyName(String ontologyName) {
        this.ontologyName = ontologyName;
    }

    public List<String> getFoundVocabs() {
        return foundVocabs;
    }

    public void setFoundVocabs(List<String> foundVocabs) {
        this.foundVocabs = foundVocabs;
    }

    public List<String> getFoundGdprAreas() {
        return foundGdprAreas;
    }

    public void setFoundGdprAreas(List<String> foundGdprAreas) {
        this.foundGdprAreas = foundGdprAreas;
    }


}
