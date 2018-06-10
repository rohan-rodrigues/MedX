package com.vipul.bit_hotels.utils;

import java.util.ArrayList;

/**
 * Created by rohanrodrigues on 5/5/17.
 */

public class Alliance {
    private ArrayList<TeamForSuperScout> mTeamForSuperScouts;
    private ALLIANCE_COLOR color;
    public enum ALLIANCE_COLOR {
            RED, BLUE
    }

    public Alliance(ALLIANCE_COLOR color) {
        this.color = color;
        mTeamForSuperScouts = new ArrayList<>();
    }

    public ArrayList<TeamForSuperScout> getTeamList() {
        return this.mTeamForSuperScouts;
    }

    public void addTeamForSuperScout(TeamForSuperScout team) {
        mTeamForSuperScouts.add(team);
    }

    public void setTeamForSuperScouts(ArrayList<TeamForSuperScout> teamForSuperScouts) {
        mTeamForSuperScouts = teamForSuperScouts;
    }

    public ALLIANCE_COLOR getColor() {
        return color;
    }

    public void setColor(ALLIANCE_COLOR color) {
        this.color = color;
    }

    public TeamForSuperScout findTeamByNumber(String teamNumber) {
        for (TeamForSuperScout t : mTeamForSuperScouts) {
            if (t.getTeamNumber().equals(teamNumber)) {
                return t;
            }
        }
        return null;
    }

    public void setScout(String scout) {
        for (int i = 0; i < mTeamForSuperScouts.size(); i++) {
            mTeamForSuperScouts.get(i).setSuperScout(scout);
            System.out.println("SET VALUE:  " + scout);
        }
    }
}
