package com.ucsc.tutionplatform.constants;

import java.io.File;

public class Constants {

    public static final String ASSERT_DIR = "AssertDir" + File.separator;

    public static final String SYLLABUS_ASSERTION_PATH = ASSERT_DIR + "syllabus_details.json";

    public static final String USER_DETAILS_ASSERTION_PATH = ASSERT_DIR + "user_details.json";

    private Constants() {
    }
}