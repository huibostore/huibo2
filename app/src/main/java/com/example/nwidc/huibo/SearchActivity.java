package com.example.nwidc.huibo;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by root on 17-6-24.
 */

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        //设置透明状态栏
        StatusbarUtils.enableTranslucentStatusbar(this);

        //按钮返回
        final TextView GoBack2 = (TextView) findViewById(R.id.btn_back);
        GoBack2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        TabLayout tabLayout= (TabLayout) findViewById(R.id.tab_essence);

        tabLayout.addTab(tabLayout.newTab().setText("商品"));

        tabLayout.addTab(tabLayout.newTab().setText("店铺"));


    }
}
