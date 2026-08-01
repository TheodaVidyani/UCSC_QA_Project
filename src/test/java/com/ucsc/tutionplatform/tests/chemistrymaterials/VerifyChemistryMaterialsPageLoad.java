package com.ucsc.tutionplatform.tests.chemistrymaterials;

import com.ucsc.tutionplatform.core.DriverManager;
import com.ucsc.tutionplatform.pages.ChemistryMaterialPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyChemistryMaterialsPageLoad extends ChemistryMaterialsBaseTest {

    @Test(description = "CHEM_MAT_002: Verify Chemistry Materials page loads successfully without crash or blank page")
    public void verifyChemistryMaterialsPageLoadTest() {
        ChemistryMaterialPage chemistryMaterialPage = new ChemistryMaterialPage(DriverManager.getDriver());

        // The admin login is handled in the base setup; click the tab and validate the workspace.
        chemistryMaterialPage.clickChemistryMaterialsTab();

        boolean isPageLoaded = chemistryMaterialPage.isChemistryMaterialsPageLoaded();
        Assert.assertTrue(isPageLoaded, "Chemistry Materials page should load successfully without a blank or crashed UI.");

        System.out.println("CHEM_MAT_002 executed successfully: Chemistry Materials page loaded with visible content and controls.");
    }
}