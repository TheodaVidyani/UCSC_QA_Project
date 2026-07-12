package com.ucsc.tutionplatform.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ChemistryMaterialPage extends BasePage{

    private By cloneFromSyllabus = By.xpath("//button[normalize-space()='Clone From Syllabus']");
    private By saveMaterialsLibrary = By.xpath("//button[normalize-space()='Save Materials Library']");
    private By reload = By.xpath("//button[normalize-space()='Reload']");

    // The constructor is mandatory to pass the driver to the BasePage
    public ChemistryMaterialPage(WebDriver driver) {
        super(driver);
    }
}
