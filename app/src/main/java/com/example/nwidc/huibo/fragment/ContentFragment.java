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
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelperEx;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;
import com.example.nwidc.huibo.Adapter.HomeBannerAdapter;
import com.example.nwidc.huibo.Adapter.HomeContextAdapter;
import com.example.nwidc.huibo.Adapter.HomeGridAdapter;
import com.example.nwidc.huibo.Adapter.HomeIntegAdapter;
import com.example.nwidc.huibo.Adapter.HomeLinearAdapter;
import com.example.nwidc.huibo.Adapter.HomeOneNAdapter;
import com.example.nwidc.huibo.Adapter.HomePromotionAdapter;
import com.example.nwidc.huibo.Adapter.HomeStagAdapter;
import com.example.nwidc.huibo.Adapter.HomeTitleAdapter;
import com.example.nwidc.huibo.Adapter.SubAdapter;
import com.example.nwidc.huibo.Person;
import com.example.nwidc.huibo.R;
import com.example.nwidc.huibo.Util.Config;
import com.example.nwidc.huibo.View.FourPlusNLayoutHelperEx;
import com.example.nwidc.huibo.View.GlideImageLoader;
import com.example.nwidc.huibo.View.ItemClick;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import cn.alien95.resthttp.BuildConfig;
import cn.alien95.resthttp.request.RestHttp;
import cn.alien95.resthttp.request.callback.HttpCallback;
import cn.alien95.resthttp.request.http.HttpRequest;

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

    private List<advresult> adv;

    public void setType(int mType) {
        this.mType = mType;
    }
    private  String GET_URL = Config.Adver_URL;
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
        //restHttp
//        RestHttp.initialize(context);
//        RestHttp.setDiskCacheSize(100 * 1024 * 1024);
//        if (BuildConfig.DEBUG) {
//            RestHttp.setDebug(true, "network");
//        }


        get("江苏");
        return viewContent;


    }

    /*
    * http get()请求；
    *
    * */

    public void get(String key){
        //搜索接口+搜索值
        GET_URL = GET_URL + key;
        System.out.println(GET_URL);
        //restHttp
        HttpRequest.getInstance().get(GET_URL, new HttpCallback() {

            //返回http页面信息 info
            @Override
            public void success(String info) {
                //搜索列表listview
                advresult(info);

            }
        });

    }


    public void advresult(String info){

        Gson gson = new Gson();

        adv = gson.fromJson(info, new TypeToken<List<advresult>>(){}.getType());





//        for(advresult o: adv){
//
//            System.out.println(o);
//        }


        date();
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

        /*
        * {"adv_img":"http:\/\/192.168.1.107:808\/data\/upload\/shop\/adv\/05539679555927531.jpg","adv_url":"regoin","adv_types":"1","adv_typesid":"1"}
        * adv_img:图片
        * adv_url:
        * adv_types: 0：商品、1:商品、2：商家、3：活动；
        * adv_typesid: 对应上述id；
        * */

        //轮播
        //bannerurl = new String[]{"http://upload.news.cecb2b.com/2016/0327/1459067610166.jpg", "http://www.uli.com.cn/film/Article/UploadFiles/201105/2011052902361682.jpg", "http://img0.imgtn.bdimg.com/it/u=4207633120,3867175424&fm=214&gp=0.jpg"};
        int banadv;
        if(adv.size()< 5){
            banadv = adv.size();
        }else{
            banadv = 5;
        }
        ArrayList bannerlist = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < banadv; i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("adv_types", adv.get(i).adv_types);
            map.put("goods_id", adv.get(i).adv_typesid);
            map.put("adv_img", adv.get(i).adv_img);
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
        String[] oneplus = {"限时抢购","阶梯团购","一元云购","好货预售","低价批发",};
        String[] ItemTextColor = {"#C36C80","#FF8356","#4DC8CD","#9E4FCC","#C06566",};
        String[] ItemTextIcon = {"\ue67c","\ue64a","\ue6cd","\ue719","\ue815",};
        String[] ItemId = {"55","56","57","19","815",};
        String[] ItemUrl = {"http://pic99.nipic.com/file/20160528/22428631_174702466001_2.jpg","http://pic134.nipic.com/file/20170706/9558814_130718096000_2.jpg","http://pic42.nipic.com/20140622/7447430_153027359001_2.jpg","http://pic89.nipic.com/file/20160219/22577866_105725337000_2.jpg","http://pic3.nipic.com/20090612/1242397_114710001_2.jpg",};

        ArrayList timelist = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 5; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemTitle",oneplus[i]);
            map.put("ItemTextColor",ItemTextColor[i] );
            map.put("ItemUrl",ItemUrl[i] );
            map.put("ItemTextIcon",ItemTextIcon[i] );
            map.put("ItemId",ItemId[i] );
            timelist.add(map);

        }

