package com.ucsc.tutionplatform.tests.syllabus;

import com.ucsc.tutionplatform.consts.Constants;
import com.ucsc.tutionplatform.models.TestData;
import com.ucsc.tutionplatform.pages.LoginPage;
import com.ucsc.tutionplatform.pages.SyllabusPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SyllabusTreeCreationFlowTest extends SyllabusBaseTest {

    private SyllabusPage syllabusPage;
    private LoginPage loginPage;

    @BeforeClass(alwaysRun = true)
    public void setupPage() {
        syllabusPage = new SyllabusPage();
        loginPage = new LoginPage();

        // 1. Open admin portal URL
        driver().get(Constants.USER_DETAILS_URL);

        // 2. Login with credentials groupb / 123456
        loginPage.loginAsAdmin("groupb", "123456");
    }

    @Test(description = "SYL-TC-093", dataProvider = "commonDataProvider")
    public void createAndSaveFullSyllabusTree(TestData testData) {

        // 3. Navigate to Syllabus section
        syllabusPage.clickSyllabusTab();
        getSoftAssert().assertTrue(syllabusPage.isSyllabusHeaderVisible(), "Syllabus Tree Editor header should be visible");

        // 4. Create Root Topic: Physical Chemistry
        syllabusPage.clickAddRootTopic();
        syllabusPage.setTopicTextForNewNode("Physical Chemistry");
        getSoftAssert().assertTrue(syllabusPage.isNodeVisible("Physical Chemistry"), "Physical Chemistry root node should be created");

        // 5. Create Sub Topic: Mole Concept under Physical Chemistry
        syllabusPage.clickAddChildForNode("Physical Chemistry");
        syllabusPage.setTopicTextForNewNode("Mole Concept");
        getSoftAssert().assertTrue(syllabusPage.isNodeVisible("Mole Concept"), "Mole Concept subtopic node should be created");

        // 6. Create Child Topic: Relative Atomic Mass under Mole Concept
        syllabusPage.clickAddChildForNode("Mole Concept");
        syllabusPage.setTopicTextForNewNode("Relative Atomic Mass");
        getSoftAssert().assertTrue(syllabusPage.isNodeVisible("Relative Atomic Mass"), "Relative Atomic Mass child node should be created");

        // 7. Create Second Root Topic: Organic Chemistry
        syllabusPage.clickAddRootTopic();
        syllabusPage.setTopicTextForNewNode("Organic Chemistry");
        getSoftAssert().assertTrue(syllabusPage.isNodeVisible("Organic Chemistry"), "Organic Chemistry root node should be created");

        // 8. Create Sub Topic: Hydrocarbons under Organic Chemistry
        syllabusPage.clickAddChildForNode("Organic Chemistry");
        syllabusPage.setTopicTextForNewNode("Hydrocarbons");
        getSoftAssert().assertTrue(syllabusPage.isNodeVisible("Hydrocarbons"), "Hydrocarbons subtopic node should be created");

        // 9. Save the entire syllabus tree
        syllabusPage.clickSaveTree();

        // 10. Reload to verify tree persistence
        syllabusPage.clickReload();
        getSoftAssert().assertTrue(syllabusPage.isNodeVisible("Physical Chemistry"), "Saved Physical Chemistry root topic should persist after reload");
        getSoftAssert().assertTrue(syllabusPage.isNodeVisible("Organic Chemistry"), "Saved Organic Chemistry root topic should persist after reload");
    }
}
