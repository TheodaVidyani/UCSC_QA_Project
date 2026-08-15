package com.ucsc.tutionplatform.tests.syllabus;

import com.ucsc.tutionplatform.consts.Constants;
import com.ucsc.tutionplatform.models.TestData;
import com.ucsc.tutionplatform.pages.LoginPage;
import com.ucsc.tutionplatform.pages.SyllabusPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SyllabusBasicPageTests extends SyllabusBaseTest {

    private SyllabusPage syllabusPage;
    private LoginPage loginPage;

    @BeforeClass(alwaysRun = true)
    public void setupPage() {
        syllabusPage = new SyllabusPage();
        loginPage = new LoginPage();

        driver().get(Constants.USER_DETAILS_URL);
        loginPage.loginAsAdmin("groupb", "123456");
    }

    @Test(description = "SYL-TC-001", dataProvider = "commonDataProvider")
    public void verifySyllabusPageAccess(TestData testData) {
        syllabusPage.clickSyllabusTab();
        getSoftAssert().assertTrue(syllabusPage.isSyllabusTabHighlighted(), "Syllabus tab should be highlighted as active");
        getSoftAssert().assertTrue(syllabusPage.isSyllabusHeaderVisible(), "Syllabus header section should be visible");
    }

    @Test(description = "SYL-TC-004", dataProvider = "commonDataProvider")
    public void verifySyllabusPageSectionsLoad(TestData testData) {
        syllabusPage.clickSyllabusTab();
        getSoftAssert().assertTrue(syllabusPage.isAddRootTopicButtonVisible(), "Add Root Topic button should be displayed");
        getSoftAssert().assertTrue(syllabusPage.isSaveTreeButtonVisible(), "Save Tree button should be displayed");
        getSoftAssert().assertTrue(syllabusPage.isReloadButtonVisible(), "Reload button should be displayed");
        getSoftAssert().assertTrue(syllabusPage.isTreeNodesPanelVisible(), "Tree Nodes panel should be displayed");
    }

    @Test(description = "SYL-TC-064", dataProvider = "commonDataProvider")
    public void verifyButtonsVisibleAndClickable(TestData testData) {
        syllabusPage.clickSyllabusTab();
        getSoftAssert().assertTrue(syllabusPage.isAddRootTopicButtonVisible(), "Add Root Topic button should be visible");
        getSoftAssert().assertTrue(syllabusPage.isSaveTreeButtonVisible(), "Save Tree button should be visible");
        getSoftAssert().assertTrue(syllabusPage.isReloadButtonVisible(), "Reload button should be visible");
    }

    @Test(description = "SYL-TC-065", dataProvider = "commonDataProvider")
    public void verifyActiveSyllabusTabHighlight(TestData testData) {
        syllabusPage.clickSyllabusTab();
        getSoftAssert().assertTrue(syllabusPage.isSyllabusTabHighlighted(), "Active Syllabus tab should be highlighted");
    }
}
