package com.example.nwidc.huibo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nwidc.huibo.LoginActivity;
import com.example.nwidc.huibo.LoginChenge;
import com.example.nwidc.huibo.R;

/**
 * Created by hello on 2017/6/14.
 */

public class Sign_Fragment extends Fragment {
    View view;
    View sign;
    String result;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        //view = inflater.inflate(R.layout.fragment_member,null);

        result = LoginChenge.getInstance().getLoginInfo();

            view = inflater.inflate(R.layout.fragment_sign,null);


//        RelativeLayout myorder = (RelativeLayout) view.findViewById(R.id.my_order);
//        myorder.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                Intent intent = new Intent(getActivity(), MyorderActivity.class);
//                startActivity(intent);
//
//            }
//        });

        if(result == "null"){
            Intent intent = new Intent();
            intent.setClass(getActivity(),LoginActivity.class);
            startActivity(intent);

        }

        return view;
    }
}
