package com.shaoxiang.app;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

//                ARouter.getInstance().build("/TestPage/Main").navigation();

                try {
                    if (isAppInstalled(MainActivity.this, "com.shaoxiang.PluginApp")) {

                        Uri uri = Uri.parse("app://test");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        intent.putExtra("a", "asdaasdadasd");
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "未安装程序", Toast.LENGTH_SHORT).show();
                    }
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(MainActivity.this, "插件启动失败", Toast.LENGTH_SHORT).show();
                }

//                try{
//                    Intent intent = getPackageManager().getLaunchIntentForPackage("com.shaoxiang.PluginApp");
//                    intent.putExtra("a", "asdaasdadasd");
//                    startActivity(intent);
//                }catch(Exception e){
//                    Toast.makeText(MainActivity.this, "没有安装", Toast.LENGTH_LONG).show();
//                }
            }
        });
    }

    public static boolean isAppInstalled(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();
        //取得所有的PackageInfo
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        List<String> pName = new ArrayList<>();
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                pName.add(pn);
            }
        }
        //判断包名是否在系统包名列表中
        return pName.contains(packageName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
