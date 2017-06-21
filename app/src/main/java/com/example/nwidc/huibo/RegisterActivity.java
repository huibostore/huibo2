package com.example.nwidc.huibo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by hello on 2017/6/20.
 */

public class RegisterActivity extends Activity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //按钮返回
        final TextView GoBack2 = (TextView) findViewById(R.id.textView);
        GoBack2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}
