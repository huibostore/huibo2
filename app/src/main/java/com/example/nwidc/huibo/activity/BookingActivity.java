package com.example.nwidc.huibo.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.nwidc.huibo.R;
import com.example.nwidc.huibo.View.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class BookingActivity extends AppCompatActivity {

    static final int REFRESH_COMPLETE = 0X1112;
    SwipeRefreshLayout mSwipeLayout;
    ListView listView;
    Banner banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);

        Banner banner = (Banner) findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());

        //资源文件
        //Integer[] images={R.drawable.ginfo_bg,R.drawable.ginfo_bg};
        //Uri
        //Uri uri = resourceIdToUri(context, R.mipmap.ic_launcher);
        //Uri[] images={uri};
        //设置图片集合
        //本地图片数据（资源文件）
        List<Integer> list=new ArrayList<>();
            list.add(R.drawable.ginfo_bg);
            list.add(R.drawable.ginfo_bg);
        banner.setImages(list);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

    }


}
