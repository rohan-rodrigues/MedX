package com.vipul.bit_hotels.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.vipul.bit_hotels.R;
import com.vipul.bit_hotels.activity.TripDetailActivity;
import com.vipul.bit_hotels.utils.Trip;

import java.util.List;

/**
 * Created by rohanrodrigues on 12/13/17.
 */

public class TripsAdapter extends RecyclerView.Adapter<TripsAdapter.ViewHolder> {

    private List<Trip> items;
    private int itemLayout;
    private View v;
    private Context context;

    public TripsAdapter(Context context, List<Trip> items, int itemLayout) {
        this.items = items;
        this.itemLayout = itemLayout;
        this.context = context;
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);

        return new ViewHolder(v);
    }

    @Override public void onBindViewHolder(final ViewHolder holder, int position) {
        final Trip item = items.get(position);
        holder.text.setText(item.getName());
        holder.date.setText(item.getDate());
        holder.image.setImageBitmap(null);
        Picasso.with(holder.image.getContext()).cancelRequest(holder.image);
        Picasso.with(holder.image.getContext()).load(item.getImageString()).into(holder.image);
        holder.itemView.setTag(item);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle  = new Bundle();
                bundle.putString("item", new Gson().toJson(item));
                Intent i = new Intent(context, TripDetailActivity.class);
                i.putExtras(bundle);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i, bundle);
            }
        });
    }

    @Override public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView text;
        public TextView date;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            text = (TextView) itemView.findViewById(R.id.text);
            date = (TextView) itemView.findViewById(R.id.date);
        }
    }

    public void add(Trip item, int position) {
        items.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(Trip item) {
        int position = items.indexOf(item);
        items.remove(position);
        notifyItemRemoved(position);
    }
}