package com.vipul.bit_hotels.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vipul.bit_hotels.R;
import com.vipul.bit_hotels.activity.TripDetailActivity;
import com.vipul.bit_hotels.model.LocationItem;

import uk.co.ribot.easyadapter.ItemViewHolder;
import uk.co.ribot.easyadapter.PositionInfo;
import uk.co.ribot.easyadapter.annotations.LayoutId;
import uk.co.ribot.easyadapter.annotations.ViewId;

@LayoutId(R.layout.item_location)
public class LocationsAdapter extends ItemViewHolder<LocationItem> implements View.OnClickListener {
    @ViewId(R.id.tv_title)
    private TextView tvTitle;
    @ViewId(R.id.tv_subtitle)
    private TextView tvSubtitle;
    @ViewId(R.id.iv_location)
    private ImageView ivLocation;
    @ViewId(R.id.location_layout)
    private RelativeLayout locationLayout;
    @ViewId(R.id.search_layout)
    private RelativeLayout searchLayout;
    @ViewId(R.id.filters_layout)
    private LinearLayout filtersLayout;

    private String[] titlesArray = {"Peru", "Sierra Leone"};
    private String[] subtitlesArray = {"South America", "Africa"};
    private int[] drawables4 = {R.drawable.peru, R.drawable.sierra_leone};
    private int[] drawables2 = {R.drawable.img_city_37, R.drawable.img_city_27};
    private int[] drawables = {R.drawable.img_city_37, R.drawable.img_city_31};
    private int[] drawables3 = {R.drawable.img_wall_1, R.drawable.img_wall_4};
    private String[] descriptions = new String[] {"Peru, country in western South America. Except for the Lake Titicaca basin in the southeast, its borders lie in sparsely populated zones.", "Sierra Leone is located on the west coast of Africa, between the 7th and 10th parallels north of the equator."};

    private String accessCode;

    private LocationItem locationItem;

    public LocationsAdapter(View view) {
        super(view);
    }

    @Override
    public void onSetValues(LocationItem locationItem, final PositionInfo positionInfo) {
        this.locationItem = locationItem;
        this.accessCode = locationItem.getCode();
        tvTitle.setText(titlesArray[positionInfo.getPosition()]);
        tvSubtitle.setText(subtitlesArray[positionInfo.getPosition()]);

//        Picasso.with(getContext())
//                .load(drawables3[positionInfo.getPosition()])
//                .placeholder(R.drawable.ic_pl)
//                .into(ivLocation);

        ivLocation.setImageResource(drawables4[positionInfo.getPosition()]);

//        if (positionInfo.getPosition() == 20) {
//            searchLayout.setVisibility(View.VISIBLE);
//        } else {
//            searchLayout.setVisibility(View.GONE);
//        }
        searchLayout.setVisibility(View.GONE);

//        if (positionInfo.getPosition() % 2 != 0) {
//            filtersLayout.setVisibility(View.VISIBLE);
//        } else {
//            filtersLayout.setVisibility(View.GONE);
//        }

        locationLayout.setOnClickListener
                (new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = null;

                        Pair<View, String> p1 = Pair.create((View) ivLocation, ivLocation.getTransitionName());
                        ActivityOptionsCompat options = ActivityOptionsCompat.
                                makeSceneTransitionAnimation(((Activity) getContext()), p1);
                        bundle = options.toBundle();
                        Intent i = new Intent(getContext(), TripDetailActivity.class);
                      //  i.putExtra("code", ((MainActivity)this.get).getCode());
                        i.putExtra("image", drawables4[positionInfo.getPosition()]);
                        i.putExtra("title", titlesArray[positionInfo.getPosition()]);
                        i.putExtra("date", subtitlesArray[positionInfo.getPosition()]);
                        i.putExtra("competitionDesc", descriptions[positionInfo.getPosition()]);
                        i.putExtra("code", accessCode);
                        getContext().startActivity(i, bundle);
                    }
    }

    );
}

    @Override
    public void onClick(View clickedView) {
        switch (clickedView.getId()) {
        }
    }

    public void setAccessCode(String code) {
        this.accessCode = code;
    }

}