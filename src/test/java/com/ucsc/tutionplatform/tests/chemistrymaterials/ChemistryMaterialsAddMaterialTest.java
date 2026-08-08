package com.ucsc.tutionplatform.tests.chemistrymaterials;

import com.ucsc.tutionplatform.consts.Constants;
import com.ucsc.tutionplatform.models.TestData;
import com.ucsc.tutionplatform.pages.ChemistryMaterialPage;
import com.ucsc.tutionplatform.pages.LoginPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ChemistryMaterialsAddMaterialTest extends ChemistryMaterialsBaseTest {

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

    @Test(description = "CM-AM-001", dataProvider = "commonDataProvider")
    public void verifyAddMaterialOpensEntryForm(TestData testData) {

        chemistryMaterialPage.clickChemistryMaterialsTab();
        chemistryMaterialPage.clickCloneFromSyllabus();

        // Pre-condition 4: Verify Level 1 and Level 2 nodes are visible
        getSoftAssert().assertTrue(
                chemistryMaterialPage.isNodeVisible(testData.getTargetNode()),
                "Target node '1. Quality Assurance Theory' should be visible"
        );
        getSoftAssert().assertTrue(
                chemistryMaterialPage.isNodeVisible(testData.getOtherNode()),
                "Neighboring node '1.1 QA Fundamentals' should be visible"
        );

        // Step 2: Click Add Material under target node
        chemistryMaterialPage.clickAddMaterialForNode(testData.getTargetNode());

        // Expectation 1 & 2: Form displays PDF badge, topic input, file upload controls, Remove button, and Add Another button
        getSoftAssert().assertTrue(
                chemistryMaterialPage.isMaterialEntryFormDisplayed(),
                "Material entry form controls should expand inside target node"
        );

        // Expectation 3: Neighboring node remains visible and unchanged
        getSoftAssert().assertTrue(
                chemistryMaterialPage.isNodeVisible(testData.getOtherNode()),
                "Neighboring node '1.1 QA Fundamentals' should remain unchanged and displayed"
        );
    }
}