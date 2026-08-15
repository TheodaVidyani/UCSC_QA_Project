package com.ucsc.tutionplatform.tests.chemistrymaterials;

import com.ucsc.tutionplatform.models.TestData;
import org.testng.annotations.Test;

public class ChemistryMaterialsBasicPageTests extends ChemistryMaterialsBaseTest {

    @Test(description = "CHEM_MAT_001", dataProvider = "commonDataProvider")
    public void verifyChemistryMaterialsButtonTest(TestData testData) {
        chemistryMaterialPage.clickChemistryMaterialsTab();
        getSoftAssert().assertTrue(chemistryMaterialPage.isChemistryMaterialsButtonVisible(), "Button should be visible");
    }

    @Test(description = "CHEM_MAT_002", dataProvider = "commonDataProvider")
    public void verifyChemistryMaterialsPageLoadTest(TestData testData) {
        chemistryMaterialPage.clickChemistryMaterialsTab();
        getSoftAssert().assertTrue(chemistryMaterialPage.isChemistryMaterialsSectionDisplayed(), "'Chemistry Materials' section should be loaded and visible");
        getSoftAssert().assertTrue(chemistryMaterialPage.isMaterialMappingSectionDisplayed(), "'Material Mapping' section should be loaded and visible");
    }
}