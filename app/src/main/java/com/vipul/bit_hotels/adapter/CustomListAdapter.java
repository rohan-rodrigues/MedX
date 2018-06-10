package com.vipul.bit_hotels.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.vipul.bit_hotels.R;

import java.util.List;

/**
 * Created by rohanrodrigues on 3/10/18.
 */

public class CustomListAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private int id;
    private List<String> items, alliances;

    public CustomListAdapter(Context context, int textViewResourceId , List<String> list, List<String> listAlliances) {
        super(context, textViewResourceId, list);
        mContext = context;
        id = textViewResourceId;
        items = list ;
        alliances = listAlliances;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent)
    {
        View mView = v ;
        if(mView == null){
            LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = vi.inflate(id, null);
        }

        TextView text = (TextView) mView.findViewById(R.id.textView);

        if(items.get(position) != null ) {

            if(alliances.get(position).equals("[\"red:\"")) {
                // Set a background color for ListView regular row/item
                text.setBackgroundColor(Color.RED);
            }
            else {
                // Set the background color for alternate row/item
                text.setBackgroundColor(Color.BLUE);
            }

            text.setTextColor(Color.WHITE);
            text.setText(items.get(position));
        }

        return mView;
    }

}