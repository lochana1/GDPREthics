package com.comparision;

import org.apache.jena.ontology.*;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFList;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.DCTerms;
import org.apache.jena.vocabulary.OWL2;
import org.apache.jena.vocabulary.XSD;

import java.io.FileWriter;
import java.io.IOException;


import static com.comparision.Util.*;


/*
 * This class creates the Ontology based on the Phase1 report output.
 * For Evaluation Mode: Comment out lines: 375-455 and Uncomment lines: 460-539
 * For Normal Application Mode: Comment out line: 460-539 and uncomment: 375-455
 */

public class CreateOntology {

    private final static String BASE_URI = "http://www.gdpr-ethics/linked-datasets";
    private final static String NAMESPACE = BASE_URI +"#";
    private final static String gdprtext = "http://purl.org/adaptcentre/openscience/ontologies/GDPRtEXT#";





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
        GdprAreas.addLabel("GDPRAreas", null);
        GdprAreas.addComment("Contains List of affected GDPR Areas", null);

        OntClass ExGdprAreas = ontModel.createUnionClass(NAMESPACE + "ExGdprAreas", list);
        GdprAreas.addLabel("ExGdprAreas", null);
        GdprAreas.addComment("Contains List of GDPR Areas from external link", null);

        GdprPrinciple.addSuperClass(GdprAreas);
        GdprActivity.addSuperClass(GdprAreas);
        GdprData.addSuperClass(GdprAreas);

        GdprPrinciple.addRDFType(GdprAreas);
        GdprActivity.addRDFType(GdprAreas);
        GdprData.addRDFType(GdprAreas);
        DatatypeProperty isRelatedToPrinciples = ontModel.createDatatypeProperty(NAMESPACE + "isRelatedToPrinciples");
        DatatypeProperty isRelatedToActivities = ontModel.createDatatypeProperty(NAMESPACE + "isRelatedToActivities");
        DatatypeProperty isRelatedToData = ontModel.createDatatypeProperty(NAMESPACE + "isRelateisRelatedToDatadTo");
        isRelatedToPrinciples.addLabel("Relation properties", null);
        isRelatedToActivities.addLabel("Relation properties", null);
        isRelatedToData.addLabel("Relation properties", null);

        Resource ExGdprPrinciple = ontModel.createResource(gdprtext + "Principle");
        Resource ExGdprData = ontModel.createResource(gdprtext + "Data");
        Resource ExGdprActivity = ontModel.createResource(gdprtext + "Activity");



        isRelatedToPrinciples.addDomain(GdprPrinciple);
        isRelatedToPrinciples.addRange(ExGdprPrinciple);

        isRelatedToActivities.addDomain(GdprData);
        isRelatedToActivities.addRange(ExGdprData);

        isRelatedToData.addDomain(GdprActivity);
        isRelatedToData.addRange(ExGdprActivity);



        OntClass Vocabs = ontModel.createUnionClass(NAMESPACE + "Vocab", null);
        Vocabs.addLabel("Vocabs", null);
        Vocabs.addComment("Contains the information of relevant Linked Open Vocabs", null);

        RDFList list2 = ontModel.createList(new RDFNode[]{Vocabs, GdprAreas});


        OntClass Dataset = ontModel.createUnionClass(NAMESPACE + "Dataset", list2);
        Dataset.addLabel("Dataset", null);
        Dataset.addComment("Dataset in Consideration", null);


        GdprAreas.addSuperClass(Dataset);
        Vocabs.addSuperClass(Dataset);
        Vocabs.addDisjointWith(GdprAreas);
        Vocabs.addRDFType(Dataset);


        /*************************** GDPRArea Principles  ***************************/

        DatatypeProperty containsGdprPrinciples = ontModel.createDatatypeProperty(NAMESPACE + "containsGDPRPrinciples");
        containsGdprPrinciples.setDomain(GdprPrinciple);
        containsGdprPrinciples.setRange(XSD.xboolean);
        containsGdprPrinciples.addLabel("hasLawfulnessFairness-containsGDPRPrinciples", null);

        hasLFTprinciple = ontModel.createDatatypeProperty(NAMESPACE + "hasLawfulnessFairness-Transparencyprinciple");
        hasLFTprinciple.setDomain(GdprPrinciple);
        hasLFTprinciple.setRange(XSD.xboolean);
        hasLFTprinciple.addSuperProperty(containsGdprPrinciples);
        hasLFTprinciple.addLabel("hasLawfulnessFairness-Transparencyprinciple", null);

