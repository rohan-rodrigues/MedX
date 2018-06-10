package com.vipul.bit_hotels.utils;

import android.app.Application;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vipul.bit_hotels.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by vipulkumar on 17/10/16.
 */

public class HotelsApplication extends Application {
    private static Firebase mRef;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeCalligraphy();

        if (!FirebaseApp.getApps(this).isEmpty()) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }

        Firebase.setAndroidContext(this.getApplicationContext());
        mRef = new Firebase("https://fireapp-cf0ac.firebaseio.com/");

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

//        mRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot child: dataSnapshot.child("Users").getChildren()) {
//                }
//            }
//        });
    }

    private void initializeUi() {
        Toast.makeText(this, "initialize ui", Toast.LENGTH_SHORT).show();
    }

    private void initializeCalligraphy() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/open_sansc_medium.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());

//        Bitter - Serif fonts
//        Dekar.otf - SD fonts, sophisticated
    }
}
