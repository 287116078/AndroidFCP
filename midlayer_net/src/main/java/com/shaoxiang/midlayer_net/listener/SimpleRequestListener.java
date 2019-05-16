package com.shaoxiang.midlayer_net.listener;

/**
 * 一般请求Listener
 * Created by LK on 2017/3/15.
 */

public abstract class SimpleRequestListener extends RequestListener {
    @Override
    public void writeProgress(float percent, long totalSize) {

    }

    @Override
    public void readProgress(float percent, long totalSize) {

    }
}
