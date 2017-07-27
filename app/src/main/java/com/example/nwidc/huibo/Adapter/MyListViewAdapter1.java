package com.example.nwidc.huibo.Adapter;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nwidc.huibo.R;

import java.util.ArrayList;


/**
 * Created by a on 2016/5/12.
 */
public class MyListViewAdapter1 extends BaseAdapter{

    private final ArrayList mMenus;
    private final Context context;
    private int selectIndex;

    public MyListViewAdapter1(ArrayList mMenus, Context context, int selectIndex){
        this.mMenus=mMenus;
        this.context=context;
        this.selectIndex=selectIndex;
    }
    @Override
    public int getCount() {
        return mMenus.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if(convertView==null){
            convertView=View.inflate(context, R.layout.item_listview_1,null);
            vh=new ViewHolder();
            vh.tv= (TextView) convertView.findViewById(R.id.textview);
            convertView.setTag(vh);
        }else {
            vh= (ViewHolder) convertView.getTag();
        }

        LinearLayout.LayoutParams selectParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
       //左上右下
        selectParams.setMargins(1,1,0,0);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.setMargins(1,1,1,0);

        if(position==selectIndex){
            vh.tv.setBackgroundColor(Color.parseColor("#f4f4f4"));
            vh.tv.setTextColor(Color.parseColor("#ff4965"));
            vh.tv.setLayoutParams(selectParams);
        }else {
            vh.tv.setBackgroundColor(Color.parseColor("#ffffff"));
            vh.tv.setTextColor(Color.parseColor("#555555"));
            vh.tv.setLayoutParams(params);
        }



//        vh.tv.setText("sty"+position);
        vh.tv.setText(mMenus.get(position).toString());
        return convertView;
    }

    public void setIndex(int index){
        selectIndex = index;
    }

    class ViewHolder{
        TextView tv;
    }
}
