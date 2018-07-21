package com.ontology;

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
    private static List<String> Allvocabs = new ArrayList<>();
    private static List<String> gdprAreasPresent = new ArrayList<>();
    private static String folderName = "PHASE1_OUTPUT/";
    private static String foundVocab = "vocabFound";
    private static String foundGDPRAreas = "foundGDPRAreas";







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

        OntClass gdprPrinciple = ontModel.createClass(NAMESPACE+" GDPR_Principles");
        OntClass gdprActivity = ontModel.createClass(NAMESPACE+" GDPR_Activity");
        OntClass gdprData = ontModel.createClass(NAMESPACE+" GDPR_Data");

        gdprPrinciple.addLabel("GDPR Principles",null);
        gdprActivity.addLabel("GDPR Activity",null);
        gdprData.addLabel("GDPR Data",null);

        RDFList list  = ontModel.createList(new RDFNode[] {gdprPrinciple,gdprActivity,gdprData});



        OntClass gdprAreas = ontModel.createUnionClass(NAMESPACE+ "GDPR_Areas", list);
        gdprAreas.addLabel("GDPR Areas", null);
        gdprAreas.addComment("Contains List of affected GDPR Areas", null);

        gdprPrinciple.addSuperClass(gdprAreas);
        gdprActivity.addSuperClass(gdprAreas);
        gdprData.addSuperClass(gdprAreas);

        /*************************** GDPRArea Principles  ***************************/

        DatatypeProperty has_LFT_principle = ontModel.createDatatypeProperty(NAMESPACE+ "has_Lawfulness, Fairness & Transparency_principle");
        has_LFT_principle.setDomain(gdprPrinciple);
        has_LFT_principle.setRange(XSD.xboolean);

        DatatypeProperty has_Accuracy_principle = ontModel.createDatatypeProperty(NAMESPACE+ "has_Accuracy_principle");
        has_Accuracy_principle.setDomain(gdprPrinciple);
        has_Accuracy_principle.setRange(XSD.xboolean);


        DatatypeProperty has_IC_principle = ontModel.createDatatypeProperty(NAMESPACE+ "has_Integrity & Confidentiality_principle");
        has_IC_principle.setDomain(gdprPrinciple);
        has_IC_principle.setRange(XSD.xboolean);


        DatatypeProperty has_Data_Minimization_principle = ontModel.createDatatypeProperty(NAMESPACE+ "has_Data_Minimization_principle");
        has_Data_Minimization_principle.setDomain(gdprPrinciple);
        has_Data_Minimization_principle.setRange(XSD.xboolean);


        DatatypeProperty has_Storage_Limitation_principle = ontModel.createDatatypeProperty(NAMESPACE+ "has_Storage_Limitation_principle");
        has_Storage_Limitation_principle.setDomain(gdprPrinciple);
        has_Storage_Limitation_principle.setRange(XSD.xboolean);


        DatatypeProperty has_Accountability_principle = ontModel.createDatatypeProperty(NAMESPACE+ "has_Accountability_principle");
        has_Accountability_principle.setDomain(gdprPrinciple);
        has_Accountability_principle.setRange(XSD.xboolean);


        DatatypeProperty has_Purpose_Limitation_principle = ontModel.createDatatypeProperty(NAMESPACE+ "has_Purpose_Limitation_principle");
        has_Purpose_Limitation_principle.setDomain(gdprPrinciple);
        has_Purpose_Limitation_principle.setRange(XSD.xboolean);

        DatatypeProperty contains_GDPR_principles = ontModel.createDatatypeProperty(NAMESPACE + "contains_GDPR_principles");
        contains_GDPR_principles.setDomain(gdprPrinciple);
        contains_GDPR_principles.setRange(XSD.xboolean);



        /*************************** GDPRArea Activities  ***************************/

        DatatypeProperty has_Processing_activity = ontModel.createDatatypeProperty(NAMESPACE+ "has_Processing_activity");
        has_Processing_activity.setDomain(gdprActivity);
        has_Processing_activity.setRange(XSD.xboolean);


        DatatypeProperty has_Marketing_activity = ontModel.createDatatypeProperty(NAMESPACE+ "has_Marketing_activity");
        has_Marketing_activity.setDomain(gdprActivity);
        has_Marketing_activity.setRange(XSD.xboolean);


        DatatypeProperty has_Data_Activity_activity = ontModel.createDatatypeProperty(NAMESPACE+ "has_Data_Activity_activity");
        has_Data_Activity_activity.setDomain(gdprActivity);
        has_Data_Activity_activity.setRange(XSD.xboolean);


        DatatypeProperty has_Consent_activity = ontModel.createDatatypeProperty(NAMESPACE+ "has_Consent_activity");
        has_Consent_activity.setDomain(gdprActivity);
        has_Consent_activity.setRange(XSD.xboolean);


        DatatypeProperty has_Exercise_rights_activity = ontModel.createDatatypeProperty(NAMESPACE+ "has_Exercise_rights_activity");
        has_Exercise_rights_activity.setDomain(gdprActivity);
        has_Exercise_rights_activity.setRange(XSD.xboolean);


        DatatypeProperty has_Impact_Assessment_activity = ontModel.createDatatypeProperty(NAMESPACE+ "has_Impact_Assessment_activity");
        has_Impact_Assessment_activity.setDomain(gdprActivity);
        has_Impact_Assessment_activity.setRange(XSD.xboolean);


        DatatypeProperty has_Identification_of_Data_Subject_activity = ontModel.createDatatypeProperty(NAMESPACE+ "has_Identification_of_Data_Subject_activity");
        has_Identification_of_Data_Subject_activity.setDomain(gdprActivity);
        has_Identification_of_Data_Subject_activity.setRange(XSD.xboolean);


        DatatypeProperty has_Security_of_Personal_Data_activity = ontModel.createDatatypeProperty(NAMESPACE+ "has_Security_of_Personal_Data_activity");
        has_Security_of_Personal_Data_activity.setDomain(gdprActivity);
        has_Security_of_Personal_Data_activity.setRange(XSD.xboolean);


        DatatypeProperty has_Demonstrating_Consent_activity = ontModel.createDatatypeProperty(NAMESPACE+ "has_Demonstrating_Consent_activity");
        has_Demonstrating_Consent_activity.setDomain(gdprActivity);
        has_Demonstrating_Consent_activity.setRange(XSD.xboolean);


        DatatypeProperty has_Report_Data_Breach_activity = ontModel.createDatatypeProperty(NAMESPACE+ "has_Report_Data_Breach_activity");
        has_Report_Data_Breach_activity.setDomain(gdprActivity);
        has_Report_Data_Breach_activity.setRange(XSD.xboolean);

        DatatypeProperty contains_GDPR_activities = ontModel.createDatatypeProperty(NAMESPACE + "contains_GDPR_activities");
        contains_GDPR_activities.setDomain(gdprActivity);
        contains_GDPR_activities.setRange(XSD.xboolean);




        /*************************** GDPRArea Data  ***************************/


        DatatypeProperty has_Pseudo_anonymous_data = ontModel.createDatatypeProperty(NAMESPACE+ "has_Pseudo_anonymous_data");
        has_Pseudo_anonymous_data.setDomain(gdprData);
        has_Pseudo_anonymous_data.setRange(XSD.xboolean);


        DatatypeProperty has_Personal_data = ontModel.createDatatypeProperty(NAMESPACE+ "has_Personal_data");
        has_Personal_data.setDomain(gdprData);
        has_Personal_data.setRange(XSD.xboolean);


        DatatypeProperty has_Anonymous_data = ontModel.createDatatypeProperty(NAMESPACE+ "has_Anonymous_data");
        has_Anonymous_data.setDomain(gdprData);
        has_Anonymous_data.setRange(XSD.xboolean);


        DatatypeProperty contains_GDPR_data = ontModel.createDatatypeProperty(NAMESPACE + "contains_GDPR_data");
        contains_GDPR_data.setDomain(gdprData);
        contains_GDPR_data.setRange(XSD.xboolean);






        /*************************** Vocabs Class  ***************************/

        OntClass vocabs = ontModel.createUnionClass(NAMESPACE + "Vocab", null);
        vocabs.addLabel("Vocabs",null);
        vocabs.addComment("Contains the information of relevant Linked Open Vocabs", null);


        DatatypeProperty has_BiologyRelated_Vocab = ontModel.createDatatypeProperty(NAMESPACE+ "has_BiologyRelated_Vocab");
        has_BiologyRelated_Vocab.setDomain(vocabs);
        has_BiologyRelated_Vocab.setRange(XSD.xboolean);

        DatatypeProperty has_Environmental_Vocab = ontModel.createDatatypeProperty(NAMESPACE+ "has_Environmental_Vocab");
        has_Environmental_Vocab.setDomain(vocabs);
        has_Environmental_Vocab.setRange(XSD.xboolean);

        DatatypeProperty has_Geographical_Vocab = ontModel.createDatatypeProperty(NAMESPACE+ "has_Geographical_Vocab");
        has_Geographical_Vocab.setDomain(vocabs);
        has_Geographical_Vocab.setRange(XSD.xboolean);

        DatatypeProperty has_GovernmentRelated_Vocab = ontModel.createDatatypeProperty(NAMESPACE+ "has_GovernmentRelated_Vocab");
        has_GovernmentRelated_Vocab.setDomain(vocabs);
        has_GovernmentRelated_Vocab.setRange(XSD.xboolean);

        DatatypeProperty has_HealthRelated_Vocab = ontModel.createDatatypeProperty(NAMESPACE+ "has_HealthRelated_Vocab");
        has_HealthRelated_Vocab.setDomain(vocabs);
        has_HealthRelated_Vocab.setRange(XSD.xboolean);

        DatatypeProperty has_IndustrialRelated_Vocab = ontModel.createDatatypeProperty(NAMESPACE+ "has_IndustrialRelated_Vocab");
        has_IndustrialRelated_Vocab.setDomain(vocabs);
        has_IndustrialRelated_Vocab.setRange(XSD.xboolean);

        DatatypeProperty has_IoTRelated_Vocab = ontModel.createDatatypeProperty(NAMESPACE+ "has_IoTRelated_Vocab");
        has_IoTRelated_Vocab.setDomain(vocabs);
        has_IoTRelated_Vocab.setRange(XSD.xboolean);

        DatatypeProperty has_MetaDataRelated_Vocab = ontModel.createDatatypeProperty(NAMESPACE+ "has_MetaDataRelated_Vocab");
        has_MetaDataRelated_Vocab.setDomain(vocabs);
        has_MetaDataRelated_Vocab.setRange(XSD.xboolean);

        DatatypeProperty has_MethodsRelated_Vocab = ontModel.createDatatypeProperty(NAMESPACE+ "has_MethodsRelated_Vocab");
        has_MethodsRelated_Vocab.setDomain(vocabs);
        has_MethodsRelated_Vocab.setRange(XSD.xboolean);

        DatatypeProperty has_PeopleRelated_Vocab = ontModel.createDatatypeProperty(NAMESPACE+ "has_PeopleRelated_Vocab");
        has_PeopleRelated_Vocab.setDomain(vocabs);
        has_PeopleRelated_Vocab.setRange(XSD.xboolean);

        DatatypeProperty has_QualityRelated_Vocab = ontModel.createDatatypeProperty(NAMESPACE+ "has_QualityRelated_Vocab");
        has_QualityRelated_Vocab.setDomain(vocabs);
        has_QualityRelated_Vocab.setRange(XSD.xboolean);

        DatatypeProperty has_RDFRelated_Vocab = ontModel.createDatatypeProperty(NAMESPACE+ "has_RDFRelated_Vocab");
        has_RDFRelated_Vocab.setDomain(vocabs);
        has_RDFRelated_Vocab.setRange(XSD.xboolean);

        DatatypeProperty has_SecurityRelated_Vocab = ontModel.createDatatypeProperty(NAMESPACE+ "has_SecurityRelated_Vocab");
        has_SecurityRelated_Vocab.setDomain(vocabs);
        has_SecurityRelated_Vocab.setRange(XSD.xboolean);

        DatatypeProperty has_ServicesRelated_Vocab = ontModel.createDatatypeProperty(NAMESPACE+ "has_ServicesRelated_Vocab");
        has_ServicesRelated_Vocab.setDomain(vocabs);
        has_ServicesRelated_Vocab.setRange(XSD.xboolean);

        DatatypeProperty has_SocietyRelated_Vocab = ontModel.createDatatypeProperty(NAMESPACE+ "has_SocietyRelated_Vocab");
        has_SocietyRelated_Vocab.setDomain(vocabs);
        has_SocietyRelated_Vocab.setRange(XSD.xboolean);

        DatatypeProperty has_TimeRelated_Vocab = ontModel.createDatatypeProperty(NAMESPACE+ "has_TimeRelated_Vocab");
        has_TimeRelated_Vocab.setDomain(vocabs);
        has_TimeRelated_Vocab.setRange(XSD.xboolean);

        DatatypeProperty has_VocabularyRelated_Vocab = ontModel.createDatatypeProperty(NAMESPACE+ "has_VocabularyRelated_Vocab");
        has_VocabularyRelated_Vocab.setDomain(vocabs);
        has_VocabularyRelated_Vocab.setRange(XSD.xboolean);

        DatatypeProperty contains_Vocab = ontModel.createDatatypeProperty(NAMESPACE + " Contains Vocabularies");
        contains_Vocab.setDomain(vocabs);
        contains_Vocab.setRange(XSD.xboolean);


        /*************************** Adding Values to GDPR Area properties  ***************************/

        Individual anArea;
        anArea = gdprAreas.createIndividual();
        getpresentGDPRAreas();
        for(String g: gdprAreasPresent){
            switch(g){

                case "P1": anArea.addLiteral(has_LFT_principle, true);
                anArea.addLiteral(contains_GDPR_principles, true);
                break;

                case "P2": anArea.addLiteral(has_Accuracy_principle, true);
                anArea.addLiteral(contains_GDPR_principles, true);
                break;

                case "P3": anArea.addLiteral(has_IC_principle, true);
                anArea.addLiteral(contains_GDPR_principles, true);
                break;

                case "P4": anArea.addLiteral(has_Data_Minimization_principle, true);
                anArea.addLiteral(contains_GDPR_principles, true);
                break;

                case "P5": anArea.addLiteral(has_Storage_Limitation_principle, true);
                anArea.addLiteral(contains_GDPR_principles, true);
                break;

                case "P6": anArea.addLiteral(has_Accountability_principle, true);
                anArea.addLiteral(contains_GDPR_principles, true);
                break;

                case "P7": anArea.addLiteral(has_Purpose_Limitation_principle, true);
                anArea.addLiteral(contains_GDPR_principles, true);
                break;

                case "P8": anArea.addLiteral(has_Processing_activity, true);
                anArea.addLiteral(contains_GDPR_activities, true);
                break;

                case "1": anArea.addLiteral(has_Marketing_activity, true);
                anArea.addLiteral(contains_GDPR_activities, true);
                break;

                case "1": anArea.addLiteral(has_Data_Activity_activity, true);
                anArea.addLiteral(contains_GDPR_activities, true);
                break;

                case "1": anArea.addLiteral(has_Consent_activity, true);
                anArea.addLiteral(contains_GDPR_activities, true);
                break;

                case "1": anArea.addLiteral(has_Exercise_rights_activity, true);
                anArea.addLiteral(contains_GDPR_activities, true);
                break;

                case "1": anArea.addLiteral(has_Impact_Assessment_activity, true);
                anArea.addLiteral(contains_GDPR_activities, true);
                break;

                case "1": anArea.addLiteral(has_Identification_of_Data_Subject_activity, true);
                anArea.addLiteral(contains_GDPR_activities, true);
                break;

                case "1": anArea.addLiteral(has_Security_of_Personal_Data_activity, true);
                anArea.addLiteral(contains_GDPR_activities, true);
                break;

                case "1": anArea.addLiteral(has_Demonstrating_Consent_activity, true);
                anArea.addLiteral(contains_GDPR_activities, true);
                break;

                case "1": anArea.addLiteral(has_Report_Data_Breach_activity, true);
                anArea.addLiteral(contains_GDPR_activities, true);
                break;

                case "1": anArea.addLiteral(has_Pseudo_anonymous_data, true);
                anArea.addLiteral(contains_GDPR_data, true);
                break;

                case "1": anArea.addLiteral(has_Personal_data, true);
                anArea.addLiteral(contains_GDPR_data, true);
                break;

                case "1": anArea.addLiteral(has_Anonymous_data, true);
                anArea.addLiteral(contains_GDPR_data, true);
                break;



                default:
                    anArea.addLiteral(contains_GDPR_activities, true);
                    anArea.addLiteral(contains_GDPR_activities, true);
                    anArea.addLiteral(contains_GDPR_activities, true);
            }
        }






        /*************************** Adding Values to Vocab properties  ***************************/
        // getting the list of vocabs present

        Individual aVocab;
        aVocab  = vocabs.createIndividual();
        getpresentVocabs();
        for(String v: vocabsPresent){
            switch(v){

                case "1": aVocab.addLiteral(has_BiologyRelated_Vocab, true);
                    aVocab.addLiteral(contains_Vocab, true);
                    break;

                case "2": aVocab.addLiteral(has_Environmental_Vocab, true);
                    aVocab.addLiteral(contains_Vocab, true);
                    break;

                case "3": aVocab.addLiteral(has_Geographical_Vocab, true);
                    aVocab.addLiteral(contains_Vocab, true);
                    break;

                case "4": aVocab.addLiteral(has_GovernmentRelated_Vocab, true);
                    aVocab.addLiteral(contains_Vocab, true);
                    break;

                case "5": aVocab.addLiteral(has_HealthRelated_Vocab, true);
                    aVocab.addLiteral(contains_Vocab, true);
                    break;

                case "6": aVocab.addLiteral(has_IndustrialRelated_Vocab, true);
                    aVocab.addLiteral(contains_Vocab, true);
                    break;

                case "7": aVocab.addLiteral(has_IoTRelated_Vocab, true);
                    aVocab.addLiteral(contains_Vocab, true);
                    break;

                case "8": aVocab.addLiteral(has_MetaDataRelated_Vocab, true);
                    aVocab.addLiteral(contains_Vocab, true);
                    break;

                case "9": aVocab.addLiteral(has_MethodsRelated_Vocab, true);
                    aVocab.addLiteral(contains_Vocab, true);
                    break;

                case "10": aVocab.addLiteral(has_PeopleRelated_Vocab, true);
                    aVocab.addLiteral(contains_Vocab, true);
                    break;

                case "11": aVocab.addLiteral(has_QualityRelated_Vocab, true);
                    aVocab.addLiteral(contains_Vocab, true);
                    break;


                case "12": aVocab.addLiteral(has_RDFRelated_Vocab, true);
                    aVocab.addLiteral(contains_Vocab, true);
                    break;

                case "13": aVocab.addLiteral(has_SecurityRelated_Vocab, true);
                    aVocab.addLiteral(contains_Vocab, true);
                    break;

                case "14": aVocab.addLiteral(has_ServicesRelated_Vocab, true);
                    aVocab.addLiteral(contains_Vocab, true);
                    break;

                case "15": aVocab.addLiteral(has_SocietyRelated_Vocab, true);
                    aVocab.addLiteral(contains_Vocab, true);
                    break;

                case "16": aVocab.addLiteral(has_TimeRelated_Vocab, true);
                    aVocab.addLiteral(contains_Vocab, true);
                    break;

                case "17": aVocab.addLiteral(has_VocabularyRelated_Vocab, true);
                    aVocab.addLiteral(contains_Vocab, true);
                    break;

                default: aVocab.addLiteral(contains_Vocab, false);
            }
        }





        writeToFile(ontModel);









    }

    public static void getpresentVocabs() throws IOException {
        vocabsPresent = Files.readAllLines(Paths.get((folderName+foundVocab)));
    }

    private static void getpresentGDPRAreas() throws IOException {
        gdprAreasPresent = Files.readAllLines(Paths.get((folderName+foundGDPRAreas)));

    }


    public static void writeToFile(OntModel ontModel) {
        try {
            ontModel.write(new FileWriter(ONTOLOGY_PATH), "TURTLE");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
