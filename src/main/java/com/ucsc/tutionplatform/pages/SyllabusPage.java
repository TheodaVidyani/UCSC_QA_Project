package com.ucsc.tutionplatform.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SyllabusPage extends BasePage{

    private By addRootTopic = By.xpath("//button[normalize-space()='Add Root Topic']");
    private By saveTree = By.xpath("//button[normalize-space()='Save Tree']");
    private By reload = By.xpath("//button[normalize-space()='Reload']");

    private By topicByIndex(String index){
        return By.xpath(String.format("(//input[@placeholder='Topic text'])[%s]", index));
    }

    private By topicByValue(String value){
        return By.xpath(String.format("//input[@placeholder='Topic text' and @value='[%s]']", value));
    }

    //comment one of the following
    private By topic = topicByIndex("1");
    //private By topic = topicByValue("example topic1");


    private By addChild(String index){
        return By.xpath(String.format("(//button[text()='Add Child'])[%s]", index));
    }


    // The constructor is mandatory to pass the driver to the BasePage
    public SyllabusPage(WebDriver driver) {
        super(driver);
    }

}
//input[@value='topic1']