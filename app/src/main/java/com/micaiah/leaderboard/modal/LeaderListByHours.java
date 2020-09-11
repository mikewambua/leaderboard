package com.micaiah.leaderboard.modal;

public class LeaderListByHours {
    private String country;
    private String name;
    private int hours;
    private String badgeUrl;

    public LeaderListByHours(){}

    public LeaderListByHours(String name, int hours, String country, String badgeUrl) {
        this.hours = hours;
        this.name = name;
        this.country = country;
        this.badgeUrl = badgeUrl;

    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }
}
