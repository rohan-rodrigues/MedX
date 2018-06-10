package com.vipul.bit_hotels.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.gson.Gson;
import com.vipul.bit_hotels.R;
import com.vipul.bit_hotels.utils.Alliance;

/**
 * Created by rohanrodrigues on 2/22/18.
 */

public class SuperScoutDetailsActivity extends AppCompatActivity {
    private final int STATIC_INTEGER_VALUE = 5;
    private EditText team1, team2, team3, scoutName, matchNumber;
    private RadioButton blueAllianceRadio, redAllianceRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_scout_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //   toolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);
        setSupportActionBar(toolbar);
        toolbar.setTitle("ASd");
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        final String competition = i.getStringExtra("competition");

        scoutName = (EditText) findViewById(R.id.superScoutName);
        matchNumber = (EditText) findViewById(R.id.superScoutMatch);
        blueAllianceRadio = (RadioButton) findViewById(R.id.blueAllianceToggle);
        redAllianceRadio = (RadioButton) findViewById(R.id.redAllianceToggle);

        team1 = (EditText) findViewById(R.id.allianceTeam1);
        team2 = (EditText) findViewById(R.id.allianceTeam2);
        team3 = (EditText) findViewById(R.id.allianceTeam3);


        Button finish = (Button)findViewById(R.id.startMatchButton);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CreateSuperScoutActivity.class);
                i.putExtra("competition", competition);

                if (blueAllianceRadio.isChecked()) {
                    String s = (new Gson().toJson(Alliance.ALLIANCE_COLOR.BLUE));
                    i.putExtra("alliance", s);
                }
                else {
                    String s = (new Gson().toJson(Alliance.ALLIANCE_COLOR.RED));
                    i.putExtra("alliance", s);
                }

                i.putExtra("scout", scoutName.getText().toString());
                i.putExtra("matchNumber", matchNumber.getText().toString());
                i.putExtra("team1", team1.getText().toString());
                i.putExtra("team2", team2.getText().toString());
                i.putExtra("team3", team3.getText().toString());
                resetValues();
                startActivityForResult(i, 1);
            }
        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
              //  presetValues(data.getStringExtra("match"), data.getStringExtra("scout"));

//                System.out.println("MatchNumber: " + matchNumber.getText().toString());
//                System.out.println("Scot:  " + scoutName.getText().toString());
//                int m = Integer.parseInt(matchNumber.getText().toString());
//                scoutName.setText(scoutName.getText().toString());
//                matchNumber.setText(m + 1);
            }
        }
    }

    private void resetValues() {
        team1.setText("");
        team2.setText("");
        team3.setText("");
        scoutName.setText("");
        matchNumber.setText("");
        blueAllianceRadio.setChecked(false);
        redAllianceRadio.setChecked(false);
    }

    private void presetValues(String matchNum, String scout) {
        int m = Integer.parseInt(matchNum);
        scoutName.setText(scout);
        matchNumber = (EditText) findViewById(R.id.superScoutMatch);
        matchNumber.setText(m + 1);
    }


}