        hasAccuracyprinciple = ontModel.createDatatypeProperty(NAMESPACE + "hasAccuracyprinciple");
        hasAccuracyprinciple.setDomain(GdprPrinciple);
        hasAccuracyprinciple.setRange(XSD.xboolean);
        hasAccuracyprinciple.addSuperProperty(containsGdprPrinciples);
        hasAccuracyprinciple.addLabel("hasAccuracyprinciple", null);

        hasICprinciple = ontModel.createDatatypeProperty(NAMESPACE + "hasIntegrity-Confidentialityprinciple");
        hasICprinciple.setDomain(GdprPrinciple);
        hasICprinciple.setRange(XSD.xboolean);
        hasICprinciple.addSuperProperty(containsGdprPrinciples);
        hasICprinciple.addLabel("hasICprinciple", null);

        hasDataMinimizationprinciple = ontModel.createDatatypeProperty(NAMESPACE + "hasDataMinimizationprinciple");
        hasDataMinimizationprinciple.setDomain(GdprPrinciple);
        hasDataMinimizationprinciple.setRange(XSD.xboolean);
        hasDataMinimizationprinciple.addSuperProperty(containsGdprPrinciples);
        hasDataMinimizationprinciple.addLabel("hasDataMinimizationprinciple", null);

        hasStorageLimitationprinciple = ontModel.createDatatypeProperty(NAMESPACE + "hasStorageLimitationprinciple");
        hasStorageLimitationprinciple.setDomain(GdprPrinciple);
        hasStorageLimitationprinciple.setRange(XSD.xboolean);
        hasStorageLimitationprinciple.addSuperProperty(containsGdprPrinciples);
        hasStorageLimitationprinciple.addLabel("hasStorageLimitationprinciple", null);


        hasAccountabilityprinciple = ontModel.createDatatypeProperty(NAMESPACE + "hasAccountabilityprinciple");
        hasAccountabilityprinciple.setDomain(GdprPrinciple);
        hasAccountabilityprinciple.setRange(XSD.xboolean);
        hasAccountabilityprinciple.addSuperProperty(containsGdprPrinciples);
        hasAccountabilityprinciple.addLabel("hasAccountabilityprinciple", null);


        hasPurposeLimitationprinciple = ontModel.createDatatypeProperty(NAMESPACE + "hasPurposeLimitationprinciple");
        hasPurposeLimitationprinciple.setDomain(GdprPrinciple);
        hasPurposeLimitationprinciple.setRange(XSD.xboolean);
        hasPurposeLimitationprinciple.addSuperProperty(containsGdprPrinciples);
        hasPurposeLimitationprinciple.addLabel("hasPurposeLimitationprinciple", null);


        /*************************** GDPRArea Activities  ***************************/

        DatatypeProperty containsGDPRactivities = ontModel.createDatatypeProperty(NAMESPACE + "containsGDPRactivities");
        containsGDPRactivities.setDomain(GdprActivity);
        containsGDPRactivities.setRange(XSD.xboolean);
        containsGDPRactivities.addLabel("containsGDPRactivities", null);

        hasProcessingactivity = ontModel.createDatatypeProperty(NAMESPACE + "hasProcessingactivity");
        hasProcessingactivity.setDomain(GdprActivity);
        hasProcessingactivity.setRange(XSD.xboolean);
        hasProcessingactivity.addSuperProperty(containsGDPRactivities);
        hasProcessingactivity.addLabel("hasProcessingactivity", null);



        hasMarketingactivity = ontModel.createDatatypeProperty(NAMESPACE + "hasMarketingactivity");
        hasMarketingactivity.setDomain(GdprActivity);
        hasMarketingactivity.setRange(XSD.xboolean);
        hasMarketingactivity.addSuperProperty(containsGDPRactivities);
        hasMarketingactivity.addLabel("hasMarketingactivity", null);


        hasDataActivityactivity = ontModel.createDatatypeProperty(NAMESPACE + "hasDataActivityactivity");
        hasDataActivityactivity.setDomain(GdprActivity);
        hasDataActivityactivity.setRange(XSD.xboolean);
        hasDataActivityactivity.addSuperProperty(containsGDPRactivities);
        hasDataActivityactivity.addLabel("hasDataActivityactivity", null);


