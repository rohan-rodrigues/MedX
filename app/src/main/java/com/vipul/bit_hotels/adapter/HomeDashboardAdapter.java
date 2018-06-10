package com.vipul.bit_hotels.adapter;

/**
 * Created by rohanrodrigues on 4/28/17.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.vipul.bit_hotels.R;

import java.util.List;

public class HomeDashboardAdapter extends RecyclerView.Adapter<HomeDashboardAdapter.MyViewHolder> {

    private Context mContext;
    private List<Integer> thumbNailList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        }
    }


    public HomeDashboardAdapter(Context mContext, List<Integer> thumbnailList) {
        this.mContext = mContext;
        this.thumbNailList = thumbnailList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_page_dashboard, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        // loading album cover using Glide library
        Glide.with(mContext).load(thumbNailList.get(position)).into(holder.thumbnail);
    }


    @Override
    public int getItemCount() {
        return thumbNailList.size();
    }
}