package com.ucsc.tutionplatform.pages;

import org.openqa.selenium.By;

public class ClassVideosPage extends BasePage{

    private final By classVideos = By.xpath("//button[normalize-space()='Class Videos']");

    private final By cloneFromSyllabus = By.xpath("//button[normalize-space()='Clone From Syllabus']");
    private final By saveVideoLibrary = By.xpath("//button[normalize-space()='Save Video Library']");
    private final By reload = By.xpath("//button[normalize-space()='Reload']");
    private final By videoSelectionDropDown = By.xpath("//select");
    private final By accessStart = By.xpath("(//input[@type='datetime-local'])[1]");
    private final By accessEnd = By.xpath("(//input[@type='datetime-local'])[2]");
    private final By name = By.xpath("//input[@placeholder='Search by student ID, name, username, email, phone, NIC, stream, shy, or college']");
    private final By checkbox = By.xpath("//input[@type='checkbox']");
    private final By grantAccess = By.xpath("//button[@type=\"submit\"]");
    private final By grantedVidTopic = By.xpath("//div[@class='selected-student-summary class-video-selection-summary']/child::strong");

    private final By videoToBeSelected(String index)
    {
        return By.xpath(String.format("(//option)[%s]", index));
    }
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
    public String getVideoTopicText(String index)
    {
        return seleniumCardrige.getValue(videoTopic(index));
    }

    public String getVideoUrlText(String index)
    {
        return seleniumCardrige.getValue(videoUrl(index));
    }

    public void selectVideoToGrantAccess()
    {
        seleniumCardrige.click(videoSelectionDropDown);
        seleniumCardrige.click(videoToBeSelected("2"));
    }

    public void enterStartAndEndDates(String startDate, String endDate)
    {
        seleniumCardrige.click(accessStart);
        seleniumCardrige.type(accessStart, startDate);
        seleniumCardrige.click(accessEnd);
        seleniumCardrige.type(accessEnd, endDate);

    }

    public void selectStudentByName(String name)
    {
        seleniumCardrige.type(this.name, name);
        seleniumCardrige.click(checkbox);
    }

    public void clickGrantAccess(){seleniumCardrige.click(grantAccess);}

    public String getGrantedVideoTopic(){
        return seleniumCardrige.getText(grantedVidTopic);
    }






}
