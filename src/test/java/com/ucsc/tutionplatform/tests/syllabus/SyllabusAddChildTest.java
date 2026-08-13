package com.ucsc.tutionplatform.tests.syllabus;

import com.ucsc.tutionplatform.consts.Constants;
import com.ucsc.tutionplatform.models.TestData;
import com.ucsc.tutionplatform.pages.LoginPage;
import com.ucsc.tutionplatform.pages.SyllabusPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SyllabusAddChildTest extends SyllabusBaseTest {

    private SyllabusPage syllabusPage;
    private LoginPage loginPage;

    private static final String PARENT_TOPIC = "test2";
    private static final String CHILD_TOPIC  = "test xxx";

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
        System.out.println(">>> Add Child test done. Browser stays open for 60s.");
        System.out.println(">>> Validate that 'test xxx' appears as a child of 'test2'...");
        System.out.println("=================================================");
        try {
            Thread.sleep(60_000); // 60 seconds
        } catch (InterruptedException ignored) {}
        super.quitBrowser();
    }

    @Test(description = "SYL-TC-017", dataProvider = "commonDataProvider", priority = 1)
    public void verifyAddChildUnderTest2(TestData testData) {
        syllabusPage.clickSyllabusTab();

        System.out.println(">>> Checking parent topic '" + PARENT_TOPIC + "' is visible...");

        if (syllabusPage.isNodeVisible(PARENT_TOPIC)) {
            System.out.println(">>> Found 'test2' — clicking Add Child button...");
            syllabusPage.clickAddChildForNode(PARENT_TOPIC);

            System.out.println(">>> Typing child topic name: '" + CHILD_TOPIC + "'...");
            syllabusPage.setTopicTextForNewNode(CHILD_TOPIC);

            System.out.println(">>> Verifying '" + CHILD_TOPIC + "' is visible in the tree...");
            getSoftAssert().assertTrue(
                    syllabusPage.isNodeVisible(CHILD_TOPIC),
                    "'" + CHILD_TOPIC + "' should appear as a child node under 'test2'"
            );
        } else {
            System.out.println(">>> WARNING: 'test2' not found in the tree.");
            System.out.println(">>> Make sure SyllabusAddTopicTest was run first to create 'test2'.");
            getSoftAssert().fail("'test2' parent topic was not found — cannot add a child to it");
        }
    }

    @Test(description = "SYL-TC-017", dataProvider = "commonDataProvider", priority = 2,
            dependsOnMethods = "verifyAddChildUnderTest2")
    public void verifySaveAfterAddingChild(TestData testData) {
        syllabusPage.clickSyllabusTab();

        System.out.println(">>> Saving the tree with the new child 'test xxx' under 'test2'...");
        syllabusPage.clickSaveTree();

        System.out.println(">>> Tree saved! Verifying parent and child are still visible...");
        getSoftAssert().assertTrue(
                syllabusPage.isNodeVisible(PARENT_TOPIC),
                "'test2' parent should still be visible after save"
        );
        getSoftAssert().assertTrue(
                syllabusPage.isNodeVisible(CHILD_TOPIC),
                "'test xxx' child should still be visible after save"
        );
    }
}
