package com.ucsc.tutionplatform.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class BaseTest {

    //Assertion path tells how to read detail json files. So we have to assign the path.
    //we define variable assertionpath here, but the assigning part happens at other separate BaseTest.java which are being extended by BaseClass.java
    protected final String assertionPath;

    protected BaseTest(String assertionPath){
        this.assertionPath = assertionPath;
    }
    @DataProvider
    public Object[][] commonDataProvider(Method method){
        System.out.println(method.getAnnotation(Test.class).description());
        return null;
    }
}
