package com.example.nwidc.huibo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        //  跳转填写订单
//        final Button home2 = (Button) findViewById(R.id.sub_order);
//        home2.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(PayActivity.this,Edit_orderActivity.class);
//                startActivity(intent);
//            }
//        });
        //按钮返回
        final TextView GoBack3 = (TextView) findViewById(R.id.btn_back);
        GoBack3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}
