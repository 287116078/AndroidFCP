package com.shaoxiang.contexthook;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by LK on 2017/3/16.
 */

public class HookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContextHook.pushContext(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ContextHook.popContext(this);
    }
}
