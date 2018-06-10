package com.vipul.bit_hotels.adapter;

/**
 * Created by rohanrodrigues on 3/7/18.
 */


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vipul.bit_hotels.R;
import com.vipul.bit_hotels.utils.DisplayTeam;

import java.util.List;

public class TeamViewDataAdapter extends RecyclerView.Adapter<TeamViewDataAdapter.MyViewHolder> {

    private Context mContext;
    private List<DisplayTeam> mDisplayTeamList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        }
    }


    public TeamViewDataAdapter(Context mContext, List<DisplayTeam> displayTeamList) {
        this.mContext = mContext;
        this.mDisplayTeamList = displayTeamList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.team_view_adapter, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        DisplayTeam displayTeam = mDisplayTeamList.get(position);
        holder.title.setText(displayTeam.getName());
        holder.count.setText("" + displayTeam.getTeamNumber());

        // loading displayTeam cover using Glide library
        Glide.with(mContext).load(displayTeam.getThumbnail()).into(holder.thumbnail);
    }


    @Override
    public int getItemCount() {
        return mDisplayTeamList.size();
    }
}