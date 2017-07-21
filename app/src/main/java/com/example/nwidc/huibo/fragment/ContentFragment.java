package com.example.nwidc.huibo.fragment;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelperEx;
import com.example.nwidc.huibo.Adapter.HomeBannerAdapter;
import com.example.nwidc.huibo.Adapter.HomeContextAdapter;
import com.example.nwidc.huibo.Adapter.HomeGridAdapter;
import com.example.nwidc.huibo.Adapter.HomeLinearAdapter;
import com.example.nwidc.huibo.Adapter.HomeOneNAdapter;
import com.example.nwidc.huibo.Adapter.HomePromotionAdapter;
import com.example.nwidc.huibo.Adapter.HomeStagAdapter;
import com.example.nwidc.huibo.Adapter.HomeTitleAdapter;
import com.example.nwidc.huibo.Adapter.SubAdapter;
import com.example.nwidc.huibo.R;
import com.example.nwidc.huibo.View.GlideImageLoader;
import com.example.nwidc.huibo.View.ItemClick;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by janiszhang on 2016/6/6.
 */

public class ContentFragment extends Fragment implements ItemClick{

    private View viewContent;
    private int mType = 0;
    private String mTitle;

    RecyclerView recyclerView;
    HomeContextAdapter HomeCol;
    //单行线性布局
    HomeLinearAdapter HomeLinear;
    //宫格
    HomeGridAdapter HomeGrid;
    HomePromotionAdapter HomePromotion;
    HomePromotionAdapter HomeHot;
    //瀑布流
    HomeStagAdapter HomeStag;
    //一拖n
    HomeOneNAdapter HomeOneNA;
    HomeOneNAdapter HomeOneNA2;
    HomeOneNAdapter HomeOneNA3;
    HomeOneNAdapter HomeOneNA4;
    //标题
    HomeTitleAdapter HomeTitle;
    HomeTitleAdapter HomeHotTitle;
    //轮播
    SubAdapter sub;
    //banner
    HomeBannerAdapter HomeBanner;
    private ArrayList<HashMap<String, Object>> listItem;
    private ArrayList<HashMap<String, Object>> gridlist;

    public void setType(int mType) {
        this.mType = mType;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }
//    public VirtualLayoutManager layoutManager;
    public Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //布局文件中只有一个居中的TextView
//        viewContent = inflater.inflate(R.layout.fragment_homecontent, container, false);
        viewContent = inflater.inflate(R.layout.main_activity, container, false);


        context= getActivity();

