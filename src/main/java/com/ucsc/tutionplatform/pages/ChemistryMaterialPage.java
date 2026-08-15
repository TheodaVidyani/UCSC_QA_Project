package com.ucsc.tutionplatform.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

public class ChemistryMaterialPage extends BasePage {

    // Tab Locators
    private final By chemistryMaterialsTab =
            By.xpath("//button[normalize-space()='Chemistry Materials']");

    private final By activeChemistryMaterialsTab = 
            By.xpath("//button[contains(@class,'switcher') and contains(@class,'active') and normalize-space()='Chemistry Materials']");

    private final By pageHeader =
            By.xpath("//h1|//div[contains(@class, 'card-head')]");

    // Action Controls
    private final By cloneFromSyllabusButton =
            By.xpath("//button[normalize-space()='Clone From Syllabus']");

    // Dynamic Node Locators
    private String nodeCardXPath(String nodeTitle) {
        String safeNodeTitle = toXPathLiteral(nodeTitle);

        return "//strong[normalize-space(.)=" + safeNodeTitle + "]" +
                "/ancestor::div[" +
                "contains(" +
                "concat(' ', normalize-space(@class), ' '), " +
                "' syllabus-node-card '" +
                ")" +
                "][1]";
    }

    private By nodeCard(String nodeTitle) {
        return By.xpath(nodeCardXPath(nodeTitle));
    }

    private By addMaterialButtonForNode(String nodeTitle) {
        return By.xpath(
                nodeCardXPath(nodeTitle) +
                        "/child::div[" +
                        "contains(" +
                        "concat(' ', normalize-space(@class), ' '), " +
                        "' syllabus-node-actions '" +
                        ")" +
                        "]" +
                        "/child::button[" +
                        "@type='button' and " +
                        "normalize-space(.)='Add Material'" +
                        "]"
        );
    }

    private By noMaterialsMessageForNode(String nodeTitle) {
        return By.xpath(
                nodeCardXPath(nodeTitle) +
                        "/descendant::*[" +
                        "not(*) and " +
                        "normalize-space(.)='No materials added yet.'" +
                        "]"
        );
    }

    private By materialTopicInputForNode(String nodeTitle) {
        return By.xpath(
                nodeCardXPath(nodeTitle) +
                        "/descendant::input[@placeholder='Material topic']"
        );
    }

    private By levelLabelForNode(String nodeTitle) {
        String safeNodeTitle = toXPathLiteral(nodeTitle);

        return By.xpath(
                "//strong[normalize-space(.)=" + safeNodeTitle + "]" +
                        "/preceding-sibling::span[" +
                        "contains(" +
                        "concat(' ', normalize-space(@class), ' '), " +
                        "' chip '" +
                        ") and " +
                        "contains(" +
                        "concat(' ', normalize-space(@class), ' '), " +
                        "' subtle '" +
                        ") and " +
                        "starts-with(normalize-space(.), 'Level ')" +
                        "][1]"
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

    private final By chemistryMaterialsCard = 
            By.xpath("//article[contains(@class,'detail-card') and contains(@class,'class-videos-toolbar-card')]");

    private final By materialMappingCard = 
            By.xpath("//article[contains(@class,'user-list-card') and contains(@class,'syllabus-tree-card')]");

    // XPath Sanitization Helper
    private static String toXPathLiteral(String value) {
        if (value == null) {
            throw new IllegalArgumentException("XPath value cannot be null");
        }

        if (!value.contains("'")) {
            return "'" + value + "'";
        }

        if (!value.contains("\"")) {
            return "\"" + value + "\"";
        }

        String[] parts = value.split("'", -1);

        return "concat('" +
                String.join("', \"'\", '", parts) +
                "')";
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
        } catch (TimeoutException exception) {
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

    // Actions for CHEM_MAT_OO2
    public boolean isChemistryMaterialsSectionDisplayed() {
        return seleniumCardrige.isDisplayed(chemistryMaterialsCard);
    }

    public boolean isMaterialMappingSectionDisplayed() {
        return seleniumCardrige.isDisplayed(materialMappingCard);
    }

    public String getLevelLabelForNode(String nodeTitle) {
        return seleniumCardrige.getText(levelLabelForNode(nodeTitle));
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
                && seleniumCardrige.isDisplayed(fileUploadInput)
                && seleniumCardrige.isDisplayed(removeButton)
                && seleniumCardrige.isDisplayed(addAnotherMaterialButton);
    }
}