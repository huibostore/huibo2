package com.example.nwidc.huibo;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nwidc.huibo.Util.PostUtils;

import java.util.Map;



public class LoginActivity extends AppCompatActivity {
    private String result = "登陆失败";
    private String strname;
    private String strpasswd;
    private SharedHelper sh;
    private Context mContext;
    private TextView password;
    private TextView user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mContext = getApplicationContext();
        sh = new SharedHelper(mContext);
        password = (TextView) findViewById(R.id.login_password);
        user = (TextView) findViewById(R.id.login_user);

        TextView tvAppVariable = (TextView) findViewById(R.id.loginTitle);
        // 自定义Application，保存全局变量

        //LoginChenge.getInstance().setLoginInfo(Login);
        String LoginInfos = LoginChenge.getInstance().getLoginInfo();

        tvAppVariable.setText(LoginInfos);

        TextView back = (TextView)findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });





        bindViews();

    }

    private void bindViews() {

        Button login = (Button)findViewById(R.id.login_btn);
        login.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v){



//                                        alert.dismiss();


                if("".equals(user.getText().toString().trim()) || "".equals(password.getText().toString().trim())){
                    String str = "用户名或密码为空";
                    Toast toast = Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT);
                    toast.show();

                } else {
//                    alert();
                    new Thread() {
                        public void run() {

                            TextView password = (TextView) findViewById(R.id.login_password);
                            TextView user = (TextView) findViewById(R.id.login_user);
                            strname = user.getText().toString().trim();
                            strpasswd = password.getText().toString().trim();

                            strpasswd = LoginInspect.LoginByPost(strname, strpasswd);

                            if("false".equals(strpasswd)){
                                result = "用户名或密码错误";
                                handler.sendEmptyMessage(0x123);


                            }else{

                                String Login = "login";
                                LoginChenge.getInstance().setLoginInfo(Login);
                                result = "登陆成功";
                                handler.sendEmptyMessage(0x123);
                                sh.save(strname,strpasswd);
                                chenge();
                            }

                        }
                    }.start();

                }

            }

        });

    }

    //新用户注册
    public void onClicknew(View v){

        Intent intent = new Intent();
        intent.setClass(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Map<String,String> data = sh.read();
        user.setText(data.get("username"));
        password.setText(data.get("passwd"));
    }

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            ProgressDialog pd = new ProgressDialog(LoginActivity.this);
            pd =  pd.show(LoginActivity.this, "登陆", "登陆中,请稍后...",false,true);
            if("用户名或密码错误".equals(result) || "登陆成功".equals(result)){

                pd.dismiss();
            }

            Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT).show();
        }
    };

    protected void chenge(){
        finish();
    }






}
