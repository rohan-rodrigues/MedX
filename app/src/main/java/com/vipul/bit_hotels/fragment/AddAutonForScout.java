package com.vipul.bit_hotels.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.vipul.bit_hotels.R;
import com.vipul.bit_hotels.activity.CreateScoutActivity;
import com.vipul.bit_hotels.utils.MatchForScout;
import com.vipul.bit_hotels.utils.PatientByStudent;

/**
 * Created by rohanrodrigues on 3/15/17.
 */

public class AddAutonForScout extends Fragment {
    View rootview;
    private EditText age, weight, height;

    private Button endAuton;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview =  inflater.inflate(R.layout.add_auton, container, false);

        age = (EditText) rootview.findViewById(R.id.team_number);
        weight = (EditText) rootview.findViewById(R.id.match_number);
        height = (EditText) rootview.findViewById(R.id.scout_name);


        endAuton = (Button)rootview.findViewById(R.id.begin_teleop_button);
        endAuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((CreateScoutActivity)getActivity()).addAutonData(Integer.parseInt(age.getText().toString()), Integer.parseInt(weight.getText().toString()), Integer.parseInt(height.getText().toString()));
                turnOffViews();
                ((CreateScoutActivity)getActivity()).getViewPager().setCurrentItem(2);
            }
        });

        if (((CreateScoutActivity)getActivity()).doesDataExist())
            setValues(((CreateScoutActivity)getActivity()).getCurrentMatchForScout());


        return rootview;
    }

    private void addToText(TextView text, int i) {
        int currentText = Integer.parseInt(text.getText().toString());
        if (currentText + i >= 0) {
            currentText += i;
        }

        text.setText(currentText + "");
    }

    public void setValues(MatchForScout m) {
        System.out.println("Set values for the match!!");
        PatientByStudent t = m.getPatientByStudentWatched();
        System.out.println("TEAM NUMBER IS: " + t.getPatientName());
    }

    private void turnOffViews() {
        age.setEnabled(false);
        weight.setEnabled(false);
        height.setEnabled(false);
        endAuton.setEnabled(false);
    }
}
