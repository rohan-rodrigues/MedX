package com.vipul.bit_hotels.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vipul.bit_hotels.R;

public class TripDetailActivity extends BaseActivity {
    private int imageResource;
    private String title;
    private String date;
    private ImageView ivHeroImage;
    private TextView tvLocation;
    private TextView tvDate;

    private final int STATIC_INTEGER_VALUE = 5;

    private String access_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_detail);


//        String jsonMyObject = null;
//        Trip myObject = null;
//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            jsonMyObject = extras.getString("item");
//        }
//
//        if (jsonMyObject != null) {
//            myObject = new Gson().fromJson(jsonMyObject, Trip.class);
//        }

        ivHeroImage = (ImageView) findViewById(R.id.iv_hero_image);
        tvLocation = (TextView) findViewById(R.id.tv_location);
        tvDate = (TextView) findViewById(R.id.competition_date);

        imageResource = getIntent().getIntExtra("image", R.drawable.img_city_1);
        title = getIntent().getStringExtra("title");
        date = getIntent().getStringExtra("date");
        String competitionDesc = getIntent().getStringExtra("competitionDesc");

        ivHeroImage.setImageResource(imageResource);
        tvLocation.setText(title);
        tvDate.setText(date);

        TextView competitionText = (TextView) findViewById(R.id.competition_description);
        competitionText.setText(competitionDesc);


        initializeToolbar();

        Intent i = getIntent();
        access_code = i.getStringExtra("code");
        System.out.println("ACCESS CODE " + access_code);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startScoutingActivities(access_code);
            }
        });
    }



    private void initializeToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("");
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        mToolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (STATIC_INTEGER_VALUE) : {
                if (resultCode == Activity.RESULT_OK) {
                    //  ArrayList<String> teamPropertyList = data.getStringArrayListExtra("ABCD");
                  //  MatchForScout t = new Gson().fromJson(data.getStringExtra("match"), MatchForScout.class);
//                    if (data.getStringExtra("code").equals("lead"))
//                        finish();
//                    else
//                        startScoutingActivities(access_code);
                }
                break;
            }

        }
    }

    private void startScoutingActivities(String access_code) {
        switch(access_code + "") {
            case "student":
                Intent i = new Intent(getApplicationContext(), CreateScoutActivity.class);
                i.putExtra("competition", title);
                startActivityForResult(i, STATIC_INTEGER_VALUE);
                break;
            case "professor":
                Intent i2 = new Intent(getApplicationContext(), SuperScoutDetailsActivity.class);
                i2.putExtra("competition", title);
                startActivityForResult(i2, STATIC_INTEGER_VALUE);
                break;

        }
    }

}