//        //时间计时抢购
//        String[] oneplus = {"限时抢购","阶梯团购","一元云购","好货预售","低价批发","司马仲达"};
//        String[] ItemTextColor = {"#C36C80","#FF8356","#4DC8CD","#9E4FCC","#C06566","#C06566"};
//        String[] ItemTextIcon = {"\ue67c","\ue64a","\ue6cd","\ue719","\ue815","\ue815"};
//        String[] ItemId = {"55","56","57","19","815","55"};
//        String[] ItemUrl = {"http://pic99.nipic.com/file/20160528/22428631_174702466001_2.jpg","http://pic134.nipic.com/file/20170706/9558814_130718096000_2.jpg","http://pic42.nipic.com/20140622/7447430_153027359001_2.jpg","http://pic89.nipic.com/file/20160219/22577866_105725337000_2.jpg","http://pic3.nipic.com/20090612/1242397_114710001_2.jpg","http://pic99.nipic.com/file/20160528/22428631_174702466001_2.jpg"};
//
//        ArrayList timelist = new ArrayList<HashMap<String, Object>>();
//        for (int i = 0; i < 5; i++) {
//            HashMap<String, Object> map = new HashMap<String, Object>();
//            map.put("ItemTitle",oneplus[i]);
//            map.put("ItemTextColor",ItemTextColor[i] );
//            map.put("ItemUrl",ItemUrl[i] );
//            map.put("ItemTextIcon",ItemTextIcon[i] );
//            map.put("ItemId",ItemId[i] );
//            timelist.add(map);
//
//        }

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

//        gridLayoutHelper.setPadding(10,10,10,10);

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
//        helper.setPadding(10,10,10,10);
        helper.setColWeights(new float[]{36f, 32f, 32f, 32f, 32f});

        HomeOneNA = new HomeOneNAdapter(context, helper,5, timelist) {
            @Override
            public void onBindViewHolder(HomeOneNAdapter.MainViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);

            }
        };

        HomeOneNA.setOnItemClickListener(this);

        /**
         设置N拖1布局
         */

        FourPlusNLayoutHelperEx helper2 = new FourPlusNLayoutHelperEx();
        helper2.setBgColor(Color.WHITE);
//        helper2.setPadding(10,10,10,10);
        helper2.setColWeights(new float[]{36f, 32f, 32f, 32f, 32f});

        HomeOneNA2 = new HomeOneNAdapter(context, helper2,5, timelist) {
            @Override
            public void onBindViewHolder(HomeOneNAdapter.MainViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);



            }
        };

        HomeOneNA2.setOnItemClickListener(this);


        /**
         设置线性布局
         */
        SingleLayoutHelper single = new SingleLayoutHelper();
        // 公共属性
        single.setItemCount(1);// 设置布局里Item个数
        single.setBgColor(Color.WHITE);// 设置背景颜色
        single.setMarginTop(10);


        // 创建自定义的Adapter对象 & 绑定数据 & 绑定对应的LayoutHelper进行布局绘制
        HomeLinear  = new HomeLinearAdapter(context, single, 1, listItem) {
            @Override
            public void onBindViewHolder(MainViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                if (position == 0) {
                    holder.image.setImageResource(R.drawable.ad1);
                }
            }
        };

        HomeLinear.setOnItemClickListener(this);






        /**
         设置栏格布局
         */
        ColumnLayoutHelper columnLayoutHelper = new ColumnLayoutHelper();
        // 创建对象

        // 公共属性
        columnLayoutHelper.setItemCount(3);// 设置布局里Item个数
        columnLayoutHelper.setBgColor(Color.WHITE);// 设置背景颜色
        columnLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比

        // columnLayoutHelper特有属性
        columnLayoutHelper.setWeights(new float[]{30, 40, 30});// 设置该行每个Item占该行总宽度的比例

        HomeCol = new HomeContextAdapter(context, columnLayoutHelper,3, listItem) {
            // 设置需要展示的数据总数,此处设置是3
            // 为了展示效果,通过重写onBindViewHolder()将布局的第一个数据设置为Column
            @Override
            public void onBindViewHolder(MainViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);

            }
        };

        HomeCol.setOnItemClickListener(this);
        // 设置每个Item的点击事件



        /**
         设置1拖N布局
         */

        OnePlusNLayoutHelperEx helper3 = new OnePlusNLayoutHelperEx();
        helper3.setBgColor(Color.WHITE);
        helper3.setMargin(20, 10, 10, 20);
        helper3.setColWeights(new float[]{40f, 30f, 30f, 30f, 30f});

        HomeOneNA3 = new HomeOneNAdapter(context, helper3,5, timelist) {
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

        HomeOneNA3.setOnItemClickListener(this);


        /**
         设置线性布局
         */
        SingleLayoutHelper singles = new SingleLayoutHelper();
        // 公共属性
        singles.setItemCount(1);// 设置布局里Item个数
        singles.setBgColor(Color.WHITE);// 设置背景颜色
        singles.setMarginTop(10);
        // 设置布局底部与下个布局的间隔
        HomeTitle  = new HomeTitleAdapter(context, singles , 1) {
            @Override
            public void onBindViewHolder(MainViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                if (position == 0) {
                    holder.Text.setText("促销专题");
                }
            }
        };

        /**
         设置Grid布局
         促销
         */

        GridLayoutHelper  gridb = new GridLayoutHelper(3);
        // 在构造函数设置每行的网格个数

        // 公共属性
        gridb.setItemCount(9);// 设置布局里Item个数
        gridb.setBgColor(Color.parseColor("#f4f4f4"));
        gridb.setSpanCount(3);// 设置每行多少个网格
        gridb.setPadding(0,10,0,10);
        gridb.setAspectRatio(3);// 设置设置布局内每行布局的宽与高的比
//        gridb.setWeights(new float[]{ 30f,30f,30f});

        gridb.setVGap(20);// 控制子元素之间的垂直间距
        gridb.setHGap(20);// 控制子元素之间的水平间距

        HomePromotion  = new HomePromotionAdapter(context, gridb,9, gridlist) {
            // 设置需要展示的数据总数,此处设置是8,即展示总数是8个,然后每行是4个(上面设置的)
            // 为了展示效果,通过重写onBindViewHolder()将布局的第一个数据设置为gridLayoutHelper
            @Override
            public void onBindViewHolder(MainViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));

            }
        };

        HomePromotion.setOnItemClickListener(this);
        // 设置每个Item的点击事件



        /**
         设置Grid布局
         促销
         */

        GridLayoutHelper  gridc = new GridLayoutHelper(3);
        // 在构造函数设置每行的网格个数

        // 公共属性
        gridc.setItemCount(8);// 设置布局里Item个数
        gridc.setBgColor(Color.parseColor("#f4f4f4"));
        gridc.setSpanCount(4);// 设置每行多少个网格
        gridc.setPadding(0,10,0,10);
        gridc.setAspectRatio(3);// 设置设置布局内每行布局的宽与高的比
