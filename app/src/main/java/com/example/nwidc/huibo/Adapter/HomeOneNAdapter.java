package com.example.nwidc.huibo.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.daidingkang.SnapUpCountDownTimerView;
import com.example.nwidc.huibo.R;
import com.example.nwidc.huibo.View.ItemClick;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Integer.parseInt;

/**
 * Created by Carson_Ho on 17/4/26.
 */
public class HomeOneNAdapter extends DelegateAdapter.Adapter<HomeOneNAdapter.MainViewHolder>{
    // 使用DelegateAdapter首先就是要自定义一个它的内部类Adapter，让LayoutHelper和需要绑定的数据传进去
    // 此处的Adapter和普通RecyclerView定义的Adapter只相差了一个onCreateLayoutHelper()方法，其他的都是一样的做法.

    private ArrayList<HashMap<String, Object>> listItem;
    // 用于存放数据列表

    private Context context;
    private LayoutHelper layoutHelper;
    private RecyclerView.LayoutParams layoutParams;
    private int count = 0;

    private ItemClick myItemClickListener;
    // 用于设置Item点击事件

    private Typeface iconfont;

    //构造函数(传入每个的数据列表 & 展示的Item数量)
    public HomeOneNAdapter(Context context, LayoutHelper layoutHelper, int count, ArrayList<HashMap<String, Object>> listItem) {
        this(context, layoutHelper, count, new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 350), listItem);
    }

    public HomeOneNAdapter(Context context, LayoutHelper layoutHelper, int count, @NonNull RecyclerView.LayoutParams layoutParams, ArrayList<HashMap<String, Object>> listItem) {
        this.context = context;
        this.layoutHelper = layoutHelper;
        this.count = count;
        this.layoutParams = layoutParams;
        this.listItem = listItem;


    }

    @Override
    public int getItemViewType(int position) {
//            return super.getItemViewType(position);
            return position;
    }

    // 把ViewHolder绑定Item的布局
    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType == 0){
            return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.item_hometime, parent, false),viewType);
        }else{
            return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.item_homeonen, parent, false),viewType);
        }


    }


    // 此处的Adapter和普通RecyclerView定义的Adapter只相差了一个onCreateLayoutHelper()方法
    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return layoutHelper;
    }


    // 绑定Item的数据
    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        holder.Text.setText((String) listItem.get(position).get("ItemTitle"));
        holder.Text.setTextColor(Color.parseColor((String) listItem.get(position).get("ItemTextColor")));
        holder.icon.setTextColor(Color.parseColor((String) listItem.get(position).get("ItemTextColor")));

//        holder.image.setImageResource((Integer) listItem.get(position).get("ItemImage"));
        Picasso.with(context)
                .load((String) listItem.get(position).get("ItemUrl"))
                .into(holder.image);
        if(position == 0){
            holder.rushBuyCountDownTimerView.setTime(1,55,3);//设置小时，分钟，秒。注意不能大于正常值，否则会抛出异常
            holder.rushBuyCountDownTimerView.start();//开始倒计时
        }
        iconfont = Typeface.createFromAsset(context.getAssets(), "iconfont.ttf");

        holder.icon.setTypeface(iconfont);

        holder.icon.setText((String) listItem.get(position).get("ItemTextIcon"));

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
        public TextView Text;
        public TextView icon;
        public ImageView image;
        private SnapUpCountDownTimerView rushBuyCountDownTimerView;
        private int  position;
        public MainViewHolder(View root , final int position) {
            super(root);
            // 绑定视图
            Text = (TextView) root.findViewById(R.id.Item);
            image = (ImageView) root.findViewById(R.id.Image);
            icon = (TextView)root.findViewById(R.id.like);
            rushBuyCountDownTimerView = (SnapUpCountDownTimerView) root.findViewById(R.id.RushBuyCountDownTimerView);



            root.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (myItemClickListener != null)
                                                myItemClickListener.onItemClick(v, getPosition());
                                            ItemId(position);
                                        }

                                    }
                    //监听到点击就回调MainActivity的onItemClick函数
            );



        }

        public void ItemId(int position){
            System.out.println(position+"one");
        }


        public TextView getText() {
            return Text;
        }


    }



}

