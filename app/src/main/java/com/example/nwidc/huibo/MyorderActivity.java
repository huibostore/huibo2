package com.example.nwidc.huibo;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nwidc.huibo.Adapter.HomeFragmentAdapter;
import com.example.nwidc.huibo.Adapter.MyorderFragmentAdetpter;
import com.example.nwidc.huibo.fragment.ContentFragment;
import com.example.nwidc.huibo.fragment.OrderlistFragment;
import com.example.nwidc.huibo.fragment.TestFragment3;
import com.shamanland.fonticon.FontIconTypefaceHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyorderActivity extends AppCompatActivity {

    private TabLayout tab_essence;
    private ViewPager vp_essence;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FontIconTypefaceHolder.init(getAssets(), "fontawesome-webfont.ttf");
        setContentView(R.layout.activity_myorder_title);
        //  跳转填写订单
//
        //按钮返回
        final TextView GoBack = (TextView) findViewById(R.id.btn_back);
        GoBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        tab_essence = (TabLayout) findViewById(R.id.tab_essence);
        vp_essence = (ViewPager) findViewById(R.id.vp_essence);

        String[] titles = new String[]{"待付款@dream@0", "待发货@dream@1", "待收货@dream@2", "待评价@dream@3","售后/退款@dream@3",};
        fragments= new ArrayList<Fragment>();
        fragments.add(new OrderlistFragment());
        fragments.add(new TestFragment3());
        fragments.add(new TestFragment3());
        fragments.add(new TestFragment3());
        fragments.add(new TestFragment3());

        //创建一个viewpager的adapter
        MyorderFragmentAdetpter adapter = new MyorderFragmentAdetpter(getSupportFragmentManager(), Arrays.asList(titles),fragments);
        vp_essence.setAdapter(adapter);

        //将TabLayout和ViewPager关联起来
        tab_essence.setupWithViewPager(vp_essence);
    }





}
