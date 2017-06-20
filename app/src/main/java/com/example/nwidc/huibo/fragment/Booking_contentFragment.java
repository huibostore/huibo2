package com.example.nwidc.huibo.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nwidc.huibo.R;

/**
 * Created by hello on 2017/6/2.
 */

public class Booking_contentFragment extends Fragment {

    protected View view;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        view = inflater.inflate(R.layout.fragment_booking,null);


        return view;
    }
}
