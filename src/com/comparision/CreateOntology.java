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


import static com.comparision.Util.*;


/*
 * This class creates the Ontology based on the Phase1 report output.
 */

public class CreateOntology {

    private final static String BASE_URI = "http://www.gdpr-ethics/linked-datasets";
    private final static String NAMESPACE = BASE_URI +"#";




    public static void createOntology() throws IOException {
        OntModel ontModel = ModelFactory.createOntologyModel();
        ontModel.setNsPrefix("base", NAMESPACE);

        Ontology ontology = ontModel.createOntology(BASE_URI);
        ontology.addLabel("Ethics Ontology", null);
        String comment = "Ontology to answer GDPR based ethics related questions";
        ontology.addComment(comment, null);

        ontology.addProperty(OWL2.versionInfo, "1.0.0");
        ontology.addProperty(DCTerms.contributor, "Ashish Lochan");
        ontology.addProperty(DCTerms.description, comment);


        /*************************** GDPRArea Class and sub-class  ***************************/

        OntClass GdprPrinciple = ontModel.createClass(NAMESPACE + "GDPR_Principles");
        OntClass GdprActivity = ontModel.createClass(NAMESPACE + "GDPR_Activity");
        OntClass GdprData = ontModel.createClass(NAMESPACE + "GDPR_Data");

        GdprPrinciple.addLabel("GDPRPrinciples", null);
        GdprActivity.addLabel("GDPRActivity", null);
        GdprData.addLabel("GDPRData", null);

        RDFList list = ontModel.createList(new RDFNode[]{GdprPrinciple, GdprActivity, GdprData});



        OntClass GdprAreas = ontModel.createUnionClass(NAMESPACE + "GDPR_Areas", list);
//        OntClass GdprAreas = ontModel.createClass(NAMESPACE + "GDPR_Areas");
        GdprAreas.addLabel("GDPRAreas", null);
        GdprAreas.addComment("Contains List of affected GDPR Areas", null);

        GdprPrinciple.addSuperClass(GdprAreas);
        GdprActivity.addSuperClass(GdprAreas);
        GdprData.addSuperClass(GdprAreas);

        GdprPrinciple.addRDFType(GdprAreas);
        GdprActivity.addRDFType(GdprAreas);
        GdprData.addRDFType(GdprAreas);



        OntClass Vocabs = ontModel.createUnionClass(NAMESPACE + "Vocab", null);
        Vocabs.addLabel("Vocabs", null);
        Vocabs.addComment("Contains the information of relevant Linked Open Vocabs", null);

        RDFList list2 = ontModel.createList(new RDFNode[]{Vocabs, GdprAreas});


        OntClass Dataset = ontModel.createUnionClass(NAMESPACE + "Dataset", list2);
        Dataset.addLabel("Dataset", null);
        Dataset.addComment("Dataset in Consideration", null);

        GdprAreas.addSuperClass(Dataset);
        Vocabs.addSuperClass(Dataset);
//        Vocabs.addRDFType(Dataset);






        /*************************** GDPRArea Principles  ***************************/

        DatatypeProperty containsGdprPrinciples = ontModel.createDatatypeProperty(NAMESPACE + "containsGDPRPrinciples");
        containsGdprPrinciples.setDomain(GdprPrinciple);
        containsGdprPrinciples.setRange(XSD.xboolean);

        hasLFTprinciple = ontModel.createDatatypeProperty(NAMESPACE + "hasLawfulnessFairness&Transparencyprinciple");
        hasLFTprinciple.setDomain(GdprPrinciple);
        hasLFTprinciple.setRange(XSD.xboolean);
        hasLFTprinciple.addSuperProperty(containsGdprPrinciples);

        hasAccuracyprinciple = ontModel.createDatatypeProperty(NAMESPACE + "hasAccuracyprinciple");
        hasAccuracyprinciple.setDomain(GdprPrinciple);
        hasAccuracyprinciple.setRange(XSD.xboolean);
        hasAccuracyprinciple.addSuperProperty(containsGdprPrinciples);

        hasICprinciple = ontModel.createDatatypeProperty(NAMESPACE + "hasIntegrity&Confidentialityprinciple");
        hasICprinciple.setDomain(GdprPrinciple);
        hasICprinciple.setRange(XSD.xboolean);
        hasICprinciple.addSuperProperty(containsGdprPrinciples);

        hasDataMinimizationprinciple = ontModel.createDatatypeProperty(NAMESPACE + "hasDataMinimizationprinciple");
        hasDataMinimizationprinciple.setDomain(GdprPrinciple);
        hasDataMinimizationprinciple.setRange(XSD.xboolean);
        hasDataMinimizationprinciple.addSuperProperty(containsGdprPrinciples);

        hasStorageLimitationprinciple = ontModel.createDatatypeProperty(NAMESPACE + "hasStorageLimitationprinciple");
        hasStorageLimitationprinciple.setDomain(GdprPrinciple);
        hasStorageLimitationprinciple.setRange(XSD.xboolean);
        hasStorageLimitationprinciple.addSuperProperty(containsGdprPrinciples);


        hasAccountabilityprinciple = ontModel.createDatatypeProperty(NAMESPACE + "hasAccountabilityprinciple");
        hasAccountabilityprinciple.setDomain(GdprPrinciple);
        hasAccountabilityprinciple.setRange(XSD.xboolean);
        hasAccountabilityprinciple.addSuperProperty(containsGdprPrinciples);


        hasPurposeLimitationprinciple = ontModel.createDatatypeProperty(NAMESPACE + "hasPurposeLimitationprinciple");
        hasPurposeLimitationprinciple.setDomain(GdprPrinciple);
        hasPurposeLimitationprinciple.setRange(XSD.xboolean);
        hasPurposeLimitationprinciple.addSuperProperty(containsGdprPrinciples);




        /*************************** GDPRArea Activities  ***************************/

        DatatypeProperty containsGDPRactivities = ontModel.createDatatypeProperty(NAMESPACE + "containsGDPRactivities");
        containsGDPRactivities.setDomain(GdprActivity);
        containsGDPRactivities.setRange(XSD.xboolean);

        hasProcessingactivity = ontModel.createDatatypeProperty(NAMESPACE + "hasProcessingactivity");
        hasProcessingactivity.setDomain(GdprActivity);
        hasProcessingactivity.setRange(XSD.xboolean);
        hasProcessingactivity.addSuperProperty(containsGDPRactivities);


        hasMarketingactivity = ontModel.createDatatypeProperty(NAMESPACE + "hasMarketingactivity");
        hasMarketingactivity.setDomain(GdprActivity);
        hasMarketingactivity.setRange(XSD.xboolean);
        hasMarketingactivity.addSuperProperty(containsGDPRactivities);


        hasDataActivityactivity = ontModel.createDatatypeProperty(NAMESPACE + "hasDataActivityactivity");
        hasDataActivityactivity.setDomain(GdprActivity);
        hasDataActivityactivity.setRange(XSD.xboolean);
        hasDataActivityactivity.addSuperProperty(containsGDPRactivities);


        hasConsentactivity = ontModel.createDatatypeProperty(NAMESPACE + "hasConsentactivity");
        hasConsentactivity.setDomain(GdprActivity);
        hasConsentactivity.setRange(XSD.xboolean);
        hasConsentactivity.addSuperProperty(containsGDPRactivities);


        hasExerciserightsactivity = ontModel.createDatatypeProperty(NAMESPACE + "hasExerciserightsactivity");
        hasExerciserightsactivity.setDomain(GdprActivity);
        hasExerciserightsactivity.setRange(XSD.xboolean);
        hasExerciserightsactivity.addSuperProperty(containsGDPRactivities);


        hasImpactAssessmentactivity = ontModel.createDatatypeProperty(NAMESPACE + "hasImpactAssessmentactivity");
        hasImpactAssessmentactivity.setDomain(GdprActivity);
        hasImpactAssessmentactivity.setRange(XSD.xboolean);
        hasImpactAssessmentactivity.addSuperProperty(containsGDPRactivities);


        hasIdentificationofDataSubjectactivity = ontModel.createDatatypeProperty(NAMESPACE + "hasIdentificationofDataSubjectactivity");
        hasIdentificationofDataSubjectactivity.setDomain(GdprActivity);
        hasIdentificationofDataSubjectactivity.setRange(XSD.xboolean);
        hasIdentificationofDataSubjectactivity.addSuperProperty(containsGDPRactivities);


        hasSecurityofPersonalDataactivity = ontModel.createDatatypeProperty(NAMESPACE + "hasSecurityofPersonalDataactivity");
        hasSecurityofPersonalDataactivity.setDomain(GdprActivity);
        hasSecurityofPersonalDataactivity.setRange(XSD.xboolean);
        hasSecurityofPersonalDataactivity.addSuperProperty(containsGDPRactivities);


        hasDemonstratingConsentactivity = ontModel.createDatatypeProperty(NAMESPACE + "hasDemonstratingConsentactivity");
        hasDemonstratingConsentactivity.setDomain(GdprActivity);
        hasDemonstratingConsentactivity.setRange(XSD.xboolean);
        hasDemonstratingConsentactivity.addSuperProperty(containsGDPRactivities);


        hasReportDataBreachactivity = ontModel.createDatatypeProperty(NAMESPACE + "hasReportDataBreachactivity");
        hasReportDataBreachactivity.setDomain(GdprActivity);
        hasReportDataBreachactivity.setRange(XSD.xboolean);
        hasReportDataBreachactivity.addSuperProperty(containsGDPRactivities);





        /*************************** GDPRArea Data  ***************************/

        DatatypeProperty containsGDPRData = ontModel.createDatatypeProperty(NAMESPACE + "containsGDPRData");
        containsGDPRData.setDomain(GdprData);
        containsGDPRData.setRange(XSD.xboolean);


        hasPseudoAnonymousData = ontModel.createDatatypeProperty(NAMESPACE + "hasPseudoAnonymousData");
        hasPseudoAnonymousData.setDomain(GdprData);
        hasPseudoAnonymousData.setRange(XSD.xboolean);
        hasPseudoAnonymousData.addSuperProperty(containsGDPRData);


        hasPersonalData = ontModel.createDatatypeProperty(NAMESPACE + "hasPersonalData");
        hasPersonalData.setDomain(GdprData);
        hasPersonalData.setRange(XSD.xboolean);
        hasPersonalData.addSuperProperty(containsGDPRData);


        hasAnonymousData = ontModel.createDatatypeProperty(NAMESPACE + "hasAnonymousData");
        hasAnonymousData.setDomain(GdprData);
        hasAnonymousData.setRange(XSD.xboolean);
        hasAnonymousData.addSuperProperty(containsGDPRData);


        /*************************** Vocabs Class  ***************************/

        DatatypeProperty containsVocab = ontModel.createDatatypeProperty(NAMESPACE + "containsVocabularies");
        containsVocab.setDomain(Vocabs);
        containsVocab.setRange(XSD.xboolean);


        hasBiologyRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasBiologyRelatedVocab");
        hasBiologyRelatedVocab.setDomain(Vocabs);
        hasBiologyRelatedVocab.setRange(XSD.xboolean);
        hasBiologyRelatedVocab.addSuperProperty(containsVocab);

        hasEnvironmentalVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasEnvironmentalVocab");
        hasEnvironmentalVocab.setDomain(Vocabs);
        hasEnvironmentalVocab.setRange(XSD.xboolean);
        hasEnvironmentalVocab.addSuperProperty(containsVocab);

        hasGeographicalVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasGeographicalVocab");
        hasGeographicalVocab.setDomain(Vocabs);
        hasGeographicalVocab.setRange(XSD.xboolean);
        hasGeographicalVocab.addSuperProperty(containsVocab);

        hasGovernmentRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasGovernmentRelatedVocab");
        hasGovernmentRelatedVocab.setDomain(Vocabs);
        hasGovernmentRelatedVocab.setRange(XSD.xboolean);
        hasGovernmentRelatedVocab.addSuperProperty(containsVocab);

        hasHealthRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasHealthRelatedVocab");
        hasHealthRelatedVocab.setDomain(Vocabs);
        hasHealthRelatedVocab.setRange(XSD.xboolean);
        hasHealthRelatedVocab.addSuperProperty(containsVocab);

        hasIndustrialRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasIndustrialRelatedVocab");
        hasIndustrialRelatedVocab.setDomain(Vocabs);
        hasIndustrialRelatedVocab.setRange(XSD.xboolean);
        hasIndustrialRelatedVocab.addSuperProperty(containsVocab);

        hasIoTRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasIoTRelatedVocab");
        hasIoTRelatedVocab.setDomain(Vocabs);
        hasIoTRelatedVocab.setRange(XSD.xboolean);
        hasIoTRelatedVocab.addSuperProperty(containsVocab);

        hasMetaDataRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasMetaDataRelatedVocab");
        hasMetaDataRelatedVocab.setDomain(Vocabs);
        hasMetaDataRelatedVocab.setRange(XSD.xboolean);
        hasMetaDataRelatedVocab.addSuperProperty(containsVocab);

        hasMethodsRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasMethodsRelatedVocab");
        hasMethodsRelatedVocab.setDomain(Vocabs);
        hasMethodsRelatedVocab.setRange(XSD.xboolean);
        hasMethodsRelatedVocab.addSuperProperty(containsVocab);

        hasPeopleRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasPeopleRelatedVocab");
        hasPeopleRelatedVocab.setDomain(Vocabs);
        hasPeopleRelatedVocab.setRange(XSD.xboolean);
        hasPeopleRelatedVocab.addSuperProperty(containsVocab);

        hasQualityRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasQualityRelatedVocab");
        hasQualityRelatedVocab.setDomain(Vocabs);
        hasQualityRelatedVocab.setRange(XSD.xboolean);
        hasQualityRelatedVocab.addSuperProperty(containsVocab);

        hasRDFRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasRDFRelatedVocab");
        hasRDFRelatedVocab.setDomain(Vocabs);
        hasRDFRelatedVocab.setRange(XSD.xboolean);
        hasRDFRelatedVocab.addSuperProperty(containsVocab);

        hasSecurityRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasSecurityRelatedVocab");
        hasSecurityRelatedVocab.setDomain(Vocabs);
        hasSecurityRelatedVocab.setRange(XSD.xboolean);
        hasSecurityRelatedVocab.addSuperProperty(containsVocab);

        hasServicesRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasServicesRelatedVocab");
        hasServicesRelatedVocab.setDomain(Vocabs);
        hasServicesRelatedVocab.setRange(XSD.xboolean);
        hasServicesRelatedVocab.addSuperProperty(containsVocab);

        hasSocietyRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasSocietyRelatedVocab");
        hasSocietyRelatedVocab.setDomain(Vocabs);
        hasSocietyRelatedVocab.setRange(XSD.xboolean);
        hasSocietyRelatedVocab.addSuperProperty(containsVocab);

        hasTimeRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasTimeRelatedVocab");
        hasTimeRelatedVocab.setDomain(Vocabs);
        hasTimeRelatedVocab.setRange(XSD.xboolean);
        hasTimeRelatedVocab.addSuperProperty(containsVocab);

        hasVocabularyRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasVocabularyRelatedVocab");
        hasVocabularyRelatedVocab.setDomain(Vocabs);
        hasVocabularyRelatedVocab.setRange(XSD.xboolean);
        hasVocabularyRelatedVocab.addSuperProperty(containsVocab);





        /*************************** Adding Data to the Newly created Ontology  ***************************/

        System.out.println("\n\n\nWriting to the Knowledge Base......");

        for (ReportPhase1 r: report) {


        /*************************** Adding Values classes & Subclasses  ***************************/
        Individual OntDataset;
        OntDataset = Dataset.createIndividual(NAMESPACE + r.getName());



        /*************************** Adding Values to GDPR Area properties  ***************************/

        //            Individual anArea;
        //            anArea = GdprAreas.createIndividual();
            SetGDPRPropertyList.setGDPRPropertyList();
            boolean gp=false,ga=false,gd=false;


            for (String g : r.getFoundGdprAreas()) {

                if (Util.GdprPrinciple.containsKey(g)) {
                    OntDataset.addLiteral(Util.GdprPrinciple.get(g), true);
                    OntDataset.addLiteral(containsGdprPrinciples, true);
                    gp = true;
                }

                if (Util.GdprActivity.containsKey(g)) {
                    OntDataset.addLiteral(Util.GdprActivity.get(g), true);
                    OntDataset.addLiteral(containsGDPRactivities, true);
                    ga = true;
                }

                if (Util.GdprData.containsKey(g)) {
                    OntDataset.addLiteral(Util.GdprData.get(g), true);
                    OntDataset.addLiteral(containsGDPRData, true);
                    gd = true;
                }


                if(gp)
                    OntDataset.addOntClass(GdprPrinciple);
                if(ga)
                    OntDataset.addOntClass(GdprActivity);
                if(gd)
                    OntDataset.addOntClass(GdprData);

            }


        /*************************** Adding Values to Vocab properties  ***************************/

        //            Individual aVocab;
        //            aVocab = Vocabs.createIndividual();
            SetVocabPropertyList.setVocabPropertyList();
            System.out.println("VOCAB BUILDING: " +r.getFoundVocabsAreas());
            System.out.println("AllVocabsProperty " + AllVocabsProperty);
            for (String v : r.getFoundVocabsAreas()) {



                if (AllVocabsProperty.containsKey(v)) {
                    OntDataset.addLiteral(AllVocabsProperty.get(v), true);
                    OntDataset.addLiteral(containsVocab, true);
                }
            }
        //            System.out.println(ontModel);
            writeToFile(ontModel);

        }
        System.out.println("\n\n\nOntology has been created now. Please check PHASE2_OUTPUT folder for the gdpr-ethics.rdf file");
    }


    public static void writeToFile(OntModel ontModel) {
        try {
            ontModel.write(new FileWriter(ONTOLOGY_PATH), "RDF/XML");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
