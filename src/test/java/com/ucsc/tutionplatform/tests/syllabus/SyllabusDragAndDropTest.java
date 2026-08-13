package com.ucsc.tutionplatform.tests.syllabus;

import com.ucsc.tutionplatform.consts.Constants;
import com.ucsc.tutionplatform.models.TestData;
import com.ucsc.tutionplatform.pages.LoginPage;
import com.ucsc.tutionplatform.pages.SyllabusPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SyllabusDragAndDropTest extends SyllabusBaseTest {

    private SyllabusPage syllabusPage;
    private LoginPage loginPage;

    // test3 is currently below test2 — clicking Up on test3 swaps them
    private static final String TOPIC_TO_MOVE_UP = "test3";
    private static final String TOPIC_ABOVE    = "test2";

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
        System.out.println(">>> Up button test done. Browser stays open for 60s.");
        System.out.println(">>> Validate that 'test3' has moved above 'test2'...");
        System.out.println("=================================================");
        try {
            Thread.sleep(60_000); // 60 seconds
        } catch (InterruptedException ignored) {}
        super.quitBrowser();
    }

    @Test(description = "SYL-TC-042", dataProvider = "commonDataProvider", priority = 1)
    public void verifyUpButtonMovesTest3AboveTest2(TestData testData) {
        syllabusPage.clickSyllabusTab();

        System.out.println(">>> Checking both 'test2' and 'test3' are visible...");

        boolean test2Visible = syllabusPage.isNodeVisible(TOPIC_ABOVE);
        boolean test3Visible = syllabusPage.isNodeVisible(TOPIC_TO_MOVE_UP);

        getSoftAssert().assertTrue(test2Visible, "'test2' should be visible in the tree");
        getSoftAssert().assertTrue(test3Visible, "'test3' should be visible in the tree");

        if (test2Visible && test3Visible) {
            System.out.println(">>> Clicking Up on 'test3' to move it above 'test2'...");
            syllabusPage.clickUpForNode(TOPIC_TO_MOVE_UP);

            System.out.println(">>> Verifying both nodes are still visible after reorder...");
            getSoftAssert().assertTrue(
                    syllabusPage.isNodeVisible(TOPIC_TO_MOVE_UP),
                    "'test3' should still be visible after clicking Up"
            );
            getSoftAssert().assertTrue(
                    syllabusPage.isNodeVisible(TOPIC_ABOVE),
                    "'test2' should still be visible after reorder"
            );
        } else {
            System.out.println(">>> WARNING: One or both topics not found.");
            System.out.println(">>> Make sure SyllabusAddTopicTest was run first to create test2 and test3.");
        }
    }

    @Test(description = "SYL-TC-042", dataProvider = "commonDataProvider", priority = 2,
            dependsOnMethods = "verifyUpButtonMovesTest3AboveTest2")
    public void verifySaveAfterReorder(TestData testData) {
        syllabusPage.clickSyllabusTab();

        System.out.println(">>> Saving the tree with new order (test3 above test2)...");
        syllabusPage.clickSaveTree();

        System.out.println(">>> Tree saved! Verifying both topics still exist...");
        getSoftAssert().assertTrue(
                syllabusPage.isNodeVisible(TOPIC_TO_MOVE_UP),
                "'test3' should be visible after save"
        );
        getSoftAssert().assertTrue(
                syllabusPage.isNodeVisible(TOPIC_ABOVE),
                "'test2' should be visible after save"
        );
    }
}
