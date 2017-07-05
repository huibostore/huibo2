package com.example.nwidc.huibo.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nwidc.huibo.LoginActivity;
import com.example.nwidc.huibo.LoginChenge;
import com.example.nwidc.huibo.R;
import com.example.nwidc.huibo.SearchActivity;

/**
 * Created by hello on 2017/6/30.
 */

public class Search_historyFragment extends Fragment {

    View view;
    String result;
    private int number = 0;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        //view = inflater.inflate(R.layout.fragment_member,null);

        view = inflater.inflate(R.layout.fragment_search_history,null);

        TabLayout tabLayout= (TabLayout) view.findViewById(R.id.tab_essence);

        tabLayout.addTab(tabLayout.newTab().setText("商品"));

        tabLayout.addTab(tabLayout.newTab().setText("店铺"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                number = 1;
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });


        return view;
    }

    /*接口*/
    public interface CallBack{
        /*定义一个获取信息的方法*/
        public void getResult(int result);
    }

    /*接口回调*/
    public void getData(CallBack callBack){
    /*获取文本框的信息,当然你也可以传其他类型的参数,看需求咯*/

        callBack.getResult(number);
    }

}
