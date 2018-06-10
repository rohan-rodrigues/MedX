package com.vipul.bit_hotels.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.vipul.bit_hotels.fragment.SuperScoutTeamFragment;

/**
 * Created by rohanrodrigues on 2/22/18.
 */

public class SuperScoutPagerAdapter extends FragmentStatePagerAdapter {
    private int mNumOfTabs;

    public SuperScoutPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                SuperScoutTeamFragment frag1 = new SuperScoutTeamFragment();
                frag1.position = 0;
                return frag1;
            case 1:
                SuperScoutTeamFragment frag2 = new SuperScoutTeamFragment();
                frag2.position = 1;
                return frag2;
            case 2:
                SuperScoutTeamFragment frag3 = new SuperScoutTeamFragment();
                frag3.position = 2;
                return frag3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}