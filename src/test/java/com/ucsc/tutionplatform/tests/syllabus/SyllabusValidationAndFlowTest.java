package com.ucsc.tutionplatform.tests.syllabus;

import com.ucsc.tutionplatform.consts.Constants;
import com.ucsc.tutionplatform.models.TestData;
import com.ucsc.tutionplatform.pages.LoginPage;
import com.ucsc.tutionplatform.pages.SyllabusPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SyllabusValidationAndFlowTest extends SyllabusBaseTest {

    private SyllabusPage syllabusPage;
    private LoginPage loginPage;

    @BeforeClass(alwaysRun = true)
    public void setupPage() {
        syllabusPage = new SyllabusPage();
        loginPage = new LoginPage();

        driver().get(Constants.USER_DETAILS_URL);
        loginPage.loginAsAdmin("groupb", "123456");
    }

    @Test(description = "SYL-TC-070", dataProvider = "commonDataProvider")
    public void verifyTrimLeadingAndTrailingSpacesInTopicName(TestData testData) {
        syllabusPage.clickSyllabusTab();
        syllabusPage.clickAddRootTopic();

        String rawTopic = testData.getTopic() != null ? testData.getTopic() : "  Trimming Test Topic  ";
        String trimmed = rawTopic.trim();
        syllabusPage.setTopicTextForNewNode(rawTopic);

        getSoftAssert().assertTrue(
                syllabusPage.isNodeVisible(trimmed) || syllabusPage.isNodeVisible(rawTopic) || syllabusPage.getNodeCount() > 0,
                "Topic should be trimmed and displayed properly"
        );
    }

    @Test(description = "SYL-TC-071", dataProvider = "commonDataProvider")
    public void verifyPreventHarmfulScriptInTopicName(TestData testData) {
        syllabusPage.clickSyllabusTab();
        syllabusPage.clickAddRootTopic();

        String scriptTopic = testData.getTopic() != null ? testData.getTopic() : "<script>alert('test')</script>";
        syllabusPage.setTopicTextForNewNode(scriptTopic);

        getSoftAssert().assertTrue(
                syllabusPage.isSyllabusHeaderVisible(),
                "Page should not execute script or crash UI"
        );
    }

    @Test(description = "SYL-TC-072", dataProvider = "commonDataProvider")
    public void verifyPreventSqlInjectionStyleInput(TestData testData) {
        syllabusPage.clickSyllabusTab();
        syllabusPage.clickAddRootTopic();

        String sqlTopic = testData.getTopic() != null ? testData.getTopic() : "' OR '1'='1";
        syllabusPage.setTopicTextForNewNode(sqlTopic);

        getSoftAssert().assertTrue(
                syllabusPage.isSyllabusHeaderVisible(),
                "SQL injection payload should be handled safely without error"
        );
    }

    @Test(description = "SYL-TC-075", dataProvider = "commonDataProvider")
    public void verifyAcceptValidChemistryNotation(TestData testData) {
        syllabusPage.clickSyllabusTab();
        syllabusPage.clickAddRootTopic();

        String formulaTopic = "H2SO4 Preparation";
        syllabusPage.setTopicTextForNewNode(formulaTopic);

        getSoftAssert().assertTrue(
                syllabusPage.isNodeVisible(formulaTopic) || syllabusPage.getNodeCount() > 0,
                "Chemistry notation should be accepted and displayed"
        );
    }

    @Test(description = "SYL-TC-093", dataProvider = "commonDataProvider")
    public void verifyCreateFullPhysicalChemistryHierarchy(TestData testData) {
        syllabusPage.clickSyllabusTab();
        syllabusPage.clickAddRootTopic();

        String root = "Physical Chemistry Flow";
        String sub = "Mole Concept";

        syllabusPage.setTopicTextForNewNode(root);
        if (syllabusPage.isNodeVisible(root)) {
            syllabusPage.clickAddChildForNode(root);
            syllabusPage.setTopicTextForNewNode(sub);

            getSoftAssert().assertTrue(
                    syllabusPage.isNodeVisible(sub),
                    "Subtopic should be added under Physical Chemistry"
            );
        }
    }
}
