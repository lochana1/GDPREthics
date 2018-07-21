package com.comparision;

import org.apache.jena.ontology.*;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFList;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.vocabulary.DCTerms;
import org.apache.jena.vocabulary.OWL2;
import org.apache.jena.vocabulary.XSD;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CreateOntology {

    private final static String BASE_URI = "http://www.gdpr-ethics/linked-datasets";
    private final static String NAMESPACE = BASE_URI +"#";
    private static final String ONTOLOGY_PATH = "";
    private static List<String> vocabsPresent = new ArrayList<>();

    private static List<String> GdprAreasPresent = new ArrayList<>();
    private static String folderName = "PHASE1_OUTPUT/";
    private static String foundVocab = "vocabFound";
    private static String foundGdprAreas = "foundGdprAreas";









    public static void createOntology() throws IOException{
        OntModel ontModel = ModelFactory.createOntologyModel();
        ontModel.setNsPrefix("base", NAMESPACE);

        Ontology ontology = ontModel.createOntology(BASE_URI);
        ontology.addLabel("Ethics Ontology", null);
        String comment = "Ontology to answer GDPR based ethics related questions";
        ontology.addComment(comment, null);

        ontology.addProperty(OWL2.versionInfo, "1.0.0");
        ontology.addProperty(DCTerms.contributor, "Ashish Lochan");
        ontology.addProperty(DCTerms.description, comment);
//
//
//        OntClass dataset = ontModel.createClass(NAMESPACE + "dataset");
//        dataset.addLabel("Dataset", null);
//        dataset.addComment("Dataset contains Ontology Description for a dataset", null);
//
//        DatatypeProperty hasPersonalData = ontModel.createDatatypeProperty(NAMESPACE + "hasPersonalData");
//        hasPersonalData.addLabel("personalData", null);
//        hasPersonalData.addComment("Has Personal Data", null);
//        hasPersonalData.setDomain(dataset);
////        hasPersonalData.setRange(boolean);
//
//
//        dataset.addSuperClass(ontModel.createCardinalityRestriction(null, hasPersonalData, 1));
//
//
//        writeToFile(ontModel);

        /*************************** GDPRArea Class and sub-class  ***************************/

        OntClass GdprPrinciple = ontModel.createClass(NAMESPACE+" GDPR_Principles");
        OntClass GdprActivity = ontModel.createClass(NAMESPACE+" GDPR_Activity");
        OntClass GdprData = ontModel.createClass(NAMESPACE+" GDPR_Data");

        GdprPrinciple.addLabel("GDPR Principles",null);
        GdprActivity.addLabel("GDPR Activity",null);
        GdprData.addLabel("GDPR Data",null);

        RDFList list  = ontModel.createList(new RDFNode[] {GdprPrinciple,GdprActivity,GdprData});



        OntClass GdprAreas = ontModel.createUnionClass(NAMESPACE+ "GDPR_Areas", list);
        GdprAreas.addLabel("GDPR Areas", null);
        GdprAreas.addComment("Contains List of affected GDPR Areas", null);

        GdprPrinciple.addSuperClass(GdprAreas);
        GdprActivity.addSuperClass(GdprAreas);
        GdprData.addSuperClass(GdprAreas);

        /*************************** GDPRArea Principles  ***************************/

        Util.hasLFTprinciple = ontModel.createDatatypeProperty(NAMESPACE+ "has_Lawfulness, Fairness & Transparency_principle");
        Util.hasLFTprinciple.setDomain(GdprPrinciple);
        Util.hasLFTprinciple.setRange(XSD.xboolean);

        Util.hasAccuracyprinciple = ontModel.createDatatypeProperty(NAMESPACE+ "hasAccuracyprinciple");
        Util.hasAccuracyprinciple.setDomain(GdprPrinciple);
        Util.hasAccuracyprinciple.setRange(XSD.xboolean);


        Util.hasICprinciple = ontModel.createDatatypeProperty(NAMESPACE+ "has_Integrity & Confidentiality_principle");
        Util.hasICprinciple.setDomain(GdprPrinciple);
        Util.hasICprinciple.setRange(XSD.xboolean);


        Util.hasDataMinimizationprinciple = ontModel.createDatatypeProperty(NAMESPACE+ "hasDataMinimizationprinciple");
        Util.hasDataMinimizationprinciple.setDomain(GdprPrinciple);
        Util.hasDataMinimizationprinciple.setRange(XSD.xboolean);


        Util.hasStorageLimitationprinciple = ontModel.createDatatypeProperty(NAMESPACE+ "hasStorageLimitationprinciple");
        Util.hasStorageLimitationprinciple.setDomain(GdprPrinciple);
        Util.hasStorageLimitationprinciple.setRange(XSD.xboolean);


        Util.hasAccountabilityprinciple = ontModel.createDatatypeProperty(NAMESPACE+ "hasAccountabilityprinciple");
        Util.hasAccountabilityprinciple.setDomain(GdprPrinciple);
        Util.hasAccountabilityprinciple.setRange(XSD.xboolean);


        Util.hasPurposeLimitationprinciple = ontModel.createDatatypeProperty(NAMESPACE+ "hasPurposeLimitationprinciple");
        Util.hasPurposeLimitationprinciple.setDomain(GdprPrinciple);
        Util.hasPurposeLimitationprinciple.setRange(XSD.xboolean);

        DatatypeProperty containsGdprPrinciples = ontModel.createDatatypeProperty(NAMESPACE + "containsGdprPrinciples");
        containsGdprPrinciples.setDomain(GdprPrinciple);
        containsGdprPrinciples.setRange(XSD.xboolean);



        /*************************** GDPRArea Activities  ***************************/

        Util.hasProcessingactivity = ontModel.createDatatypeProperty(NAMESPACE+ "hasProcessingactivity");
        Util.hasProcessingactivity.setDomain(GdprActivity);
        Util.hasProcessingactivity.setRange(XSD.xboolean);


        Util.hasMarketingactivity = ontModel.createDatatypeProperty(NAMESPACE+ "hasMarketingactivity");
        Util.hasMarketingactivity.setDomain(GdprActivity);
        Util.hasMarketingactivity.setRange(XSD.xboolean);


        Util.hasDataActivityactivity = ontModel.createDatatypeProperty(NAMESPACE+ "hasDataActivityactivity");
        Util.hasDataActivityactivity.setDomain(GdprActivity);
        Util.hasDataActivityactivity.setRange(XSD.xboolean);


        Util.hasConsentactivity = ontModel.createDatatypeProperty(NAMESPACE+ "hasConsentactivity");
        Util.hasConsentactivity.setDomain(GdprActivity);
        Util.hasConsentactivity.setRange(XSD.xboolean);


        Util.hasExerciserightsactivity = ontModel.createDatatypeProperty(NAMESPACE+ "hasExerciserightsactivity");
        Util.hasExerciserightsactivity.setDomain(GdprActivity);
        Util.hasExerciserightsactivity.setRange(XSD.xboolean);


        Util.hasImpactAssessmentactivity = ontModel.createDatatypeProperty(NAMESPACE+ "hasImpactAssessmentactivity");
        Util.hasImpactAssessmentactivity.setDomain(GdprActivity);
        Util.hasImpactAssessmentactivity.setRange(XSD.xboolean);


        Util.hasIdentificationofDataSubjectactivity = ontModel.createDatatypeProperty(NAMESPACE+ "hasIdentificationofDataSubjectactivity");
        Util.hasIdentificationofDataSubjectactivity.setDomain(GdprActivity);
        Util.hasIdentificationofDataSubjectactivity.setRange(XSD.xboolean);


        Util.hasSecurityofPersonalDataactivity = ontModel.createDatatypeProperty(NAMESPACE+ "hasSecurityofPersonalDataactivity");
        Util.hasSecurityofPersonalDataactivity.setDomain(GdprActivity);
        Util.hasSecurityofPersonalDataactivity.setRange(XSD.xboolean);


        Util.hasDemonstratingConsentactivity = ontModel.createDatatypeProperty(NAMESPACE+ "hasDemonstratingConsentactivity");
        Util.hasDemonstratingConsentactivity.setDomain(GdprActivity);
        Util.hasDemonstratingConsentactivity.setRange(XSD.xboolean);


        Util.hasReportDataBreachactivity = ontModel.createDatatypeProperty(NAMESPACE+ "hasReportDataBreachactivity");
        Util.hasReportDataBreachactivity.setDomain(GdprActivity);
        Util.hasReportDataBreachactivity.setRange(XSD.xboolean);

        DatatypeProperty containsGDPRactivities = ontModel.createDatatypeProperty(NAMESPACE + "containsGDPRactivities");
        containsGDPRactivities.setDomain(GdprActivity);
        containsGDPRactivities.setRange(XSD.xboolean);




        /*************************** GDPRArea Data  ***************************/


        Util.has_Pseudo_anonymous_data = ontModel.createDatatypeProperty(NAMESPACE+ "has_Pseudo_anonymous_data");
        Util.has_Pseudo_anonymous_data.setDomain(GdprData);
        Util.has_Pseudo_anonymous_data.setRange(XSD.xboolean);


        Util.has_Personal_data = ontModel.createDatatypeProperty(NAMESPACE+ "has_Personal_data");
        Util.has_Personal_data.setDomain(GdprData);
        Util.has_Personal_data.setRange(XSD.xboolean);


        Util.has_Anonymous_data = ontModel.createDatatypeProperty(NAMESPACE+ "has_Anonymous_data");
        Util.has_Anonymous_data.setDomain(GdprData);
        Util.has_Anonymous_data.setRange(XSD.xboolean);


        DatatypeProperty contains_GDPR_data = ontModel.createDatatypeProperty(NAMESPACE + "contains_GDPR_data");
        contains_GDPR_data.setDomain(GdprData);
        contains_GDPR_data.setRange(XSD.xboolean);






        /*************************** Vocabs Class  ***************************/

        OntClass Vocabs = ontModel.createUnionClass(NAMESPACE + "Vocab", null);
        Vocabs.addLabel("Vocabs",null);
        Vocabs.addComment("Contains the information of relevant Linked Open Vocabs", null);


        Util.hasBiologyRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE+ "hasBiologyRelatedVocab");
        Util.hasBiologyRelatedVocab.setDomain(Vocabs);
        Util.hasBiologyRelatedVocab.setRange(XSD.xboolean);

        Util.hasEnvironmentalVocab = ontModel.createDatatypeProperty(NAMESPACE+ "hasEnvironmentalVocab");
        Util.hasEnvironmentalVocab.setDomain(Vocabs);
        Util.hasEnvironmentalVocab.setRange(XSD.xboolean);

        Util.hasGeographicalVocab = ontModel.createDatatypeProperty(NAMESPACE+ "hasGeographicalVocab");
        Util.hasGeographicalVocab.setDomain(Vocabs);
        Util.hasGeographicalVocab.setRange(XSD.xboolean);

        Util.hasGovernmentRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE+ "hasGovernmentRelatedVocab");
        Util.hasGovernmentRelatedVocab.setDomain(Vocabs);
        Util.hasGovernmentRelatedVocab.setRange(XSD.xboolean);

        Util.hasHealthRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE+ "hasHealthRelatedVocab");
        Util.hasHealthRelatedVocab.setDomain(Vocabs);
        Util.hasHealthRelatedVocab.setRange(XSD.xboolean);

        Util.hasIndustrialRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE+ "hasIndustrialRelatedVocab");
        Util.hasIndustrialRelatedVocab.setDomain(Vocabs);
        Util.hasIndustrialRelatedVocab.setRange(XSD.xboolean);

        Util.hasIoTRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE+ "hasIoTRelatedVocab");
        Util.hasIoTRelatedVocab.setDomain(Vocabs);
        Util.hasIoTRelatedVocab.setRange(XSD.xboolean);

        Util.hasMetaDataRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE+ "hasMetaDataRelatedVocab");
        Util.hasMetaDataRelatedVocab.setDomain(Vocabs);
        Util.hasMetaDataRelatedVocab.setRange(XSD.xboolean);

        Util.hasMethodsRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE+ "hasMethodsRelatedVocab");
        Util.hasMethodsRelatedVocab.setDomain(Vocabs);
        Util.hasMethodsRelatedVocab.setRange(XSD.xboolean);

        Util.hasPeopleRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE+ "hasPeopleRelatedVocab");
        Util.hasPeopleRelatedVocab.setDomain(Vocabs);
        Util.hasPeopleRelatedVocab.setRange(XSD.xboolean);

        Util.hasQualityRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE+ "hasQualityRelatedVocab");
        Util.hasQualityRelatedVocab.setDomain(Vocabs);
        Util.hasQualityRelatedVocab.setRange(XSD.xboolean);

        Util.hasRDFRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE+ "hasRDFRelatedVocab");
        Util.hasRDFRelatedVocab.setDomain(Vocabs);
        Util.hasRDFRelatedVocab.setRange(XSD.xboolean);

        Util.hasSecurityRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE+ "hasSecurityRelatedVocab");
        Util.hasSecurityRelatedVocab.setDomain(Vocabs);
        Util.hasSecurityRelatedVocab.setRange(XSD.xboolean);

        Util.hasServicesRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE+ "hasServicesRelatedVocab");
        Util.hasServicesRelatedVocab.setDomain(Vocabs);
        Util.hasServicesRelatedVocab.setRange(XSD.xboolean);

        Util.hasSocietyRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE+ "hasSocietyRelatedVocab");
        Util.hasSocietyRelatedVocab.setDomain(Vocabs);
        Util.hasSocietyRelatedVocab.setRange(XSD.xboolean);

        Util.hasTimeRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE+ "Util.hasTimeRelatedVocab");
        Util.hasTimeRelatedVocab.setDomain(Vocabs);
        Util.hasTimeRelatedVocab.setRange(XSD.xboolean);

        Util.hasVocabularyRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE+ "hasVocabularyRelatedVocab");
        Util.hasVocabularyRelatedVocab.setDomain(Vocabs);
        Util.hasVocabularyRelatedVocab.setRange(XSD.xboolean);

        DatatypeProperty containsVocab = ontModel.createDatatypeProperty(NAMESPACE + " Contains Vocabularies");
        containsVocab.setDomain(Vocabs);
        containsVocab.setRange(XSD.xboolean);


        /*************************** Adding Values to GDPR Area properties  ***************************/

        Individual anArea;
        anArea = GdprAreas.createIndividual();
        getpresentGdprAreas();
        SetGDPRPropertyList.setGDPRPropertyList();

        for(String g: GdprAreasPresent){

            if(Util.AllGDPRProperty.containsKey(g)){

                anArea.addLiteral(Util.AllGDPRProperty.get(g), true);
                anArea.addLiteral(containsVocab, true);

            }

        }






        /*************************** Adding Values to Vocab properties  ***************************/
        // getting the list of Vocabs present

        Individual aVocab;
        aVocab  = Vocabs.createIndividual();
        getpresentVocabs();
        SetVocabPropertyList.setVocabPropertyList();

        for(String v: vocabsPresent){


            if(Util.AllVocabsProperty.containsKey(v)){

                aVocab.addLiteral(Util.AllVocabsProperty.get(v), true);
                aVocab.addLiteral(containsVocab, true);

            }


        }
        writeToFile(ontModel);

    }

    public static void getpresentVocabs() throws IOException {
        vocabsPresent = Files.readAllLines(Paths.get((folderName+foundVocab)));
    }

    private static void getpresentGdprAreas() throws IOException {
        GdprAreasPresent = Files.readAllLines(Paths.get((folderName+foundGdprAreas)));

    }


    public static void writeToFile(OntModel ontModel) {
        try {
            ontModel.write(new FileWriter(ONTOLOGY_PATH), "TURTLE");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
