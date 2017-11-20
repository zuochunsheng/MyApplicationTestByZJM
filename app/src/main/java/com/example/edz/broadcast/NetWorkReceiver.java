package com.example.edz.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.edz.util.NetUtil;

import java.util.ArrayList;

/**
 * authr : edz on 2017/11/20  上午10:54
 * describe ：
 */


public class NetWorkReceiver extends BroadcastReceiver {
    public static ArrayList<EventHandler> ehList = new ArrayList<EventHandler>();

    public static abstract interface EventHandler {

        public abstract void onNetChange(boolean isNetConnected);

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            boolean isNetConnected = NetUtil.isNetConnected(context);
            for (int i = 0; i < ehList.size(); i++) {
                ((EventHandler) ehList.get(i)).onNetChange(isNetConnected);
            }
        }
    }
}
