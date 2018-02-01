package com.example.edz.service;

import android.app.Service;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;

import com.example.edz.util.LogUtil;

import java.util.Date;

public class MyService extends Service {

    private MyThread myThread;

    @Override
    public void onCreate() {
        super.onCreate();
        myThread = new MyThread();
    }

    @Override
    public IBinder onBind(Intent intent) {

        return null ;
    }

    /**
     *
     * @param intent
     * @param flags  官方解释：有关此开始请求的附加数据。目前是0
     * @param startId 官方解释：一个唯一的整数，代表这个特定的请求开始
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        myThread.start();
        return super.onStartCommand(intent, flags, startId);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        myThread.setStop();
    }

    public class  MyThread extends Thread{


        private boolean isStop = false;

        public void setStop(){
            isStop = true;
        }
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void run() {
            super.run();

            while (!isStop){

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

                LogUtil.e("caoxzuo" + df.format(new Date()));

                //耗时操作
                try {
                    Thread.sleep(1000);
                }catch (Exception e){

                }

            }


        }
    }
}
