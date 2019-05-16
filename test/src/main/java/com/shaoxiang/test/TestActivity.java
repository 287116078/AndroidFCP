package com.shaoxiang.test;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.shaoxiang.controller.views.activity.SXActivity;

/**
 * Created by Luo Kun on 2018/3/19.
 */
@Route(path = "/TestPage/Main")
public class TestActivity extends SXActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }
}
