package com.ucsc.tutionplatform.tests.syllabus;

import com.ucsc.tutionplatform.consts.Constants;
import com.ucsc.tutionplatform.models.TestData;
import com.ucsc.tutionplatform.pages.LoginPage;
import com.ucsc.tutionplatform.pages.SyllabusPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SyllabusEditTopicTest extends SyllabusBaseTest {

    private SyllabusPage syllabusPage;
    private LoginPage loginPage;

    private static final String OLD_TOPIC_NAME = "test1";
    private static final String NEW_TOPIC_NAME = "test a";

    @BeforeClass(alwaysRun = true)
    public void setupPage() {
        syllabusPage = new SyllabusPage();
        loginPage = new LoginPage();

        driver().get(Constants.USER_DETAILS_URL);
        loginPage.loginAsAdmin("groupb", "123456");
    }

    // ----------------------------------------------------------------
    // Override quitBrowser so the browser stays open for 60 seconds
    // after all tests finish — giving time to visually validate the edit.
    // ----------------------------------------------------------------
    @AfterClass(alwaysRun = true)
    @Override
    public void quitBrowser() {
        System.out.println("=================================================");
        System.out.println(">>> Edit test done. Browser stays open for 60s.");
        System.out.println(">>> Validate that 'test1' is now 'test a'...");
        System.out.println("=================================================");
        try {
            Thread.sleep(60_000); // 60 seconds — change if you need more time
        } catch (InterruptedException ignored) {}
        super.quitBrowser();
    }

    @Test(description = "SYL-TC-023", dataProvider = "commonDataProvider", priority = 1)
    public void verifyEditTopicName(TestData testData) {
        syllabusPage.clickSyllabusTab();

        System.out.println(">>> Looking for topic: '" + OLD_TOPIC_NAME + "'");

        if (syllabusPage.isNodeVisible(OLD_TOPIC_NAME)) {
            System.out.println(">>> Found 'test1' — clearing field and replacing with 'test a'");

            // Clear the field and type the new value
            syllabusPage.enterTopicName(OLD_TOPIC_NAME, NEW_TOPIC_NAME);

            System.out.println(">>> Verifying the field now shows 'test a'");
            getSoftAssert().assertEquals(
                    syllabusPage.getEnteredTopicValue(NEW_TOPIC_NAME),
                    NEW_TOPIC_NAME,
                    "Topic field should now show 'test a' after editing 'test1'"
            );
        } else {
            System.out.println(">>> WARNING: 'test1' topic not found on the page.");
            System.out.println(">>> Make sure SyllabusAddTopicTest was run first to create test1.");
            getSoftAssert().fail("'test1' topic was not found — cannot test edit functionality");
        }
    }

    @Test(description = "SYL-TC-023", dataProvider = "commonDataProvider", priority = 2,
            dependsOnMethods = "verifyEditTopicName")
    public void verifySaveAfterEdit(TestData testData) {
        syllabusPage.clickSyllabusTab();

        System.out.println(">>> Saving the tree with updated topic name 'test a'");

        // Save the tree
        syllabusPage.clickSaveTree();

        System.out.println(">>> Tree saved! Verifying 'test a' persists on page...");

        getSoftAssert().assertTrue(
                syllabusPage.isNodeVisible(NEW_TOPIC_NAME),
                "'test a' should still be visible in the tree after saving"
        );
    }
}
