package com.vipul.bit_hotels.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.vipul.bit_hotels.R;
import com.vipul.bit_hotels.adapter.SuperScoutPagerAdapter;
import com.vipul.bit_hotels.utils.Alliance;
import com.vipul.bit_hotels.utils.CSVSuperData;
import com.vipul.bit_hotels.utils.MatchForSuperScout;
import com.vipul.bit_hotels.utils.TeamForSuperScout;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by rohanrodrigues on 2/22/18.
 */

public class CreateSuperScoutActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private SuperScoutPagerAdapter adapter;

    private MatchForSuperScout mCurrentMatchForSuperScout;
    private String competition;

    private int numTeamsSaved = 0;

    // private int currentTabPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_scout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //   toolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);
        setSupportActionBar(toolbar);
        toolbar.setTitle("ASd");
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        competition = i.getStringExtra("competition");
        String scoutName = i.getStringExtra("scout");
        String colorString = i.getStringExtra("alliance");
        Alliance.ALLIANCE_COLOR color = new Gson().fromJson(colorString, Alliance.ALLIANCE_COLOR.class);
        String matchNumber = i.getStringExtra("matchNumber");
        String team1 = i.getStringExtra("team1");
        String team2 = i.getStringExtra("team2");
        String team3 = i.getStringExtra("team3");
        createMatch(color, scoutName, matchNumber, team1, team2, team3);


        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(team1));
        tabLayout.addTab(tabLayout.newTab().setText(team2));
        tabLayout.addTab(tabLayout.newTab().setText(team3));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

//        Bundle b;
//        if ((b = i.getExtras()) != null) {
//            System.out.println("CURRENT MATCH EXISTS!!!");
//            this.mCurrentMatchForScout = new Gson().fromJson(b.getString("match"), MatchForScout.class);
//            this.competition = b.getString("competition");
//            System.out.println("ABCDEFGH : " + competition);
//            dataExists = true;
//        }
//        else {
//            System.out.println("NO CURRENT MATCH EXISTS!!!");
//            mCurrentMatchForScout = new MatchForScout();
//            dataExists = false;
//        }

        viewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new SuperScoutPagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //    currentTabPosition = tab.getPosition();
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


    }

    public ViewPager getViewPager() {
        return viewPager;
    }

    public void createMatch(Alliance.ALLIANCE_COLOR color, String scoutName, String matchNumber, String team1, String team2, String team3) {
        if (mCurrentMatchForSuperScout == null) {
            if (color == null) {
                color = Alliance.ALLIANCE_COLOR.BLUE;
            }
            mCurrentMatchForSuperScout = new MatchForSuperScout(color, matchNumber);
        }

        TeamForSuperScout teamFirst = new TeamForSuperScout(team1);
        TeamForSuperScout teamSecond = new TeamForSuperScout(team2);
        TeamForSuperScout teamThird = new TeamForSuperScout(team3);
        ArrayList<TeamForSuperScout> teams = new ArrayList<>();
        teams.add(teamFirst);
        teams.add(teamSecond);
        teams.add(teamThird);

        mCurrentMatchForSuperScout.getAlliance().setTeamForSuperScouts(teams);
        mCurrentMatchForSuperScout.setSuperScout(scoutName);
        mCurrentMatchForSuperScout.setCompetition(competition);
    }

//    public void setLocation(String competition) {
//        this.competition = competition;
//    }

    public MatchForSuperScout getMatch() {
        return mCurrentMatchForSuperScout;
    }


    public void saveTeam(int driverSkill, String driverNotes, int robotsLifted, boolean selfClimbed) {
        int currentPage = viewPager.getCurrentItem();
        TeamForSuperScout team = mCurrentMatchForSuperScout.getAlliance().getTeamList().get(currentPage);
        team.setDriverSkill(driverSkill + "");
        team.setDriverSkillNotes(driverNotes);
        team.setRobotsLifted(robotsLifted);

        int selfClimbedVal = 0;
        if (selfClimbed)
            selfClimbedVal = 1;

        team.setSelfClimbed(selfClimbedVal);

        numTeamsSaved++;

        if (numTeamsSaved == 3) {
          //  writeData();


//            Intent resultIntent = new Intent();
//            String s = (new Gson().toJson(mCurrentMatchForSuperScout));
//            //   System.out.println("COMPETITION:  " + competition);
//            resultIntent.putExtra("competition", competition);
//            resultIntent.putExtra("match", s);
//            setResult(Activity.RESULT_OK, resultIntent);
//            finish();

            writeData();
        }
        else {
            viewPager.setCurrentItem(numTeamsSaved);
        }
    }

//    public void addTeleopData(int cubesOwnSwitch, int cubesOwnScale, int cubesExchange, int cubesInOpponentsSwitch, int cubesPlacedBehind, int cubesMissedBehind) {
//        mCurrentMatchForScout.getTeamForScoutWatched().setNumCubesSwitchTeleop(cubesOwnSwitch);
//        mCurrentMatchForScout.getTeamForScoutWatched().setNumCubesScaleTeleop(cubesOwnScale);
//        mCurrentMatchForScout.getTeamForScoutWatched().setNumExchangeTeleop(cubesExchange);
//        mCurrentMatchForScout.getTeamForScoutWatched().setNumCubesInOpponentsSwitch(cubesInOpponentsSwitch);
//        mCurrentMatchForScout.getTeamForScoutWatched().setCubesPlacedBehind(cubesPlacedBehind);
//        mCurrentMatchForScout.getTeamForScoutWatched().setCubesMissedBehind(cubesMissedBehind);
//    }
//
//    public void addAutonData(boolean crossedBaseline, int fuelHigh, int fuelLow) {
//        mCurrentMatchForScout.getTeamForScoutWatched().setCrossedBaseline(crossedBaseline);
//        mCurrentMatchForScout.getTeamForScoutWatched().setNumCubesSwitchAuton(fuelHigh);
//        mCurrentMatchForScout.getTeamForScoutWatched().setNumCubesScaleAuton(fuelLow);
//    }

    public MatchForSuperScout getCurrentMatchForSuperScout() {
        return this.mCurrentMatchForSuperScout;
    }


    public String getCompetition() {
        return this.competition;
    }

    private void writeData() {
      //  FirebaseDatabaseClass.updatedStoreMatchForSuperScout(competition, mCurrentMatchForSuperScout);
        Intent intent = new Intent(this, QRCodeActivity.class);
        String s = (new Gson().toJson(mCurrentMatchForSuperScout));
        intent.putExtra("data", s);
        startActivityForResult(intent, 1);

        saveToCSV();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if(resultCode == RESULT_OK) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("scout", mCurrentMatchForSuperScout.getAlliance().getTeamList().get(0).getSuperScout());
                resultIntent.putExtra("match", mCurrentMatchForSuperScout.getMatchNum());
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
    }

    private void saveToCSV() {
        ArrayList<MatchForSuperScout> as = new ArrayList<>();
        as.add(mCurrentMatchForSuperScout);
        CSVSuperData csvDataFile = new CSVSuperData(as);

        try {
            csvDataFile.writeDataToCSV(getApplicationContext(), competition);
            csvDataFile.writeDataToExternalDrive(getApplicationContext(), competition);
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean hasCreatedFile = csvDataFile.doesFileExist();

        if (!hasCreatedFile) {
            Toast t = Toast.makeText(this, "Created .csv file", Toast.LENGTH_LONG);
            t.show();
        }
        else {
            Toast t = Toast.makeText(this, "Added to .csv file", Toast.LENGTH_LONG);
            t.show();
        }
    }

}