package com.vipul.bit_hotels.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vipul.bit_hotels.R;
import com.vipul.bit_hotels.activity.CreateSuperScoutActivity;

/**
 * Created by rohanrodrigues on 2/22/18.
 */

public class SuperScoutTeamFragment extends Fragment {
    View rootview;

    private Button endMatch;
    private CheckBox selfClimbed;
    private EditText driverSkill;

    private Button[] btnClimbing = new Button[3];
    private Button btnClimbing_unfocus;
    private int currentFocusedButtonDriving = -1;
    private int currentFocusedButtonClimbing = -1;
    private int[] btnClimbing_id = {R.id.btnClimbing0, R.id.btnClimbing1, R.id.btnClimbing2};

    private Button[] btnDriving = new Button[4];
    private Button btnDriving_unfocus;
    private int[] btnDriving_id = {R.id.btnDriving0, R.id.btnDriving1, R.id.btnDriving2, R.id.btnDriving3};
    public int position;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview =  inflater.inflate(R.layout.super_scout_team, container, false);

        for(int i = 0; i < btnDriving.length; i++){
            btnDriving[i] = (Button) rootview.findViewById(btnDriving_id[i]);
            btnDriving[i].setBackgroundColor(Color.rgb(207, 207, 207));
            btnDriving[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()){
                        case R.id.btnDriving0 :
                            setFocus(btnDriving_unfocus, btnDriving[0], true);
                            currentFocusedButtonDriving = 0;
                            break;

                        case R.id.btnDriving1 :
                            setFocus(btnDriving_unfocus, btnDriving[1], true);
                            currentFocusedButtonDriving = 1;
                            break;

                        case R.id.btnDriving2 :
                            setFocus(btnDriving_unfocus, btnDriving[2], true);
                            currentFocusedButtonDriving = 2;
                            break;

                        case R.id.btnDriving3 :
                            setFocus(btnDriving_unfocus, btnDriving[3], true);
                            currentFocusedButtonDriving = 3;
                            break;
                    }
                }
            });
        }

        btnDriving_unfocus = btnDriving[0];


        for(int i = 0; i < btnClimbing.length; i++){
            btnClimbing[i] = (Button) rootview.findViewById(btnClimbing_id[i]);
            btnClimbing[i].setBackgroundColor(Color.rgb(207, 207, 207));
            btnClimbing[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()){
                        case R.id.btnClimbing0 :
                            setFocus(btnClimbing_unfocus, btnClimbing[0], false);
                            currentFocusedButtonClimbing = 0;
                            break;

                        case R.id.btnClimbing1 :
                            setFocus(btnClimbing_unfocus, btnClimbing[1], false);
                            currentFocusedButtonClimbing = 1;
                            break;

                        case R.id.btnClimbing2 :
                            setFocus(btnClimbing_unfocus, btnClimbing[2], false);
                            currentFocusedButtonClimbing = 2;
                            break;
                    }
                }
            });
        }

        btnClimbing_unfocus = btnClimbing[0];

        selfClimbed = (CheckBox)rootview.findViewById(R.id.robot_climb);

        driverSkill = (EditText)rootview.findViewById(R.id.driver_skill);


        endMatch = (Button)rootview.findViewById(R.id.end_match_button);
        endMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (currentFocusedButtonClimbing != -1 && currentFocusedButtonDriving != -1) {
                    turnOffViews();
                    ((CreateSuperScoutActivity) getActivity()).saveTeam(currentFocusedButtonDriving, driverSkill.getText().toString(), currentFocusedButtonClimbing, selfClimbed.isChecked());
                } else {
                    Toast t;
                    if (currentFocusedButtonDriving == -1 && currentFocusedButtonClimbing == -1)
                        t = Toast.makeText(getContext(), "Fill in climbing and driving ability", Toast.LENGTH_LONG);
                    else if (currentFocusedButtonDriving == -1)
                        t = Toast.makeText(getContext(), "Fill in driving ability", Toast.LENGTH_LONG);
                    else
                        t = Toast.makeText(getContext(), "Fill in climbing ability", Toast.LENGTH_LONG);
                    t.show();
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

    private void setFocus(Button btn_unfocus, Button btn_focus, boolean isDriving){
        btn_unfocus.setTextColor(Color.rgb(49, 50, 51));
        btn_unfocus.setBackgroundColor(Color.rgb(207, 207, 207));
        btn_focus.setTextColor(Color.rgb(255, 255, 255));
        btn_focus.setBackgroundColor(Color.rgb(3, 106, 150));

        if (isDriving)
            this.btnDriving_unfocus = btn_focus;
        else
            this.btnClimbing_unfocus = btn_focus;
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
        driverSkill.setEnabled(false);
        for (int i = 0; i < 3; i++) {
            btnClimbing[i].setEnabled(false);
            btnDriving[i].setEnabled(false);
        }
        btnDriving[3].setEnabled(false);
        btnDriving_unfocus.setEnabled(false);

        selfClimbed.setEnabled(false);
        endMatch.setEnabled(false);
    }

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putString("dataGotFromServer", dataGotFromServer);
//    }
//
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        dataGotFromServer = savedInstanceState.getString("dataGotFromServer");
//    }

}