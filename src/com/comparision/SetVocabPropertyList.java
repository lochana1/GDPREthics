package com.comparision;

/*
 * Sets the ontology data property for vocabularies
 */
public class SetVocabPropertyList {
    static void setVocabPropertyList(){
        Util.AllVocabsProperty.put("Vocab_Biology", Util.hasBiologyRelatedVocab);
        Util.AllVocabsProperty.put("Vocab_Environment", Util.hasEnvironmentalVocab);
        Util.AllVocabsProperty.put("Vocab_Geography",Util.hasGeographicalVocab);
        Util.AllVocabsProperty.put("Vocab_Government",Util.hasGovernmentRelatedVocab);
        Util.AllVocabsProperty.put("Vocab_Health",Util.hasHealthRelatedVocab);
        Util.AllVocabsProperty.put("Vocab_Industry",Util.hasIndustrialRelatedVocab);
        Util.AllVocabsProperty.put("Vocab_IoT",Util.hasIoTRelatedVocab);
        Util.AllVocabsProperty.put("Vocab_Metadata",Util.hasMetaDataRelatedVocab);
        Util.AllVocabsProperty.put("Vocab_Methods",Util.hasMethodsRelatedVocab);
        Util.AllVocabsProperty.put("Vocab_People",Util.hasPeopleRelatedVocab);
        Util.AllVocabsProperty.put("Vocab_Quality",Util.hasQualityRelatedVocab);
        Util.AllVocabsProperty.put("Vocab_RDF",Util.hasRDFRelatedVocab);
        Util.AllVocabsProperty.put("Vocab_Security",Util.hasSecurityRelatedVocab);
        Util.AllVocabsProperty.put("Vocab_Services",Util.hasServicesRelatedVocab);
        Util.AllVocabsProperty.put("Vocab_Society",Util.hasSocietyRelatedVocab);
        Util.AllVocabsProperty.put("Vocab_Time",Util.hasTimeRelatedVocab);
        Util.AllVocabsProperty.put("Vocab_Vocabularies",Util.hasVocabularyRelatedVocab);
    }
}

