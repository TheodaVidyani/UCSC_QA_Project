// Pages represent application screens/components. 
// Contains locators and methods/actions to interact with the elements on the page.

package com.ucsc.tutionplatform.pages;

import org.openqa.selenium.By;

public class ChemistryMaterialPage extends BasePage {

    private final By chemistryMaterialsTab =
            By.xpath("//button[normalize-space()='Chemistry Materials']");

    private final By pageHeader =
            By.xpath("//h1|//div[contains(@class, 'card-head')]");

    private final By cloneFromSyllabusButton =
            By.xpath("//button[normalize-space()='Clone From Syllabus']");

    private By nodeCard(String nodeTitle) {
        return By.xpath(
                "(//*[normalize-space()='" + nodeTitle + "']" +
                        " | //input[@value='" + nodeTitle + "'])" +
                        "/ancestor::div[contains(@class,'syllabus-node-card')][1]"
        );
    }

    private By addMaterialButtonForNode(String nodeTitle) {
        return By.xpath(
                "(//*[normalize-space()='" + nodeTitle + "']" +
                        " | //input[@value='" + nodeTitle + "'])" +
                        "/ancestor::div[contains(@class,'syllabus-node-card')][1]" +
                        "//button[normalize-space()='Add Material']"
        );
    }

    private By noMaterialsMessageForNode(String nodeTitle) {
        return By.xpath(
                "(//*[normalize-space()='" + nodeTitle + "']" +
                        " | //input[@value='" + nodeTitle + "'])" +
                        "/ancestor::div[contains(@class,'syllabus-node-card')][1]" +
                        "//*[normalize-space()='No materials added yet.']"
        );
    }

    private By materialTopicInputForNode(String nodeTitle) {
        return By.xpath(
                "(//*[normalize-space()='" + nodeTitle + "']" +
                        " | //input[@value='" + nodeTitle + "'])" +
                        "/ancestor::div[contains(@class,'syllabus-node-card')][1]" +
                        "//input[@placeholder='Material topic']"
        );
    }

    public void clickChemistryMaterialsTab() {
        seleniumCardrige.click(chemistryMaterialsTab);
    }

    public boolean isChemistryMaterialsButtonVisible() {
        return seleniumCardrige.isDisplayed(chemistryMaterialsTab);
    }

    public String getChemistryMaterialsTabText() {
        return seleniumCardrige.getText(chemistryMaterialsTab);
    }

    public void clickCloneFromSyllabus() {
        seleniumCardrige.click(cloneFromSyllabusButton);
    }

    public boolean isNodeVisible(String nodeTitle) {
        try {
            seleniumCardrige.waitUntilVisible(nodeCard(nodeTitle));
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public boolean isNoMaterialsMessageVisibleForNode(String nodeTitle) {
        return seleniumCardrige.isDisplayed(
                noMaterialsMessageForNode(nodeTitle)
        );
    }

    public boolean isAddMaterialButtonVisibleForNode(String nodeTitle) {
        return seleniumCardrige.isDisplayed(
                addMaterialButtonForNode(nodeTitle)
        );
    }

    public void clickAddMaterialForNode(String nodeTitle) {
        By locator = addMaterialButtonForNode(nodeTitle);

        seleniumCardrige.scrollToElement(locator);
        seleniumCardrige.click(locator);
    }

    public boolean isMaterialTopicInputVisibleForNode(String nodeTitle) {
        return seleniumCardrige.isDisplayed(
                materialTopicInputForNode(nodeTitle)
        );
    }

    private By levelLabelForNode(String nodeTitle) {
        return By.xpath(
                "(//*[normalize-space()='" + nodeTitle + "']" +
                        " | //input[@value='" + nodeTitle + "'])" +
                        "/ancestor::div[contains(@class,'syllabus-node-card')][1]" +
                        "//span[contains(@class,'chip') and contains(@class,'subtle')]"
        );
    }

    public String getLevelLabelForNode(String nodeTitle) {
        return seleniumCardrige.getText(
                levelLabelForNode(nodeTitle)
        );
    }
}