package com.shaoxiang.contexthook;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Context锚点
 * Created by LK on 2017/2/21.
 */
public class ContextHook {
    private static LinkedHashMap<String, ArrayList<Context>> contextMap;

    private synchronized static LinkedHashMap<String, ArrayList<Context>> getContextMap() {
        if (contextMap == null) {
            contextMap = new LinkedHashMap<String, ArrayList<Context>>();
        }
        return contextMap;
    }

    /**
     * 压入Context
     * @param applicationContext
     */
    public static void pushApplicationContext(Context applicationContext) {
        release(true);
        pushContext(applicationContext);
    }

    /**
     * 压入Context
     * @param context
     */
    public static void pushContext(Context context) {
        pushContext(context.getClass().getName(), context);
    }

    /**
     * 压入Context
     * @param tag
     * @param context
     */
    public static void pushContext(String tag, Context context) {
        LinkedHashMap<String, ArrayList<Context>> contextMap = getContextMap();
        ArrayList<Context> contextArrayList = contextMap.get(tag);
        if (contextArrayList == null) {
            contextArrayList = new ArrayList<Context>();
            contextMap.put(tag, contextArrayList);
        } else {
            for (Context contx : contextArrayList) {
                if (contx == context) {
                    //如果Context已存在，直接返回
                    return;
                }
            }
        }

        contextArrayList.add(context);
    }

    /**
     * 放出Context
     * @return
     */
    public static Context popContext() {
        LinkedHashMap<String, ArrayList<Context>> contextMap = getContextMap();
        Map.Entry<String, ArrayList<Context>> entry = getTailByReflection(contextMap);
        ArrayList<Context> contextArrayList = entry.getValue();
        Context context = contextArrayList.remove(contextArrayList.size() - 1);
        if (contextArrayList.isEmpty()) {
            contextMap.remove(entry.getKey());
        }
        return context;
    }

    /**
     * 放出Context
     * @param context
     * @return
     */
    public static Context popContext(Context context) {
        return popContext(context.getClass().getName(), context);
    }

    /**
     * 放出Context
     * @param tag
     * @param context
     * @return
     */
    public static Context popContext(String tag, Context context) {
        LinkedHashMap<String, ArrayList<Context>> contextMap = getContextMap();

        ArrayList<Context> contextArrayList = contextMap.get(tag);
        for (Context cx : contextArrayList) {
            if (cx == context) {
                contextArrayList.remove(context);
                if (contextArrayList.isEmpty()) {
                    contextMap.remove(tag);
                }
                break;
            }
        }
        return context;
    }

    /**
     * 获取当前屏幕的Context
     * @return
     */
    public static Context getContext() {
        ArrayList<Context> contextArrayList = getTailByReflection(getContextMap()).getValue();
        return contextArrayList.get(contextArrayList.size() - 1);
    }

    /**
     * 释放内存
     * @param finishContext
     */
    public static void release(boolean finishContext) {
        LinkedHashMap<String, ArrayList<Context>> contextMap = getContextMap();
        for (Map.Entry<String, ArrayList<Context>> entry : contextMap.entrySet()) {
            ArrayList<Context> contextArrayList = entry.getValue();
            if (finishContext) {
                for (Context context : contextArrayList) {
                    if (context instanceof Activity) {
                        ((Activity) context).finish();
                    }
                }
            }
            contextArrayList.clear();
        }
        contextMap.clear();
    }

    /**
     * 获取最后一个元素
     * @param map
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private static Map.Entry<String, ArrayList<Context>> getTailByReflection(LinkedHashMap<String, ArrayList<Context>> map) {
        Iterator<Map.Entry<String, ArrayList<Context>>> iterator = map.entrySet().iterator();
        Map.Entry<String, ArrayList<Context>> entry = null;
        while (iterator.hasNext()) {
            entry = iterator.next();
        }
        return entry;
    }
}
