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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

        GdprPrinciple.addLabel("GDPR Principles", null);
        GdprActivity.addLabel("GDPR Activity", null);
        GdprData.addLabel("GDPR Data", null);

        RDFList list = ontModel.createList(new RDFNode[]{GdprPrinciple, GdprActivity, GdprData});


        OntClass GdprAreas = ontModel.createUnionClass(NAMESPACE + "GDPR_Areas", list);
        GdprAreas.addLabel("GDPR Areas", null);
        GdprAreas.addComment("Contains List of affected GDPR Areas", null);

        GdprPrinciple.addSuperClass(GdprAreas);
        GdprActivity.addSuperClass(GdprAreas);
        GdprData.addSuperClass(GdprAreas);

        /*************************** GDPRArea Principles  ***************************/

        Util.hasLFTprinciple = ontModel.createDatatypeProperty(NAMESPACE + "hasLawfulnessFairness&Transparencyprinciple");
        Util.hasLFTprinciple.setDomain(GdprPrinciple);
        Util.hasLFTprinciple.setRange(XSD.xboolean);

        Util.hasAccuracyprinciple = ontModel.createDatatypeProperty(NAMESPACE + "hasAccuracyprinciple");
        Util.hasAccuracyprinciple.setDomain(GdprPrinciple);
        Util.hasAccuracyprinciple.setRange(XSD.xboolean);


        Util.hasICprinciple = ontModel.createDatatypeProperty(NAMESPACE + "hasIntegrity&Confidentialityprinciple");
        Util.hasICprinciple.setDomain(GdprPrinciple);
        Util.hasICprinciple.setRange(XSD.xboolean);


        Util.hasDataMinimizationprinciple = ontModel.createDatatypeProperty(NAMESPACE + "hasDataMinimizationprinciple");
        Util.hasDataMinimizationprinciple.setDomain(GdprPrinciple);
        Util.hasDataMinimizationprinciple.setRange(XSD.xboolean);


        Util.hasStorageLimitationprinciple = ontModel.createDatatypeProperty(NAMESPACE + "hasStorageLimitationprinciple");
        Util.hasStorageLimitationprinciple.setDomain(GdprPrinciple);
        Util.hasStorageLimitationprinciple.setRange(XSD.xboolean);


        Util.hasAccountabilityprinciple = ontModel.createDatatypeProperty(NAMESPACE + "hasAccountabilityprinciple");
        Util.hasAccountabilityprinciple.setDomain(GdprPrinciple);
        Util.hasAccountabilityprinciple.setRange(XSD.xboolean);


        Util.hasPurposeLimitationprinciple = ontModel.createDatatypeProperty(NAMESPACE + "hasPurposeLimitationprinciple");
        Util.hasPurposeLimitationprinciple.setDomain(GdprPrinciple);
        Util.hasPurposeLimitationprinciple.setRange(XSD.xboolean);

        DatatypeProperty containsGdprPrinciples = ontModel.createDatatypeProperty(NAMESPACE + "containsGdprPrinciples");
        containsGdprPrinciples.setDomain(GdprPrinciple);
        containsGdprPrinciples.setRange(XSD.xboolean);


        /*************************** GDPRArea Activities  ***************************/

        Util.hasProcessingactivity = ontModel.createDatatypeProperty(NAMESPACE + "hasProcessingactivity");
        Util.hasProcessingactivity.setDomain(GdprActivity);
        Util.hasProcessingactivity.setRange(XSD.xboolean);


        Util.hasMarketingactivity = ontModel.createDatatypeProperty(NAMESPACE + "hasMarketingactivity");
        Util.hasMarketingactivity.setDomain(GdprActivity);
        Util.hasMarketingactivity.setRange(XSD.xboolean);


        Util.hasDataActivityactivity = ontModel.createDatatypeProperty(NAMESPACE + "hasDataActivityactivity");
        Util.hasDataActivityactivity.setDomain(GdprActivity);
        Util.hasDataActivityactivity.setRange(XSD.xboolean);


        Util.hasConsentactivity = ontModel.createDatatypeProperty(NAMESPACE + "hasConsentactivity");
        Util.hasConsentactivity.setDomain(GdprActivity);
        Util.hasConsentactivity.setRange(XSD.xboolean);


        Util.hasExerciserightsactivity = ontModel.createDatatypeProperty(NAMESPACE + "hasExerciserightsactivity");
        Util.hasExerciserightsactivity.setDomain(GdprActivity);
        Util.hasExerciserightsactivity.setRange(XSD.xboolean);


        Util.hasImpactAssessmentactivity = ontModel.createDatatypeProperty(NAMESPACE + "hasImpactAssessmentactivity");
        Util.hasImpactAssessmentactivity.setDomain(GdprActivity);
        Util.hasImpactAssessmentactivity.setRange(XSD.xboolean);


        Util.hasIdentificationofDataSubjectactivity = ontModel.createDatatypeProperty(NAMESPACE + "hasIdentificationofDataSubjectactivity");
        Util.hasIdentificationofDataSubjectactivity.setDomain(GdprActivity);
        Util.hasIdentificationofDataSubjectactivity.setRange(XSD.xboolean);


        Util.hasSecurityofPersonalDataactivity = ontModel.createDatatypeProperty(NAMESPACE + "hasSecurityofPersonalDataactivity");
        Util.hasSecurityofPersonalDataactivity.setDomain(GdprActivity);
        Util.hasSecurityofPersonalDataactivity.setRange(XSD.xboolean);


        Util.hasDemonstratingConsentactivity = ontModel.createDatatypeProperty(NAMESPACE + "hasDemonstratingConsentactivity");
        Util.hasDemonstratingConsentactivity.setDomain(GdprActivity);
        Util.hasDemonstratingConsentactivity.setRange(XSD.xboolean);


        Util.hasReportDataBreachactivity = ontModel.createDatatypeProperty(NAMESPACE + "hasReportDataBreachactivity");
        Util.hasReportDataBreachactivity.setDomain(GdprActivity);
        Util.hasReportDataBreachactivity.setRange(XSD.xboolean);

        DatatypeProperty containsGDPRactivities = ontModel.createDatatypeProperty(NAMESPACE + "containsGDPRactivities");
        containsGDPRactivities.setDomain(GdprActivity);
        containsGDPRactivities.setRange(XSD.xboolean);


        /*************************** GDPRArea Data  ***************************/


        Util.hasPseudoAnonymousData = ontModel.createDatatypeProperty(NAMESPACE + "hasPseudoAnonymousData");
        Util.hasPseudoAnonymousData.setDomain(GdprData);
        Util.hasPseudoAnonymousData.setRange(XSD.xboolean);


        Util.hasPersonalData = ontModel.createDatatypeProperty(NAMESPACE + "hasPersonalData");
        Util.hasPersonalData.setDomain(GdprData);
        Util.hasPersonalData.setRange(XSD.xboolean);


        Util.hasAnonymousData = ontModel.createDatatypeProperty(NAMESPACE + "hasAnonymousData");
        Util.hasAnonymousData.setDomain(GdprData);
        Util.hasAnonymousData.setRange(XSD.xboolean);


        DatatypeProperty containsGDPRData = ontModel.createDatatypeProperty(NAMESPACE + "containsGDPRData");
        containsGDPRData.setDomain(GdprData);
        containsGDPRData.setRange(XSD.xboolean);


        /*************************** Vocabs Class  ***************************/

        OntClass Vocabs = ontModel.createUnionClass(NAMESPACE + "Vocab", null);
        Vocabs.addLabel("Vocabs", null);
        Vocabs.addComment("Contains the information of relevant Linked Open Vocabs", null);


        Util.hasBiologyRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasBiologyRelatedVocab");
        Util.hasBiologyRelatedVocab.setDomain(Vocabs);
        Util.hasBiologyRelatedVocab.setRange(XSD.xboolean);

        Util.hasEnvironmentalVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasEnvironmentalVocab");
        Util.hasEnvironmentalVocab.setDomain(Vocabs);
        Util.hasEnvironmentalVocab.setRange(XSD.xboolean);

        Util.hasGeographicalVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasGeographicalVocab");
        Util.hasGeographicalVocab.setDomain(Vocabs);
        Util.hasGeographicalVocab.setRange(XSD.xboolean);

        Util.hasGovernmentRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasGovernmentRelatedVocab");
        Util.hasGovernmentRelatedVocab.setDomain(Vocabs);
        Util.hasGovernmentRelatedVocab.setRange(XSD.xboolean);

        Util.hasHealthRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasHealthRelatedVocab");
        Util.hasHealthRelatedVocab.setDomain(Vocabs);
        Util.hasHealthRelatedVocab.setRange(XSD.xboolean);

        Util.hasIndustrialRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasIndustrialRelatedVocab");
        Util.hasIndustrialRelatedVocab.setDomain(Vocabs);
        Util.hasIndustrialRelatedVocab.setRange(XSD.xboolean);

        Util.hasIoTRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasIoTRelatedVocab");
        Util.hasIoTRelatedVocab.setDomain(Vocabs);
        Util.hasIoTRelatedVocab.setRange(XSD.xboolean);

        Util.hasMetaDataRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasMetaDataRelatedVocab");
        Util.hasMetaDataRelatedVocab.setDomain(Vocabs);
        Util.hasMetaDataRelatedVocab.setRange(XSD.xboolean);

        Util.hasMethodsRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasMethodsRelatedVocab");
        Util.hasMethodsRelatedVocab.setDomain(Vocabs);
        Util.hasMethodsRelatedVocab.setRange(XSD.xboolean);

        Util.hasPeopleRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasPeopleRelatedVocab");
        Util.hasPeopleRelatedVocab.setDomain(Vocabs);
        Util.hasPeopleRelatedVocab.setRange(XSD.xboolean);

        Util.hasQualityRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasQualityRelatedVocab");
        Util.hasQualityRelatedVocab.setDomain(Vocabs);
        Util.hasQualityRelatedVocab.setRange(XSD.xboolean);

        Util.hasRDFRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasRDFRelatedVocab");
        Util.hasRDFRelatedVocab.setDomain(Vocabs);
        Util.hasRDFRelatedVocab.setRange(XSD.xboolean);

        Util.hasSecurityRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasSecurityRelatedVocab");
        Util.hasSecurityRelatedVocab.setDomain(Vocabs);
        Util.hasSecurityRelatedVocab.setRange(XSD.xboolean);

        Util.hasServicesRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasServicesRelatedVocab");
        Util.hasServicesRelatedVocab.setDomain(Vocabs);
        Util.hasServicesRelatedVocab.setRange(XSD.xboolean);

        Util.hasSocietyRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasSocietyRelatedVocab");
        Util.hasSocietyRelatedVocab.setDomain(Vocabs);
        Util.hasSocietyRelatedVocab.setRange(XSD.xboolean);

        Util.hasTimeRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasTimeRelatedVocab");
        Util.hasTimeRelatedVocab.setDomain(Vocabs);
        Util.hasTimeRelatedVocab.setRange(XSD.xboolean);

        Util.hasVocabularyRelatedVocab = ontModel.createDatatypeProperty(NAMESPACE + "hasVocabularyRelatedVocab");
        Util.hasVocabularyRelatedVocab.setDomain(Vocabs);
        Util.hasVocabularyRelatedVocab.setRange(XSD.xboolean);

        DatatypeProperty containsVocab = ontModel.createDatatypeProperty(NAMESPACE + "containsVocabularies");
        containsVocab.setDomain(Vocabs);
        containsVocab.setRange(XSD.xboolean);



        /*************************** Adding Data to the Newly created Ontology  ***************************/

        System.out.println("\n\n\nWriting to the Ontology......");

        for (ReportDatasetOntology r: Util.report) {
        /*************************** Adding Values classes & Subclasses  ***************************/
        Individual OntDataset;
        OntDataset = GdprAreas.createIndividual(NAMESPACE + r.getOntologyName());

            // TODO: 21/07/18 set this Individual to the correct subclasses





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

            for (String v : r.getFoundVocabs()) {


                if (Util.AllVocabsProperty.containsKey(v)) {

                    OntDataset.addLiteral(Util.AllVocabsProperty.get(v), true);
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
            ontModel.write(new FileWriter(Util.ONTOLOGY_PATH), "RDF/XML");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
