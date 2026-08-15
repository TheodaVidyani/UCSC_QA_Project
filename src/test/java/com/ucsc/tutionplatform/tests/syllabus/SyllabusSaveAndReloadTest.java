package com.ucsc.tutionplatform.tests.syllabus;

import com.ucsc.tutionplatform.consts.Constants;
import com.ucsc.tutionplatform.models.TestData;
import com.ucsc.tutionplatform.pages.LoginPage;
import com.ucsc.tutionplatform.pages.SyllabusPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SyllabusSaveAndReloadTest extends SyllabusBaseTest {

    private SyllabusPage syllabusPage;
    private LoginPage loginPage;

    @BeforeClass(alwaysRun = true)
    public void setupPage() {
        syllabusPage = new SyllabusPage();
        loginPage = new LoginPage();

        driver().get(Constants.USER_DETAILS_URL);
        loginPage.loginAsAdmin("groupb", "123456");
    }

    @Test(description = "SYL-TC-048", dataProvider = "commonDataProvider")
    public void verifySaveNewlyCreatedSyllabusTree(TestData testData) {
        syllabusPage.clickSyllabusTab();
        syllabusPage.clickAddRootTopic();

        String topicName = testData.getTopic() != null ? testData.getTopic() : "Physical Chemistry";
        syllabusPage.setTopicTextForNewNode(topicName);

        syllabusPage.clickSaveTree();
        getSoftAssert().assertTrue(
                syllabusPage.isNodeVisible(topicName),
                "Saved topic should remain visible in syllabus tree editor"
        );
    }

    @Test(description = "SYL-TC-054", dataProvider = "commonDataProvider")
    public void verifyReloadSavedSyllabusTree(TestData testData) {
        syllabusPage.clickSyllabusTab();
        syllabusPage.clickReload();

        getSoftAssert().assertTrue(
                syllabusPage.isSyllabusHeaderVisible(),
                "Syllabus editor should reload and display active tree"
        );
    }
}
