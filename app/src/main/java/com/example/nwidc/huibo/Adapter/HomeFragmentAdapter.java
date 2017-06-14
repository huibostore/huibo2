package com.example.nwidc.huibo.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.nwidc.huibo.fragment.ContentFragment;

import java.util.List;

/**
 * Created by janiszhang on 2016/6/10.
 */
//继承FragmentStatePagerAdapter
public class HomeFragmentAdapter extends FragmentStatePagerAdapter {

    public static final String TAB_TAG = "@dream@";

    private List<String> mTitles;
    private List<Fragment> fragments;
    public HomeFragmentAdapter(FragmentManager fm, List<String> titles,List<Fragment> fragments) {
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

