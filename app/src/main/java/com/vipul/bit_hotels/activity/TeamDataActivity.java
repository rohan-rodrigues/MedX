package com.vipul.bit_hotels.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vipul.bit_hotels.R;
import com.vipul.bit_hotels.adapter.MyAdapter;
import com.vipul.bit_hotels.utils.FirebaseDatabaseClass;

import java.util.ArrayList;

public class TeamDataActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
   // private ListView listview;
    private ArrayList<String> properties = new ArrayList<>();
    private String[] components = {"Crossed baseline in auton", "Auton - Cubes in switch", "Auton - Cubes in scale", "Teleop - Cubes in exchange", "Teleop - Cubes in scale", "Teleop - Cubes in switch", "Teleop - Cubes in opposite switch", "Fouls", "Driver Skill", "Climb%", "Climb 1/2 %", "Climb 1 %", "Climb 2 %", "Climb 3 %", "Defense Notes"};
//    private RecyclerView recyclerView;
//    private TeamViewDataAdapter adapter;
//    private List<DisplayTeam> mDisplayTeamList;
    private String teamNumber;

    private TextView teamTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_data);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("ASd");
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Intent i = getIntent();
        this.teamNumber = i.getStringExtra("team");
        teamTextView = (TextView) findViewById(R.id.team_number);
        teamTextView.setText("Team #" + teamNumber);

        ImageView imageView = (ImageView) findViewById(R.id.improvement_chart);

//        listview = (ListView) findViewById(R.id.viewList);
//        CustomListAdapter listAdapter = new CustomListAdapter(this, R.layout.list_item, properties);
//        listview.setAdapter(listAdapter);

        mAdapter = new MyAdapter(components, FirebaseDatabaseClass.listToArray(properties));
        mRecyclerView.setAdapter(mAdapter);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("SacramentoDriver tab");

//        // Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
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
//                        arr2 = ViewDataActivity.removeBrackets(arr2, 0);
//                    }
//                    else {
//                        arr2 = ViewDataActivity.removeBrackets(arr2, 1);
//                    }
//
//                    for (int j = 0; j < arr2.length; j++) {
//                        finalArr[i][j] = arr2[j];
//                    }
//                }

                String[][] finalArr = FirebaseDatabaseClass.createData(dataSnapshot);

                for (int i = 0; i < finalArr.length; i++) {
                    if (finalArr[i][0].equals(teamNumber)) {
                        for (int j = 2; j < finalArr[i].length; j++) {
                            properties.add(finalArr[i][j]);
                        }
                    }

                    System.out.println("AAAAA: " + properties);
                }

//                CustomListAdapter listAdapter = new CustomListAdapter(getApplicationContext(), R.layout.list_item_customized, properties);
//                listview.setAdapter(listAdapter);

                mAdapter = new MyAdapter(components, FirebaseDatabaseClass.listToArray(properties));
                mRecyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });




      //  ArrayList<PatientByStudent> teams = FIREBASE code from above

//        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//
//        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.addItemDecoration(new TeamDataActivity.GridSpacingItemDecoration(2, dpToPx(10), true));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//
//        mDisplayTeamList = new ArrayList<>();
//        adapter = new TeamViewDataAdapter(getApplicationContext(), mDisplayTeamList);
//        prepareTeams();
//        recyclerView.setAdapter(adapter);
    }


//    private void prepareTeams() {
//        int[] teams = new int[]{
//                R.drawable.firebots};
//
//        DisplayTeam a = new DisplayTeam("Firebots", 3501, teams[0]);
//        mDisplayTeamList.add(a);
//
//        DisplayTeam b = new DisplayTeam("Firebots", 3501, teams[0]);
//        mDisplayTeamList.add(b);
//
//        DisplayTeam c = new DisplayTeam("Firebots", 3501, teams[0]);
//        mDisplayTeamList.add(c);
//
//        adapter.notifyDataSetChanged();
//    }


//    /**
//     * RecyclerView item decoration - give equal margin around grid item
//     */
//    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
//
//        private int spanCount;
//        private int spacing;
//        private boolean includeEdge;
//
//        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
//            this.spanCount = spanCount;
//            this.spacing = spacing;
//            this.includeEdge = includeEdge;
//        }
//
//        @Override
//        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//            int position = parent.getChildAdapterPosition(view); // item position
//            int column = position % spanCount; // item column
//
//            if (includeEdge) {
//                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
//                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)
//
//                if (position < spanCount) { // top edge
//                    outRect.top = spacing;
//                }
//                outRect.bottom = spacing; // item bottom
//            } else {
//                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
//                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
//                if (position >= spanCount) {
//                    outRect.top = spacing; // item top
//                }
//            }
//        }
//    }
//
//    /**
//     * Converting dp to pixel
//     */
//    private int dpToPx(int dp) {
//        Resources r = getResources();
//        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
//    }
//
//
//
//    public class CustomListAdapter extends ArrayAdapter<String> {
//
//        private Context mContext;
//        private int id;
//        private List<String> items ;
//
//        public CustomListAdapter(Context context, int textViewResourceId , List<String> list ) {
//            super(context, textViewResourceId, list);
//            mContext = context;
//            id = textViewResourceId;
//            items = list ;
//        }
//
//        @Override
//        public View getView(int position, View v, ViewGroup parent)
//        {
//            View mView = v ;
//            if(mView == null){
//                LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                mView = vi.inflate(id, null);
//            }
//
//            TextView property = (TextView) mView.findViewById(R.id.textView);
//            TextView value = (TextView) mView.findViewById(R.id.textView2);
//
//            if(items.get(position) != null ) {
//                switch(position) {
//                    case 2:
//                        property.setText("Auton Baseline");
//                        break;
//                    case 3:
//                        property.setText("Auton Switch");
//                        break;
//                    case 4:
//                        property.setText("Auton Scale");
//                        break;
//                    case 5:
//                        property.setText("Tele Exchange");
//                        break;
//                    case 6:
//                        property.setText("Tele Scale");
//                        break;
//                    case 7:
//                        property.setText("Tele Switch");
//                        break;
//                    case 8:
//                        property.setText("Tele Opp Switch");
//                        break;
//                    case 9:
//                        property.setText("Cubes Placed While Not Owning Scale");
//                        break;
//                    case 10:
//                        property.setText("% of Cubes Placed While Not Owning Scale");
//                        break;
//                    case 11:
//                        property.setText("Fouls");
//                        break;
//                    case 12:
//                        property.setText("Driver Skill");
//                        break;
//                    case 13:
//                        property.setText("Fouls");
//                        break;
//                    case 14:
//                        property.setText("Get On Platform");
//                        break;
//                    case 15:
//                        property.setText("Climb %");
//                        break;
//                    case 16:
//                        property.setText("Climb 1/2 %");
//                        break;
//                    case 17:
//                        property.setText("Climb 1 %");
//                        break;
//                    case 18:
//                        property.setText("Climb 2 %");
//                        break;
//                    case 19:
//                        property.setText("Climb 3 %");
//                        break;
//                    case 20:
//                        property.setText("Qual Rank");
//                        break;
//
//                }
//                value.setText(items.get(position));
//
//            }
//
//            return mView;
//        }


}