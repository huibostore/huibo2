package com.example.nwidc.huibo.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.graphics.Typeface;
import android.widget.TextView;

import com.example.nwidc.huibo.Adapter.SortFragmentAdapter;
import com.example.nwidc.huibo.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hello on 2017/5/15.
 */


public class SortFragment extends Fragment {
    private TabLayout tab_essence;
    private ViewPager vp_essence;
    private List<Fragment> fragments;
    //private String[] titles = new String[]{"惠博易购@dream@0", "惠博易购@dream@1", "惠博易购@dream@2", "惠博易购@dream@3"};



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sort,container,false);

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
        //String[] titles = getResources().getStringArray(R.array.sort_tab);
        String[] titles = new String[]{"惠博易购@dream@0", "惠博易购@dream@1", "惠博易购@dream@2", "惠博易购@dream@3", "惠博易购@dream@3"};
        //viewpager页面;
        fragments= new ArrayList<Fragment>();
        fragments.add(new Sort_ContentFragment());
        fragments.add(new TestFragment3());
        fragments.add(new TestFragment3());
        fragments.add(new TestFragment3());
        fragments.add(new TestFragment3());

        //创建一个viewpager的adapter
        SortFragmentAdapter adapter = new SortFragmentAdapter(getChildFragmentManager(), Arrays.asList(titles), fragments);
        this.vp_essence.setAdapter(adapter);
        this.vp_essence.setOffscreenPageLimit(5
        );

        //将TabLayout和ViewPager关联起来
        this.tab_essence.setupWithViewPager(this.vp_essence);
    }

}