        date();
        return viewContent;


    }


    public void date(){

        /**
         * 创建RecyclerView & VirtualLayoutManager 对象并进行绑定
         * */
        recyclerView = (RecyclerView) viewContent.findViewById(R.id.my_recycler_view);
        // 创建RecyclerView对象

        VirtualLayoutManager layoutManager = new VirtualLayoutManager(context);
        // 创建VirtualLayoutManager对象
        // 同时内部会创建一个LayoutHelperFinder对象，用来后续的LayoutHelper查找

        recyclerView.setLayoutManager(layoutManager);
        // 将VirtualLayoutManager绑定到recyclerView

        /**
         * 设置组件复用回收池
         * */
        final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);


        /**
         * 设置需要存放的数据
         * */
        listItem = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 150; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemTitle", "第" + i + "行");
            map.put("ItemImage", R.drawable.a2);
            listItem.add(map);


        }


        //轮播
        String[] bannerurl = {"http://upload.news.cecb2b.com/2016/0327/1459067610166.jpg","http://www.uli.com.cn/film/Article/UploadFiles/201105/2011052902361682.jpg","http://img0.imgtn.bdimg.com/it/u=4207633120,3867175424&fm=214&gp=0.jpg"};
        ArrayList bannerlist = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < bannerurl.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemTitle", bannerurl[i]);
            map.put("ItemImage", bannerurl[i]);
            bannerlist.add(map);

        }


        //首页格栏功能
        String[] grid = {"热卖","天猫","售后","淘宝","经典","条毛","哈哈","一二","外卖","按住"};
        Object[] img = {R.drawable.daojia,R.drawable.tmall,R.drawable.daojia,R.drawable.daojia,R.drawable.a2,R.drawable.a2,R.drawable.a2,
                R.drawable.a2,R.drawable.waimai,R.drawable.daojia,};
        gridlist = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 10; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemTitle", grid[i]);
            map.put("ItemImage", img[i]);
            gridlist.add(map);

        }

        //时间计时抢购
        ArrayList timelist = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 5; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemTitle", "第" + i + "行");
            map.put("ItemImage", R.drawable.a2);
            timelist.add(map);

        }

        /**
         设置线性布局
         */

        // 设置每个Item的点击事件
        //

        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        // 创建对应的LayoutHelper对象

        // 公共属性
        linearLayoutHelper.setItemCount(1);// 设置布局里Item个数
        linearLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色

        // linearLayoutHelper特有属性
        linearLayoutHelper.setDividerHeight(10);
        // 设置间隔高度
        // 设置的间隔会与RecyclerView的addItemDecoration（）添加的间隔叠加.

        linearLayoutHelper.setMarginBottom(10);
        // 设置布局底部与下个布局的间隔


        // 创建自定义的Adapter对象 & 绑定数据 & 绑定对应的LayoutHelper进行布局绘制
        HomeBanner  = new HomeBannerAdapter(context, linearLayoutHelper, 1, bannerlist) {
            @Override
            public void onBindViewHolder(MainViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);


            }


        };

        HomeBanner.setOnItemClickListener(this);
        // 设置每个Item的点击事件


        /**
         设置Grid布局
         */
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(5);
        // 在构造函数设置每行的网格个数

        // 公共属性
        gridLayoutHelper.setItemCount(10);// 设置布局里Item个数


        gridLayoutHelper.setSpanCount(5);// 设置每行多少个网格

        gridLayoutHelper.setPadding(10,10,10,10);

        HomeGrid  = new HomeGridAdapter(context, gridLayoutHelper,10, gridlist) {
            // 为了展示效果,通过重写onBindViewHolder()将布局的第一个数据设置为gridLayoutHelper
            @Override
            public void onBindViewHolder(MainViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);

            }
        };

        HomeGrid.setOnItemClickListener(this);
        // 设置每个Item的点击事件

        /**
         设置1拖N布局
         */

        OnePlusNLayoutHelperEx helper = new OnePlusNLayoutHelperEx();
        helper.setBgColor(Color.WHITE);
        helper.setPadding(10, 10, 10, 10);
        helper.setColWeights(new float[]{40f, 30f, 30f, 30f, 30f});

        HomeOneNA = new HomeOneNAdapter(context, helper,5, timelist) {
            @Override
            public void onBindViewHolder(HomeOneNAdapter.MainViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);


                // 为了展示效果,通过将布局的第一个数据设置为staggeredGrid
                if (position == 0) {
                    holder.image.setMaxHeight(1000);

                    holder.image.setImageResource(R.drawable.qg);
                }
            }
        };

        HomeOneNA.setOnItemClickListener(this);


        /**
         *将生成的LayoutHelper 交给Adapter，并绑定到RecyclerView 对象
         **/

        // 1. 设置Adapter列表（同时也是设置LayoutHelper列表）
        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();

        // 2. 将上述创建的Adapter对象放入到DelegateAdapter.Adapter列表里

        adapters.add(HomeBanner);
        adapters.add(HomeGrid);
        adapters.add(HomeOneNA);
//        adapters.add(HomeOneNA2);
//        adapters.add(HomeOneNA3);
//        adapters.add(HomeLinear);
//        adapters.add(HomeTitle);
//        adapters.add(HomePromotion);
//        adapters.add(HomeHotTitle);
//        adapters.add(HomeHot);
//        adapters.add(HomeBrandTitle);
//        adapters.add(HomeOneNA4);
//        adapters.add(HomeIntegTitle);
//        adapters.add(HomeInteg);
//        adapters.add(HomeFindTitle);
//        adapters.add(HomeFind);

//        adapters.add(HomeCol) ;
//        adapters.add(HomeStag);


        // 3. 创建DelegateAdapter对象 & 将layoutManager绑定到DelegateAdapter
        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager);

        // 4. 将DelegateAdapter.Adapter列表绑定到DelegateAdapter
        delegateAdapter.setAdapters(adapters);
        recyclerView.setAdapter(delegateAdapter);
    }




    /**
     *实现Item点击事件
     **/
    // 点击事件的回调函数
    @Override
    public void onItemClick(View view, int postion) {
        System.out.println("点击了第"+postion+"行");

        if(postion == 0){
            Toast.makeText(context, "这是zero", Toast.LENGTH_SHORT).show();
        }else{

            Toast.makeText(context, (String) listItem.get(postion).get("ItemTitle"), Toast.LENGTH_SHORT).show();
        }

    }


}

