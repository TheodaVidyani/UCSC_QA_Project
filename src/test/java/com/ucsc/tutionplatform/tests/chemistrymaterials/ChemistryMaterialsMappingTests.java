package com.ucsc.tutionplatform.tests.chemistrymaterials;

import com.ucsc.tutionplatform.database.DatabaseHandler;
import com.ucsc.tutionplatform.pages.ChemistryMaterialPage;
import com.ucsc.tutionplatform.pages.LoginPage;
import com.ucsc.tutionplatform.utils.ConfigReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class ChemistryMaterialsMappingTests extends ChemistryMaterialsBaseTest {

    private static final String APP_URL_PROPERTY = "user.default.url";

    private ChemistryMaterialPage chemistryMaterialPage;
    private LoginPage loginPage;

    private String testNodeTitle;
    private String parentNodeTitle;
    private String childNodeTitle;

    @BeforeClass(alwaysRun = true)
    public void setupPage() {

        chemistryMaterialPage = new ChemistryMaterialPage();
        loginPage = new LoginPage();

        driver().get(
                ConfigReader.getProperty(APP_URL_PROPERTY)
        );

        loginPage.loginAsAdmin("groupa", "123456");
    }

    @BeforeMethod(alwaysRun = true)
    public void prepareTestNode() {

        testNodeTitle = null;
        parentNodeTitle = null;
        childNodeTitle = null;

        driver().get(
                ConfigReader.getProperty(APP_URL_PROPERTY)
        );
    }

    @Test(description = "CM-MD-002")
    public void verifyLevel1AndLevel2LabelsAreCorrect() {

        long timestamp = System.currentTimeMillis();

        parentNodeTitle = "CM_L1_" + timestamp;
        childNodeTitle = "CM_L2_" + timestamp;

        int insertedParentRows = DatabaseHandler.insert(
                "INSERT INTO syllabus_nodes (title) VALUES (?)",
                parentNodeTitle
        );

        if (insertedParentRows != 1) {
            throw new IllegalStateException(
                    "Unable to create parent syllabus node: " + parentNodeTitle
            );
        }

        List<Map<String, Object>> parentRows = DatabaseHandler.select(
                "SELECT node_id FROM syllabus_nodes WHERE title = ?",
                parentNodeTitle
        );

        if (parentRows.size() != 1) {
            throw new IllegalStateException(
                    "Unable to retrieve parent node ID for: " + parentNodeTitle
            );
        }

        long parentNodeId =
                ((Number) parentRows.get(0).get("node_id")).longValue();

        int insertedChildRows = DatabaseHandler.insert(
                "INSERT INTO syllabus_nodes (parent_id, title) VALUES (?, ?)",
                parentNodeId,
                childNodeTitle
        );

        if (insertedChildRows != 1) {
            throw new IllegalStateException(
                    "Unable to create child syllabus node: " + childNodeTitle
            );
        }

        chemistryMaterialPage.clickChemistryMaterialsTab();
        chemistryMaterialPage.clickCloneFromSyllabus();

        getSoftAssert().assertTrue(
                chemistryMaterialPage.isNodeVisible(parentNodeTitle),
                "Created Level 1 node should be visible in Material Mapping"
        );

        getSoftAssert().assertEquals(
                chemistryMaterialPage.getLevelLabelForNode(parentNodeTitle),
                "Level 1",
                "Parent node should display Level 1"
        );

        getSoftAssert().assertTrue(
                chemistryMaterialPage.isNodeVisible(childNodeTitle),
                "Created Level 2 node should be visible in Material Mapping"
        );

        getSoftAssert().assertEquals(
                chemistryMaterialPage.getLevelLabelForNode(childNodeTitle),
                "Level 2",
                "Child node should display Level 2"
        );
    }

    @Test(description = "CM-MD-003")
    public void verifyEmptyMaterialMessageAndAddMaterialButton() {

        testNodeTitle = "CM_AUTO_" + System.currentTimeMillis();

        int insertedRows = DatabaseHandler.insert(
                "INSERT INTO syllabus_nodes (title) VALUES (?)",
                testNodeTitle
        );

        if (insertedRows != 1) {
            throw new IllegalStateException(
                    "Unable to create syllabus test node: " + testNodeTitle
            );
        }

        chemistryMaterialPage.clickChemistryMaterialsTab();
        chemistryMaterialPage.clickCloneFromSyllabus();

        getSoftAssert().assertTrue(
                chemistryMaterialPage.isNodeVisible(testNodeTitle),
                "Created syllabus node should be visible in Material Mapping"
        );

        getSoftAssert().assertTrue(
                chemistryMaterialPage.isNoMaterialsMessageVisibleForNode(testNodeTitle),
                "'No materials added yet.' message should be visible for the created node"
        );

        getSoftAssert().assertTrue(
                chemistryMaterialPage.isAddMaterialButtonVisibleForNode(testNodeTitle),
                "Add Material button should be visible for the created node"
        );

        chemistryMaterialPage.clickAddMaterialForNode(testNodeTitle);

        getSoftAssert().assertTrue(
                chemistryMaterialPage.isMaterialTopicInputVisibleForNode(testNodeTitle),
                "Material entry form should appear after clicking Add Material"
        );
    }

    @AfterMethod(alwaysRun = true)
    public void cleanupTestData() {

        if (childNodeTitle != null && !childNodeTitle.isBlank()) {
            DatabaseHandler.delete(
                    "DELETE FROM syllabus_nodes WHERE title = ?",
                    childNodeTitle
            );
        }

        if (parentNodeTitle != null && !parentNodeTitle.isBlank()) {
            DatabaseHandler.delete(
                    "DELETE FROM syllabus_nodes WHERE title = ?",
                    parentNodeTitle
            );
        }

        if (testNodeTitle != null && !testNodeTitle.isBlank()) {

            DatabaseHandler.delete(
                    "DELETE FROM syllabus_material_resources " +
                            "WHERE node_id IN " +
                            "(SELECT node_id FROM syllabus_nodes WHERE title = ?)",
                    testNodeTitle
            );

            DatabaseHandler.delete(
                    "DELETE FROM syllabus_nodes WHERE title = ?",
                    testNodeTitle
            );
        }
    }
}