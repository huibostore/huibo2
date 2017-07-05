package com.example.nwidc.huibo.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nwidc.huibo.Adapter.SearchAdapter;
import com.example.nwidc.huibo.Goods_infoActivity;
import com.example.nwidc.huibo.R;
import com.example.nwidc.huibo.Util.Config;
import com.example.nwidc.huibo.View.Search;
import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.List;

import cn.alien95.resthttp.BuildConfig;
import cn.alien95.resthttp.request.RestHttp;
import cn.alien95.resthttp.request.callback.HttpCallback;
import cn.alien95.resthttp.request.http.HttpRequest;

/**
 * Created by hello on 2017/6/30.
 */

public class Search_listFragment  extends Fragment implements AdapterView.OnItemClickListener{
    private List<Search> mData = null;
    private Context mContext;
    private Context Context;
    private SearchAdapter mAdapter = null;
    private ListView list_animal;
    private View view;
    //定义一个图片显示控件
    private ImageView imageView;
    private TextView result;
    private  String GET_URL = Config.Search;
    private String keyword ;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        view = inflater.inflate(R.layout.fragment_searchresult,null);

        mContext = getActivity();
        Context = getContext();
        list_animal = (ListView) view.findViewById(R.id.list_animal);
        result = (TextView)view.findViewById(R.id.result);
        String [] [] list = {{"广东商人出资百万助人上位 操控村委决策8年广东商人出资百万助人上位 操控村委决策8年","￥123.01元","https://gw3.alicdn.com/bao/uploaded/i1/581894172/TB2TSydq4RDOuFjSZFzXXcIipXa_!!581894172.jpg_210x210.jpg","5"},
                {"广东商人出资百万助人上位 操控村委决策8年广东商人出资百万助人上位 操控村委决策8年","￥123.11元","https://img.alicdn.com/imgextra/i4/1835106055803824256/TB2Wxc3vCFmpuFjSZFrXXayOXXa_!!0-saturn_solar.jpg_210x210.jpg","6"},
                {"广东商人出资百万助人上位 操控村委决策8年广东商人出资百万助人上位 操控村委决策8年","￥123.11元","https://img.alicdn.com/imgextra/i4/1835106055803824256/TB2Wxc3vCFmpuFjSZFrXXayOXXa_!!0-saturn_solar.jpg_210x210.jpg","6"},
                {"广东商人出资百万助人上位 操控村委决策8年广东商人出资百万助人上位 操控村委决策8年","￥123.11元","https://img.alicdn.com/imgextra/i4/1835106055803824256/TB2Wxc3vCFmpuFjSZFrXXayOXXa_!!0-saturn_solar.jpg_210x210.jpg","6"},
                {"广东商人出资百万助人上位 操控村委决策8年广东商人出资百万助人上位 操控村委决策8年","￥123.11元","https://img.alicdn.com/imgextra/i4/1835106055803824256/TB2Wxc3vCFmpuFjSZFrXXayOXXa_!!0-saturn_solar.jpg_210x210.jpg","6"},
                {"广东商人出资百万助人上位 操控村委决策8年广东商人出资百万助人上位 操控村委决策8年","￥123.11元","https://img.alicdn.com/imgextra/i4/1835106055803824256/TB2Wxc3vCFmpuFjSZFrXXayOXXa_!!0-saturn_solar.jpg_210x210.jpg","6"},
                {"广东商人出资百万助人上位 操控村委决策8年广东商人出资百万助人上位 操控村委决策8年","￥123.11元","https://img.alicdn.com/imgextra/i4/1835106055803824256/TB2Wxc3vCFmpuFjSZFrXXayOXXa_!!0-saturn_solar.jpg_210x210.jpg","7"},
                {"广东商人出资百万助人上位 操控村委决策8年广东商人出资百万助人上位 操控村委决策8年","￥123111.01元","https://gw3.alicdn.com/bao/uploaded/i3/TB1wR8LRpXXXXbvXVXXXXXXXXXX_!!0-item_pic.jpg_210x210.jpg","8"}};



        mData = new LinkedList<Search>();

        for(int i=0;i<list.length;i++){
            mData.add(new Search(list[i][0] , list[i][1] , list[i][2], Integer.parseInt(list[i][3])));

        }

        mAdapter = new SearchAdapter((LinkedList<Search>) mData, mContext);
        list_animal.setAdapter(mAdapter);
        list_animal.setOnItemClickListener( this);


        //图片资源
//        String url = "http://m.weather.com.cn/img/b0.gif";
//        imageView = (ImageView)view.findViewById(R.id.img_icon);
//        Picasso.with(getContext())
//                .load(url)
//                .into(imageView);

        RestHttp.initialize(mContext);
        RestHttp.setDiskCacheSize(100 * 1024 * 1024);
        if (BuildConfig.DEBUG) {
            RestHttp.setDebug(true, "network");
        }
        Bundle bundle = getArguments();//从activity传过来的Bundle
        if(bundle!=null){
            keyword =  bundle.getString("search_con");
            Toast.makeText(mContext,keyword, Toast.LENGTH_SHORT).show();
        }

        get();
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(mContext,"你点击了第" + id + "项", Toast.LENGTH_SHORT).show();
        int gid= new Long(id).intValue();

        Intent it = new Intent();
        it.setClass(getActivity(), Goods_infoActivity.class);
        it.putExtra("gid", gid);
        startActivity(it);
    }

    public void get(){
        GET_URL = GET_URL + keyword;
        HttpRequest.getInstance().get(GET_URL, new HttpCallback() {
            @Override
            public void success(String info) {
                Toast.makeText(mContext,"你点击了第" + "项", Toast.LENGTH_SHORT).show();
                //result.setText(new Gson().toJson(info));
                result.setText(info+GET_URL);
            }
        });
    }



}
