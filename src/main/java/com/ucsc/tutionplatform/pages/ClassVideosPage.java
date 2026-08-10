package com.ucsc.tutionplatform.pages;

import org.openqa.selenium.By;

public class ClassVideosPage extends BasePage{

    private final By classVideos = By.xpath("//button[normalize-space()='Class Videos']");

    private final By cloneFromSyllabus = By.xpath("//button[normalize-space()='Clone From Syllabus']");
    private final By saveVideoLibrary = By.xpath("//button[normalize-space()='Save Video Library']");
    private final By reload = By.xpath("//button[normalize-space()='Reload']");


    private By addVideo(String index){
        return By.xpath(String.format("(//button[normalize-space()='Add Video'])[%s]", index));
    }

    private By addAnotherVideo(String index){
        return By.xpath(String.format("(//button[normalize-space()='Add Another Video'])[%s]", index));
    }

    private By videoTopic(String index){
        return By.xpath(String.format("(//input[@placeholder='Video topic'])[%s]", index));
    }
    private By videoUrl(String index){
        return By.xpath(String.format("(//input[@placeholder='https://www.youtube.com/watch?v=...'])[%s]", index));
    }
    private By remove(String index){
        return By.xpath(String.format("//button[normalize-space()='Remove'])[%s]", index));
    }

    public void clickClassVideosTab() {
        seleniumCardrige.click(classVideos);
    }

    public void clickSaveVideoLibrary()
    {
        seleniumCardrige.click(saveVideoLibrary);
    }

    public boolean isClassVideosButtonVisible() {
            return seleniumCardrige.isDisplayed(classVideos);
    }

    public void clickCloneFromSyllabus() {
        seleniumCardrige.click(cloneFromSyllabus);
    }

    public void clickAddVideo(){
        seleniumCardrige.click(addVideo("1"));
    }

    public void clickReload(){
        seleniumCardrige.click(reload);
    }

    public void enterVideoDetails(String topic, String url)
    {
        seleniumCardrige.type(videoTopic("1"), topic);
        seleniumCardrige.type(videoUrl("1"), url);

    }

    public String getVideoTopicText()
    {
        return seleniumCardrige.getValue(videoTopic("1"));
    }

    public String getVideoUrlText()
    {
        return seleniumCardrige.getValue(videoUrl("1"));
    }


}
