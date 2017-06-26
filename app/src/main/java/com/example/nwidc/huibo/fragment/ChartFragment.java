package com.example.nwidc.huibo.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nwidc.huibo.R;
import com.example.nwidc.huibo.View.AwsomeIconFont;

/**
 * Created by hello on 2017/5/12.
 */

public class ChartFragment extends Fragment implements View.OnClickListener{


    private TextView chart_check;
    private View view;
    private int status = 0;
//    public ChartFragment(String content) {
//        this.content = content;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_chart,container,false);
        bindViews();
        return view;
    }

    //UI组件初始化与事件绑定
    private void bindViews() {
        chart_check = (TextView) view.findViewById(R.id.chart_check);

        chart_check.setOnClickListener(this);
    }

    //重置所有文本的选中状态
    private void setSelected(){
        chart_check.setSelected(false);
    }


    @Override
    public void onClick (View v){
        setSelected();
        if(status == 0){
            chart_check.setSelected(true);
            status = 1;
        }else{
            chart_check.setSelected(false);
            status = 0;
        }



    }



}
