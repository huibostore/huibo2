package com.example.nwidc.huibo.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by hello on 2017/7/14.
 */

public class MainViewHolder extends RecyclerView.ViewHolder {

    public static volatile int existing = 0;
    public static int createdTimes = 0;

    public MainViewHolder(View itemView) {
        super(itemView);
        createdTimes++;
        existing++;
    }

    @Override
    protected void finalize() throws Throwable {
        existing--;
        super.finalize();
    }

}
