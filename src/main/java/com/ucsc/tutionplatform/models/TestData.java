package com.ucsc.tutionplatform.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TestData {

    private String testCaseId;
    private String name;
    private String address;

    private String videoTopic;
    private String videoUrl;

    private String targetNode;
    private String otherNode;

    private String materialTopic;

    private String startDate;
    private String endDate;

    public TestData() {
    }

    public TestData(String testCaseId) {
        this.testCaseId = testCaseId;
    }

    public String getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(String testCaseId) {
        this.testCaseId = testCaseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getVideoTopic() {
        return videoTopic;
    }

    public void setVideoTopic(String videoTopic) {
        this.videoTopic = videoTopic;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

        public String getTargetNode() {
        return targetNode;
    }

    public void setTargetNode(String targetNode) {
        this.targetNode = targetNode;
    }

    public String getOtherNode() {
        return otherNode;
    }

    public void setOtherNode(String otherNode) {
        this.otherNode = otherNode;
    }

    public String getMaterialTopic() {
        return materialTopic;
    }

    public void setMaterialTopic(String materialTopic) { this.materialTopic = materialTopic; }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
