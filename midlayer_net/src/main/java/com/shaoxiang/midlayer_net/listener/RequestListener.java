package com.shaoxiang.midlayer_net.listener;



import com.shaoxiang.midlayer_net.model.NetResponse;

import java.util.HashMap;

/**
 * Created by LK on 2017/3/15.
 */

public abstract class RequestListener {
    private Object extra;
    private HashMap<Integer, Object> extras;

    public abstract void writeProgress(float percent, long totalSize);
    public abstract void readProgress(float percent, long totalSize);
    public abstract void onResponse(NetResponse netResponse);

    public void putExtra(int id, Object extra) {
        if (extra != null) {
            if (extras == null) {
                extras = new HashMap<Integer, Object>();
            }
            extras.put(id, extra);
        }
    }

    public void putExtra(Object extra) {
        this.extra = extra;
    }

    public Object getExtra() {
        return extra;
    }

    public Object getExtra(int id) {
        if (extras == null) {
            return null;
        }

        return extras.get(id);
    }
}
