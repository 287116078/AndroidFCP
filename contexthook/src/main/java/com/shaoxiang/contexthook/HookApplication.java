package com.shaoxiang.contexthook;

import android.app.Application;

/**
 *
 * Created by LK on 2017/3/16.
 */

public class HookApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ContextHook.pushApplicationContext(this);
    }
}
