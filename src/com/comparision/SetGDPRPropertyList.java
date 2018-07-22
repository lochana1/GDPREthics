package com.comparision;

/*
 * Initialises the Ontology dataproperty
 */

public class SetGDPRPropertyList {

    static void setGDPRPropertyList(){

        Util.GdprPrinciple.put("Principle of Lawfullness, Fairness & Transparency", Util.hasLFTprinciple);
        Util.GdprPrinciple.put("Principle of Accuracy", Util.hasAccuracyprinciple);
        Util.GdprPrinciple.put("Principle of Integrity & Confidentiality", Util.hasICprinciple);
        Util.GdprPrinciple.put("Principle of Data Mnimization", Util.hasDataMinimizationprinciple);
        Util.GdprPrinciple.put("Principle of Storage Limitation", Util.hasStorageLimitationprinciple);
        Util.GdprPrinciple.put("Principle of Accountability", Util.hasAccountabilityprinciple);
        Util.GdprPrinciple.put("Principle of Purpose Limitation", Util.hasPurposeLimitationprinciple);

        Util.GdprActivity.put("Processing", Util.hasProcessingactivity);
        Util.GdprActivity.put("Marketing", Util.hasMarketingactivity);
        Util.GdprActivity.put("Data Activity", Util.hasDataActivityactivity);
        Util.GdprActivity.put("Consent Activity", Util.hasConsentactivity);
        Util.GdprActivity.put("Exercise rights", Util.hasExerciserightsactivity);
        Util.GdprActivity.put("Impact Assessment", Util.hasImpactAssessmentactivity);
        Util.GdprActivity.put("Identification of Data Subject", Util.hasIdentificationofDataSubjectactivity);
        Util.GdprActivity.put("Security of Personal Data", Util.hasSecurityofPersonalDataactivity);
        Util.GdprActivity.put("Demonstrating Consent", Util.hasDemonstratingConsentactivity);
        Util.GdprActivity.put("Report Data Breach", Util.hasReportDataBreachactivity);

        Util.GdprData.put("Pseudo-anonymous data", Util.hasPseudoAnonymousData);
        Util.GdprData.put("Personal Data", Util.hasPersonalData);
        Util.GdprData.put("Anonymous Data", Util.hasAnonymousData);
   }
}




