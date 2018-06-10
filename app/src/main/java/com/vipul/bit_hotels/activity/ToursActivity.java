package com.vipul.bit_hotels.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.vipul.bit_hotels.R;

public class ToursActivity extends BaseActivity {
    private int[] imageIds;
    private int[] backupImages;
    private String[] titles;
    private String[] dates;
    private String[] descriptions;
    private String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tours);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //   toolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);
        setSupportActionBar(toolbar);
        toolbar.setTitle("MedX");
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.code = getIntent().getStringExtra("code");

        int[] ids = {R.id.peru, R.id.sierra_leone};
        String[] classes = {"peru", "sierra_leone"};
        imageIds = new int[]{R.drawable.peru, R.drawable.sierra_leone};
        backupImages = new int[]{R.drawable.peru, R.drawable.sierra_leone};
        titles = new String[] {"Peru", "Sierra Leone"};
        dates = new String[] {"South America", "Africa"};
        descriptions = new String[] {"Peru, country in western South America. Except for the Lake Titicaca basin in the southeast, its borders lie in sparsely populated zones.", "Sierra Leone is located on the west coast of Africa, between the 7th and 10th parallels north of the equator."};

        setImageListeners(ids, classes);
        initializeToolbar();
    }

    private void initializeToolbar() {
    }

    private void setImageListeners(int[] ids, final String[] classes) {
        if (ids.length != classes.length) {
            return;
        }

        for (int i = 0; i < ids.length; i++) {
            final RelativeLayout layout = (RelativeLayout) findViewById(ids[i]);
            final int finalI = i;
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(getApplicationContext(), TripDetailActivity.class);
                    i.putExtra("image", imageIds[finalI]);
                    i.putExtra("title", titles[finalI]);
                    i.putExtra("date", dates[finalI]);
                    i.putExtra("competitionDesc", descriptions[finalI]);
                    i.putExtra("code", code);
                    startActivityForResult(i, 1);
                }
            });

        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                this.code = data.getStringExtra("code");
            }
        }
    }
}
