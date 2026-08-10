package com.ucsc.tutionplatform.tests.classvideos;

import com.ucsc.tutionplatform.consts.Constants;
import com.ucsc.tutionplatform.pages.ChemistryMaterialPage;
import com.ucsc.tutionplatform.pages.ClassVideosPage;
import com.ucsc.tutionplatform.selenium.SeleniumCardrige;
import com.ucsc.tutionplatform.tests.BaseTest;
import com.ucsc.tutionplatform.utils.ConfigReader;
import org.testng.annotations.BeforeClass;

public class ClassVideosBaseTest extends BaseTest {


    private ClassVideosPage classVideosPage;

    public ClassVideosBaseTest() {
        super(Constants.CLASS_VIDEO_ASSERTION_PATH);
    }

}