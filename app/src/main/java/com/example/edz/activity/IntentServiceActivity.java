package com.example.edz.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.edz.R;
import com.example.edz.service.MyIntentService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IntentServiceActivity extends Activity {


    /**
     * 状态文字
     */
    @BindView(R.id.tv_status)
    TextView tv_status;

    /**
     * 进度文字
     */
    @BindView(R.id.tv_progress)
    TextView tv_progress;

    /**
     * 进度条
     */
    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    private LocalBroadcastManager mLocalBroadcastManager;
    private MyBroadcastReceiver mBroadcastReceiver;
    public final static String ACTION_TYPE_THREAD = "action.type.thread";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);
        ButterKnife.bind(this);

        //注册广播
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        mBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_TYPE_THREAD);
        mLocalBroadcastManager.registerReceiver(mBroadcastReceiver, intentFilter);

        initView();
    }

    public void initView() {
        tv_status.setText("线程状态：未运行");
        progressbar.setMax(100);
        progressbar.setProgress(0);
        tv_progress.setText("0%");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销广播
        mLocalBroadcastManager.unregisterReceiver(mBroadcastReceiver);
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {

                case ACTION_TYPE_THREAD:
                    //更改UI
                    int progress = intent.getIntExtra("progress", 0);
                    tv_status.setText("线程状态：" + intent.getStringExtra("status"));
                    progressbar.setProgress(progress);
                    tv_progress.setText(progress + "%");
                    if (progress >= 100) {
                        tv_status.setText("线程结束");
                    }
                    break;
            }
        }
    }

    @OnClick({R.id.btn_start})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                Intent intent = new Intent(IntentServiceActivity.this,
                        MyIntentService.class);
                startService(intent);
                break;
        }
    }
}
