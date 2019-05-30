package com.example.edz.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.edz.util.LogUtil;

public class MyBindService extends Service {

    private IBinder myBinder ;

    @Override
    public void onCreate() {
        super.onCreate();
        myBinder = new MyBinder();

    }

    //通讯
    @Override
    public IBinder onBind(Intent intent) {

        return myBinder ;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        // 当调用者退出(即使没有调用unbindService)或者主动停止服务时会调用
        LogUtil.e("调用者退出了");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        // 当调用者退出(即使没有调用unbindService)或者主动停止服务时会调用
        super.onDestroy();
    }



    public class MyBinder extends Binder {

        public MyBindService getService(){

            return MyBindService.this;
        }

    }

    public void excute() {
        LogUtil.e("通过Binder得到Service的引用来调用Service内部的方法");
    }

    public void excuteABC() {
        LogUtil.e("通过Binder得到Service的引用来调用Service内部的方法ABC");
    }

}
