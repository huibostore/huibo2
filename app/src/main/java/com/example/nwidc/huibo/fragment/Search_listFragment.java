package com.example.nwidc.huibo.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nwidc.huibo.Adapter.SearchAdapter;
import com.example.nwidc.huibo.Adapter.UserAdapter;
import com.example.nwidc.huibo.Goods_infoActivity;
import com.example.nwidc.huibo.Person;
import com.example.nwidc.huibo.R;
import com.example.nwidc.huibo.Util.Config;
import com.example.nwidc.huibo.View.Search;
import com.example.nwidc.huibo.View.UserBean;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
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
    private ImageView imageView;
    private TextView result;
    private  String GET_URL = Config.Search;
    private String keyword ;
    private String infos;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        view = inflater.inflate(R.layout.fragment_searchresult,null);

        mContext = getActivity();
        Context = getContext();

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




        list_animal = (ListView) view.findViewById(R.id.list_animal);
        result = (TextView)view.findViewById(R.id.result);
        get();
//        result.setText(infos+GET_URL);





        //图片资源
//        String url = "http://m.weather.com.cn/img/b0.gif";
//        imageView = (ImageView)view.findViewById(R.id.img_icon);
//        Picasso.with(getContext())
//                .load(url)
//                .into(imageView);


        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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
                infos = info;
                //result.setText(infos+GET_URL);
                //searchList(new Gson().toJson(info));
//                new Gson().toJson(info);
                searchList(info);
            }
        });

    }

    public void searchList(String lists) {
        //result.setText(lists+GET_URL);

//        Person person = new Person();

        //Gson gson = new Gson();
        try {
            Gson gson = new Gson();
            ArrayList persons = new ArrayList();
//            JSONArray jsonArray = new JSONArray(lists);
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
//                Person p = new Person();
//                p.setGoods_id(jsonObject.getString("id"));
//                p.setGoods_name(jsonObject.getString("goods_name"));
//                p.setGoods_price(jsonObject.getString("goods_price"));
//                persons.add(p);
//                Person p = new Person();
//                p.setGoods_id("id");
//                p.setGoods_name("goods_name");
//                p.setGoods_price("goods_price");
//                persons.add(p);
//            }
            //String str = gson.toJson(lists);
//            result.setText(str);
//            Type founderListType = new TypeToken<ArrayList>(){}.getType();
//            persons = gson.fromJson(lists, founderListType);
//
//
//            for(int i = 0; i < persons.size() ; i++)
//            {
//                Object p = persons;
//                System.out.println(p.toString());
//                result.setText(jsonArray.toString());
//            }


            //拿到本地JSON 并转成String
            String strByJson = lists;

            //先转JsonObject
            JsonObject jsonObject = new JsonParser().parse(strByJson).getAsJsonObject();
            //再转JsonArray 加上数据头
            JsonArray jsonArray = jsonObject.getAsJsonArray("muser");


            ArrayList<UserBean> userBeanList = new ArrayList<>();

            //循环遍历
            for (JsonElement user : jsonArray) {
                //通过反射 得到UserBean.class
                UserBean userBean = gson.fromJson(user, new TypeToken<UserBean>() {}.getType());
                userBeanList.add(userBean);
            }
            list_animal.setAdapter(new UserAdapter(getActivity(), userBeanList));




//            String [] [] list = {{"广东商人出资百万助人上位 操控村委决策8年广东商人出资百万助人上位 操控村委决策8年","￥123.01元","https://gw3.alicdn.com/bao/uploaded/i1/581894172/TB2TSydq4RDOuFjSZFzXXcIipXa_!!581894172.jpg_210x210.jpg","5"},
//                    {"广东商人出资百万助人上位 操控村委决策8年广东商人出资百万助人上位 操控村委决策8年","￥123.11元","https://img.alicdn.com/imgextra/i4/1835106055803824256/TB2Wxc3vCFmpuFjSZFrXXayOXXa_!!0-saturn_solar.jpg_210x210.jpg","6"},
//                    {"广东商人出资百万助人上位 操控村委决策8年广东商人出资百万助人上位 操控村委决策8年","￥123.11元","https://img.alicdn.com/imgextra/i4/1835106055803824256/TB2Wxc3vCFmpuFjSZFrXXayOXXa_!!0-saturn_solar.jpg_210x210.jpg","6"},
//                    {"广东商人出资百万助人上位 操控村委决策8年广东商人出资百万助人上位 操控村委决策8年","￥123.11元","https://img.alicdn.com/imgextra/i4/1835106055803824256/TB2Wxc3vCFmpuFjSZFrXXayOXXa_!!0-saturn_solar.jpg_210x210.jpg","6"},
//                    {"广东商人出资百万助人上位 操控村委决策8年广东商人出资百万助人上位 操控村委决策8年","￥123.11元","https://img.alicdn.com/imgextra/i4/1835106055803824256/TB2Wxc3vCFmpuFjSZFrXXayOXXa_!!0-saturn_solar.jpg_210x210.jpg","6"},
//                    {"广东商人出资百万助人上位 操控村委决策8年广东商人出资百万助人上位 操控村委决策8年","￥123.11元","https://img.alicdn.com/imgextra/i4/1835106055803824256/TB2Wxc3vCFmpuFjSZFrXXayOXXa_!!0-saturn_solar.jpg_210x210.jpg","6"},
//                    {"广东商人出资百万助人上位 操控村委决策8年广东商人出资百万助人上位 操控村委决策8年","￥123.11元","https://img.alicdn.com/imgextra/i4/1835106055803824256/TB2Wxc3vCFmpuFjSZFrXXayOXXa_!!0-saturn_solar.jpg_210x210.jpg","7"},
//                    {"广东商人出资百万助人上位 操控村委决策8年广东商人出资百万助人上位 操控村委决策8年","￥123111.01元","https://gw3.alicdn.com/bao/uploaded/i3/TB1wR8LRpXXXXbvXVXXXXXXXXXX_!!0-item_pic.jpg_210x210.jpg","8"}};
//
//
//
//            mData = new LinkedList<Search>();
//
//            for(int i=0;i<list.length;i++){
//                mData.add(new Search(list[i][0] , list[i][1] , list[i][2], Integer.parseInt(list[i][3])));
//
//            }
//
//            mAdapter = new SearchAdapter((LinkedList<Search>) mData, mContext);
//            list_animal.setAdapter(mAdapter);
//            list_animal.setOnItemClickListener( this);
        }catch (Exception e){e.printStackTrace();}
    }



}
