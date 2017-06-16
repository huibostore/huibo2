package com.example.nwidc.huibo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nwidc.huibo.R;
import com.example.nwidc.huibo.SharedHelper;

import java.util.Map;

/**
 * Created by hello on 2017/5/27.
 */

public class NearbyFragment extends Fragment {

    View view;
    TextView city;
    TextView head;
    private Context mContext;
    private SharedHelper sh;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.activity_nearby,null);

        mContext = getActivity().getApplicationContext();
        sh = new SharedHelper(mContext);

        TextView tv = (TextView)view.findViewById(R.id.btn_back);


        tv.setOnClickListener(new View.OnClickListener(){
            @Nullable
            public void onClick(View v){

                getFragmentManager().popBackStack();

            }

        });
        city = (TextView)view.findViewById(R.id.neabycity);
        head = (TextView)view.findViewById(R.id.citytop);
        city();

        return view;
    }

    private void city() {
        Map<String,String> data = sh.readcity();

        if(data.get("city") == ""){
            city.setText("城市");
            head.setText("城市");
        }else{
            city.setText(data.get("city"));
            head.setText(data.get("city"));
        }
    }

}
