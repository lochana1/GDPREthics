package com.comparision;

import org.apache.jena.ontology.DatatypeProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Util {

     static Map<String, String> BOW_Vocabs = new HashMap<>();
     static Map<String, String> BOW_Terms = new HashMap<>();
     static Map<String, List<String>> Onts = new HashMap<>();

    //Contains a map of DatasetOntology Name and List of vocabs present in the Ontology
     static Map<String, List<String>> OntRep = new HashMap<>();

     static Map<String, List<String>> OntTagsVocabs = new HashMap<>();
     static Map<String, List<String>> GdprInVocab = new HashMap<>();


     static List<String> OntLines = new ArrayList<>();
     static List<String> vocabs = new ArrayList<>();
     static List<String> bowFiles = new ArrayList<>();
     static List<String> bowTagsFiles = new ArrayList<>();
     static List<String> ontologyFiles = new ArrayList<>();


     static final String bowFolder = "BAG_OF_WORDS/";
     static final String ontFolder = "DATASET_ONTOLOGY/";
     static final String gdprTagFolder = "GDPR_TAGS/";
     static final String bowTagsFolder = "PROPERTY_TERMS/";



    static Map<String,DatatypeProperty> AllVocabsProperty = new HashMap<>();
    static Map<String,DatatypeProperty> AllGDPRProperty = new HashMap<>();

    static List<ReportDatasetOntology> report = new ArrayList<>();

    static DatatypeProperty hasLFTprinciple;
    static DatatypeProperty hasAccuracyprinciple;
    static DatatypeProperty hasICprinciple;
    static DatatypeProperty hasDataMinimizationprinciple;
    static DatatypeProperty hasStorageLimitationprinciple;
    static DatatypeProperty hasAccountabilityprinciple;
    static DatatypeProperty hasPurposeLimitationprinciple;
    static DatatypeProperty hasProcessingactivity;
    static DatatypeProperty hasMarketingactivity;
    static DatatypeProperty hasDataActivityactivity;
    static DatatypeProperty hasConsentactivity;
    static DatatypeProperty hasExerciserightsactivity;
    static DatatypeProperty hasImpactAssessmentactivity;
    static DatatypeProperty hasIdentificationofDataSubjectactivity;
    static DatatypeProperty hasSecurityofPersonalDataactivity;
    static DatatypeProperty hasDemonstratingConsentactivity;
    static DatatypeProperty hasReportDataBreachactivity;
    static DatatypeProperty has_Pseudo_anonymous_data;
    static DatatypeProperty has_Personal_data;
    static DatatypeProperty has_Anonymous_data;

    static DatatypeProperty hasBiologyRelatedVocab;
    static DatatypeProperty hasEnvironmentalVocab;
    static DatatypeProperty hasGeographicalVocab;
    static DatatypeProperty hasGovernmentRelatedVocab;
    static DatatypeProperty hasHealthRelatedVocab;
    static DatatypeProperty hasIndustrialRelatedVocab;
    static DatatypeProperty hasIoTRelatedVocab;
    static DatatypeProperty hasMetaDataRelatedVocab;
    static DatatypeProperty hasMethodsRelatedVocab;
    static DatatypeProperty hasPeopleRelatedVocab;
    static DatatypeProperty hasQualityRelatedVocab;
    static DatatypeProperty hasRDFRelatedVocab;
    static DatatypeProperty hasSecurityRelatedVocab;
    static DatatypeProperty hasServicesRelatedVocab;
    static DatatypeProperty hasSocietyRelatedVocab;
    static DatatypeProperty hasTimeRelatedVocab;
    static DatatypeProperty hasVocabularyRelatedVocab;








}
