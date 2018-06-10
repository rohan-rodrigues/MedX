package com.vipul.bit_hotels.utils;

public class DisplayTeam {
    private String name;
    private int teamNumber;
    private int thumbnail;

    public DisplayTeam() {
    }

    public DisplayTeam(String name, int teamNumber, int thumbnail) {
        this.name = name;
        this.teamNumber = teamNumber;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(int teamNumber) {
        this.teamNumber = teamNumber;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}