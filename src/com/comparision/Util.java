package com.comparision;

import org.apache.jena.ontology.DatatypeProperty;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Util {

    //Contains a map of vocab prefix and associated Vocab Area
    static Map<String, String> BOW_Vocabs = new HashMap<>();

    //Contains a map of a term prefix and corresponding Topic tags...dogont:FailureStateValue=IOT_Tags
    static Map<String, String> BOW_Terms = new HashMap<>();

    //Contains a map of DatasetOntology Name and List on lines present in ontology file
    static Map<String, List<String>> Onts = new HashMap<>();

    //Contains a map of DatasetOntology Name and List of vocabs Areas present in the Ontology
    static Map<String, List<String>> OntRep = new HashMap<>();

   //Contains a map of DatasetOntology Name and List of vocabs prefixes present in the Ontology
    static Map<String, List<String>> OntVocabPrefixes = new HashMap<>();

    //Contains a map of vocab prefix and associated Vocab Area todo same as BOW_Vocabs
    static Map<String, String> PrefixesAndVocabBow = new HashMap<>();

    //Contains a map of DatasetOntology Name and List of VocabArea and Vocabs ([Vocab_People:foaf....)
    static Map<String, List<String>> OntTagsVocabs = new HashMap<>();

    //Contains a map of Vocab Area and associated list of GDPR Areas
    static Map<String, List<String>> GdprInVocab = new HashMap<>();

    static Map<String, Set<String>> VocabInDatasetLabel = new HashMap<>();

    //Contains a map of Dataset Label & Vocab areas
    static Map<String, Set<String>> VocabAreasInDatasetLabel = new HashMap<>();
    static Map<String, List<String>> GDPRAreasInDatasetLabel = new HashMap<>();


    static Set<String> BOWVocabs = new HashSet<>();
    static List<String> bowFiles = FileListsInFolder.listFilesForFolder(new File(Util.bowFolder));
    static List<String> bowTagsFiles = FileListsInFolder.listFilesForFolder(new File(Util.bowTagsFolder));
    static List<String> ontologyFiles = FileListsInFolder.listFilesForFolder(new File(Util.ontFolder));

    //Contains the list of the Dataset Label read from the file
    static List<String> DatasetLabelURIList = new ArrayList<>();


    static final String bowFolder = "Temp/BAG_OF_WORDS/";
    static final String ontFolder = "Temp/DATASET_ONTOLOGY/";
    static final String gdprTagFolder = "Temp/GDPR_TAGS/";
    static final String bowTagsFolder = "Temp/PROPERTY_TERMS/";
    static final String ONTOLOGY_PATH = "Temp/PHASE2_OUTPUT/gdpr-ethics.rdf";
    static final String VOCAB_DETAILS_LOV = "Temp/VOCAB_DETAILS_LOV/";
    static final Path REPORT_PATH = Paths.get("Temp/PHASE1_OUTPUT/report.txt");
    static final String VOCAB_DETAILS_FROM_LOV = "https://lov.linkeddata.es/dataset/lov/api/v2/vocabulary/info?vocab=";
    static final String DatasetLabelURIFile = "Temp/DATASET_LABELS/";
    static final String resource = "Temp/";

    // Vocab string and the data property
    static Map<String,DatatypeProperty> AllVocabsProperty = new HashMap<>();
    static Map<String,DatatypeProperty> GdprPrinciple = new HashMap<>();
    static Map<String,DatatypeProperty> GdprActivity = new HashMap<>();
    static Map<String,DatatypeProperty> GdprData = new HashMap<>();

    static List<ReportPhase1> report = new ArrayList<>();

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
    static DatatypeProperty hasPseudoAnonymousData;
    static DatatypeProperty hasPersonalData;
    static DatatypeProperty hasAnonymousData;

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
    static DatatypeProperty hasAgeRelatedVocab;
    static DatatypeProperty hasCellsRelatedVocab;
    static DatatypeProperty hasChildRelatedVocab;
    static DatatypeProperty hasDrugsRelatedVocab;
    static DatatypeProperty hasEmbryoRelatedVocab;
    static DatatypeProperty hasGeneRelatedVocab;
    static DatatypeProperty hasTissueRelatedVocab;
    static DatatypeProperty hasSexualRelatedVocab;
    static DatatypeProperty hasVocabularyRelatedVocab;

}
