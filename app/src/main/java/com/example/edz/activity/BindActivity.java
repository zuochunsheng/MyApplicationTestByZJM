package com.example.edz.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import com.example.edz.R;
import com.example.edz.service.MyBindService;
import com.example.edz.util.LogUtil;

public class BindActivity extends Activity {

    private MyBindService myService;
    private MyServiceConnection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind);
    }

    public void startBindService(View view){

        Intent intent = new Intent(this,MyBindService.class);

        connection = new MyServiceConnection();
        bindService(intent, connection, Context.BIND_AUTO_CREATE);


    }

    public void stopBindService(View view){
        //Intent intent = new Intent(this,MyBindService.class);
        unbindService(connection);
    }

    public void setMyService(View view){

        myService.excuteABC();
    }


    private class MyServiceConnection implements ServiceConnection {
        //通讯
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LogUtil.e("componentName = " + componentName );
            //获取服务对象
            myService = ((MyBindService.MyBinder) iBinder).getService();

            LogUtil.e("Service连接成功");
            // 执行Service内部自己的方法
            myService.excute();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            LogUtil.e("onServiceDisconnected");
            myService = null;
        }

        @Override
        public void onBindingDied(ComponentName name) {
            LogUtil.e("onBindingDied");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //unbindService(connection);
    }
}
