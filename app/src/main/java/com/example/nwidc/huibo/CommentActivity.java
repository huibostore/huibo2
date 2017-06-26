package com.example.nwidc.huibo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by root on 17-6-25.
 */

public class CommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        //按钮返回
        final TextView GoBack2 = (TextView) findViewById(R.id.btn_back);
        GoBack2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        TextView title = (TextView) findViewById(R.id.btn_title);
        title.setText("商品评论");

    }
}
