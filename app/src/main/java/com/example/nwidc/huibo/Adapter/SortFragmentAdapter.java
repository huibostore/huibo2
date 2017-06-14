package com.example.nwidc.huibo.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.example.nwidc.huibo.fragment.Sort_ContentFragment;


import java.util.List;

/**
 * Created by janiszhang on 2016/6/10.
 */
//继承FragmentStatePagerAdapter
public class SortFragmentAdapter extends FragmentStatePagerAdapter {

    public static final String TAB_TAG = "@dream@";
    private List<Fragment> fragments;
    private List<String> mTitles;

    public SortFragmentAdapter(FragmentManager fm, List<String> titles,List<Fragment> fragments) {
        super(fm);
        mTitles = titles;
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        //初始化Fragment数据
        //Sort_ContentFragment fragment = new Sort_ContentFragment();
        return fragments.get(position);
        //return fragment;
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

