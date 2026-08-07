package com.ucsc.tutionplatform.pages;

import org.openqa.selenium.By;

public class ClassVideos extends BasePage{

    private By classVideos = By.xpath("//button[normalize-space()='Class Videos']");

    private By cloneFromSyllabus = By.xpath("//button[normalize-space()='Clone From Syllabus']");
    private By saveVideoLibrary = By.xpath("//button[normalize-space()='Save Video Library']");
    private By reload = By.xpath("//button[normalize-space()='Reload']");


    private By addVideo(String index){
        return By.xpath(String.format("(//button[normalize-space()='Add Video'])[%s]", index));
    }

    private By addAnotherVideo(String index){
        return By.xpath(String.format("(//button[normalize-space()='Add Another Video'])[%s]", index));
    }

    private By videoTopic(String index){
        return By.xpath(String.format("(//input[@placeholder='Video topic'])[%s]", index));
    }
    private By videoURL(String index){
        return By.xpath(String.format("(//input[@placeholder=''])[%s]", index));
    }
    private By remove(String index){
        return By.xpath(String.format("//button[normalize-space()='Remove'])[%s]", index));
    }

}
