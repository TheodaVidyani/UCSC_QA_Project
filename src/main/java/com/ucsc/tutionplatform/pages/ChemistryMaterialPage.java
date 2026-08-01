//Chemistry Material Page

package com.ucsc.tutionplatform.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ChemistryMaterialPage extends BasePage{

    // Locator for the Chemistry Materials tab/button based on your DOM snippet
    private By chemistryMaterialsTab = By.xpath("//button[normalize-space()='Chemistry Materials']");

    // Header check locator
    private By pageHeader = By.xpath("//h1|//div[contains(@class, 'card-head')]"); // Adjust based on your header element structure

    private By cloneFromSyllabus = By.xpath("//button[normalize-space()='Clone From Syllabus']");
    private By saveMaterialsLibrary = By.xpath("//button[normalize-space()='Save Materials Library']");
    private By reload = By.xpath("//button[normalize-space()='Reload']");
    private By pageBody = By.tagName("body");

    // The constructor is mandatory to pass the driver to the BasePage
    public ChemistryMaterialPage(WebDriver driver) {
        super(driver);
    }

    public void clickChemistryMaterialsTab() {
        click(chemistryMaterialsTab);
    }

    public boolean isChemistryMaterialsButtonVisible() {
        return isElementVisible(chemistryMaterialsTab);
    }

    public boolean isChemistryMaterialsButtonEnabled() {
        WebElement btn = driver.findElement(chemistryMaterialsTab);
        // Hover over the element first as requested in your test steps
        Actions actions = new Actions(driver);
        actions.moveToElement(btn).perform();
        
        return btn.isEnabled() && btn.isDisplayed();
    }

    public boolean isChemistryMaterialsPageLoaded() {
        boolean hasRenderedContent = !driver.findElement(pageBody).getText().trim().isEmpty();
        boolean hasWorkspaceElements = isElementVisible(pageHeader)
                || isElementVisible(cloneFromSyllabus)
                || isElementVisible(saveMaterialsLibrary)
                || isElementVisible(reload);

        return hasRenderedContent && hasWorkspaceElements;
    }

}
