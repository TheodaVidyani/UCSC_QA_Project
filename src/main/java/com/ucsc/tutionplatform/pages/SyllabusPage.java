package com.ucsc.tutionplatform.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class SyllabusPage extends BasePage {

    // Main Tab & Page Locators
    private final By syllabusTab = By.xpath("//button[normalize-space()='Syllabus' or contains(text(),'Syllabus')]");
    private final By syllabusHeader = By.xpath("//h3[normalize-space()='Syllabus Tree Editor']");
    private final By addRootTopicButton = By.xpath("//button[normalize-space()='Add Root Topic']");
    private final By saveTreeButton = By.xpath("//button[normalize-space()='Save Tree']");
    private final By reloadButton = By.xpath("//button[normalize-space()='Reload']");
    private final By rootDropZone = By.xpath("//div[contains(@class,'syllabus-root-dropzone')]");
    private final By treeNodesPanel = By.xpath("//article[contains(@class,'syllabus-tree-card')]");
    private final By emptyStateMessage = By.xpath("//*[contains(text(),'No syllabus nodes yet') or contains(text(),'Start by adding a root topic')]");
    private final By footer = By.xpath("//div[contains(@class,'portal-credit')]");

    // Dynamic Node Locators
    private By nodeCard(String nodeTitle) {
        return By.xpath(
                "(//*[normalize-space()='" + nodeTitle + "']" +
                        " | //input[@value='" + nodeTitle + "'])" +
                        "/ancestor::div[contains(@class,'syllabus-node-card')][1]"
        );
    }

    private By nodeInput(String nodeTitle) {
        return By.xpath(
                "(//*[normalize-space()='" + nodeTitle + "']" +
                        " | //input[@value='" + nodeTitle + "'])" +
                        "/ancestor::div[contains(@class,'syllabus-node-card')][1]" +
                        "//input[@placeholder='Topic text' or @type='text']"
        );
    }

    private By addChildButtonForNode(String nodeTitle) {
        return By.xpath(
                "(//*[normalize-space()='" + nodeTitle + "']" +
                        " | //input[@value='" + nodeTitle + "'])" +
                        "/ancestor::div[contains(@class,'syllabus-node-card')][1]" +
                        "//button[normalize-space()='Add Child']"
        );
    }

    private By upButtonForNode(String nodeTitle) {
        return By.xpath(
                "(//*[normalize-space()='" + nodeTitle + "']" +
                        " | //input[@value='" + nodeTitle + "'])" +
                        "/ancestor::div[contains(@class,'syllabus-node-card')][1]" +
                        "//button[normalize-space()='Up']"
        );
    }

    private By downButtonForNode(String nodeTitle) {
        return By.xpath(
                "(//*[normalize-space()='" + nodeTitle + "']" +
                        " | //input[@value='" + nodeTitle + "'])" +
                        "/ancestor::div[contains(@class,'syllabus-node-card')][1]" +
                        "//button[normalize-space()='Down']"
        );
    }

    private By deleteButtonForNode(String nodeTitle) {
        return By.xpath(
                "(//*[normalize-space()='" + nodeTitle + "']" +
                        " | //input[@value='" + nodeTitle + "'])" +
                        "/ancestor::div[contains(@class,'syllabus-node-card')][1]" +
                        "//button[normalize-space()='Delete']"
        );
    }

    private By levelLabelForNode(String nodeTitle) {
        return By.xpath(
                "(//*[normalize-space()='" + nodeTitle + "']" +
                        " | //input[@value='" + nodeTitle + "'])" +
                        "/ancestor::div[contains(@class,'syllabus-node-card')][1]" +
                        "//span[contains(@class,'chip')]"
        );
    }

    // Actions & Methods
    public void clickSyllabusTab() {
        try {
            seleniumCardrige.waitUntilVisible(syllabusTab);
            seleniumCardrige.click(syllabusTab);
        } catch (Exception e) {
            seleniumCardrige.jsClick(syllabusTab);
        }
        try {
            seleniumCardrige.waitUntilVisible(addRootTopicButton);
        } catch (Exception ignored) {}
    }

    public boolean isSyllabusTabVisible() {
        return seleniumCardrige.isDisplayed(syllabusTab);
    }

    public boolean isSyllabusTabHighlighted() {
        try {
            By activeSyllabusTab = By.xpath("//button[contains(@class,'switcher') and contains(@class,'active') and normalize-space()='Syllabus']");
            return seleniumCardrige.isDisplayed(activeSyllabusTab);
        } catch (Exception e) {
            return false;
        }
    }

    public String getSyllabusTabText() {
        return seleniumCardrige.getText(syllabusTab);
    }

    public boolean isSyllabusHeaderVisible() {
        return seleniumCardrige.isDisplayed(syllabusHeader);
    }

    public void clickAddRootTopic() {
        seleniumCardrige.click(addRootTopicButton);
    }

    public boolean isAddRootTopicButtonVisible() {
        return seleniumCardrige.isDisplayed(addRootTopicButton);
    }

    public void clickSaveTree() {
        seleniumCardrige.click(saveTreeButton);
        try {
            Thread.sleep(1500);
        } catch (Exception ignored) {}
    }

    public boolean isSaveTreeButtonVisible() {
        return seleniumCardrige.isDisplayed(saveTreeButton);
    }

    public void clickReload() {
        try {
            Thread.sleep(1000);
            seleniumCardrige.jsClick(reloadButton);
            Thread.sleep(1000);
        } catch (Exception e) {
            seleniumCardrige.click(reloadButton);
        }
    }

    public boolean isReloadButtonVisible() {
        return seleniumCardrige.isDisplayed(reloadButton);
    }

    public boolean isNodeVisible(String topicName) {
        try {
            By locator = nodeCard(topicName);
            seleniumCardrige.waitUntilVisible(locator);
            return true;
        } catch (Exception exception) {
            return seleniumCardrige.isDisplayed(nodeCard(topicName));
        }
    }

    public String getLevelLabelForNode(String topicName) {
        return seleniumCardrige.getText(levelLabelForNode(topicName));
    }

    public String getEnteredTopicValue(String topicName) {
        return seleniumCardrige.getValue(nodeInput(topicName));
    }

    public void enterTopicName(String oldTopicName, String newTopicName) {
        By locator = nodeInput(oldTopicName);
        WebElement input = seleniumCardrige.waitUntilVisible(locator);
        input.sendKeys(Keys.CONTROL + "a");
        input.sendKeys(Keys.BACK_SPACE);
        input.sendKeys(newTopicName);
        input.sendKeys(Keys.TAB);
        seleniumCardrige.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", input);
        seleniumCardrige.executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", input);
        seleniumCardrige.click(syllabusHeader);
    }

    public void setTopicTextForNewNode(String topicName) {
        By newTopicInput = By.xpath("//div[contains(@class,'syllabus-node-card')]//input[@value='New Topic' or @value='']");
        try {
            WebElement input = seleniumCardrige.waitUntilVisible(newTopicInput);
            input.sendKeys(Keys.CONTROL + "a");
            input.sendKeys(Keys.BACK_SPACE);
            input.sendKeys(topicName);
            input.sendKeys(Keys.TAB);
            seleniumCardrige.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", input);
            seleniumCardrige.executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", input);
            seleniumCardrige.click(syllabusHeader);
        } catch (Exception e) {
            By fallbackInput = By.xpath("(//div[contains(@class,'syllabus-node-card')]//input)[last()]");
            WebElement input = seleniumCardrige.waitUntilVisible(fallbackInput);
            input.sendKeys(Keys.CONTROL + "a");
            input.sendKeys(Keys.BACK_SPACE);
            input.sendKeys(topicName);
            input.sendKeys(Keys.TAB);
            seleniumCardrige.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", input);
            seleniumCardrige.executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", input);
            seleniumCardrige.click(syllabusHeader);
        }
    }

    public void clickAddChildForNode(String topicName) {
        By locator = addChildButtonForNode(topicName);
        seleniumCardrige.scrollToElement(locator);
        seleniumCardrige.click(locator);
    }

    public boolean isAddChildButtonVisibleForNode(String topicName) {
        return seleniumCardrige.isDisplayed(addChildButtonForNode(topicName));
    }

    public void clickUpForNode(String topicName) {
        By locator = upButtonForNode(topicName);
        seleniumCardrige.scrollToElement(locator);
        seleniumCardrige.click(locator);
    }

    public void clickDownForNode(String topicName) {
        By locator = downButtonForNode(topicName);
        seleniumCardrige.scrollToElement(locator);
        seleniumCardrige.click(locator);
    }

    public void clickDeleteForNode(String topicName) {
        By locator = deleteButtonForNode(topicName);
        seleniumCardrige.scrollToElement(locator);
        seleniumCardrige.click(locator);
    }

    public void dragAndDropNode(String sourceTopicName, String targetTopicName) {
        By source = nodeCard(sourceTopicName);
        By target = nodeCard(targetTopicName);
        seleniumCardrige.dragAndDrop(source, target);
    }

    public void dragNodeToRootZone(String sourceTopicName) {
        By source = nodeCard(sourceTopicName);
        seleniumCardrige.dragAndDrop(source, rootDropZone);
    }

    public boolean isEmptyStateMessageVisible() {
        return seleniumCardrige.isDisplayed(emptyStateMessage);
    }

    public boolean isTreeNodesPanelVisible() {
        return seleniumCardrige.isDisplayed(treeNodesPanel);
    }

    public boolean isNodeInTreeNodesPanel(String topicName) {
        By panelItem = By.xpath("//article[contains(@class,'syllabus-tree-card')]//*[normalize-space()='" + topicName + "']");
        return seleniumCardrige.isDisplayed(panelItem);
    }

    public boolean isFooterVisible() {
        return seleniumCardrige.isDisplayed(footer);
    }

    public int getNodeCount() {
        return seleniumCardrige.getElementCount(By.xpath("//div[contains(@class,'syllabus-node-card')]"));
    }
}
