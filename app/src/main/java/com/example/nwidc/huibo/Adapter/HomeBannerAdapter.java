package com.example.nwidc.huibo.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.example.nwidc.huibo.R;
import com.example.nwidc.huibo.View.GlideImageLoader;
import com.example.nwidc.huibo.View.ItemClick;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * Created by Carson_Ho on 17/4/26.
 */
public class HomeBannerAdapter extends DelegateAdapter.Adapter<HomeBannerAdapter.MainViewHolder> implements  OnBannerListener  {
    // 使用DelegateAdapter首先就是要自定义一个它的内部类Adapter，让LayoutHelper和需要绑定的数据传进去
    // 此处的Adapter和普通RecyclerView定义的Adapter只相差了一个onCreateLayoutHelper()方法，其他的都是一样的做法.

    private ArrayList<HashMap<String, String>> listItem;
    // 用于存放数据列表

    private Context context;
    private LayoutHelper layoutHelper;
    private RecyclerView.LayoutParams layoutParams;
    private int count = 0;

    private ItemClick myItemClickListener;
    // 用于设置Item点击事件

    //构造函数(传入每个的数据列表 & 展示的Item数量)
    public HomeBannerAdapter(Context context, LayoutHelper layoutHelper, int count, ArrayList<HashMap<String, String>> listItem) {
        this(context, layoutHelper, count, new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300), listItem);
    }

    public HomeBannerAdapter(Context context, LayoutHelper layoutHelper, int count, @NonNull RecyclerView.LayoutParams layoutParams, ArrayList<HashMap<String, String>> listItem) {
        this.context = context;
        this.layoutHelper = layoutHelper;
        this.count = count;
        this.layoutParams = layoutParams;
        this.listItem = listItem;


    }

    // 把ViewHolder绑定Item的布局
    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.item_pager, parent, false));
    }


    // 此处的Adapter和普通RecyclerView定义的Adapter只相差了一个onCreateLayoutHelper()方法
    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return layoutHelper;
    }


    // 绑定Item的数据
    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
//        holder.Text.setText((String) listItem.get(position).get("ItemTitle"));
//        holder.image.setImageResource((Integer) listItem.get(position).get("ItemImage"));
        List<String> list=new ArrayList<>();

        for(int i = 0; i < listItem.size(); i++){

            list.add((String) listItem.get(i).get("adv_img"));

        }




        holder.banner.setImages(list);
        holder.banner.setOnBannerListener(this);


        //banner设置方法全部调用完毕时最后调用
        holder.banner.start();


    }

    // 返回Item数目
    @Override
    public int getItemCount() {
        return count;
    }

    // 设置Item的点击事件
    // 绑定MainActivity传进来的点击监听器
    public void setOnItemClickListener(ItemClick listener) {
        myItemClickListener = listener;
    }




    //定义Viewholder
    protected class MainViewHolder extends RecyclerView.ViewHolder {
//        public TextView Text;
//        public ImageView image;
        public Banner banner;

        public MainViewHolder(View root) {
            super(root);


            banner = (Banner) root.findViewById(R.id.banner);
            //设置图片加载器
            banner.setImageLoader(new GlideImageLoader());



            // 绑定视图
//            Text = (TextView) root.findViewById(R.id.Item);
//            image = (ImageView) root.findViewById(R.id.Image);

            root.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (myItemClickListener != null)
                                                myItemClickListener.onItemClick(v, getPosition());
                                        }

                                    }
                    //监听到点击就回调MainActivity的onItemClick函数
            );

        }

//        public TextView getText() {
//            return Text;
//        }



    }
    @Override
    // 点击事件的回调函数
    /*
        * adv_types: 1:商品、2：商家、3：活动；
        * adv_typesid: 对应上述id；
        * */
    public void OnBannerClick(int position) {

        System.out.println("点了第"+position+"行");
        System.out.println("goods_id"+listItem.get(position).get("goods_id")+"行");
        System.out.println("广告类型"+listItem.get(position).get("adv_types")+"行");


        switch (parseInt(listItem.get(position).get("adv_types"))){
            case 0 :
                //跳转商品页
                break;

            case 1 :
                    //跳转商品页
                break;

            case 2:
                //跳转商家
                break;

            case 3:
                //跳转活动页
                break;


        }



    }
}

