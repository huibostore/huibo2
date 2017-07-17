package com.example.nwidc.huibo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.FloatLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelperEx;
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.example.nwidc.huibo.Adapter.BannerPagerAdapter;
import com.example.nwidc.huibo.Adapter.HomeBannerAdapter;
import com.example.nwidc.huibo.Adapter.HomeContextAdapter;
import com.example.nwidc.huibo.Adapter.HomeGridAdapter;
import com.example.nwidc.huibo.Adapter.HomeLinearAdapter;
import com.example.nwidc.huibo.Adapter.HomeOneNAdapter;
import com.example.nwidc.huibo.Adapter.HomeStagAdapter;
import com.example.nwidc.huibo.Adapter.MainViewHolder;
import com.example.nwidc.huibo.Adapter.SubAdapter;
import com.example.nwidc.huibo.R;
import com.example.nwidc.huibo.View.ItemClick;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class BookingActivity extends AppCompatActivity implements ItemClick  {
    RecyclerView recyclerView;
    HomeContextAdapter Adapter_linearLayout,Adapter_GridLayout,Adapter_FixLayout,Adapter_ScrollFixLayout
            ,Adapter_FloatLayout,Adapter_ColumnLayout,Adapter_SingleLayout,Adapter_onePlusNLayout,
            Adapter_StickyLayout,Adapter_StaggeredGridLayout;
    //单行线性布局
    HomeLinearAdapter HomeLinear;
    //宫格
    HomeGridAdapter HomeGrid;
    //瀑布流
    HomeStagAdapter HomeStag;
    //一拖n
    HomeOneNAdapter HomeOneNA;
    //轮播
    SubAdapter sub;
    //banner
    HomeBannerAdapter HomeBanner;
    private ArrayList<HashMap<String, Object>> listItem;
    private ArrayList<HashMap<String, Object>> gridlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        /**
         * 步骤1：创建RecyclerView & VirtualLayoutManager 对象并进行绑定
         * */
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // 创建RecyclerView对象

        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        // 创建VirtualLayoutManager对象
        // 同时内部会创建一个LayoutHelperFinder对象，用来后续的LayoutHelper查找

        recyclerView.setLayoutManager(layoutManager);
        // 将VirtualLayoutManager绑定到recyclerView

        /**
         * 步骤2：设置组件复用回收池
         * */
        final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);


        /**
         * 步骤3:设置需要存放的数据
         * */
        listItem = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 100; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemTitle", "第" + i + "行");
            map.put("ItemImage", R.drawable.a2);
            listItem.add(map);

        }

        gridlist = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 10; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemTitle", "第" + i + "行");
            map.put("ItemImage", R.drawable.daojia);
            gridlist.add(map);

        }





        /**
         * 步骤4:根据数据列表,创建对应的LayoutHelper
         * */

        /*
        *
        * */
//        sub = new SubAdapter(this, new LinearLayoutHelper(), 1) {
//
//            @Override
//            public void onViewRecycled(MainViewHolder holder) {
//                if (holder.itemView instanceof ViewPager) {
//                    ((ViewPager) holder.itemView).setAdapter(null);
//                }
//            }
//
//            @Override
//            public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                if (viewType == 1)
//                    return new MainViewHolder(
//                            LayoutInflater.from(BookingActivity.this).inflate(R.layout.view_pager, parent, false));
//
//                return super.onCreateViewHolder(parent, viewType);
//            }
//
//            @Override
//            public int getItemViewType(int position) {
//                return 1;
//            }
//
//            @Override
//            protected void onBindViewHolderWithOffset(MainViewHolder holder, int position, int offsetTotal) {
//
//            }
//
//            @Override
//            public void onBindViewHolder(MainViewHolder holder, int position) {
//                if (holder.itemView instanceof ViewPager) {
//                    ViewPager viewPager = (ViewPager) holder.itemView;
//
//                    viewPager.setLayoutParams(new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200));
//
//                    // from position to get adapter
//
//                    viewPager.setAdapter(new BannerPagerAdapter(this, viewPool));
//                }
//            }
//        };


        /**
         设置线性布局
         */

        // 设置每个Item的点击事件
        //
        //
        //
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
        HomeBanner  = new HomeBannerAdapter(this, linearLayoutHelper, 1, listItem) {
            @Override
            public void onBindViewHolder(MainViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);

                    List<Integer> list=new ArrayList<>();
                    list.add(R.drawable.ginfo_bg);
                    list.add(R.drawable.ginfo_bg);
                    holder.banner.setImages(list);
                    holder.banner.setOnBannerListener(this);


                    //banner设置方法全部调用完毕时最后调用
                    holder.banner.start();

            }


        };

        HomeBanner.setOnItemClickListener(this);
        // 设置每个Item的点击事件


        /**
         设置线性布局
         */

//        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        // 创建对应的LayoutHelper对象

        // 公共属性
        linearLayoutHelper.setItemCount(1);// 设置布局里Item个数
