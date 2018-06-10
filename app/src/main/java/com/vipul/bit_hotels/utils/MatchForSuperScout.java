package com.vipul.bit_hotels.utils;

import java.util.HashMap;

/**
 * Created by rohanrodrigues on 2/22/18.
 */

public class MatchForSuperScout {
    private String matchNum;
    private Alliance alliance;
    private String competition;

    public MatchForSuperScout(Alliance.ALLIANCE_COLOR color, String matchNum) {
        this.alliance = new Alliance(color);
        this.matchNum = matchNum;
    }

    public MatchForSuperScout(Alliance.ALLIANCE_COLOR color) {
        this.alliance = new Alliance(color);
    }

    public MatchForSuperScout() {

    }

    public String getCompetition() {
        return competition;
    }

    public void setCompetition(String competition) {
        this.competition = competition;
    }

    public Alliance getAlliance() {
        return alliance;
    }

    public void setAlliance(Alliance alliance) {
        this.alliance = alliance;
    }

    public String getMatchNum() {
        return matchNum;
    }

    public void setMatchNum(String matchNum) {
        this.matchNum = matchNum;
    }

    public void setSuperScout(String scout) {
        this.alliance.setScout(scout);
    }

    public HashMap<String, String> generatePropertyList(String teamNumber) {
        HashMap<String, String> data = new HashMap<>();
        TeamForSuperScout t = alliance.findTeamByNumber(teamNumber);
        data.put("n superScout", t.getSuperScout());
        data.put("a allianceColor", alliance.getColor().name());
        data.put("o DriverSkillNotes", t.getDriverSkillNotes());
        data.put("j driverSkill", t.getDriverSkill());

        int climbed = t.selfClimbed();
        int robotsLifted = t.getRobotsLifted();

        data.put("k selfClimbed", climbed + "");
        data.put("l robotsLifted", robotsLifted + "");
        return data;
    }

}
