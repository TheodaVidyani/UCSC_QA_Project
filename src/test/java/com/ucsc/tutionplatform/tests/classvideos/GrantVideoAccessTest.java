package com.ucsc.tutionplatform.tests.classvideos;

import com.ucsc.tutionplatform.consts.Constants;
import com.ucsc.tutionplatform.models.TestData;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GrantVideoAccessTest extends ClassVideosBaseTest {

    @BeforeClass(alwaysRun = true)
    public void navigateToUserDetailsPage() {
        driver().get(Constants.USER_DETAILS_URL);
    }

    @Test(description = "CV_TC_00", dataProvider = "commonDataProvider")
    public void verifyThatUserCanBeCreatedByProvidingMandatoryFieldsOnly(TestData testData){
        System.out.println(testData.getName());
        getSoftAssert().assertNotNull(testData.getName(), "Name should be available in test data");
    }
}