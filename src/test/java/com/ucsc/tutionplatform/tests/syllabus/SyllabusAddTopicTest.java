package com.ucsc.tutionplatform.tests.syllabus;

import com.ucsc.tutionplatform.consts.Constants;
import com.ucsc.tutionplatform.models.TestData;
import com.ucsc.tutionplatform.pages.LoginPage;
import com.ucsc.tutionplatform.pages.SyllabusPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SyllabusAddTopicTest extends SyllabusBaseTest {

    private SyllabusPage syllabusPage;
    private LoginPage loginPage;

    @BeforeClass(alwaysRun = true)
    public void setupPage() {
        syllabusPage = new SyllabusPage();
        loginPage = new LoginPage();

        driver().get(Constants.USER_DETAILS_URL);
        loginPage.loginAsAdmin("groupb", "123456");
    }

    // ----------------------------------------------------------------
    // Override quitBrowser from BaseTest so the browser stays open
    // after all tests finish — giving time to visually validate the data.
    // Wait 60 seconds before closing.
    // ----------------------------------------------------------------
    @AfterClass(alwaysRun = true)
    @Override
    public void quitBrowser() {
        System.out.println("=================================================");
        System.out.println(">>> Tests done. Browser will stay open for 60s");
        System.out.println(">>> Validate the syllabus tree visually now...");
        System.out.println("=================================================");
        try {
            Thread.sleep(60_000); // 60 seconds — change this if you need more time
        } catch (InterruptedException ignored) {}
        super.quitBrowser(); // close browser after wait
    }

    @Test(description = "SYL-TC-010", dataProvider = "commonDataProvider", priority = 1)
    public void verifyAddValidRootTopic(TestData testData) {
        syllabusPage.clickSyllabusTab();
        syllabusPage.clickAddRootTopic();

        String topicName = "test1";
        syllabusPage.setTopicTextForNewNode(topicName);

        getSoftAssert().assertTrue(
                syllabusPage.isNodeVisible(topicName),
                "Root topic 'test1' should be visible in syllabus tree"
        );
    }

    @Test(description = "SYL-TC-011", dataProvider = "commonDataProvider", priority = 2)
    public void verifyAddMultipleRootTopics(TestData testData) {
        syllabusPage.clickSyllabusTab();
        syllabusPage.clickAddRootTopic();

        String topicName = "test2";
        syllabusPage.setTopicTextForNewNode(topicName);

        getSoftAssert().assertTrue(
                syllabusPage.isNodeVisible(topicName),
                "Root topic 'test2' should be visible"
        );
    }

    @Test(description = "SYL-TC-015", dataProvider = "commonDataProvider", priority = 3)
    public void verifyAddTopicWithChemistrySpecialCharacters(TestData testData) {
        syllabusPage.clickSyllabusTab();
        syllabusPage.clickAddRootTopic();

        String topicName = "test3";
        syllabusPage.setTopicTextForNewNode(topicName);

        getSoftAssert().assertTrue(
                syllabusPage.isNodeVisible(topicName),
                "Root topic 'test3' should be added and displayed"
        );
    }

    @Test(description = "SYL-TC-017", dataProvider = "commonDataProvider", priority = 4,
            dependsOnMethods = {
                "verifyAddValidRootTopic",
                "verifyAddMultipleRootTopics",
                "verifyAddTopicWithChemistrySpecialCharacters"
            })
    public void verifySaveTreeAfterAddingTopics(TestData testData) {
        syllabusPage.clickSyllabusTab();

        // Save the tree with all 3 topics (test1, test2, test3)
        syllabusPage.clickSaveTree();

        System.out.println(">>> Tree saved! Topics test1, test2, test3 should now be persisted.");

        getSoftAssert().assertTrue(
                syllabusPage.isNodeVisible("test1"),
                "test1 should still be visible after saving"
        );
        getSoftAssert().assertTrue(
                syllabusPage.isNodeVisible("test2"),
                "test2 should still be visible after saving"
        );
        getSoftAssert().assertTrue(
                syllabusPage.isNodeVisible("test3"),
                "test3 should still be visible after saving"
        );
    }
}
