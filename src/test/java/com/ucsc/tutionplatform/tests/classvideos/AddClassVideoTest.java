package com.ucsc.tutionplatform.tests.classvideos;

import com.ucsc.tutionplatform.consts.Constants;
import com.ucsc.tutionplatform.models.TestData;
import com.ucsc.tutionplatform.pages.ChemistryMaterialPage;
import com.ucsc.tutionplatform.pages.ClassVideosPage;
import com.ucsc.tutionplatform.pages.LoginPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddClassVideoTest extends ClassVideosBaseTest {

    private ClassVideosPage classVideosPage;
    private LoginPage loginPage;

    @BeforeClass(alwaysRun = true)
    public void setupPage() {
        driver().get(Constants.USER_DETAILS_URL);
        classVideosPage = new ClassVideosPage();
        loginPage = new LoginPage();
        loginPage.loginAsAdmin("groupa", "123456");
        classVideosPage.clickClassVideosTab();
    }

    @BeforeMethod
    public void clickAddVideo()
    {
        classVideosPage.clickAddVideo();
    }

    @Test(description = "CV_TC_006", dataProvider = "commonDataProvider")
    public void verifyThatVideoEntriesCanBeSavedSuccessfully(TestData testData){

        classVideosPage.enterVideoDetails(testData.getVideoTopic(), testData.getVideoUrl());
        classVideosPage.clickSaveVideoLibrary();
        classVideosPage.clickReload();
        getSoftAssert().assertEquals(classVideosPage.getVideoTopicText("1"), testData.getVideoTopic());
        getSoftAssert().assertEquals(classVideosPage.getVideoUrlText("1"), testData.getVideoUrl());
    }
}