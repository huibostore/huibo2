
/**
 *
 *----------Dragon be here!----------/
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃神兽保佑
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━神兽出没━━━━━━
 */

package com.example.nwidc.huibo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nwidc.huibo.Adapter.SortAdapter;
import com.example.nwidc.huibo.View.Person;
import com.example.nwidc.huibo.fragment.ContentFragment;
import com.example.nwidc.huibo.fragment.Sort_ConFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TestActivity extends FragmentActivity implements AdapterView.OnItemClickListener {
    /*ListView填充用*/
    private String[] strs = { "常用分类", "服饰内衣","宠物", "手机", "家用电器", "数码", "电脑办公",
            "个护化妆", "图书","鞋靴" };
    private ListView listView;
    private SortAdapter adapter;
    private Sort_ConFragment myFragment;
    /*选中的item的位数号码*/
    public static int mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        /*初始化*/
        initView();
    }

    /**
     * 初始化view
     */
    private void initView() {
        //匹配控件
        listView = (ListView) findViewById(R.id.listview);
        //传参
        adapter = new SortAdapter(this, strs);
        listView.setAdapter(adapter);
        //监听事件
        listView.setOnItemClickListener(this);

        //创建MyFragment对象
        myFragment = new Sort_ConFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, myFragment);
        //通过bundle传值给MyFragment
        Bundle bundle = new Bundle();
        bundle.putString(Sort_ConFragment.TAG, strs[mPosition]);
        myFragment.setArguments(bundle);
        fragmentTransaction.commit();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //拿到当前位置
        mPosition = position;
        //即使刷新adapter
        adapter.notifyDataSetChanged();
        for (int i = 0; i < strs.length; i++) {
            myFragment = new Sort_ConFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                    .beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, myFragment);
            Bundle bundle = new Bundle();
            bundle.putString(Sort_ConFragment.TAG, strs[position]);
            myFragment.setArguments(bundle);
            fragmentTransaction.commit();
        }
    }
}


