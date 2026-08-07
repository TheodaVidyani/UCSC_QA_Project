package com.ucsc.tutionplatform.tests.chemistrymaterials;

import com.ucsc.tutionplatform.models.TestData;
import com.ucsc.tutionplatform.pages.ChemistryMaterialPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ChemistryMaterialsBasicPageTests extends ChemistryMaterialsBaseTest {

    private ChemistryMaterialPage chemistryMaterialPage;

    @BeforeClass(alwaysRun = true)
    public void setupPage() {
        chemistryMaterialPage = new ChemistryMaterialPage();
    }

    @Test(description = "CHEM_MAT_001", dataProvider = "commonDataProvider")
    public void verifyChemistryMaterialsButtonTest(TestData testData) {
        chemistryMaterialPage.clickChemistryMaterialsTab();
        getSoftAssert().assertTrue(chemistryMaterialPage.isChemistryMaterialsButtonVisible(), "Button should be visible");
    }
}