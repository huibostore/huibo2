package com.example.nwidc.huibo;

/**
 * Created by hello on 2017/6/8.
 */

import android.app.Application;

public class LoginChenge extends Application {

    private static LoginChenge instance = null;
    private String LoginInfo = "null";

    public static LoginChenge getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }



    public String getLoginInfo() {
        return LoginInfo;
    }

    public void setLoginInfo(String LoginInfo) {
        this.LoginInfo = LoginInfo;
    }
}