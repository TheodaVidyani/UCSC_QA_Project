package com.ucsc.tutionplatform.tests.syllabus;

import com.ucsc.tutionplatform.consts.Constants;
import com.ucsc.tutionplatform.models.TestData;
import com.ucsc.tutionplatform.pages.LoginPage;
import com.ucsc.tutionplatform.pages.SyllabusPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SyllabusDeleteTopicTest extends SyllabusBaseTest {

    private SyllabusPage syllabusPage;
    private LoginPage loginPage;

    private static final String TOPIC_TO_DELETE = "test a";

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
        System.out.println(">>> Delete test done. Browser stays open for 60s.");
        System.out.println(">>> Validate that 'test a' is no longer in the tree...");
        System.out.println("=================================================");
        try {
            Thread.sleep(60_000); // 60 seconds
        } catch (InterruptedException ignored) {}
        super.quitBrowser();
    }

    @Test(description = "SYL-TC-030", dataProvider = "commonDataProvider", priority = 1)
    public void verifyDeleteTopic(TestData testData) {
        syllabusPage.clickSyllabusTab();

        System.out.println(">>> Looking for topic: '" + TOPIC_TO_DELETE + "'");

        if (syllabusPage.isNodeVisible(TOPIC_TO_DELETE)) {
            int countBefore = syllabusPage.getNodeCount();
            System.out.println(">>> Found 'test a' — clicking Delete...");

            syllabusPage.clickDeleteForNode(TOPIC_TO_DELETE);

            int countAfter = syllabusPage.getNodeCount();
            System.out.println(">>> Node count before: " + countBefore + " | after: " + countAfter);

            getSoftAssert().assertTrue(
                    !syllabusPage.isNodeVisible(TOPIC_TO_DELETE) || countAfter < countBefore,
                    "'test a' should be removed from the syllabus tree"
            );
        } else {
            System.out.println(">>> WARNING: 'test a' not found on the page.");
            System.out.println(">>> Make sure SyllabusEditTopicTest was run first to create 'test a'.");
            getSoftAssert().fail("'test a' topic was not found — cannot test delete functionality");
        }
    }

    @Test(description = "SYL-TC-030", dataProvider = "commonDataProvider", priority = 2,
            dependsOnMethods = "verifyDeleteTopic")
    public void verifySaveAfterDelete(TestData testData) {
        syllabusPage.clickSyllabusTab();

        System.out.println(">>> Saving the tree after deleting 'test a'...");
        syllabusPage.clickSaveTree();

        System.out.println(">>> Tree saved! Verifying 'test a' no longer appears...");

        getSoftAssert().assertFalse(
                syllabusPage.isNodeVisible(TOPIC_TO_DELETE),
                "'test a' should NOT be visible in the tree after saving"
        );
    }
}
