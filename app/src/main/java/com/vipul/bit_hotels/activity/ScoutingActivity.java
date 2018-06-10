package com.vipul.bit_hotels.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.gson.Gson;
import com.vipul.bit_hotels.R;
import com.vipul.bit_hotels.utils.Competition;
import com.vipul.bit_hotels.utils.InternalStorage;
import com.vipul.bit_hotels.utils.MatchForScout;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rohanrodrigues on 1/25/18.
 */

public class ScoutingActivity extends BaseActivity {
    private final int STATIC_INTEGER_VALUE = 5;

    private ArrayList<Competition> competitions;
    boolean isSacramento = true;
   // ArrayAdapter<String> itemsAdapter;

    private final String SAVE_KEY = "123";

   // private GoogleApiClient mGoogleApiClient;


    private SwipeMenuListView mListView;
    private ArrayList<String> mArrayList=new ArrayList<>();
    private ListDataAdapter mListDataAdapter;

    private static final String[] SCOPES = { SheetsScopes.SPREADSHEETS };

    private static final String APPLICATION_NAME =
            "Firebots Scouting App";

    /** Directory to store user credentials for this application. */
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
            System.getProperty("user.home"), "credentials/sheets.googleapis.com-firebots-scouting-app");

    /** Global instance of the {@link FileDataStoreFactory}. */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY =
            JacksonFactory.getDefaultInstance();

    private static HttpTransport HTTP_TRANSPORT;



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scouting);

        try {
            String a = (String)InternalStorage.readObject(getApplicationContext(), SAVE_KEY);
            ArrayList temp = new Gson().fromJson(a, ArrayList.class);
            ArrayList<Competition> c = new ArrayList<>();
            for (int i = 0; i < temp.size(); i++) {
                c.add(new Gson().fromJson(String.valueOf(temp.get(i)), Competition.class));
            }
            if (!c.isEmpty()) {
                competitions = c;
                mArrayList = competitions.get(0).getMatchNames();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (competitions == null) {
            competitions = new ArrayList<>();
            competitions.add(new Competition("Sacramento"));
            competitions.add(new Competition("Arizona"));
        }

        initControls();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    //   toolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);
        setSupportActionBar(toolbar);
        toolbar.setTitle("ASd");
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        try {
//            HTTP_TRANSPORT = new com.google.api.client.http.javanet.NetHttpTransport();
//            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
//        } catch (Throwable t) {
//            t.printStackTrace();
//            System.out.println("EEEEXXXITTT");
//            System.exit(1);
//        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CreateScoutActivity.class);
                startActivityForResult(i, STATIC_INTEGER_VALUE);
             //   startActivity(new Intent(getApplicationContext(), CreateScoutActivity.class));
            }
        });

        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Snackbar mySnackbar = Snackbar.make(view,
                        "This will export the data to the Scouting Database", Snackbar.LENGTH_LONG);
                mySnackbar.setAction("Export", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//
//                        FirebaseDatabaseClass.storeMultipleCompetitions(competitions);
//                        competitions.get(0).getMatchForScouts().clear();
//                        competitions.get(1).getMatchForScouts().clear();

//                        try {
//                            writeDataToApi();
//                        } catch (IOException | GeneralSecurityException e) {
//                            e.printStackTrace();
//                        }

                    }
                });
                mySnackbar.show();

            }
        });


        final Switch simpleSwitch = (Switch) findViewById(R.id.simpleSwitch);

        simpleSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSacramento = !simpleSwitch.isChecked();
                if (isSacramento) {
                  //  itemsAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, competitions.get(0).getMatchNames());
                 //   listview.setAdapter(itemsAdapter);
                  //  mListDataAdapter.switchArrayList(competitions.get(0).getMatchNames());
                    mArrayList = competitions.get(0).getMatchNames();
                    mListDataAdapter.notifyDataSetChanged();
                }
                else {
                  //  itemsAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, competitions.get(1).getMatchNames());
                  //  listview.setAdapter(itemsAdapter);
                  //  mListDataAdapter.switchArrayList(competitions.get(1).getMatchNames());
                    mArrayList = competitions.get(1).getMatchNames();
                    mListDataAdapter.notifyDataSetChanged();
                }
            }
        });


