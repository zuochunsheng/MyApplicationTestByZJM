package com.example.edz.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * authr : edz on 2017/11/20  上午10:52
 * describe ：
 */


public class NetUtil {

    public static boolean isNetConnected(Context context) {
        boolean isNetConnected;
        // 获得网络连接服务
        ConnectivityManager connManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connManager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            isNetConnected = true;
        } else {
            Log.e("--->>", "没有可用网络");
            isNetConnected = false;
        }
        return isNetConnected;
    }
}
