package com.vipul.bit_hotels.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.vipul.bit_hotels.R;
import com.vipul.bit_hotels.activity.CreateScoutActivity;

import java.io.IOException;


/**
 * Created by rohanrodrigues on 3/15/17.
 */

public class AddTeleopForScout extends Fragment {
    View rootview;

    private EditText surgicalProcedureTaken;
    private int effectOfProcedure;
    private EditText review;
    private CheckBox recommend;
    private Button endMatch;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview =  inflater.inflate(R.layout.add_teleop, container, false);

        recommend = (CheckBox)rootview.findViewById(R.id.robot_climb);
        surgicalProcedureTaken = (EditText) rootview.findViewById(R.id.name_of_competition);
        review = (EditText) rootview.findViewById(R.id.review);

        endMatch = (Button)rootview.findViewById(R.id.end_match_button);
        endMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int climb = recommend.isChecked() ? 1 : 0;

                turnOffViews();
                ((CreateScoutActivity)getActivity()).addTeleopData(surgicalProcedureTaken.toString(), effectOfProcedure, review.toString(), climb);
                try {
                    ((CreateScoutActivity)getActivity()).saveTeam();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

//        if (((CreateScoutActivity)getActivity()).doesDataExist())
//            setValues(((CreateScoutActivity)getActivity()).getCurrentMatchForScout());

        return rootview;
    }

    private void addToText(TextView text, int i) {
        int currentText = Integer.parseInt(text.getText().toString());
        if (currentText+i >= 0) {
            currentText += i;
        }
        text.setText(currentText + "");
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.arizonaToggle:
                if (checked)
                    effectOfProcedure = 0;
                    break;
            case R.id.sacramentoToggle:
                if (checked)
                    effectOfProcedure = 1;
                    break;
            case R.id.dToggle:
                if (checked)
                    effectOfProcedure = 2;
                    break;
        }
    }

//    public void setValues(MatchForScout m) {
//        PatientByStudent t = m.getPatientByStudentWatched();
//
//      //  climbed.setChecked(t.isClimb());
//        cubesOwnSwitch.setText(t.getNumCubesSwitchTeleop() + "");
//        cubesOwnScale.setText(t.getNumCubesScaleTeleop()  + "");
//        cubesExchange.setText(t.getNumExchangeTeleop() + "");
//      //  fouls.setText(t.getFouls() + "");
//      //  robotsClimbed.setText(t.getNumRobotsClimbedWhileHangingToRobot() + "");
//        cubesInOpponentSwitch.setText(t.getNumCubesInOpponentsSwitch() + "");
//    }

    private void turnOffViews() {
        surgicalProcedureTaken.setEnabled(false);
        review.setEnabled(false);
        recommend.setEnabled(false);
        endMatch.setEnabled(false);
    }

}
