package com.shaoxiang.midlayer_net.listener;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.shaoxiang.midlayer_net.model.ErrorMsg;
import com.shaoxiang.midlayer_net.model.ErrorType;
import com.shaoxiang.midlayer_net.model.NetResponse;

import java.lang.reflect.ParameterizedType;

/**
 * Created by LK on 2017/4/25.
 */

public abstract class JSONRequestListener<T> extends SimpleRequestListener {

    @Override
    public void onResponse(NetResponse netResponse) {
        if (netResponse.isSuccess()) {
            try {
                T t = (T) new Gson().fromJson(netResponse.getData(), getTClass());
                onJSONResponse(t);
            } catch (JsonSyntaxException e) {
                onError(0, new ErrorMsg(ErrorType.DATA_ERROR, "解析数据失败"));
            }
        } else {
            onError(netResponse.getCode(), netResponse.getErrorMsg());
        }
    }

    public abstract void onJSONResponse(T t);

    public abstract void onError(int code, ErrorMsg errorMsg);

    private Class getTClass() {
        return  (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
