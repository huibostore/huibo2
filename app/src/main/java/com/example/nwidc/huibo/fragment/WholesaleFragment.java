package com.example.nwidc.huibo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nwidc.huibo.Adapter.HomeFragmentAdapter;
import com.example.nwidc.huibo.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hello on 2017/5/27.
 */

public class WholesaleFragment extends Fragment {

    private TabLayout tab_essence;
    private ViewPager vp_essence;
    private List<Fragment> fragments;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_wholesale_title,null);

        TextView tv = (TextView) view.findViewById(R.id.btn_back);

        tv.setOnClickListener(new View.OnClickListener(){
            @Nullable
            public void onClick(View v){

                getFragmentManager().popBackStack();

            }
        });

        initConentView(view);
        initData();



        return view;
    }
    public void initConentView(View view) {
        this.tab_essence = (TabLayout) view.findViewById(R.id.tab_essence);
        this.vp_essence = (ViewPager) view.findViewById(R.id.vp_essence);
    }

    public void initData() {
        //获取标签数据
        String[] titles = new String[]{"推荐@dream@0", "精选@dream@1", "热卖@dream@2", "易购@dream@3","淘宝@dream@3","本地@dream@3","团购@dream@3",};
        fragments= new ArrayList<Fragment>();
        fragments.add(new Wholesale_contentFragmeng());
        fragments.add(new TestFragment3());
        fragments.add(new TestFragment3());
        fragments.add(new TestFragment3());
        fragments.add(new TestFragment3());
        fragments.add(new TestFragment3());
        fragments.add(new TestFragment3());

        //创建一个viewpager的adapter
        HomeFragmentAdapter adapter = new HomeFragmentAdapter(getChildFragmentManager(), Arrays.asList(titles),fragments);
        this.vp_essence.setAdapter(adapter);

        //将TabLayout和ViewPager关联起来
        this.tab_essence.setupWithViewPager(this.vp_essence);
    }

}
