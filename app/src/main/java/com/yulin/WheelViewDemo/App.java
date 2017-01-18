package com.yulin.WheelViewDemo;

import android.app.Application;

/**
 * Created by YuLin on 2017/1/18 0018.
 */
public class App extends Application {
    private static App mContext = null;
    public static App getInstance() {
        return mContext;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        if (mContext == null) {
            synchronized (App.class) {
                if (mContext == null) {
                    mContext = this;
                }
            }
        }
    }
}
