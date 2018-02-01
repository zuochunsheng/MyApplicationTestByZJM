package com.example.edz.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.example.edz.activity.IntentServiceActivity;
import com.example.edz.util.LogUtil;

/**
 *  IntentService，可以看做是Service和HandlerThread的结合体，
 * 在完成了使命之后会自动停止，适合需要在工作线程处理UI无关任务的场景。
 */
public class MyIntentService extends IntentService {
    /**
     * 是否正在运行
     */
    private boolean isRunning;

    /**
     *进度
     */
    private int count;

    /**
     * 广播
     */
    private LocalBroadcastManager mLocalBroadcastManager;

    public MyIntentService() {
        super("MyIntentService");
        LogUtil.e("MyIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.e("onCreate");
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        LogUtil.e("onHandleIntent");
        try {
            Thread.sleep(1000);
            isRunning = true;
            count = 0;
            while (isRunning) {
                count++;
                if (count >= 100) {
                    isRunning = false;
                }
                Thread.sleep(50);
                sendThreadStatus("线程运行中...", count);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送进度消息
     */
    private void sendThreadStatus(String status, int progress) {
        Intent intent = new Intent(IntentServiceActivity.ACTION_TYPE_THREAD);
        intent.putExtra("status", status);
        intent.putExtra("progress", progress);
        mLocalBroadcastManager.sendBroadcast(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.e("线程结束运行..." + count);
    }
}
