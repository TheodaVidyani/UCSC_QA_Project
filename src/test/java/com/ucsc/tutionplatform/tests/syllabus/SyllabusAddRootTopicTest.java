package com.ucsc.tutionplatform.tests.syllabus;

import com.ucsc.tutionplatform.consts.Constants;
import com.ucsc.tutionplatform.models.TestData;
import com.ucsc.tutionplatform.pages.LoginPage;
import com.ucsc.tutionplatform.pages.SyllabusPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SyllabusAddRootTopicTest extends SyllabusBaseTest {

    private SyllabusPage syllabusPage;
    private LoginPage loginPage;

    private static final String NEW_ROOT_TOPIC = "Root Test Topic";

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
        System.out.println(">>> Add Root Topic test done. Browser stays open for 60s.");
        System.out.println(">>> Validate that '" + NEW_ROOT_TOPIC + "' appears in the tree...");
        System.out.println("=================================================");
        try {
            Thread.sleep(60_000); // 60 seconds
        } catch (InterruptedException ignored) {}
        super.quitBrowser();
    }

    @Test(description = "SYL-TC-004", dataProvider = "commonDataProvider", priority = 1)
    public void verifyAddRootTopicButtonIsVisible(TestData testData) {
        syllabusPage.clickSyllabusTab();

        System.out.println(">>> Verifying 'Add Root Topic' button is visible on the page...");
        getSoftAssert().assertTrue(
                syllabusPage.isAddRootTopicButtonVisible(),
                "'Add Root Topic' button should be visible on the Syllabus page"
        );
    }

    @Test(description = "SYL-TC-010", dataProvider = "commonDataProvider", priority = 2,
            dependsOnMethods = "verifyAddRootTopicButtonIsVisible")
    public void verifyClickAddRootTopicCreatesNewNode(TestData testData) {
        syllabusPage.clickSyllabusTab();

        System.out.println(">>> Clicking 'Add Root Topic' button...");
        syllabusPage.clickAddRootTopic();

        System.out.println(">>> Typing new root topic name: '" + NEW_ROOT_TOPIC + "'...");
        syllabusPage.setTopicTextForNewNode(NEW_ROOT_TOPIC);

        System.out.println(">>> Verifying '" + NEW_ROOT_TOPIC + "' appears in the tree...");
        getSoftAssert().assertTrue(
                syllabusPage.isNodeVisible(NEW_ROOT_TOPIC),
                "'" + NEW_ROOT_TOPIC + "' should appear as a new root node in the syllabus tree"
        );
    }

    @Test(description = "SYL-TC-048", dataProvider = "commonDataProvider", priority = 3,
            dependsOnMethods = "verifyClickAddRootTopicCreatesNewNode")
    public void verifySaveAfterAddingRootTopic(TestData testData) {
        syllabusPage.clickSyllabusTab();

        System.out.println(">>> Saving the tree with new root topic '" + NEW_ROOT_TOPIC + "'...");
        syllabusPage.clickSaveTree();

        System.out.println(">>> Tree saved! Verifying '" + NEW_ROOT_TOPIC + "' still appears...");
        getSoftAssert().assertTrue(
                syllabusPage.isNodeVisible(NEW_ROOT_TOPIC),
                "'" + NEW_ROOT_TOPIC + "' should still be visible in the tree after saving"
        );
        getSoftAssert().assertTrue(
                syllabusPage.isSyllabusHeaderVisible(),
                "Syllabus header should still be visible after save"
        );
    }
}
