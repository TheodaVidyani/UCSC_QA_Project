package com.ucsc.tutionplatform.tests.chemistrymaterials;

import com.ucsc.tutionplatform.core.DriverManager;
import com.ucsc.tutionplatform.pages.ChemistryMaterialPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ChemistryMaterialsBasicPageTests extends ChemistryMaterialsBaseTest {

    private ChemistryMaterialPage chemistryMaterialPage;

    @BeforeMethod
    public void initPage() {
        chemistryMaterialPage = new ChemistryMaterialPage(DriverManager.getDriver());
        // Navigate and click the tab once so it's ready for each test
        chemistryMaterialPage.navigateToApp();
        new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Chemistry Materials']")));
        chemistryMaterialPage.clickChemistryMaterialsTab();
    }

    @Test(description = "CHEM_MAT_001: Verify Chemistry Materials button is visible and enabled")
    public void verifyChemistryMaterialsButtonTest() {
        boolean isVisible = chemistryMaterialPage.isChemistryMaterialsButtonVisible();
        Assert.assertTrue(isVisible, "Chemistry Materials button should be visible!");

        boolean isEnabled = chemistryMaterialPage.isChemistryMaterialsButtonEnabled();
        Assert.assertTrue(isEnabled, "Chemistry Materials button should be enabled and interactable!");
    }

    @Test(description = "CHEM_MAT_002: Verify Chemistry Materials page loads successfully without crash or blank page")
    public void verifyChemistryMaterialsPageLoadTest() {
        boolean isPageLoaded = chemistryMaterialPage.isChemistryMaterialsPageLoaded();
        Assert.assertTrue(isPageLoaded, "Chemistry Materials page should load successfully without a blank or crashed UI.");
    }

    @Test(description = "CHEM_MAT_003: Verify Chemistry Materials tab is active")
    public void verifyChemistryMaterialsTabActiveTest() {
        chemistryMaterialPage.waitForChemistryMaterialsTabActive();

        Assert.assertTrue(
                chemistryMaterialPage.isChemistryMaterialsSectionHeaderVisible(),
                "Chemistry Materials section header should be visible after opening the section."
        );
        Assert.assertEquals(
                chemistryMaterialPage.getChemistryMaterialsTabText(),
                "Chemistry Materials",
                "Chemistry Materials tab title should match the expected section title."
        );
        Assert.assertTrue(
                chemistryMaterialPage.isChemistryMaterialsTabActive(),
                "Chemistry Materials tab should be marked active after clicking it."
        );
    }
}