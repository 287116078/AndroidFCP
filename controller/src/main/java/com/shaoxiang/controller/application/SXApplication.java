package com.shaoxiang.controller.application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.shaoxiang.contexthook.HookApplication;
import com.shaoxiang.controller.BuildConfig;
import com.shaoxiang.midlayer_net.NetRequestUtils;

import me.yokeyword.fragmentation.Fragmentation;

/**
 * Created by Luo Kun on 2018/3/16.
 */

public class SXApplication extends HookApplication {@Override
public void onCreate() {
    super.onCreate();

    NetRequestUtils.init(this);

    Fragmentation.builder()
            // 显示悬浮球 ; 其他Mode:SHAKE: 摇一摇唤出   NONE：隐藏
            .stackViewMode(Fragmentation.NONE)
            .debug(BuildConfig.DEBUG)
            .install();

    if (BuildConfig.DEBUG) {
        ARouter.openDebug();
    }
    ARouter.init(this);
}
}
