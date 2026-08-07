package com.ucsc.tutionplatform.pages;

import org.openqa.selenium.By;

public class ChemistryMaterialPage extends BasePage {

    private final By chemistryMaterialsTab = By.xpath("//button[normalize-space()='Chemistry Materials']");
    private final By pageHeader = By.xpath("//h1|//div[contains(@class, 'card-head')]");

    public void clickChemistryMaterialsTab() {
        seleniumCardrige.click(chemistryMaterialsTab);
    }

    public boolean isChemistryMaterialsButtonVisible() {
        return seleniumCardrige.isDisplayed(chemistryMaterialsTab);
    }

    public String getChemistryMaterialsTabText() {
        return seleniumCardrige.getText(chemistryMaterialsTab);
    }
}