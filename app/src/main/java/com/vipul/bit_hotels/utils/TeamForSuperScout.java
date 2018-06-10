package com.vipul.bit_hotels.utils;

/**
 * Created by rohanrodrigues on 2/22/18.
 */

public class TeamForSuperScout {
    private String teamNumber;
    private String driverSkill;
    private int selfClimbed;
    private int robotsLifted;
    private String driverSkillNotes = " ";
    private String superScout;

    public TeamForSuperScout() {

    }

    public TeamForSuperScout(String teamNumber) {
        this.teamNumber = teamNumber;
    }

    public String getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(String teamNumber) {
        this.teamNumber = teamNumber;
    }

    public String getDriverSkill() {
        return driverSkill;
    }

    public void setDriverSkill(String driverSkill) {
        if (driverSkill.equals("0"))
            this.driverSkill = "";
        else
            this.driverSkill = driverSkill;
    }

    public String getDriverSkillNotes() {
        return driverSkillNotes;
    }

    public void setDriverSkillNotes(String driverSkillNotes) {
        this.driverSkillNotes = driverSkillNotes;
    }

    public int selfClimbed() {
        return selfClimbed;
    }

    public void setSelfClimbed(int selfClimbed) {
        this.selfClimbed = selfClimbed;
    }

    public int getRobotsLifted() {
        return robotsLifted;
    }

    public void setRobotsLifted(int robotsLifted) {
        this.robotsLifted = robotsLifted;
    }

    public String getSuperScout() {
        return superScout;
    }

    public void setSuperScout(String superScout) {
        this.superScout = superScout;
    }
}
