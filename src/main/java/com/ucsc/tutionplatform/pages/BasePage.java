package com.ucsc.tutionplatform.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    private final By usernameInput = By.xpath("//input[@name='username' or @id='username' or contains(translate(@placeholder, 'USERNAME', 'username'), 'username') or @type='text']");
    private final By passwordInput = By.xpath("//input[@name='password' or @id='password' or @type='password']");
    private final By loginButton = By.xpath("//button[@type='submit' or normalize-space()='Login' or normalize-space()='Log In' or normalize-space()='Sign in'] | //input[@type='submit']");

    // Configuration Properties
    protected final String BASE_URL = "http://75.119.154.239/admin";
    protected final String ADMIN_USER = "groupa";
    protected final String ADMIN_PASS = "123456";

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // --- Core Navigation ---
    public void navigateToApp() {
        driver.get(BASE_URL);
    }

    public void loginAsAdmin() {
        navigateToApp();
        type(usernameInput, ADMIN_USER);
        type(passwordInput, ADMIN_PASS);
        click(loginButton);
    }

    // --- Core Wrapper Methods ---
    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void type(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    protected boolean isElementVisible(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}