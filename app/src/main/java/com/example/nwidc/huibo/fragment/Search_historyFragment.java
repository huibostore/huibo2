package com.example.nwidc.huibo.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nwidc.huibo.LoginActivity;
import com.example.nwidc.huibo.LoginChenge;
import com.example.nwidc.huibo.R;

/**
 * Created by hello on 2017/6/30.
 */

public class Search_historyFragment extends Fragment {

    View view;
    String result;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        //view = inflater.inflate(R.layout.fragment_member,null);

        view = inflater.inflate(R.layout.fragment_search_history,null);

        TabLayout tabLayout= (TabLayout) view.findViewById(R.id.tab_essence);

        tabLayout.addTab(tabLayout.newTab().setText("商品"));

        tabLayout.addTab(tabLayout.newTab().setText("店铺"));


        return view;
    }

}
