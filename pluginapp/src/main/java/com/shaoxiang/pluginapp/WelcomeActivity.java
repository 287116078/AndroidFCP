package com.shaoxiang.pluginapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Luo Kun on 2018/3/27.
 */

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, getIntent().getStringExtra("a"), Toast.LENGTH_SHORT).show();
    }
}
