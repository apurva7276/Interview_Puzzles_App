package com.example.interview_puzzle_app.ui.PracticeMode.QueList;

import java.io.Serializable;

public class QueModel implements Serializable {
    private int level;
    private String QueID, shortQ, detail, domain, option1, option2, option3, option4, coption, link, QueImg;
    private boolean Microsoft, Google, Yahoo, Meta, Apple, Netflix;

    public QueModel(){}

    public QueModel(int level, String queID, String shortQ, String detail, String domain, String option1, String option2, String option3, String option4, String coption, String link, String queImg, boolean microsoft, boolean google, boolean yahoo, boolean meta, boolean apple, boolean netflix) {
        this.level = level;
        QueID = queID;
        this.shortQ = shortQ;
        this.detail = detail;
        this.domain = domain;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.coption = coption;
        this.link = link;
        QueImg = queImg;
        Microsoft = microsoft;
        Google = google;
        Yahoo = yahoo;
        Meta = meta;
        Apple = apple;
        Netflix = netflix;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getQueID() {
        return QueID;
    }

    public void setQueID(String queID) {
        QueID = queID;
    }

    public String getShortQ() {
        return shortQ;
    }

    public void setShortQ(String shortQ) {
        this.shortQ = shortQ;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getCoption() {
        return coption;
    }

    public void setCoption(String coption) {
        this.coption = coption;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getQueImg() {
        return QueImg;
    }

    public void setQueImg(String queImg) {
        QueImg = queImg;
    }

    public boolean isMicrosoft() {
        return Microsoft;
    }

    public void setMicrosoft(boolean microsoft) {
        Microsoft = microsoft;
    }

    public boolean isGoogle() {
        return Google;
    }

    public void setGoogle(boolean google) {
        Google = google;
    }

    public boolean isYahoo() {
        return Yahoo;
    }

    public void setYahoo(boolean yahoo) {
        Yahoo = yahoo;
    }

    public boolean isMeta() {
        return Meta;
    }

    public void setMeta(boolean meta) {
        Meta = meta;
    }

    public boolean isApple() {
        return Apple;
    }

    public void setApple(boolean apple) {
        Apple = apple;
    }

    public boolean isNetflix() {
        return Netflix;
    }

    public void setNetflix(boolean netflix) {
        Netflix = netflix;
    }
}