package com.comparision;

import java.util.List;

public class ReportDataset implements Report{
    
//    OntologyName
    private String Name;
    private List<String> foundVocabs;
    private List<String> foundGdprAreas;


    public ReportDataset(){}

    public ReportDataset(String Name, List<String> foundVocabs, List<String> foundGdprAreas ){
        this.Name = Name;
        this.foundVocabs = foundVocabs;
        this.foundGdprAreas = foundGdprAreas;

    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
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

    @Override
    public String toString() {
        return  "\nName='" + Name + '\'' +
                ", \nFoundVocabs=" + foundVocabs +
                ", \nFoundGdprAreas=" + foundGdprAreas +
                "}\n\n";
    }
}
