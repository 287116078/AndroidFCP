package com.shaoxiang.app;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.shaoxiang.controller.application.SXApplication;

/**
 * Created by Luo Kun on 2018/3/19.
 */

public class App extends SXApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化ARoute
        ARouter.init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // 将MultiDex注入到项目中
        MultiDex.install(this);
    }
}
