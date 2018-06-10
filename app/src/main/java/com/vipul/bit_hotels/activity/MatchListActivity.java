package com.vipul.bit_hotels.activity;

/**
 * Created by rohanrodrigues on 3/22/18.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vipul.bit_hotels.R;
import com.vipul.bit_hotels.adapter.MatchListAdapter;
import com.vipul.bit_hotels.utils.FirebaseDatabaseClass;

import java.util.ArrayList;

/**
 * Created by rohanrodrigues on 3/7/18.
 */

public class MatchListActivity extends AppCompatActivity {
    private ListView listview;
    private ArrayList<String> matches = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //   toolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);
        setSupportActionBar(toolbar);
        toolbar.setTitle("ASd");
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listview = (ListView) findViewById(R.id.viewList);
        MatchListAdapter listAdapter = new MatchListAdapter(this, R.layout.list_item, matches);
        listview.setAdapter(listAdapter);

        //  String[][] data = FirebaseDatabaseClass.readData();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("SacramentoDriver tab");

//        // Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String[][] finalArr = FirebaseDatabaseClass.createData(dataSnapshot);

                for (int i = 0; i < finalArr.length; i++) {
                    System.out.println("DAATA: " + finalArr[i][1]);
                    matches.add(finalArr[i][1]);
                }

                MatchListAdapter listAdapter = new MatchListAdapter(getApplicationContext(), R.layout.list_item, matches);
                listview.setAdapter(listAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

//        for (int i = 0; i < finalArr.length; i++) {
//            for (int j = 0; j < finalArr[i].length; j++) {
//                System.out.print(finalArr[i][j]);
//            }
//            System.out.println();
//        }


//        for (int i = 0; i < finalArr.length; i++) {
//            System.out.println("DAATA: " + finalArr[i][1]);
//            teams.add(finalArr[i][1] + "");
//            teamAlliances.add(finalArr[i][0] + "");
//        }


//        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
//                (this, android.R.layout.simple_list_item_1, teams){
//
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent){
//                // Get the current item from ListView
//                View view = super.getView(position,convertView,parent);
//                if(teamAlliances.get(position).equals("red"))
//                {
//                    // Set a background color for ListView regular row/item
//                    view.setBackgroundColor(Color.RED);
//                }
//                else
//                {
//                    // Set the background color for alternate row/item
//                    view.setBackgroundColor(Color.BLUE);
//                }
//                return view;
//            }
//        };

        // CustomListAdapter listAdapter = new CustomListAdapter(this, R.layout.list_item, teams);
        //  listview.setAdapter(listAdapter);


        listview.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int matchNumber = Integer.parseInt(matches.get(position));

                Intent i = new Intent(getApplicationContext(), ViewDataActivity.class);
                i.putExtra("matchNumber", matchNumber + "");
                startActivity(i);
            }
        });
    }



}

