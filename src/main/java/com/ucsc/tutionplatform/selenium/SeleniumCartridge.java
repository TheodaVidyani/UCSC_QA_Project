package com.ucsc.tutionplatform.selenium;

import com.ucsc.tutionplatform.core.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SeleniumCartridge {

    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);

    public WebDriver driver(){
        return DriverManager.getDriver();
    }

    public WebElement findElement(By locator){
        return driver().findElement(locator);
    }

    public List<WebElement> findElements(By locator){
        return driver().findElements(locator);
    }
    public WebElement waitUntilVisible(By locator) {
        return wait(DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitUntilClickable(By locator) {
        return wait(DEFAULT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void click(By locator) {
        waitUntilClickable(locator).click();
    }

    public void type(By locator, String value) {
        WebElement element = waitUntilVisible(locator);
        element.clear();
        element.sendKeys(value);
    }

    public void clear(By locator) {
        waitUntilVisible(locator).clear();
    }

    public String getText(By locator) {
        return waitUntilVisible(locator).getText();
    }

    public boolean isDisplayed(By locator) {
        return waitUntilVisible(locator).isDisplayed();
    }

    private WebDriverWait wait(Duration timeout) {
        return new WebDriverWait(driver(), timeout);
    }
}

