package com.example.nwidc.huibo.fragment;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nwidc.huibo.R;
import com.example.nwidc.huibo.View.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by janiszhang on 2016/6/6.
 */

public class ContentFragment extends Fragment implements  OnBannerListener {

    private View viewContent;
    private int mType = 0;
    private String mTitle;


    public void setType(int mType) {
        this.mType = mType;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //布局文件中只有一个居中的TextView
        viewContent = inflater.inflate(R.layout.fragment_homecontent, container, false);
//        TextView textView = (TextView) viewContent.findViewById(R.id.tv_content);
//        textView.setText(this.mTitle);

        Banner banner = (Banner) viewContent.findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());

        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.banner);
        list.add(R.drawable.banner);

        //设置图片集合
        banner.setImages(list);
        banner.setOnBannerListener(this);
        //banner设置方法全部调用完毕时最后调用
        banner.start();



        return viewContent;
    }



    // 点击事件的回调函数
    @Override
    public void OnBannerClick(int position) {

        Toast.makeText(getActivity(), "ItemTitle"+position, Toast.LENGTH_SHORT).show();


    }

}

