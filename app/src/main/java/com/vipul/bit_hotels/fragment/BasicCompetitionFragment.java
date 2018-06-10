package com.vipul.bit_hotels.fragment;

/**
 * Created by rohanrodrigues on 12/16/17.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vipul.bit_hotels.R;

public class BasicCompetitionFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_basic_competition, container, false);
    }
}