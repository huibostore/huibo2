package com.example.nwidc.huibo;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nwidc.huibo.fragment.ChartFragment;
import com.example.nwidc.huibo.fragment.CollectFragment;
import com.example.nwidc.huibo.fragment.HomeMenuFragment;
import com.example.nwidc.huibo.fragment.MemberFragment;
import com.example.nwidc.huibo.fragment.Search_historyFragment;
import com.example.nwidc.huibo.fragment.Search_listFragment;
import com.example.nwidc.huibo.fragment.Sign_Fragment;
import com.example.nwidc.huibo.fragment.SortFragment;

/**
 * Created by root on 17-6-24.
 */

public class SearchActivity extends AppCompatActivity {
    private FragmentManager fManager;
    private FrameLayout ly_content;
    private Search_historyFragment history;
    private Search_listFragment list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        fManager = getSupportFragmentManager();

        //设置透明状态栏
        StatusbarUtils.enableTranslucentStatusbar(this);

        //按钮返回
        final TextView GoBack2 = (TextView) findViewById(R.id.btn_back);
        GoBack2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        TextView search = (TextView)findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                LinearLayout content = (LinearLayout)findViewById(R.id.content);

            }

        });

        ly_content = (FrameLayout) findViewById(R.id.ly_content);

        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        if(history == null){
            history = new Search_historyFragment();

            fTransaction.add(R.id.ly_content,history);

        }else{
            fTransaction.show(history);

        }

        TextView searchs = (TextView) findViewById(R.id.search);

        searchs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fTransaction = fManager.beginTransaction();
                hideAllFragment(fTransaction);
                if(list == null){
                    list = new Search_listFragment();
                    fTransaction.add(R.id.ly_content,list);
                }else{
                    fTransaction.show(list);
                }

                fTransaction.commit();
            }
        });
        fTransaction.commit();


    }

    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(history != null)fragmentTransaction.hide(history);
    }


}