//        listview.setAdapter(itemsAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListDataAdapter.notifyDataSetChanged();
                MatchForScout t;
                Intent i = new Intent(getApplicationContext(), CreateScoutActivity.class);
                Bundle b = new Bundle();
                if (isSacramento) {
                    t = competitions.get(0).getMatchForScouts().get(position);
                    b.putString("competition", "sacramento");
                }
                else {
                    t = competitions.get(1).getMatchForScouts().get(position);
                    b.putString("competition", "arizona");
                }
                String s = (new Gson().toJson(t));
                b.putString("match", s);
                i.putExtras(b);
                startActivityForResult(i, STATIC_INTEGER_VALUE);
            }
        });

    }


    private void initControls() {

        mListView=(SwipeMenuListView)findViewById(R.id.listview);
        mListView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        // mListView.setCloseInterpolator(new BounceInterpolator());

        mListDataAdapter=new ListDataAdapter();
        mListView.setAdapter(mListDataAdapter);


        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // Create different menus depending on the view type
//                SwipeMenuItem goodItem = new SwipeMenuItem(
//                        getApplicationContext());
//                // set item background
//                goodItem.setBackground(new ColorDrawable(Color.rgb(0x30, 0xB1,
//                        0xF5)));
//                // set item width
//                goodItem.setWidth(dp2px(90));
//                // set a icon
//                goodItem.setIcon(R.drawable.avd_airbnb);
//                // add to menu
//                menu.addMenuItem(goodItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_action_discard);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        // set creator
        mListView.setMenuCreator(creator);

        mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                if (isSacramento) {
                    competitions.get(0).getMatchForScouts().remove(position);
                    mArrayList = competitions.get(0).getMatchNames();
                    mListDataAdapter.notifyDataSetChanged();
                  //  mListDataAdapter.switchArrayList(competitions.get(0).getMatchNames());
                }
                else {
                    competitions.get(1).getMatchForScouts().remove(position);
                    mArrayList = competitions.get(1).getMatchNames();
                    mListDataAdapter.notifyDataSetChanged();
                   // mListDataAdapter.switchArrayList(competitions.get(1).getMatchNames());
                }

                try {
                    // String s = (new Gson().toJson(competitions));
                    ArrayList<String> strings = new ArrayList<>();
                    for (int i = 0; i < competitions.size(); i++) {
                        strings.add(new Gson().toJson(competitions.get(i)));
                    }
                    String s = (new Gson().toJson(strings));
                    InternalStorage.writeObject(getApplicationContext(), SAVE_KEY, s);
                } catch (IOException e) {
                    e.printStackTrace();
                }

              //  mListDataAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(),"MatchForScout deleted",Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        //mListView

        mListView.setOnMenuStateChangeListener(new SwipeMenuListView.OnMenuStateChangeListener() {
            @Override
            public void onMenuOpen(int position) {

            }

            @Override
            public void onMenuClose(int position) {

            }
        });

        mListView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {
            @Override
            public void onSwipeStart(int position) {

            }

            @Override
            public void onSwipeEnd(int position) {

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
                    MatchForScout t = new Gson().fromJson(data.getStringExtra("match"), MatchForScout.class);
                    boolean doesExist = data.getBooleanExtra("exists", false);
                   // PatientByStudent t = convertArrayListToString(teamPropertyList);
                   // teamManager.addTeam(t);
                    if (!doesExist)
                        addMatchToCompetition(t, data.getStringExtra("competition"));
                    else
                        updateTeam(t, data.getStringExtra("competition"));
                    // ADD PatientByStudent to CSV FILE
                   // saveToCSV();

                    try {
                       // String s = (new Gson().toJson(competitions));
                        ArrayList<String> strings = new ArrayList<>();
                        for (int i = 0; i < competitions.size(); i++) {
                            strings.add(new Gson().toJson(competitions.get(i)));
                        }
                        String s = (new Gson().toJson(strings));
                        InternalStorage.writeObject(getApplicationContext(), SAVE_KEY, s);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }

        }
    }

    private void writeDataToApi() throws IOException, GeneralSecurityException {
        Sheets service = createSheetsService();
        String spreadsheetId = "1Lij_q-s2LsCgbKQEx2Je2oKt_zWoDg_s0mfhB6OwZqw";
        String range = "YourSheetName!A1:B1"; //Read the docs on how these ranges work.
        //Currently, this is the range of a single row which would return
        //as [[objA, objB]] if major dimension is set as ROW.(default).
        // would be [[objA],[objB]] if its set to COLUMN. Read the doc for more info.

        //for the values that you want to input, create a list of object lists
        List<List<Object>> values = new ArrayList<>();

        //Where each value represents the list of objects that is to be written to a range
        //I simply want to edit a single row, so I use a single list of objects
        List<Object> data1 = new ArrayList<>();
        data1.add("objA");
        data1.add("objB");

        //There are obviously more dynamic ways to do these, but you get the picture
        values.add(data1);

        //Create the valuerange object and set its fields
        ValueRange valueRange = new ValueRange();
        valueRange.setMajorDimension("ROWS");
        valueRange.setRange(range);
        valueRange.setValues(values);

        //then gloriously execute this copy-pasted code ;)
        service.spreadsheets().values()
                .update(spreadsheetId, range, valueRange)
                .setValueInputOption("RAW")
                .execute();

        //Try calling this method before executing the readDataFromApi method,
        //and you'll see the immediate change

    }

    public static Sheets createSheetsService() throws IOException, GeneralSecurityException {

        // TODO: Change placeholder below to generate authentication credentials. See
        // https://developers.google.com/sheets/quickstart/java#step_3_set_up_the_sample
        //
        // Authorize using one of the following scopes:
        //   "https://www.googleapis.com/auth/drive"
        //   "https://www.googleapis.com/auth/drive.file"
        //   "https://www.googleapis.com/auth/spreadsheets"

        Credential credential = authorize();

        return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public static Credential authorize() throws IOException {
        // Load client secrets.
        InputStream in =
                ScoutingActivity.class.getResourceAsStream("/client_secret.json");
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, Arrays.asList(SCOPES))
                        .setDataStoreFactory(DATA_STORE_FACTORY)
                        .setAccessType("offline")
                        .build();
        Credential credential = new AuthorizationCodeInstalledApp(
                flow, new LocalServerReceiver()).authorize("user");
        System.out.println(
                "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
    }

    public void addMatchToCompetition(MatchForScout t, String competition) {
        System.out.println("HELLOOOOOOOOOOOOOOOOOOOO");
        switch (competition) {
            case "Sacramento":
                competitions.get(0).addMatch(t);
                if (isSacramento) {
                    mArrayList = competitions.get(0).getMatchNames();

                }
                return;
            case "Arizona" :
                competitions.get(1).addMatch(t);
                if (!isSacramento) {
                    mArrayList = competitions.get(1).getMatchNames();
                }
                return;
        }
        mListDataAdapter.notifyDataSetChanged();
    }

    public void updateTeam(MatchForScout t, String competition) {
        switch (competition) {
            case "Sacramento":
                boolean set = false;
                ArrayList<MatchForScout> matchForScouts = competitions.get(0).getMatchForScouts();
                for (int i = 0; i < matchForScouts.size(); i++) {
                    if (matchForScouts.get(i).getStudentName().equals(t.getStudentName())) {
                        matchForScouts.set(i, t);
                        set = true;
                    }
                }

                if (!set)
                    addMatchToCompetition(t, "Sacramento");
                return;
            case "Arizona" :
                boolean set2 = false;
                ArrayList<MatchForScout> matches2 = competitions.get(1).getMatchForScouts();
                for (int i = 0; i < matches2.size(); i++) {
                    if (matches2.get(i).getStudentName().equals(t.getStudentName())) {
                        matches2.set(i, t);
                        set2 = true;
                    }
                }

                if (!set2)
                    addMatchToCompetition(t, "Arizona");
        }
        mListDataAdapter.notifyDataSetChanged();
    }

    public ArrayList<Competition> getCompetitions() {
        return competitions;
    }

    public Competition getCompetition(String name) {
        for (int i = 0; i < competitions.size(); i++) {
            if (competitions.get(i).getCompName().equals(name))
                return competitions.get(i);
        }
        return null;
    }

    class ListDataAdapter extends BaseAdapter {
     //   private ArrayList<String> arrayList;

//        ListDataAdapter(ArrayList<String> list) {
//            this.arrayList = list;
//        }

        ViewHolder holder;
        @Override
        public int getCount() {
            return mArrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView==null){

                holder=new ViewHolder();

                convertView=getLayoutInflater().inflate(R.layout.list_item,null);

                holder.mTextview=(TextView)convertView.findViewById(R.id.textView);

                convertView.setTag(holder);
            }else {

                holder= (ViewHolder) convertView.getTag();
            }

            holder.mTextview.setText("MatchForScout #" + mArrayList.get(position));

            return convertView;
        }

        class ViewHolder {

            TextView mTextview;

        }
    }
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }



}
