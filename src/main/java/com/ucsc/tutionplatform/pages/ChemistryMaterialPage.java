package com.ucsc.tutionplatform.pages;

import org.openqa.selenium.By;

public class ChemistryMaterialPage extends BasePage {

    // Tab Locator
    private final By chemistryMaterialsTab =
            By.xpath("//button[normalize-space()='Chemistry Materials']");

    private final By pageHeader =
            By.xpath("//h1|//div[contains(@class, 'card-head')]");

    // Action Controls
    private final By cloneFromSyllabusButton =
            By.xpath("//button[normalize-space()='Clone From Syllabus']");

    // Dynamic Node Locators
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

    private By levelLabelForNode(String nodeTitle) {
        return By.xpath(
                "(//*[normalize-space()='" + nodeTitle + "']" +
                        " | //input[@value='" + nodeTitle + "'])" +
                        "/ancestor::div[contains(@class,'syllabus-node-card')][1]" +
                        "//span[contains(@class,'chip') and contains(@class,'subtle')]"
        );
    }

    private By getNodeHeader(String nodeName) {
        return By.xpath(
                "//div[contains(@class,'syllabus-node-main')]" +
                        "//strong[normalize-space()='" + nodeName + "']"
        );
    }

    private By getAddMaterialButtonForNode(String nodeName) {
        return By.xpath(
                "//div[contains(@class,'syllabus-node-block')]" +
                        "[.//strong[normalize-space()='" + nodeName + "']]" +
                        "//button[normalize-space()='Add Material']"
        );
    }

    // Material Entry Form Controls
    private final By pdfBadge =
            By.xpath("//div[contains(@class,'practical-resource-row')]" +
                    "//span[contains(@class,'chip') and normalize-space()='PDF']");

    private final By materialTopicInput =
            By.xpath("//input[@placeholder='Material topic']");

    private final By uploadPdfDisplayInput =
            By.xpath("//input[@placeholder='Upload a PDF file']");

    private final By fileUploadInput =
            By.xpath("//input[@type='file' and contains(@accept, 'pdf')]");

    private final By removeButton =
            By.xpath("//button[contains(@class,'danger') and normalize-space()='Remove']");

    private final By addAnotherMaterialButton =
            By.xpath("//button[normalize-space()='Add Another Material']");


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

    public String getLevelLabelForNode(String nodeTitle) {
        return seleniumCardrige.getText(
                levelLabelForNode(nodeTitle)
        );
    }

    // Actions for CM-AM-002
    public void enterMaterialTopic(String topic) {
        seleniumCardrige.type(materialTopicInput, topic);
    }

    public String getEnteredMaterialTopic() {
        return seleniumCardrige.getValue(materialTopicInput);
    }

    public void blurFocusFromTopicInput() {
        seleniumCardrige.click(pdfBadge);
    }

    // Verification method for CM-AM-001
    public boolean isMaterialEntryFormDisplayed() {
        seleniumCardrige.waitUntilVisible(materialTopicInput);

        return seleniumCardrige.isDisplayed(pdfBadge)
                && seleniumCardrige.isDisplayed(materialTopicInput)
                && seleniumCardrige.isDisplayed(uploadPdfDisplayInput)
                && seleniumCardrige.isDisplayed(removeButton)
                && seleniumCardrige.isDisplayed(addAnotherMaterialButton);
    }
}