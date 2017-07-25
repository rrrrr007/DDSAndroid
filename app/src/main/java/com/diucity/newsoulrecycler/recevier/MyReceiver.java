package com.diucity.newsoulrecycler.recevier;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.diucity.newsoulrecycler.MyApplication;
import com.diucity.newsoulrecycler.biz.activity.MainActivity;


/**
 * Created by Administrator on 2017/1/16 0016.
 */

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            Log.e("ch", "自启动");
            Intent newIntent = new Intent(context, MainActivity.class);
            newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(newIntent);
        }
        if (intent.getAction().equals("com.zkc.keycode")) {
            int i = intent.getIntExtra("keyvalue", 0);
            startActivity(context, i);
        }
    }


    //F1=131  F2=132 Scan=135
    private void startActivity(Context context, int i) {
        if (i != 131 && i != 132 && i != 135) return;
        int size = MyApplication.getActivityList().size();
        if (size==0){
            Intent newIntent = new Intent(context, MainActivity.class);
            newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            newIntent.putExtra("keycode", i);
            Log.e("ch", "Code"+i);
            context.startActivity(newIntent);
        }
    }
}
