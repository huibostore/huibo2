package com.example.nwidc.huibo.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nwidc.huibo.MainActivity;
import com.example.nwidc.huibo.R;
import com.example.nwidc.huibo.TestActivity;

/**
 * Created by hello on 2017/6/6.
 */

public class SortAdapter extends BaseAdapter {

    private Context context;
    private String[] strings;
    public static int mPosition;

    public SortAdapter(Context context, String[] strings){
        this.context =context;
        this.strings = strings;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return strings.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return strings[position];
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder  viewHolder=new ViewHolder();
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_fragment, null);
            viewHolder.tv=(TextView) convertView.findViewById(R.id.tv);
            mPosition = position;
            viewHolder.tv.setText(strings[position]);
            viewHolder.tv.setSelected(false);
            if (position == TestActivity.mPosition) {
                viewHolder.tv.setSelected(true);
                convertView.setSelected(true);
            } else {
                convertView.setBackgroundColor(Color.parseColor("#f4f4f4"));
            }

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv.setText(strings[position]);
        viewHolder.tv.setSelected(false);
        //convertView.setBackgroundColor(0xFFFFFFFF);
        if (position == TestActivity.mPosition) {
            convertView.setBackgroundColor(0xFFF4F4F4);
            viewHolder.tv.setSelected(true);
        } else {
            convertView.setBackgroundColor(0xFFFFFFFF);
        }
        return convertView;
    }

    private static class ViewHolder
    {
        TextView tv;
    }
}
