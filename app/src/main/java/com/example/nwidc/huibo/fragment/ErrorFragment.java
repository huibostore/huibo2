package com.example.nwidc.huibo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nwidc.huibo.R;

/**
 * Created by hello on 2017/5/12.
 */

public class ErrorFragment extends Fragment {
    //private String content;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ly_content,container,false);

        return view;
    }
}
