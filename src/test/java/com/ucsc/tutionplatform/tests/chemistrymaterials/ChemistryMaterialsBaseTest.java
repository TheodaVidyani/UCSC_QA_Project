package com.ucsc.tutionplatform.tests.chemistrymaterials;

import com.ucsc.tutionplatform.constants.Constants;
import com.ucsc.tutionplatform.core.DriverManager;
import com.ucsc.tutionplatform.pages.ChemistryMaterialPage;
import com.ucsc.tutionplatform.tests.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class ChemistryMaterialsBaseTest extends BaseTest {

    public ChemistryMaterialsBaseTest() {
        super(Constants.CHEMISTRY_MATERIALS_ASSERTION_PATH);
    }

    @BeforeMethod
    public void setUpDriver() {
        // 1. Initialize Chrome browser using WebDriverManager
        WebDriver driver = io.github.bonigarcia.wdm.WebDriverManager.chromedriver().create();
        
        // 2. Register it with your thread-safe DriverManager
        DriverManager.setDriver(driver);
        
        // 3. Maximize window
        try {
            driver.manage().window().maximize();
        } catch (Exception ignored) {
            // Some Chrome sessions reject maximize; the tests only need a usable window.
        }

        // 4. Log in as admin so dashboard elements are available to the tests
        ChemistryMaterialPage chemistryMaterialPage = new ChemistryMaterialPage(driver);
        chemistryMaterialPage.loginAsAdmin();

        // 5. Wait for the admin workspace to load before the test starts interacting with it
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Chemistry Materials']")));
    }

    @AfterMethod
    public void tearDownDriver() {
        // 6. Safely quit and remove the driver after each test
        DriverManager.quitDriver();
    }
}