package com.ucsc.tutionplatform.tests.chemistrymaterials;

import com.ucsc.tutionplatform.consts.Constants;
import com.ucsc.tutionplatform.database.DatabaseHandler;
import com.ucsc.tutionplatform.pages.ChemistryMaterialPage;
import com.ucsc.tutionplatform.pages.LoginPage;
import com.ucsc.tutionplatform.tests.BaseTest;
import com.ucsc.tutionplatform.utils.ConfigReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class ChemistryMaterialsBaseTest extends BaseTest {

    private static final String ADMIN_USERNAME_PROPERTY = "admin.username";
    private static final String ADMIN_PASSWORD_PROPERTY = "admin.password";

    protected ChemistryMaterialPage chemistryMaterialPage;

    protected String testNodeTitle;
    protected String parentNodeTitle;
    protected String childNodeTitle;

    private LoginPage loginPage;

    public ChemistryMaterialsBaseTest() {
        super(Constants.CHEMISTRY_MATERIALS_ASSERTION_PATH);
    }


    @BeforeClass(alwaysRun = true)
    public void initializeChemistryMaterialsSession() {

        chemistryMaterialPage = new ChemistryMaterialPage();
        loginPage = new LoginPage();

        driver().get(Constants.USER_DETAILS_URL);

        loginPage.loginAsAdmin(
                ConfigReader.getProperty(ADMIN_USERNAME_PROPERTY),
                ConfigReader.getProperty(ADMIN_PASSWORD_PROPERTY)
        );
    }

    @BeforeMethod(alwaysRun = true)
    public void prepareChemistryMaterialsTest() {

        testNodeTitle = null;
        parentNodeTitle = null;
        childNodeTitle = null;

        driver().get(Constants.USER_DETAILS_URL);
    }

    @AfterMethod(alwaysRun = true)
    public void cleanupChemistryMaterialsTestData() {

        /*
         * Delete the child node before its parent because the child
         * contains a parent_id reference to the parent node.
         */
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

        /*
         * Delete any material resources related to the independent
         * CM-MD-003 node before deleting the syllabus node itself.
         */
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