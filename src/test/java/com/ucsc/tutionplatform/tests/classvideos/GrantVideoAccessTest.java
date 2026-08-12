package com.ucsc.tutionplatform.tests.classvideos;

import com.ucsc.tutionplatform.consts.Constants;
import com.ucsc.tutionplatform.models.TestData;
import com.ucsc.tutionplatform.pages.ClassVideosPage;
import com.ucsc.tutionplatform.pages.LoginPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GrantVideoAccessTest extends ClassVideosBaseTest {

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

    @Test(description = "CV_TC_007", dataProvider = "commonDataProvider")
    public void verifyAssignVideosToSyllabusNodes(TestData testData){

        classVideosPage.selectVideoToGrantAccess();
        classVideosPage.enterStartAndEndDates(testData.getStartDate(), testData.getEndDate());
        classVideosPage.selectStudentByName(testData.getName());
        classVideosPage.clickGrantAccess();
        classVideosPage.clickReload();
        getSoftAssert().assertEquals(classVideosPage.getGrantedVideoTopic(), testData.getVideoTopic());
    }
}