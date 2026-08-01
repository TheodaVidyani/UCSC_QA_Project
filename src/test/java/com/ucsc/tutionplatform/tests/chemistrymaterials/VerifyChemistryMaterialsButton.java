package com.ucsc.tutionplatform.tests.chemistrymaterials;

import com.ucsc.tutionplatform.core.DriverManager;
import com.ucsc.tutionplatform.pages.ChemistryMaterialPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyChemistryMaterialsButton extends ChemistryMaterialsBaseTest {

    @Test(description = "CHEM_MAT_001: Verify Chemistry Materials button is visible and enabled")
    public void verifyChemistryMaterialsButtonTest() {
        ChemistryMaterialPage chemistryMaterialPage = new ChemistryMaterialPage(DriverManager.getDriver());
        
        // 1. Open the application base URL
        chemistryMaterialPage.navigateToApp();

        // 2. Click on the 'Chemistry Materials' top switcher tab to load the section view
        chemistryMaterialPage.clickChemistryMaterialsTab();

        // 3. Verify the button inside the section is visible
        boolean isVisible = chemistryMaterialPage.isChemistryMaterialsButtonVisible();
        Assert.assertTrue(isVisible, "Chemistry Materials button should be visible!");

        // 4. Hover over and verify the button is enabled
        boolean isEnabled = chemistryMaterialPage.isChemistryMaterialsButtonEnabled();
        Assert.assertTrue(isEnabled, "Chemistry Materials button should be enabled and interactable!");
        
        System.out.println("CHEM_MAT_001 executed successfully: Button is visible and enabled.");
    }
}