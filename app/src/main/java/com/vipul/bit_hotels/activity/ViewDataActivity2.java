package com.vipul.bit_hotels.activity;

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
import com.vipul.bit_hotels.adapter.CustomListAdapter2;
import com.vipul.bit_hotels.utils.FirebaseDatabaseClass;

import java.util.ArrayList;

/**
 * Created by rohanrodrigues on 3/7/18.
 */

public class ViewDataActivity2 extends AppCompatActivity {
    private ListView listview;
    private ArrayList<String> teams = new ArrayList<>();

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
        CustomListAdapter2 listAdapter = new CustomListAdapter2(this, R.layout.list_item, teams);
        listview.setAdapter(listAdapter);

        //  String[][] data = FirebaseDatabaseClass.readData();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("SacramentoDriver tab");

//        // Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String[][] finalArr = FirebaseDatabaseClass.createData(dataSnapshot);
//                String[][] finalArr = new String[6][21];
//                //  RetrievedMatch post = dataSnapshot.getValue(RetrievedMatch.class);
//                //   System.out.println(post);
//                Object jsonData = dataSnapshot.getValue();
//                //   System.out.println(jsonData);
//
//                assert ((String)jsonData) != null;
//                String[] arr = ((String)jsonData).split("]");
//
//                for (int i = 0; i < arr.length; i++) {
//                    String[] arr2 = arr[i].split(",");
//
//                    if (i == 0) {
//                        arr2 = removeBrackets(arr2, 0);
//                    }
//                    else {
//                        arr2 = removeBrackets(arr2, 1);
//                    }
//
//                    System.out.println("BESSS: " + Arrays.toString(arr2));
//
//                    for (int j = 0; j < arr2.length; j++) {
//                        finalArr[i][j] = arr2[j];
//                    }
//                }

                for (int i = 0; i < finalArr.length; i++) {
                    teams.add(finalArr[i][0]);
                    System.out.println(teams);
                }

                CustomListAdapter2 listAdapter = new CustomListAdapter2(getApplicationContext(), R.layout.list_item, teams);
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

        System.out.println(teams);
        // CustomListAdapter listAdapter = new CustomListAdapter(this, R.layout.list_item, teams);
        //  listview.setAdapter(listAdapter);


        listview.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int teamNumber = Integer.parseInt(teams.get(position));

                Intent i = new Intent(getApplicationContext(), TeamDataActivity.class);
                i.putExtra("team", teamNumber + "");
                startActivity(i);
            }
        });
    }



}