//        linearLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色

        // linearLayoutHelper特有属性
        linearLayoutHelper.setDividerHeight(10);
        // 设置间隔高度
        // 设置的间隔会与RecyclerView的addItemDecoration（）添加的间隔叠加.

        linearLayoutHelper.setMarginBottom(10);
        // 设置布局底部与下个布局的间隔


        // 创建自定义的Adapter对象 & 绑定数据 & 绑定对应的LayoutHelper进行布局绘制
        HomeLinear  = new HomeLinearAdapter(this, linearLayoutHelper, 1, listItem) {
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
         设置Grid布局
         */
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(5);
        // 在构造函数设置每行的网格个数

        // 公共属性
        gridLayoutHelper.setItemCount(10);// 设置布局里Item个数
        gridLayoutHelper.setMarginBottom(100);
        // 设置布局底部与下个布局的间隔
//        gridLayoutHelper.setPadding(5, 5, 5, 5);

        // gridLayoutHelper特有属性
//        gridLayoutHelper.setAutoExpand(false);//是否自动填充空白区域
        gridLayoutHelper.setSpanCount(5);// 设置每行多少个网格

        HomeGrid  = new HomeGridAdapter(this, gridLayoutHelper,10, gridlist) {
            // 设置需要展示的数据总数,此处设置是8,即展示总数是8个,然后每行是4个(上面设置的)
            // 为了展示效果,通过重写onBindViewHolder()将布局的第一个数据设置为gridLayoutHelper
            @Override
            public void onBindViewHolder(MainViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
//                // 为了展示效果,将布局里不同位置的Item进行背景颜色设置
//                if (position < 2) {
//                    holder.itemView.setBackgroundColor(0x66cc0000 + (position - 6) * 128);
//                } else if (position % 2 == 0) {
//                    holder.itemView.setBackgroundColor(0xaa22ff22);
//                } else {
//                    holder.itemView.setBackgroundColor(0xccff22ff);
//                }



                if (position == 0) {
                    holder.Text.setText("Grid");
                }
            }
        };

        HomeGrid.setOnItemClickListener(this);
        // 设置每个Item的点击事件


        /**
         设置1拖N布局
         */

        OnePlusNLayoutHelperEx helper = new OnePlusNLayoutHelperEx();
        helper.setBgColor(0xffffffff);
        helper.setMargin(0, 10, 0, 10);
        helper.setColWeights(new float[]{40f, 30f, 30f, 30f, 30f});

        HomeOneNA = new HomeOneNAdapter(this, helper,5, listItem) {
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
         设置栏格布局
         */
        ColumnLayoutHelper columnLayoutHelper = new ColumnLayoutHelper();
        // 创建对象

        // 公共属性
        columnLayoutHelper.setItemCount(3);// 设置布局里Item个数
//        columnLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        columnLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比
        columnLayoutHelper.setMarginBottom(100);
        // 设置布局底部与下个布局的间隔

        // columnLayoutHelper特有属性
        columnLayoutHelper.setWeights(new float[]{30, 40, 30});// 设置该行每个Item占该行总宽度的比例

        Adapter_ColumnLayout = new HomeContextAdapter(this, columnLayoutHelper,3, listItem) {
            // 设置需要展示的数据总数,此处设置是3
            // 为了展示效果,通过重写onBindViewHolder()将布局的第一个数据设置为Column
            @Override
            public void onBindViewHolder(MainViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                if (position == 0) {
                    holder.Text.setText("Column");
                }
            }
        };

        Adapter_ColumnLayout.setOnItemClickListener(this);
        // 设置每个Item的点击事件

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

        HomeStag = new HomeStagAdapter(this, staggeredGridLayoutHelper,20, listItem) {
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
         *步骤5:将生成的LayoutHelper 交给Adapter，并绑定到RecyclerView 对象
         **/

        // 1. 设置Adapter列表（同时也是设置LayoutHelper列表）
        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();

        // 2. 将上述创建的Adapter对象放入到DelegateAdapter.Adapter列表里
//        adapters.add(Adapter_linearLayout) ;
//        adapters.add(sub) ;
        adapters.add(HomeBanner) ;

        adapters.add(HomeGrid) ;
//        adapters.add(HomeLinear) ;

//
        adapters.add(HomeOneNA) ;
        adapters.add(Adapter_ColumnLayout) ;
//        adapters.add(Adapter_onePlusNLayout) ;
        adapters.add(HomeStag) ;


        //
        // 3. 创建DelegateAdapter对象 & 将layoutManager绑定到DelegateAdapter
        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager);

        // 4. 将DelegateAdapter.Adapter列表绑定到DelegateAdapter
        delegateAdapter.setAdapters(adapters);

        // 5. 将delegateAdapter绑定到recyclerView
        recyclerView.setAdapter(delegateAdapter);





    }

    /**
     *步骤7:实现Item点击事件
     **/
    // 点击事件的回调函数
    @Override
    public void onItemClick(View view, int postion) {
        System.out.println("点击了第"+postion+"行");
        if(postion == 0){
            Toast.makeText(this, "这是zero", Toast.LENGTH_SHORT).show();
        }else{

            Toast.makeText(this, (String) listItem.get(postion).get("ItemTitle"), Toast.LENGTH_SHORT).show();
        }

    }



}