        hasConsentactivity = ontModel.createDatatypeProperty(NAMESPACE + "hasConsentactivity");
        hasConsentactivity.setDomain(GdprActivity);
        hasConsentactivity.setRange(XSD.xboolean);
        hasConsentactivity.addSuperProperty(containsGDPRactivities);
        hasConsentactivity.addLabel("hasConsentactivity", null);


        hasExerciserightsactivity = ontModel.createDatatypeProperty(NAMESPACE + "hasExerciserightsactivity");
        hasExerciserightsactivity.setDomain(GdprActivity);
        hasExerciserightsactivity.setRange(XSD.xboolean);
        hasExerciserightsactivity.addSuperProperty(containsGDPRactivities);
        hasExerciserightsactivity.addLabel("hasExerciserightsactivity", null);


        hasImpactAssessmentactivity = ontModel.createDatatypeProperty(NAMESPACE + "hasImpactAssessmentactivity");
        hasImpactAssessmentactivity.setDomain(GdprActivity);
        hasImpactAssessmentactivity.setRange(XSD.xboolean);
        hasImpactAssessmentactivity.addSuperProperty(containsGDPRactivities);
        hasImpactAssessmentactivity.addLabel("hasImpactAssessmentactivity", null);


        hasIdentificationofDataSubjectactivity = ontModel.createDatatypeProperty(NAMESPACE + "hasIdentificationofDataSubjectactivity");
        hasIdentificationofDataSubjectactivity.setDomain(GdprActivity);
        hasIdentificationofDataSubjectactivity.setRange(XSD.xboolean);
        hasIdentificationofDataSubjectactivity.addSuperProperty(containsGDPRactivities);
        hasIdentificationofDataSubjectactivity.addLabel("hasIdentificationofDataSubjectactivity", null);


        hasSecurityofPersonalDataactivity = ontModel.createDatatypeProperty(NAMESPACE + "hasSecurityofPersonalDataactivity");
        hasSecurityofPersonalDataactivity.setDomain(GdprActivity);
        hasSecurityofPersonalDataactivity.setRange(XSD.xboolean);
        hasSecurityofPersonalDataactivity.addSuperProperty(containsGDPRactivities);
        hasSecurityofPersonalDataactivity.addLabel("hasSecurityofPersonalDataactivity", null);


        hasDemonstratingConsentactivity = ontModel.createDatatypeProperty(NAMESPACE + "hasDemonstratingConsentactivity");
        hasDemonstratingConsentactivity.setDomain(GdprActivity);
        hasDemonstratingConsentactivity.setRange(XSD.xboolean);
        hasDemonstratingConsentactivity.addSuperProperty(containsGDPRactivities);
        hasDemonstratingConsentactivity.addLabel("hasDemonstratingConsentactivity", null);


        hasReportDataBreachactivity = ontModel.createDatatypeProperty(NAMESPACE + "hasReportDataBreachactivity");
        hasReportDataBreachactivity.setDomain(GdprActivity);
        hasReportDataBreachactivity.setRange(XSD.xboolean);
        hasReportDataBreachactivity.addSuperProperty(containsGDPRactivities);
        hasReportDataBreachactivity.addLabel("hasReportDataBreachactivity", null);


        /*************************** GDPRArea Data  ***************************/

        DatatypeProperty containsGDPRData = ontModel.createDatatypeProperty(NAMESPACE + "containsGDPRData");
        containsGDPRData.setDomain(GdprData);
        containsGDPRData.setRange(XSD.xboolean);
        containsGDPRData.addLabel("containsGDPRData", null);


        hasPseudoAnonymousData = ontModel.createDatatypeProperty(NAMESPACE + "hasPseudoAnonymousData");
        hasPseudoAnonymousData.setDomain(GdprData);
        hasPseudoAnonymousData.setRange(XSD.xboolean);
        hasPseudoAnonymousData.addSuperProperty(containsGDPRData);
        hasPseudoAnonymousData.addLabel("hasPseudoAnonymousData", null);


        hasPersonalData = ontModel.createDatatypeProperty(NAMESPACE + "hasPersonalData");
        hasPersonalData.setDomain(GdprData);
        hasPersonalData.setRange(XSD.xboolean);
        hasPersonalData.addSuperProperty(containsGDPRData);
        hasPersonalData.addLabel("hasPersonalData", null);


        hasAnonymousData = ontModel.createDatatypeProperty(NAMESPACE + "hasAnonymousData");
        hasAnonymousData.setDomain(GdprData);
        hasAnonymousData.setRange(XSD.xboolean);
        hasAnonymousData.addSuperProperty(containsGDPRData);
        hasAnonymousData.addLabel("hasAnonymousData", null);


