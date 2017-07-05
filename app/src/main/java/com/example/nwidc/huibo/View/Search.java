package com.example.nwidc.huibo.View;

/**
 * Created by hello on 2017/7/3.
 */

public class Search {
    private String aName;
    private String aSpeak;
    private String aIcon;
    private int id;
    public Search() {
    }

    public Search(String aName, String aSpeak, String aIcon, int id) {
        this.aName = aName;
        this.aSpeak = aSpeak;
        this.aIcon = aIcon;
        this.id = id;
    }

    public String getaName() {
        return aName;
    }

    public String getaSpeak() {
        return aSpeak;
    }

    public String getaIcon() {
        return aIcon;
    }

    public int getaid() {
        return id;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public void setaSpeak(String aSpeak) {
        this.aSpeak = aSpeak;
    }

    public void setaIcon(String aIcon) {
        this.aIcon = aIcon;
    }
}

