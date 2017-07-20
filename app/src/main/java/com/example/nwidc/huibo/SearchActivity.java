package com.example.nwidc.huibo;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import android.view.inputmethod.InputMethodManager;

import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nwidc.huibo.fragment.ChartFragment;
import com.example.nwidc.huibo.fragment.CollectFragment;
import com.example.nwidc.huibo.fragment.HomeMenuFragment;
import com.example.nwidc.huibo.fragment.MemberFragment;
import com.example.nwidc.huibo.fragment.Search_historyFragment;
import com.example.nwidc.huibo.fragment.Search_listFragment;
import com.example.nwidc.huibo.fragment.Sign_Fragment;
import com.example.nwidc.huibo.fragment.SortFragment;

/**
 * Created by root on 17-6-24.
 */

public class SearchActivity extends AppCompatActivity {
    private FragmentManager fManager;
    private FrameLayout ly_content;
    private Search_historyFragment history;
    private Search_listFragment list;
    private EditText search_con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        search_con = (EditText)findViewById(R.id.loginTitle);
        fManager = getSupportFragmentManager();


        //设置透明状态栏
        StatusbarUtils.enableTranslucentStatusbar(this);

        //按钮返回
        final TextView GoBack2 = (TextView) findViewById(R.id.btn_back);
        GoBack2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        TextView search = (TextView) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout content = (LinearLayout) findViewById(R.id.content);

            }

        });

        ly_content = (FrameLayout) findViewById(R.id.ly_content);

        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        if (history == null) {
            history = new Search_historyFragment();

            fTransaction.add(R.id.ly_content, history);

        } else {
            fTransaction.show(history);

        }

        TextView searchs = (TextView) findViewById(R.id.search);

        searchs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fTransaction = fManager.beginTransaction();
                hideAllFragment(fTransaction);
                //if (list == null) {
                    list = new Search_listFragment();
                    //Bundle bundle = new Bundle();
                    String strValue = search_con.getText().toString().trim();
                    //bundle.putString("search_con", strValue);
                    //list.setArguments(bundle);
//                    fTransaction.add(R.id.ly_content, list);
//                } else {
//                    Bundle bundle = new Bundle();
//                    String strValue = search_con.getText().toString().trim();
//                    bundle.putString("search_con", strValue);
//                    list.setArguments(bundle);
//                    //如果transaction  commit（）过  那么我们要重新得到transaction
//                    fTransaction.replace(R.id.ly_content, list);
//                    //fTransaction.show(list);
//                }

                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                if (!list.isVisible()) {
                    Bundle bundle = new Bundle();
                    bundle.putString("search_con", strValue);
                    list.setArguments(bundle);
                    fTransaction.add(R.id.ly_content, list, "BillList");
//                    fTransaction.addToBackStack(null);
                    fTransaction.commit();
                    /* 使用接口回调的方法获取数据 */
                    history.getData(new Search_historyFragment.CallBack() {

                        private String shop;
                        @Override
                        public void getResult(int result) {              /*打印信息*/

                            if(result == 1){
                                shop = "店铺";
                            }else if(result == 0){
                                shop = "商品";
                            }

                            Toast.makeText(SearchActivity.this, shop, Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                //fTransaction.commit();
            }
        });


        search_con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fTransaction = fManager.beginTransaction();
                hideAllFragment(fTransaction);
                if (history == null) {
                    history = new Search_historyFragment();

                    fTransaction.add(R.id.ly_content, history);



                } else {
                    fTransaction.show(history);

                }
                fTransaction.commit();
            }
        });
        fTransaction.commit();
    }

    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(history != null)fragmentTransaction.hide(history);
        if(list != null)fragmentTransaction.hide(list);
    }



}
