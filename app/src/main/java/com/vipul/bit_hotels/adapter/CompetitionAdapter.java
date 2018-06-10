package com.vipul.bit_hotels.adapter;

/**
 * Created by rohanrodrigues on 12/16/17.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.vipul.bit_hotels.fragment.BasicCompetitionFragment;

public class CompetitionAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public CompetitionAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        System.out.println("SDASDASDSADSDSAD");

        switch (position) {
            case 0:
                BasicCompetitionFragment tab1 = new BasicCompetitionFragment();
                System.out.println("NUMBER ONEEEEE");
                return tab1;
            case 1:
                BasicCompetitionFragment tab2 = new BasicCompetitionFragment();
                return tab2;
            case 2:
                BasicCompetitionFragment tab3 = new BasicCompetitionFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
