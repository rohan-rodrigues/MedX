package com.vipul.bit_hotels.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.vipul.bit_hotels.fragment.AddAutonForScout;
import com.vipul.bit_hotels.fragment.AddTeleopForScout;
import com.vipul.bit_hotels.fragment.StartMatchForScout;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new StartMatchForScout();
            case 1:
                return new AddAutonForScout();
            case 2:
                return new AddTeleopForScout();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}