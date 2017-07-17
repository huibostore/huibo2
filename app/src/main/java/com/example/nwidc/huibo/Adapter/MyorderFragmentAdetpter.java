package com.example.nwidc.huibo.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by hello on 2017/5/31.
 */

public class MyorderFragmentAdetpter extends FragmentStatePagerAdapter {

    public static final String TAB_TAG = "@dream@";

    private List<String> mTitles;
    private List<Fragment> fragments;
    public MyorderFragmentAdetpter(FragmentManager fm, List<String> titles, List<Fragment> fragments) {
        super(fm);
        mTitles = titles;
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        //初始化Fragment数据
//        ContentFragment fragment = new ContentFragment();
//        String[] title = mTitles.get(position).split(TAB_TAG);
//        fragment.setType(Integer.parseInt(title[1]));
//        fragment.setTitle(title[0]);
//        return fragment;
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position).split(TAB_TAG)[0];
    }
}