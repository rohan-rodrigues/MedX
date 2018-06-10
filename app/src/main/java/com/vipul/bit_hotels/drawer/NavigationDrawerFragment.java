package com.vipul.bit_hotels.drawer;


import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vipul.bit_hotels.R;
import com.vipul.bit_hotels.activity.MainActivity;
import com.vipul.bit_hotels.activity.ProfileActivity;
import com.vipul.bit_hotels.activity.ToursActivity;
import com.vipul.bit_hotels.utils.Utils;

public class NavigationDrawerFragment extends BaseNavigationDrawerFragment {
    private String accessCode;

    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        navigationDrawerLayout = (RelativeLayout) inflater.inflate(R.layout.drawer_layout,
                container, false);

        final TextView homeLayout = (TextView) navigationDrawerLayout.findViewById(R.id.home_layout);
        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.startActivityWithClipReveal(getActivity(), MainActivity.class, homeLayout, accessCode);
            }
        });

        final TextView competitionsLayout = (TextView) navigationDrawerLayout.findViewById(R.id.competitions_layout);
        competitionsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.startActivityWithClipReveal(getActivity(), ToursActivity.class, competitionsLayout, accessCode);
            }
        });

//        final TextView scoutDataLayout = (TextView) navigationDrawerLayout.findViewById(R.id.scoutdata_layout);
//        scoutDataLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Utils.startActivityWithClipReveal(getActivity(), ScoutingActivity.class, scoutDataLayout);
//            }
//        });

        final RelativeLayout profileLayout = (RelativeLayout) navigationDrawerLayout.findViewById(R.id.profile_layout);
        profileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.startActivityWithClipReveal(getActivity(), ProfileActivity.class, profileLayout, accessCode);
            }
        });

//        final TextView blogLayout = (TextView) navigationDrawerLayout.findViewById(R.id.blogs_layout);
//        blogLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Utils.startActivityWithClipReveal(getActivity(), PublicationActivityOld.class, blogLayout);
//            }
//        });
//        return navigationDrawerLayout;
        return navigationDrawerLayout;
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, String accessCode) {
        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
                GravityCompat.START);
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), /* host Activity */
                mDrawerLayout, /* DrawerLayout object */
                R.string.navigation_drawer_open, R.string.navigation_drawer_open) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (!isAdded()) {
                    return;
                }
                getActivity().supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!isAdded()) {
                    return;
                }
                if (!mUserLearnedDrawer) {
                    // The user manually opened the com.readies.readies.drawer; store this flag to
                    // prevent auto-showing
                    // the navigation com.readies.readies.drawer automatically in the future.
                    mUserLearnedDrawer = true;
                    SharedPreferences sp = PreferenceManager
                            .getDefaultSharedPreferences(getActivity());
                    sp.edit().putBoolean(PREF_USER_LEARNED_DRAWER, true)
                            .apply();
                }
                getActivity().supportInvalidateOptionsMenu();
            }
        };
        // If the user hasn't 'learned' about the com.readies.readies.drawer, open it to introduce
        // them to the com.readies.readies.drawer,
        // per the navigation com.readies.readies.drawer design guidelines.
        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            mDrawerLayout.openDrawer(mFragmentContainerView);
        }
        // Defer code dependent on restoration of previous instance state.
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        this.accessCode = accessCode;
    }
}