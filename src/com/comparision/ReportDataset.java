package com.comparision;

import java.util.List;

/*
 * Blueprint for creating the report
 */

public class ReportDataset implements Report{

    private String Name;
    private String Desc;
    private List<String> foundVocabs;
    private List<String> foundGdprAreas;

    public ReportDataset(){}

    public ReportDataset(String Name, String Desc, List<String> foundVocabs, List<String> foundGdprAreas ){
        this.Name = Name;
        this.Desc = Desc;
        this.foundVocabs = foundVocabs;
        this.foundGdprAreas = foundGdprAreas;
    }

    public String getDesc() {
        return Desc;
    }
    public void setDesc(String desc) {
        Desc = desc;
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
        return   "Name='" + Name + '\'' +
                ", \nDesc='" + Desc + '\'' +
                ", \nfoundVocabs=" + foundVocabs +
                ", \nfoundGdprAreas=" + foundGdprAreas +
                "}\n\n";
    }
}
