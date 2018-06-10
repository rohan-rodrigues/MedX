package com.vipul.bit_hotels.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by rohanrodrigues on 3/10/18.
 */

public class CustomListAdapter2 extends ArrayAdapter<String> {

    private Context mContext;
    private int id;
    private List<String> items;

    public CustomListAdapter2(Context context, int textViewResourceId , List<String> list) {
        super(context, textViewResourceId, list);
        mContext = context;
        id = textViewResourceId;
        items = list ;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent)
    {
        View mView = v ;
        if(mView == null){
            LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = vi.inflate(id, null);
        }

        return mView;
    }

}