package com.example.nwidc.huibo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.example.nwidc.huibo.Util.PostUtils;

import java.util.Map;

/**
 * Created by hello on 2017/5/15.
 */

public class LaunchActivity extends Activity {

    private Context mContext;
    private String Login;
    private SharedHelper sh;
    private String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载启动图片
        setContentView(R.layout.activity_launch);
        mContext = getApplicationContext();
        sh = new SharedHelper(mContext);
        //后台处理耗时任务
        new Handler().postDelayed(new Runnable() {
            public void run() {
                /* Create an Intent that will start the Main WordPress Activity. */
                Intent mainIntent = new Intent(LaunchActivity.this, MainActivity.class);
                LaunchActivity.this.startActivity(mainIntent);
                LaunchActivity.this.finish();
            }
        }, 2000);
        autoLogin();
    }

    //自动登陆
    protected void autoLogin() {
        Map<String,String> data = sh.read();
        String id = data.get("passwd");
        if(id != "" && id.length() > 10){
            new Thread() {
                public void run() {
                    Map<String,String> data = sh.read();
                    String id = data.get("passwd");

                    result = PostUtils.LoginSessionId(id);

                    handler.sendEmptyMessage(0x123);
                    String Login = "Login";
                    LoginChenge.getInstance().setLoginInfo(Login);
                    System.out.println(Login);

                }
            }.start();
        }else{
            result = "登陆失败，请稍后再试";

            handler.sendEmptyMessage(0x123);
        }
    }
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            Toast.makeText(LaunchActivity.this, result, Toast.LENGTH_SHORT).show();
        };
    };

}
