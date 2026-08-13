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
    private String parentTopic;
    private String subTopic;
    private String childTopic;
    private String newTopicName;
    private String topic;
    private String duplicateTopic;
    private String hierarchy;

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

    public String getParentTopic() {
        return parentTopic;
    }

    public void setParentTopic(String parentTopic) {
        this.parentTopic = parentTopic;
    }

    public String getSubTopic() {
        return subTopic;
    }

    public void setSubTopic(String subTopic) {
        this.subTopic = subTopic;
    }

    public String getChildTopic() {
        return childTopic;
    }

    public void setChildTopic(String childTopic) {
        this.childTopic = childTopic;
    }

    public String getNewTopicName() {
        return newTopicName;
    }

    public void setNewTopicName(String newTopicName) {
        this.newTopicName = newTopicName;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDuplicateTopic() {
        return duplicateTopic;
    }

    public void setDuplicateTopic(String duplicateTopic) {
        this.duplicateTopic = duplicateTopic;
    }

    public String getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(String hierarchy) {
        this.hierarchy = hierarchy;
    }
}
