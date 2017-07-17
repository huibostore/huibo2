package com.example.nwidc.huibo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nwidc.huibo.R;
import com.example.nwidc.huibo.View.Search;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;

/**
 * Created by hello on 2017/7/3.
 */

public class SearchAdapter  extends BaseAdapter {

    private LinkedList<Search> mData;
    private Context mContext;

    public SearchAdapter(LinkedList<Search> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position = mData.get(position).getaid();
        //return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_search_list,parent,false);
        ImageView img_icon = (ImageView) convertView.findViewById(R.id.img_icon);
        TextView txt_aName = (TextView) convertView.findViewById(R.id.txt_aName);
        TextView txt_aSpeak = (TextView) convertView.findViewById(R.id.txt_aSpeak);
//        img_icon.setBackgroundResource(mData.get(position).getaIcon());
        String url = mData.get(position).getaIcon();
        Picasso.with(mContext)
                .load(url)
                .into(img_icon);
        Picasso.with(mContext).invalidate(url);
        txt_aName.setText(mData.get(position).getaName());
        txt_aSpeak.setText(mData.get(position).getaSpeak());
        return convertView;
    }

}
