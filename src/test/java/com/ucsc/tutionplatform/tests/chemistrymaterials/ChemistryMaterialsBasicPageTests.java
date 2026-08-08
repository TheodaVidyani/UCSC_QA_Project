package com.ucsc.tutionplatform.tests.chemistrymaterials;

import com.ucsc.tutionplatform.consts.Constants;
import com.ucsc.tutionplatform.models.TestData;
import com.ucsc.tutionplatform.pages.ChemistryMaterialPage;
import com.ucsc.tutionplatform.pages.LoginPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ChemistryMaterialsBasicPageTests extends ChemistryMaterialsBaseTest {

    private ChemistryMaterialPage chemistryMaterialPage;
    private LoginPage loginPage;

    @BeforeClass(alwaysRun = true)
    public void setupPage() {
        chemistryMaterialPage = new ChemistryMaterialPage();
        loginPage = new LoginPage();

        // Pass Constants.USER_DETAILS_URL directly to driver().get()
        driver().get(Constants.USER_DETAILS_URL);

        loginPage.loginAsAdmin("groupa", "123456");
    }

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