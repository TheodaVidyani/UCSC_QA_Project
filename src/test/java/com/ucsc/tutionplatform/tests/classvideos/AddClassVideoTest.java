package com.ucsc.tutionplatform.tests.classvideos;

import com.ucsc.tutionplatform.consts.Constants;
import com.ucsc.tutionplatform.models.TestData;
import com.ucsc.tutionplatform.pages.ClassVideos;
import com.ucsc.tutionplatform.selenium.SeleniumCardrige;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddClassVideoTest extends ClassVideosBaseTest {

    @BeforeClass(alwaysRun = true)
    public void navigateToUserDetailsPage() {
        driver().get(Constants.USER_DETAILS_URL);
    }

    @BeforeMethod
    public void clickAddVideo()
    {

    }

    @Test(description = "CV_TC_006", dataProvider = "commonDataProvider")
    public void verifyThatVideoEntriesCanBeSavedSuccessfully(TestData testData){

        System.out.println(testData.getVideoTopic());
        getSoftAssert().assertNotNull(testData.getName(), "Name should be available in test data");
    }
}