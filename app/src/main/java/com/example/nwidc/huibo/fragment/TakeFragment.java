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
 * Created by hello on 2017/5/26.
 */

public class TakeFragment extends Fragment {

    private View view;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        view = inflater.inflate(R.layout.activity_take,null);

        TextView tv = (TextView) view.findViewById(R.id.btn_back);
        tv .setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        return view;
    }

}
