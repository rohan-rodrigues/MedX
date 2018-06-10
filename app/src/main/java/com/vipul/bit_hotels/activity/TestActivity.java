package com.vipul.bit_hotels.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vipul.bit_hotels.R;
import com.vipul.bit_hotels.utils.FirebaseDatabaseClass;

import java.io.IOException;

/**
 * Created by rohanrodrigues on 3/22/18.
 */

public class TestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_data);

        final ImageView image = (ImageView) findViewById(R.id.improvement_chart);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("SacramentoImage");

//        // Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Bitmap bm = null;
                try {
                    bm = FirebaseDatabaseClass.bytesToImage(dataSnapshot);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("FAiled ");
                }

                System.out.println("BITTTCCCC: " + (bm == null));
                image.setImageBitmap(Bitmap.createScaledBitmap(bm, image.getWidth(),
                        image.getHeight(), false));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


    }
}