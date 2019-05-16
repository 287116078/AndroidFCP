package com.shaoxiang.pluginapp;

import android.app.Application;
import android.widget.Toast;

/**
 * Created by Luo Kun on 2018/3/27.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Toast.makeText(this, "app created", Toast.LENGTH_SHORT).show();
    }
}
