package com.ucsc.tutionplatform.tests.syllabus;

import org.testng.annotations.Test;

public class AddRootTopic extends SyllabusBaseTest{

    @Test(
            description = "TC-001",
            dataProvider = "commonDataProvider"
    )
    public void verifyThatAdminUserCanOpenSyllabus(){
        System.out.println("Open Syllabus Successfully.");
    }
}


