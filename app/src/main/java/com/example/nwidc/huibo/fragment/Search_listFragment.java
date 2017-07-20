package com.example.nwidc.huibo.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.example.nwidc.huibo.Person;
import com.example.nwidc.huibo.R;
import com.example.nwidc.huibo.Util.Config;
import com.example.nwidc.huibo.View.Search;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.ArrayList;
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
    private  String GET_URL = Config.Search;
    private String keyword ;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        view = inflater.inflate(R.layout.fragment_searchresult,null);

        mContext = getActivity();
        Context = getContext();

        //restHttp
        RestHttp.initialize(mContext);
        RestHttp.setDiskCacheSize(100 * 1024 * 1024);
        if (BuildConfig.DEBUG) {
            RestHttp.setDebug(true, "network");
        }

        Bundle bundle = getArguments();//从activity传过来的Bundle

        if(bundle!=null){
            //搜索值
            keyword =  bundle.getString("search_con");
            Toast.makeText(mContext,keyword, Toast.LENGTH_SHORT).show();
        }

        list_animal = (ListView) view.findViewById(R.id.list_animal);
        //get请求
        get();

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int gid= new Long(id).intValue();

        Intent it = new Intent();
        //跳转详情页
        it.setClass(getActivity(), Goods_infoActivity.class);
        //rid 商品id
        it.putExtra("gid", gid);
        startActivity(it);
    }

    /*
    * http get()请求；
    *
    * */

    public void get(){
        //搜索接口+搜索值
        GET_URL = GET_URL + keyword;
        //restHttp
        HttpRequest.getInstance().get(GET_URL, new HttpCallback() {

            //返回http页面信息 info
            @Override
            public void success(String info) {
                //搜索列表listview
                searchList(info);

            }
        });

    }

    public void searchList(String lists) {

        Gson gson = new Gson();

        List<Person> ps = gson.fromJson(lists, new TypeToken<List<Person>>(){}.getType());

        /*
        *String [] [] list = {{"广东商人出资百万助人上位 操控村委决策8年广东商人出资百万助人上位 操控村委决策8年","￥123111.01元","https://gw3.alicdn.com/bao/uploaded/i3/TB1wR8LRpXXXXbvXVXXXXXXXXXX_!!0-item_pic.jpg_210x210.jpg","8"}};
        */

        mData = new LinkedList<Search>();

        //循环遍历
        for(Person o:ps){

            mData.add(new Search(
                    o.goods_name,
                    "￥"+o.goods_price,
                    o.img_url,
                    Integer.parseInt(o.goods_id)));
        }

        mAdapter = new SearchAdapter((LinkedList<Search>) mData, mContext);

        list_animal.setAdapter(mAdapter);

        list_animal.setOnItemClickListener( this);
    }


}