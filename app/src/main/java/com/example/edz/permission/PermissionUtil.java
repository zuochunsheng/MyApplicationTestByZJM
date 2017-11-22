package com.example.edz.permission;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import com.example.edz.base.ShadowActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * authr : edz on 2017/11/22  下午4:12
 * describe ：/???
 */


public class PermissionUtil  {


    static PermissionUtil sSingleton;
    private Context mCtx;

    public static PermissionUtil getInstance(Context ctx) {
        if (sSingleton == null) {
            sSingleton = new PermissionUtil(ctx.getApplicationContext());
        }
        return sSingleton;
    }

    PermissionUtil(Context ctx) {
        mCtx = ctx;
    }




    public final static int REQUEST_CODE = 1;
    private static IPermission mListener;

    @TargetApi(Build.VERSION_CODES.M)
    public  void requestRunTimePermission(String[] permissions, IPermission listener) {
        Activity topActivity = ActivityCollector.getTopActivity();
        if (topActivity == null) {
            return;
        }
        mListener = listener;
        List<String> permissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(topActivity, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }
        if (!permissionList.isEmpty()) {
            //ActivityCompat.requestPermissions(topActivity, permissionList.toArray(new String[permissionList.size()]), REQUEST_CODE);
            startShadowActivity(permissionList.toArray(new String[permissionList.size()]));

        } else {
            //doSomething
            mListener.onGranted();
        }
    }

   private void startShadowActivity(String[] permissions) {

        Intent intent = new Intent(mCtx, ShadowActivity.class);
        intent.putExtra("permissions", permissions);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mCtx.startActivity(intent);
    }



    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0) {
                    List<String> deniedPermissions = new ArrayList<>();
                    for (int i = 0; i < grantResults.length; i++) {
                        int grantResult = grantResults[i];
                        String permission = permissions[i];
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            deniedPermissions.add(permission);
                        }
                    }
                    if (deniedPermissions.isEmpty()) {
                        mListener.onGranted();
                    } else {
                        mListener.onDenied(deniedPermissions);
                    }
                }
                break;
            default:
                break;
        }
    }


}
