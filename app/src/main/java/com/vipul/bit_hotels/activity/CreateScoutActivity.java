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
import com.vipul.bit_hotels.adapter.PagerAdapter;
import com.vipul.bit_hotels.utils.CSVData;
import com.vipul.bit_hotels.utils.MatchForScout;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by rohanrodrigues on 1/26/18.
 */

public class CreateScoutActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private PagerAdapter adapter;

    private MatchForScout mCurrentMatchForScout;
    private String location;
    private boolean dataExists;

    // private int currentTabPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scout_data);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //   toolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);
        setSupportActionBar(toolbar);
        toolbar.setTitle("ASd");
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        try {
//            sheetsService = SheetsServiceUtil.getSheetsService();
//        } catch (IOException | GeneralSecurityException e) {
//            e.printStackTrace();
//        }

//        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("match");
//        System.out.println(mDatabase.child("alliance").child("Blue").child("Teams").child("Name"));
//        mDatabase.child("alliance").child("Blue").child("Teams").child("Name").setValue("ABC");


//        Button cancel = (Button)findViewById(R.id.button_cancelAddTeam);
//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(i);
//            }
//        });

//        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
//        setSupportActionBar(myToolbar);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("General"));
        tabLayout.addTab(tabLayout.newTab().setText("Details"));
        tabLayout.addTab(tabLayout.newTab().setText("Results"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        Intent i = getIntent();
        location = i.getStringExtra("location");

        mCurrentMatchForScout = new MatchForScout();

        viewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //    currentTabPosition = tab.getPosition();
                viewPager.setCurrentItem(tab.getPosition());
                adapter.notifyDataSetChanged();
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

    public void createTeam(String student, String patient, String medicalIssue) {
        mCurrentMatchForScout.getPatientByStudentWatched().setPatientName(patient);
        mCurrentMatchForScout.setStudentName(student);
        mCurrentMatchForScout.setScout(student);
        mCurrentMatchForScout.setLocation(location);
        mCurrentMatchForScout.setIssue(medicalIssue);
    }

//    public void setLocation(String location) {
//        this.location = location;
//    }

    public MatchForScout getMatch() {
        return mCurrentMatchForScout;
    }


    public void saveTeam() throws IOException {
        if (mCurrentMatchForScout.getPatientByStudentWatched().getPatientName() == null) mCurrentMatchForScout.getPatientByStudentWatched().setPatientName("Null");
        if (mCurrentMatchForScout.getStudentName() == null) mCurrentMatchForScout.setStudentName("Null");

//        Intent resultIntent = new Intent();
//        resultIntent.putExtra("ABCD", mCurrentMatchForScout.getPropertyList());
//        setResult(Activity.RESULT_OK, resultIntent);
//        finish();

        //CALL METHOD
        writeData();
     //   getResultsFromApi();

        Intent resultIntent = new Intent();
//        String s = (new Gson().toJson(mCurrentMatchForScout));
//        resultIntent.putExtra("exists", dataExists);
//     //   System.out.println("COMPETITION:  " + location);
//        resultIntent.putExtra("location", location);
//        resultIntent.putExtra("match", s);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    public void addTeleopData(String procedure, int effect, String review, int recommend) {
        mCurrentMatchForScout.getPatientByStudentWatched().setSurgicalProcedure(procedure);
        mCurrentMatchForScout.getPatientByStudentWatched().setProcedureEffect(effect);
        mCurrentMatchForScout.getPatientByStudentWatched().setReview(review);
        mCurrentMatchForScout.getPatientByStudentWatched().setRecommend(recommend);
    }

    public void addAutonData(int age, int weight, int height) {
        mCurrentMatchForScout.getPatientByStudentWatched().setAge(age);
        mCurrentMatchForScout.getPatientByStudentWatched().setWeight(weight);
        mCurrentMatchForScout.getPatientByStudentWatched().setHeight(height);
    }

    public MatchForScout getCurrentMatchForScout() {
        return this.mCurrentMatchForScout;
    }

    public String getLocation() {
        return this.location;
    }

    public boolean doesDataExist() {
        return this.dataExists;
    }



    private void writeData() {
//        FirebaseDatabaseClass.storeMatchForScout(location, mCurrentMatchForScout);
        Intent intent = new Intent(this, QRCodeActivity.class);
        String s = (new Gson().toJson(mCurrentMatchForScout));
        intent.putExtra("data", s);
        startActivityForResult(intent, 1);

        saveToCSV();
    }

    private void saveToCSV() {
        ArrayList<MatchForScout> as = new ArrayList<>();
        as.add(mCurrentMatchForScout);
        CSVData csvDataFile = new CSVData(as);

        try {
            csvDataFile.writeDataToCSV(getApplicationContext(), location);
            csvDataFile.writeDataToExternalDrive(getApplicationContext(), location);
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