        /*************************** Vocabs Class  ***************************/

        DatatypeProperty containsVocab = ontModel.createDatatypeProperty(NAMESPACE + "containsVocabularies");
        containsVocab.setDomain(Vocabs);
        containsVocab.setRange(XSD.xboolean);
        containsVocab.addLabel("containsVocab", null);


        hasBiologyRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasBiologyRelatedVocab");
        hasBiologyRelatedVocab.setDomain(Vocabs);
        hasBiologyRelatedVocab.setRange(XSD.xboolean);
        hasBiologyRelatedVocab.addSuperProperty(containsVocab);
        hasBiologyRelatedVocab.addLabel("hasBiologyRelatedVocab", null);

        hasEnvironmentalVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasEnvironmentalVocab");
        hasEnvironmentalVocab.setDomain(Vocabs);
        hasEnvironmentalVocab.setRange(XSD.xboolean);
        hasEnvironmentalVocab.addSuperProperty(containsVocab);
        hasEnvironmentalVocab.addLabel("hasEnvironmentalVocab", null);

        hasGeographicalVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasGeographicalVocab");
        hasGeographicalVocab.setDomain(Vocabs);
        hasGeographicalVocab.setRange(XSD.xboolean);
        hasGeographicalVocab.addSuperProperty(containsVocab);
        hasGeographicalVocab.addLabel("hasGeographicalVocab", null);

        hasGovernmentRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasGovernmentRelatedVocab");
        hasGovernmentRelatedVocab.setDomain(Vocabs);
        hasGovernmentRelatedVocab.setRange(XSD.xboolean);
        hasGovernmentRelatedVocab.addSuperProperty(containsVocab);
        hasGovernmentRelatedVocab.addLabel("hasGovernmentRelatedVocab", null);

        hasHealthRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasHealthRelatedVocab");
        hasHealthRelatedVocab.setDomain(Vocabs);
        hasHealthRelatedVocab.setRange(XSD.xboolean);
        hasHealthRelatedVocab.addSuperProperty(containsVocab);
        hasHealthRelatedVocab.addLabel("hasHealthRelatedVocab", null);

        hasIndustrialRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasIndustrialRelatedVocab");
        hasIndustrialRelatedVocab.setDomain(Vocabs);
        hasIndustrialRelatedVocab.setRange(XSD.xboolean);
        hasIndustrialRelatedVocab.addSuperProperty(containsVocab);
        hasIndustrialRelatedVocab.addLabel("hasIndustrialRelatedVocab", null);

        hasIoTRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasIoTRelatedVocab");
        hasIoTRelatedVocab.setDomain(Vocabs);
        hasIoTRelatedVocab.setRange(XSD.xboolean);
        hasIoTRelatedVocab.addSuperProperty(containsVocab);
        hasIoTRelatedVocab.addLabel("hasIoTRelatedVocab", null);

        hasMetaDataRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasMetaDataRelatedVocab");
        hasMetaDataRelatedVocab.setDomain(Vocabs);
        hasMetaDataRelatedVocab.setRange(XSD.xboolean);
        hasMetaDataRelatedVocab.addSuperProperty(containsVocab);
        hasMetaDataRelatedVocab.addLabel("hasMetaDataRelatedVocab", null);

        hasMethodsRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasMethodsRelatedVocab");
        hasMethodsRelatedVocab.setDomain(Vocabs);
        hasMethodsRelatedVocab.setRange(XSD.xboolean);
        hasMethodsRelatedVocab.addSuperProperty(containsVocab);
        hasMethodsRelatedVocab.addLabel("hasMethodsRelatedVocab", null);

        hasPeopleRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasPeopleRelatedVocab");
        hasPeopleRelatedVocab.setDomain(Vocabs);
        hasPeopleRelatedVocab.setRange(XSD.xboolean);
        hasPeopleRelatedVocab.addSuperProperty(containsVocab);
        hasPeopleRelatedVocab.addLabel("hasPeopleRelatedVocab", null);

        hasQualityRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasQualityRelatedVocab");
        hasQualityRelatedVocab.setDomain(Vocabs);
        hasQualityRelatedVocab.setRange(XSD.xboolean);
        hasQualityRelatedVocab.addSuperProperty(containsVocab);
        hasQualityRelatedVocab.addLabel("hasQualityRelatedVocab", null);

        hasRDFRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasRDFRelatedVocab");
        hasRDFRelatedVocab.setDomain(Vocabs);
        hasRDFRelatedVocab.setRange(XSD.xboolean);
        hasRDFRelatedVocab.addSuperProperty(containsVocab);
        hasRDFRelatedVocab.addLabel("hasRDFRelatedVocab", null);

        hasSecurityRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasSecurityRelatedVocab");
        hasSecurityRelatedVocab.setDomain(Vocabs);
        hasSecurityRelatedVocab.setRange(XSD.xboolean);
        hasSecurityRelatedVocab.addSuperProperty(containsVocab);
        hasSecurityRelatedVocab.addLabel("hasSecurityRelatedVocab", null);

        hasServicesRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasServicesRelatedVocab");
        hasServicesRelatedVocab.setDomain(Vocabs);
        hasServicesRelatedVocab.setRange(XSD.xboolean);
        hasServicesRelatedVocab.addSuperProperty(containsVocab);
        hasServicesRelatedVocab.addLabel("hasServicesRelatedVocab", null);

        hasSocietyRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasSocietyRelatedVocab");
        hasSocietyRelatedVocab.setDomain(Vocabs);
        hasSocietyRelatedVocab.setRange(XSD.xboolean);
        hasSocietyRelatedVocab.addSuperProperty(containsVocab);
        hasSocietyRelatedVocab.addLabel("hasSocietyRelatedVocab", null);

        hasTimeRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasTimeRelatedVocab");
        hasTimeRelatedVocab.setDomain(Vocabs);
        hasTimeRelatedVocab.setRange(XSD.xboolean);
        hasTimeRelatedVocab.addSuperProperty(containsVocab);
        hasTimeRelatedVocab.addLabel("hasTimeRelatedVocab", null);


        hasAgeRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasAgeRelatedVocab");
        hasAgeRelatedVocab.setDomain(Vocabs);
        hasAgeRelatedVocab.setRange(XSD.xboolean);
        hasAgeRelatedVocab.addSuperProperty(containsVocab);
        hasAgeRelatedVocab.addLabel("hasAgeRelatedVocab", null);


        hasCellsRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasCellsRelatedVocab");
        hasCellsRelatedVocab.setDomain(Vocabs);
        hasCellsRelatedVocab.setRange(XSD.xboolean);
        hasCellsRelatedVocab.addSuperProperty(containsVocab);
        hasCellsRelatedVocab.addLabel("hasCellsRelatedVocab", null);


        hasChildRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasChildRelatedVocab");
        hasChildRelatedVocab.setDomain(Vocabs);
        hasChildRelatedVocab.setRange(XSD.xboolean);
        hasChildRelatedVocab.addSuperProperty(containsVocab);
        hasChildRelatedVocab.addLabel("hasChildRelatedVocab", null);


        hasDrugsRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasDrugsRelatedVocab");
        hasDrugsRelatedVocab.setDomain(Vocabs);
        hasDrugsRelatedVocab.setRange(XSD.xboolean);
        hasDrugsRelatedVocab.addSuperProperty(containsVocab);
        hasDrugsRelatedVocab.addLabel("hasDrugsRelatedVocab", null);


        hasEmbryoRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasEmbryoRelatedVocab");
        hasEmbryoRelatedVocab.setDomain(Vocabs);
        hasEmbryoRelatedVocab.setRange(XSD.xboolean);
        hasEmbryoRelatedVocab.addSuperProperty(containsVocab);
        hasEmbryoRelatedVocab.addLabel("hasEmbryoRelatedVocab", null);


        hasGeneRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasGeneRelatedVocab");
        hasGeneRelatedVocab.setDomain(Vocabs);
        hasGeneRelatedVocab.setRange(XSD.xboolean);
        hasGeneRelatedVocab.addSuperProperty(containsVocab);
        hasGeneRelatedVocab.addLabel("hasGeneRelatedVocab", null);


        hasTissueRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasTissueRelatedVocab");
        hasTissueRelatedVocab.setDomain(Vocabs);
        hasTissueRelatedVocab.setRange(XSD.xboolean);
        hasTissueRelatedVocab.addSuperProperty(containsVocab);
        hasTissueRelatedVocab.addLabel("hasTissueRelatedVocab", null);


        hasSexualRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasSexualRelatedVocab");
        hasSexualRelatedVocab.setDomain(Vocabs);
        hasSexualRelatedVocab.setRange(XSD.xboolean);
        hasSexualRelatedVocab.addSuperProperty(containsVocab);
        hasSexualRelatedVocab.addLabel("hasSexualRelatedVocab", null);

        hasVocabularyRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasVocabularyRelatedVocab");
        hasVocabularyRelatedVocab.setDomain(Vocabs);
        hasVocabularyRelatedVocab.setRange(XSD.xboolean);
        hasVocabularyRelatedVocab.addSuperProperty(containsVocab);
        hasVocabularyRelatedVocab.addLabel("hasVocabularyRelatedVocab", null);


        /*************************** Adding Data to the Newly created Ontology  ***************************/

        System.out.println("\n\n\nWriting to the Knowledge Base......");
        long start = System.currentTimeMillis();

        for (ReportPhase1 r : EvalReport) {


            /*************************** Adding Values classes & Subclasses  ***************************/
            Individual OntDataset;
            OntDataset = Dataset.createIndividual(NAMESPACE + r.getName());


            /*************************** Adding Values to GDPR Area properties  ***************************/

            //            Individual anArea;
            //            anArea = GdprAreas.createIndividual();
            SetGDPRPropertyList.setGDPRPropertyList();
            boolean gp = false, ga = false, gd = false;


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


                if (gp)
                    OntDataset.addOntClass(GdprPrinciple);
                if (ga)
                    OntDataset.addOntClass(GdprActivity);
                if (gd)
                    OntDataset.addOntClass(GdprData);

            }



            /*************************** Adding Values to Vocab properties- Non-Evaluation  ***************************/

            //            Individual aVocab;
            //            aVocab = Vocabs.createIndividual();
            SetVocabPropertyList.setVocabPropertyList();
//            System.out.println("VOCAB BUILDING: " +r.getFoundVocabsAreas());
//            System.out.println("AllVocabsProperty " + AllVocabsProperty);
            for (String v : r.getFoundVocabsAreas()) {
//                String vocabArea = v.substring(0,v.length()-4);  // used with evaluation

//                if (Util.AllVocabsProperty.containsKey(vocabArea)) {
                if (Util.AllVocabsProperty.containsKey(v)) {
//                    OntDataset.addLiteral(AllVocabsProperty.get(vocabArea), true);
                    OntDataset.addLiteral(AllVocabsProperty.get(v), true);
                    OntDataset.addLiteral(containsVocab, true);
                }
            }
            //            System.out.println(ontModel);
            writeToFile(ontModel);

        }
        System.out.println("\n\n\nOntology has been created now. Please check PHASE2_OUTPUT folder for the gdpr-ethics.rdf file");
        System.out.println("\n This is the NORMAL SECTION. Try Creating Ontology in Evaluation mode Other results");
        System.out.println("Time Taken: " + (System.currentTimeMillis() - start) + "ms");





//
//        /*************************** Adding Values to Vocab properties- Evaluation  ***************************/
//
//            //            Individual aVocab;
//            //            aVocab = Vocabs.createIndividual();
//            SetVocabPropertyList.setVocabPropertyList();
////            System.out.println("VOCAB BUILDING: " +r.getFoundVocabsAreas());
////            System.out.println("AllVocabsProperty " + AllVocabsProperty);
//            for (String v : r.getFoundVocabsAreas()) {
//                String vocabArea = v.substring(0,v.length()-4);  // used with evaluation
//
//                if (Util.AllVocabsProperty.containsKey(vocabArea)) {
////                if (Util.AllVocabsProperty.containsKey(v)) {
//                    OntDataset.addLiteral(AllVocabsProperty.get(vocabArea), true);
////                    OntDataset.addLiteral(AllVocabsProperty.get(v), true);
//                    OntDataset.addLiteral(containsVocab, true);
//                }
//            }
//            //            System.out.println(ontModel);
//            writeToFile(ontModel);
//
//        }
//        System.out.println("\n\n\nOntology has been created now. Please check PHASE2_OUTPUT folder for the gdpr-ethics.rdf file");
//        System.out.println("\n This is the EVALUATIONK SECTION. Try Creating Ontology in Normal mode for non- evaluation mode");
//        System.out.println("Time Taken: " + (System.currentTimeMillis() - start) + "ms");






    }





    public static void writeToFile(OntModel ontModel) {
        try {
            ontModel.write(new FileWriter(ONTOLOGY_PATH), "RDF/XML");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
