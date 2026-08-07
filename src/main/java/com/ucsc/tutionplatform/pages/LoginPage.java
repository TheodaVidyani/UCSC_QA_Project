package com.ucsc.tutionplatform.pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private final By usernameInput =
            By.xpath("//input[@placeholder='Admin username']");

    private final By passwordInput =
            By.xpath("//input[@placeholder='Admin password']");

    private final By loginButton =
            By.xpath("//button[@type='submit' and normalize-space()='Login as admin']");

    public void enterUsername(String username) {
        seleniumCardrige.type(usernameInput, username);
    }

    public void enterPassword(String password) {
        seleniumCardrige.type(passwordInput, password);
    }

    public void clickLoginButton() {
        seleniumCardrige.click(loginButton);
    }

    public void loginAsAdmin(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();

        seleniumCardrige.waitUntilInvisible(usernameInput);
    }
}