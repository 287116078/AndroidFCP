package com.shaoxiang.midlayer_net;

import android.os.Handler;
import android.os.Looper;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.request.BaseRequest;
import com.lzy.okgo.request.PostRequest;
import com.shaoxiang.midlayer_net.listener.RequestListener;
import com.shaoxiang.midlayer_net.model.ErrorMsg;
import com.shaoxiang.midlayer_net.model.ErrorType;
import com.shaoxiang.midlayer_net.model.FileParams;
import com.shaoxiang.midlayer_net.model.Method;
import com.shaoxiang.midlayer_net.model.NetResponse;

import java.io.File;
import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by LK on 2017/3/15.
 */

public class NetRequest {
    private static Handler handler;

    private static Handler getHandle() {
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper());
        }
        return handler;
    }

    private NetRequestFactory.NetRequestCfg netRequestCfg;
    private BaseRequest baseRequest;
    private com.lzy.okgo.adapter.Call call;

    private NetRequest(NetRequestFactory.NetRequestCfg netRequestCfg) {
        this.netRequestCfg = netRequestCfg;
    }

    public static class NetRequestFactory {

        public static class NetRequestCfg {
            private Method method;
            private String url;
            private LinkedHashMap<String, String> headerParams = new LinkedHashMap<String, String>();
            private LinkedHashMap<String, String> normalContentParams = new LinkedHashMap<String, String>();
            private ArrayList<FileParams> fileList = new ArrayList<FileParams>();
            private String content;
            private Object tag;
            private int connectTimeout;
            private int writeTimeout;
            private int readTimeout;

            public NetRequestCfg(Method method) {
                this.method = method;
            }

            /**
             * 配置URL
             *
             * @param url
             * @return
             */
            public NetRequestCfg url(String url) {
                this.url = url;
                return this;
            }

            /**
             * 配置header
             *
             * @param headerMap
             * @param nullValue
             * @return
             */
            public NetRequestCfg headers(HashMap<String, Object> headerMap, String nullValue) {
                if (headerMap != null) {
                    for (Map.Entry<String, Object> entry : headerMap.entrySet()) {
                        headerParams.put(entry.getKey(), object2String(entry.getValue(), nullValue));
                    }
                }
                return this;
            }

            /**
             * 配置header
             *
             * @param key
             * @param value
             * @return
             */
            public NetRequestCfg header(String key, String value) {
                headerParams.put(key, value);
                return this;
            }

            /**
             * 配置请求内容 <br>
             * 使用这个方法配置请求内容后，所有通过params方法设置的请求参数将不会放入到请求中
             *
             * @param content
             * @return
             */
            @Deprecated
            public NetRequestCfg content(String content) {
//                this.content = content == null ? "" : content;
                return this;
            }

            /**
             * 配置请求参数
             *
             * @param params
             * @param nullValue
             * @return
             */
            public NetRequestCfg params(HashMap<String, Object> params, String nullValue) {
                if (params != null) {
                    for (Map.Entry<String, Object> entry : params.entrySet()) {
                        normalContentParams.put(entry.getKey(), object2String(entry.getValue(), nullValue));
                    }
                }

                return this;
            }

            /**
             * 配置请求参数
             *
             * @param params
             * @return
             */
            public NetRequestCfg params(HashMap<String, Object> params) {
                if (params != null) {
                    for (Map.Entry<String, Object> entry : params.entrySet()) {
                        normalContentParams.put(entry.getKey(), object2String(entry.getValue(), ""));
                    }
                }

                return this;
            }

            /**
             * 配置请求参数
             *
             * @param key
             * @param value
             * @return
             */
            public NetRequestCfg param(String key, String value) {
                normalContentParams.put(key, value);
                return this;
            }

            /**
             * 配置请求参数
             *
             * @param key
             * @param value
             * @return
             */
            public NetRequestCfg param(String key, File value) {
                fileList.add(new FileParams(key, value.getName(), value));
                return this;
            }

            /**
             * 设置标记
             *
             * @param tag
             * @return
             */
            public NetRequestCfg tag(Object tag) {
                this.tag = tag;
                return this;
            }

            /**
             * 设置连接超时时间
             *
             * @param connectTimeout
             * @return
             */
            public NetRequestCfg connectTimeout(int connectTimeout) {
                this.connectTimeout = connectTimeout;
                return this;
            }

            /**
             * 设置上传超时时间
             *
             * @param writeTimeout
             * @return
             */
            public NetRequestCfg writeTimeout(int writeTimeout) {
                this.writeTimeout = writeTimeout;
                return this;
            }

            /**
             * 设置下载超时时间
             *
             * @param readTimeout
             * @return
             */
            public NetRequestCfg readTimeout(int readTimeout) {
                this.readTimeout = readTimeout;
                return this;
            }

            /**
             * build
             *
             * @return
             */
            public NetRequest bulid() {
                return new NetRequest(this);
            }

            private String object2String(Object object, String nullValue) {
                if (object != null) {
                    return String.valueOf(object);
                } else {
                    return nullValue;
                }
            }

        }

        public static NetRequestCfg create(Method method) {
            return new NetRequestCfg(method);
        }
    }

    private BaseRequest buildRequestCall() {
        baseRequest = null;
        switch (netRequestCfg.method) {
            case GET:
                baseRequest = OkGo.get(netRequestCfg.url).tag(netRequestCfg.tag).params(netRequestCfg.normalContentParams);
                break;
            case POST:
                baseRequest = OkGo.post(netRequestCfg.url).tag(netRequestCfg.tag).params(netRequestCfg.normalContentParams);
                PostRequest postRequest = (PostRequest) baseRequest;
                for (FileParams fileParams : netRequestCfg.fileList) {
                    postRequest.params(fileParams.getKey(), fileParams.getFile());
                }
                break;
            default:
                return null;
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.putAll(netRequestCfg.headerParams);
        baseRequest.headers(httpHeaders);
        if (netRequestCfg.connectTimeout > 0) {
            baseRequest.connTimeOut(netRequestCfg.connectTimeout);
        }
        if (netRequestCfg.writeTimeout > 0) {
            baseRequest.writeTimeOut(netRequestCfg.writeTimeout);
        }
        if (netRequestCfg.readTimeout > 0) {
            baseRequest.readTimeOut(netRequestCfg.readTimeout);
        }
        return baseRequest;
    }

    /**
     * 执行同步请求
     *
     * @return
     */
    @Deprecated
    public NetResponse execute() {
        NetResponse netResponse = new NetResponse();
        baseRequest = buildRequestCall();
        try {
            Response response = baseRequest.execute();
            netResponse.setSuccess(true);
            netResponse.setCode(response.code());
            netResponse.setData(response.body().toString());
        } catch (IOException e) {
            e.printStackTrace();
            netResponse.setSuccess(false);
            netResponse.setCode(-1);
            netResponse.setErrorMsg(new ErrorMsg(ErrorType.NET_ERROR, "网络连接错误"));
        }
        return netResponse;
    }

    /**
     * 执行异步请求
     *
     * @param requestListener
     */
    public NetRequest execute(final RequestListener requestListener) {
        baseRequest = buildRequestCall();
        call = baseRequest.execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                NetResponse netResponse = new NetResponse();
                netResponse.setSuccess(true);
                netResponse.setData(s);
                requestListener.onResponse(netResponse);
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                e.printStackTrace();
                NetResponse netResponse = new NetResponse();
                netResponse.setSuccess(false);
                netResponse.setErrorMsg(new ErrorMsg(ErrorType.NET_ERROR, "网络访问失败"));
                requestListener.onResponse(netResponse);
            }

            @Override
            public void parseError(Call call, Exception e) {
                e.printStackTrace();
                if (e instanceof SocketException) {
                    if (e.getMessage().equals("Socket closed")) {
                        //socket已关闭
                        return;
                    }
                } else if (e instanceof IOException) {
                    if (e.getMessage().equals("Canceled")) {
                        //访问已被主动终止，不用提示错误
                        return;
                    }
                }
                final NetResponse netResponse = new NetResponse();
                netResponse.setSuccess(false);
                new ErrorMsg(ErrorType.SERVER_ERROR, "解析数据失败");
                netResponse.setErrorMsg(new ErrorMsg(ErrorType.SERVER_ERROR, "解析数据失败"));
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    requestListener.onResponse(netResponse);
                } else {
                    getHandle().post(new Runnable() {
                        @Override
                        public void run() {
                            requestListener.onResponse(netResponse);
                        }
                    });
                }

            }

            @Override
            public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                //这里回调下载进度(该回调在主线程,可以直接更新ui)
                requestListener.readProgress(progress, totalSize);
            }

            @Override
            public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                //这里回调上传进度(该回调在主线程,可以直接更新ui)
                requestListener.writeProgress(progress, totalSize);
            }
        });

        return this;
    }

    /**
     * 终止请求
     */
    public void cancel() {
        if (call != null) {
            call.cancel();
        }
    }
}