//        gridb.setWeights(new float[]{ 30f,30f,30f});

        gridc.setVGap(20);// 控制子元素之间的垂直间距
        gridc.setHGap(20);// 控制子元素之间的水平间距

        HomeHot  = new HomePromotionAdapter(context, gridc,8, gridlist) {
            // 设置需要展示的数据总数,此处设置是8,即展示总数是8个,然后每行是4个(上面设置的)
            // 为了展示效果,通过重写onBindViewHolder()将布局的第一个数据设置为gridLayoutHelper
            @Override
            public void onBindViewHolder(MainViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));

            }
        };

        HomeHotTitle  = new HomeTitleAdapter(context, single, 1) {
            @Override
            public void onBindViewHolder(MainViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                if (position == 0) {
                    holder.Text.setText("热门市场");
                }
            }
        };

        HomeHot.setOnItemClickListener(this);
        // 设置每个Item的点击事件

        /**
         设置1拖N布局
         */

        OnePlusNLayoutHelperEx helper4 = new OnePlusNLayoutHelperEx();
        helper4.setBgColor(Color.WHITE);
        helper4.setMargin(20, 10, 10, 20);
//        helper4.setColWeights(new float[]{40f, 60f, 60f, 33f, 33f,33f});

        HomeOneNA4 = new HomeOneNAdapter(context, helper4,6, timelist) {
            @Override
            public void onBindViewHolder(HomeOneNAdapter.MainViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);


            }
        };

        HomeOneNA4.setOnItemClickListener(this);

        HomeTitleAdapter HomeBrandTitle  = new HomeTitleAdapter(context, single, 1) {
            @Override
            public void onBindViewHolder(MainViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                if (position == 0) {
                    holder.Text.setText("品牌盛典");
                }
            }
        };


        /**
         设置Grid布局
         积分
         */
        GridLayoutHelper  Integ = new GridLayoutHelper(3);
        // 公共属性
        Integ.setItemCount(6);// 设置布局里Item个数
        Integ.setBgColor(Color.parseColor("#f4f4f4"));
        Integ.setSpanCount(3);// 设置每行多少个网格
        Integ.setPadding(0,10,0,10);
        Integ.setAspectRatio(3);// 设置设置布局内每行布局的宽与高的比

        Integ.setVGap(20);// 控制子元素之间的垂直间距
        Integ.setHGap(20);// 控制子元素之间的水平间距

        HomeIntegAdapter HomeInteg  = new HomeIntegAdapter(context, Integ,6, gridlist) {
            // 设置需要展示的数据总数,此处设置是8,即展示总数是8个,然后每行是4个(上面设置的)
            // 为了展示效果,通过重写onBindViewHolder()将布局的第一个数据设置为gridLayoutHelper
            @Override
            public void onBindViewHolder(MainViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));

            }
        };

        HomeInteg.setOnItemClickListener(this);
        // 设置每个Item的点击事件



        HomeTitleAdapter HomeIntegTitle  = new HomeTitleAdapter(context, single, 1) {
            @Override
            public void onBindViewHolder(MainViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                if (position == 0) {
                    holder.Text.setText("积分商城");
                }
            }
        };


        /**
         设置Grid布局
         发现
         */
        GridLayoutHelper  gride = new GridLayoutHelper(3);
        // 公共属性
        gride.setItemCount(9);// 设置布局里Item个数
        gride.setBgColor(Color.parseColor("#f4f4f4"));
        gride.setSpanCount(3);// 设置每行多少个网格
        gride.setPadding(0,10,0,10);
        gride.setAspectRatio(3);// 设置设置布局内每行布局的宽与高的比

        gride.setVGap(20);// 控制子元素之间的垂直间距
        gride.setHGap(20);// 控制子元素之间的水平间距

        HomePromotionAdapter HomeFind  = new HomePromotionAdapter(context, gride,9, gridlist) {
            // 设置需要展示的数据总数,此处设置是8,即展示总数是8个,然后每行是4个(上面设置的)
            // 为了展示效果,通过重写onBindViewHolder()将布局的第一个数据设置为gridLayoutHelper
            @Override
            public void onBindViewHolder(MainViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));

            }
        };

        HomeFind.setOnItemClickListener(this);
        // 设置每个Item的点击事件



        HomeTitleAdapter HomeFindTitle  = new HomeTitleAdapter(context, single, 1) {
            @Override
            public void onBindViewHolder(MainViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                if (position == 0) {
                    holder.Text.setText("发现好店");
                }
            }
        };

        /**
         设置瀑布流布局
         */

        StaggeredGridLayoutHelper staggeredGridLayoutHelper = new StaggeredGridLayoutHelper();
        // 创建对象

        // 公有属性
        staggeredGridLayoutHelper.setItemCount(20);// 设置布局里Item个数
        staggeredGridLayoutHelper.setBgColor(Color.parseColor("#f4f4f4"));// 设置背景颜色
        staggeredGridLayoutHelper.setAspectRatio(0);// 设置设置布局内每行布局的宽与高的比

        // 特有属性
        staggeredGridLayoutHelper.setLane(2);// 设置控制瀑布流每行的Item数
        staggeredGridLayoutHelper.setHGap(20);// 设置子元素之间的水平间距
        staggeredGridLayoutHelper.setVGap(15);// 设置子元素之间的垂直间距

        HomeStag = new HomeStagAdapter(context, staggeredGridLayoutHelper,20, listItem) {
            // 设置需要展示的数据总数,此处设置是20

            // 通过重写onBindViewHolder()设置更加丰富的布局
            @Override
            public void onBindViewHolder(MainViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                //ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,150 +position % 5 * 20);
                //holder.itemView.setLayoutParams(layoutParams);

                // 为了展示效果,设置不同位置的背景色
                holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));

                holder.image.setImageResource(R.drawable.cuxiao6);

                // 为了展示效果,通过将布局的第一个数据设置为staggeredGrid
                if (position == 0) {

                    holder.Text.setText("staggeredGrid大神请多来点简单的谢谢， 这个 对我这种基本的 太难了。");

                }
            }
        };

        HomeStag.setOnItemClickListener(this);
        // 设置每个Item的点击事件



        /**
         *将生成的LayoutHelper 交给Adapter，并绑定到RecyclerView 对象
         **/

        // 1. 设置Adapter列表（同时也是设置LayoutHelper列表）
        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();

        // 2. 将上述创建的Adapter对象放入到DelegateAdapter.Adapter列表里

        adapters.add(HomeBanner);
        adapters.add(HomeGrid);
        adapters.add(HomeOneNA);
        adapters.add(HomeOneNA2);
        adapters.add(HomeOneNA3);
        adapters.add(HomeLinear);
        adapters.add(HomeTitle);
        adapters.add(HomePromotion);
        adapters.add(HomeHotTitle);
        adapters.add(HomeHot);
//        adapters.add(HomeBrandTitle);
//        adapters.add(HomeOneNA4);
        adapters.add(HomeIntegTitle);
        adapters.add(HomeInteg);
//        adapters.add(HomeFindTitle);
//        adapters.add(HomeFind);
        adapters.add(HomeCol) ;
        adapters.add(HomeStag);


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

    public class advresult{


        public String adv_img;
        public String adv_url;
        public String adv_types;
        public String adv_typesid;


        @Override
        public String toString() {
            return this.adv_img  + this.adv_types + this.adv_typesid;
        }

    }


}

