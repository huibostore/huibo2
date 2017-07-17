package com.example.nwidc.huibo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Edit_orderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_order);
        //  跳转支付订单
        final TextView Pay = (TextView) findViewById(R.id.txt_kuaidiss);
        Pay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Edit_orderActivity.this,PayActivity.class);
                startActivity(intent);
            }
        });
        //按钮返回
        final TextView GoBack2 = (TextView) findViewById(R.id.textView);
        GoBack2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}
