package com.vipul.bit_hotels.utils;

import java.util.ArrayList;

/**
 * Created by rohanrodrigues on 5/5/17.
 */

public class Competition {
    private ArrayList<MatchForScout> mMatchForScouts;
    private String compName;

    public Competition(String compName) {
        this.compName = compName;
        mMatchForScouts = new ArrayList<>();
    }

    public String getCompName() {
        return compName;
    }

    public ArrayList<MatchForScout> getMatchForScouts() {
        return mMatchForScouts;
    }

    public void setMatchForScouts(ArrayList<MatchForScout> matchForScouts) {
        this.mMatchForScouts = matchForScouts;
    }

    public void addMatch(MatchForScout t) {
        mMatchForScouts.add(t);
    }

    public void removeMatch(MatchForScout t) {
        mMatchForScouts.remove(t);
    }

    public void removeMatch(int index) {
        mMatchForScouts.remove(index);
    }

    public ArrayList<String> getMatchNames() {
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < mMatchForScouts.size(); i++) {
            names.add(mMatchForScouts.get(i).getStudentName());
        }
        return names;
    }


}
