package com.vipul.bit_hotels.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vipul.bit_hotels.R;
import com.vipul.bit_hotels.activity.CreateScoutActivity;

/**
 * Created by rohanrodrigues on 3/15/17.
 */

public class StartMatchForScout extends Fragment {
    View rootview;
    private EditText medicalIssue;
    private EditText patient;
    private EditText student;
    private Button beginAuton;
  //  private String competition;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview =  inflater.inflate(R.layout.start_match, container, false);

        medicalIssue = (EditText) rootview.findViewById(R.id.team_number);
        patient = (EditText) rootview.findViewById(R.id.match_number);
        student = (EditText) rootview.findViewById(R.id.scout_name);


        beginAuton = (Button)rootview.findViewById(R.id.begin_auton_button);
        beginAuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isValuesFilledIn()) {
                    Toast t = Toast.makeText(getContext().getApplicationContext(), "Fill out all the questions", Toast.LENGTH_SHORT);
                    t.show();
                }
                else {
                    createTeam();
                    turnOffViews();
                    ((CreateScoutActivity)getActivity()).getViewPager().setCurrentItem(1);
                }
            }
        });

//        if (((CreateScoutActivity)getActivity()).doesDataExist())
//            setValues(((CreateScoutActivity)getActivity()).getCurrentMatchForScout(), ((CreateScoutActivity)getActivity()).getLocation());

        return rootview;
    }


    public void createTeam() {
        ((CreateScoutActivity)getActivity()).createTeam(student.getText().toString(), patient.getText().toString(), medicalIssue.getText().toString());
    }

    public boolean isValuesFilledIn() {
        EditText[] editTexts = new EditText[]{medicalIssue, patient, student};
        for (EditText e : editTexts) {
            if (e.getText().toString().equals("")) {
                return false;
            }
        }
        return true;
    }

    public void resetValues() {
        EditText[] editTexts = new EditText[]{medicalIssue, patient, student};
        for (EditText e : editTexts) {
            e.setText("");
        }
    }

    private void turnOffViews() {
        medicalIssue.setEnabled(false);
        patient.setEnabled(false);
        student.setEnabled(false);
        beginAuton.setEnabled(false);
    }

//    public void setValues(MatchForScout m, String competition) {
//        System.out.println("SETTING VALUES!!!");
//        PatientByStudent t = m.getPatientByStudentWatched();
//        medicalIssue.setText(t.getPatientName());
//        patient.setText(m.getStudentName());
//    }


}
