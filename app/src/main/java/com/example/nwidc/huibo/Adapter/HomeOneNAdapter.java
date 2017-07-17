package com.example.nwidc.huibo.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.example.nwidc.huibo.R;
import com.example.nwidc.huibo.View.ItemClick;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Carson_Ho on 17/4/26.
 */
public class HomeOneNAdapter extends DelegateAdapter.Adapter<HomeOneNAdapter.MainViewHolder> {
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

    //构造函数(传入每个的数据列表 & 展示的Item数量)
    public HomeOneNAdapter(Context context, LayoutHelper layoutHelper, int count, ArrayList<HashMap<String, Object>> listItem) {
        this(context, layoutHelper, count, new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300), listItem);
    }

    public HomeOneNAdapter(Context context, LayoutHelper layoutHelper, int count, @NonNull RecyclerView.LayoutParams layoutParams, ArrayList<HashMap<String, Object>> listItem) {
        this.context = context;
        this.layoutHelper = layoutHelper;
        this.count = count;
        this.layoutParams = layoutParams;
        this.listItem = listItem;


    }

    // 把ViewHolder绑定Item的布局
    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.item_homeonen, parent, false));
    }


    // 此处的Adapter和普通RecyclerView定义的Adapter只相差了一个onCreateLayoutHelper()方法
    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return layoutHelper;
    }


    // 绑定Item的数据
    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
<<<<<<< HEAD
        holder.Text.setText((String) listItem.get(position).get("ItemTitle"));
=======
//        holder.Text.setText((String) listItem.get(position).get("ItemTitle"));
>>>>>>> 640d1af15a6e2ad719e2843e4f20708b4e00f537
        holder.image.setImageResource((Integer) listItem.get(position).get("ItemImage"));


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
<<<<<<< HEAD
        public TextView Text;
=======
//        public TextView Text;
>>>>>>> 640d1af15a6e2ad719e2843e4f20708b4e00f537
        public ImageView image;

        public MainViewHolder(View root) {
            super(root);

            // 绑定视图
<<<<<<< HEAD
            Text = (TextView) root.findViewById(R.id.Item);
=======
//            Text = (TextView) root.findViewById(R.id.Item);
>>>>>>> 640d1af15a6e2ad719e2843e4f20708b4e00f537
            image = (ImageView) root.findViewById(R.id.Image);

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

<<<<<<< HEAD
        public TextView getText() {
            return Text;
        }
=======
//        public TextView getText() {
//            return Text;
//        }
>>>>>>> 640d1af15a6e2ad719e2843e4f20708b4e00f537


    }
}

