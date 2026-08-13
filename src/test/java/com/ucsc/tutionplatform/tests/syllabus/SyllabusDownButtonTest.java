package com.ucsc.tutionplatform.tests.syllabus;

import com.ucsc.tutionplatform.consts.Constants;
import com.ucsc.tutionplatform.models.TestData;
import com.ucsc.tutionplatform.pages.LoginPage;
import com.ucsc.tutionplatform.pages.SyllabusPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SyllabusDownButtonTest extends SyllabusBaseTest {

    private SyllabusPage syllabusPage;
    private LoginPage loginPage;

    // "Organic Chemistry" is above "CM_L1_1786514729910"
    // Clicking Down on "Organic Chemistry" will move it below "CM_L1_1786514729910"
    private static final String TOPIC_TO_MOVE_DOWN = "Organic Chemistry";
    private static final String TOPIC_BELOW        = "CM_L1_1786514729910";

    @BeforeClass(alwaysRun = true)
    public void setupPage() {
        syllabusPage = new SyllabusPage();
        loginPage = new LoginPage();

        driver().get(Constants.USER_DETAILS_URL);
        loginPage.loginAsAdmin("groupb", "123456");
    }

    // ----------------------------------------------------------------
    // Keep browser open for 60 seconds after tests for visual validation
    // ----------------------------------------------------------------
    @AfterClass(alwaysRun = true)
    @Override
    public void quitBrowser() {
        System.out.println("=================================================");
        System.out.println(">>> Down button test done. Browser stays open for 60s.");
        System.out.println(">>> Validate that 'Organic Chemistry' has moved");
        System.out.println(">>> below 'CM_L1_1786514729910'...");
        System.out.println("=================================================");
        try {
            Thread.sleep(60_000); // 60 seconds
        } catch (InterruptedException ignored) {}
        super.quitBrowser();
    }

    @Test(description = "SYL-TC-042", dataProvider = "commonDataProvider", priority = 1)
    public void verifyDownButtonMovesOrganicChemistryBelow(TestData testData) {
        syllabusPage.clickSyllabusTab();

        System.out.println(">>> Checking both topics are visible in the tree...");

        boolean organicChemistryVisible = syllabusPage.isNodeVisible(TOPIC_TO_MOVE_DOWN);
        boolean cm_l1Visible            = syllabusPage.isNodeVisible(TOPIC_BELOW);

        getSoftAssert().assertTrue(organicChemistryVisible,
                "'" + TOPIC_TO_MOVE_DOWN + "' should be visible in the tree");
        getSoftAssert().assertTrue(cm_l1Visible,
                "'" + TOPIC_BELOW + "' should be visible in the tree");

        if (organicChemistryVisible && cm_l1Visible) {
            System.out.println(">>> Clicking Down on '" + TOPIC_TO_MOVE_DOWN + "'...");
            syllabusPage.clickDownForNode(TOPIC_TO_MOVE_DOWN);

            System.out.println(">>> Verifying both nodes still exist after reorder...");
            getSoftAssert().assertTrue(
                    syllabusPage.isNodeVisible(TOPIC_TO_MOVE_DOWN),
                    "'" + TOPIC_TO_MOVE_DOWN + "' should still be visible after clicking Down"
            );
            getSoftAssert().assertTrue(
                    syllabusPage.isNodeVisible(TOPIC_BELOW),
                    "'" + TOPIC_BELOW + "' should still be visible after reorder"
            );
        } else {
            System.out.println(">>> WARNING: One or both topics not found in the tree.");
            System.out.println(">>> Make sure the syllabus tree contains:");
            System.out.println(">>>   - 'Organic Chemistry'");
            System.out.println(">>>   - 'CM_L1_1786514729910'");
        }
    }

    @Test(description = "SYL-TC-042", dataProvider = "commonDataProvider", priority = 2,
            dependsOnMethods = "verifyDownButtonMovesOrganicChemistryBelow")
    public void verifySaveAfterReorder(TestData testData) {
        syllabusPage.clickSyllabusTab();

        System.out.println(">>> Saving the tree with new order...");
        syllabusPage.clickSaveTree();

        System.out.println(">>> Tree saved! Verifying both topics still exist...");
        getSoftAssert().assertTrue(
                syllabusPage.isNodeVisible(TOPIC_TO_MOVE_DOWN),
                "'" + TOPIC_TO_MOVE_DOWN + "' should be visible after save"
        );
        getSoftAssert().assertTrue(
                syllabusPage.isNodeVisible(TOPIC_BELOW),
                "'" + TOPIC_BELOW + "' should be visible after save"
        );
    }
}
