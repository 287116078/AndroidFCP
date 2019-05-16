package com.shaoxiang.midlayer_net.listener;

/**
 * 带上传进度的请求Listener
 * Created by LK on 2017/3/16.
 */

public abstract class WriteProgressReqeustListener extends ProgressRequestListener {

    @Override
    public void readProgress(float percent, long totalSize) {

    }
}
