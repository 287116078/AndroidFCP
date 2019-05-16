package com.shaoxiang.midlayer_net.listener;

/**
 * 带下载进度的请求Listener
 * Created by LK on 2017/3/16.
 */

public abstract class ReadProgressReqeustListener extends ProgressRequestListener {
    @Override
    public void writeProgress(float percent, long totalSize) {

    }
}
